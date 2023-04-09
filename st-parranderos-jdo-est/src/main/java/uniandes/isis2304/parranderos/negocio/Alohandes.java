package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohandes;





public class Alohandes
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(Alohandes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohandes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public Alohandes ()
	{
		pp = PersistenciaAlohandes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public Alohandes (JsonObject tableConfig)
	{
		pp = PersistenciaAlohandes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los VINCULADOS UNIANDES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un VINCULADO
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public VinculadoUniandes adicionarVinculadoUniandes (String nombre, String tipo)
	{
        log.info ("Adicionando Vinculado Uninades: " + nombre);
        VinculadoUniandes vinculadoUniandes = pp.adicionarVinculadoUniandes (nombre, tipo);		
        log.info ("Adicionando VinculadoUniandes: " + vinculadoUniandes);
        return vinculadoUniandes;
	}
	
	/**
	 * Elimina un vinculado por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del vinculado a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarVinculadoPorNombre (String nombre)
	{
		log.info ("Eliminando Vinculado por nombre: " + nombre);
        long resp = pp.eliminarVinculadoPorNombre(nombre);		
        log.info ("Eliminando Vinculado por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina un vinculado por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idVinculado - El id del vinculado a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarVinculadoPorId (long idVinculado)
	{
		log.info ("Eliminando Vinculado por id: " + idVinculado);
        long resp = pp.eliminarVinculadoPorId(idVinculado);		
        log.info ("Eliminando Vinculado por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los vinculados en Alohandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Vinculado con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<VinculadoUniandes> darVinculados ()
	{
		log.info ("Consultando Vinculados");
        List<VinculadoUniandes> vinculadosUniandes = pp.darVinculados();	
        log.info ("Consultando Vinculados: " + vinculadosUniandes.size() + " existentes");
        return vinculadosUniandes;
	}

	/**
	 * Encuentra todos los vinculados en Alohandes y los devuelve como una lista de VOVinculadoUniandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOVinculadoUniandes con todos los vinculados que conoce la aplicación, llenos con su información básica
	 */
	public List<VOVinculadoUniandes> darVOVinculadosUniandes ()
	{
		log.info ("Generando los VO de VinculadoUniandes");        
        List<VOVinculadoUniandes> voVinculados = new LinkedList<VOVinculadoUniandes> ();
        for (VinculadoUniandes tb : pp.darVinculados())
        {
        	voVinculados.add (tb);
        }
        log.info ("Generando los VO de Vinculados: " + voVinculados.size() + " existentes");
        return voVinculados;
	}

	/**
	 * Encuentra el vinculado en Alohandes con el nombre solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del vinculado
	 * @return Un objeto VinculadoUniandes con el vinculado de ese nombre que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public VinculadoUniandes darVinculadosPorNombre (String nombre)
	{
		log.info ("Buscando Vinculado por nombre: " + nombre);
		List<VinculadoUniandes> tb = pp.darVinculadosporNombre(nombre);
		return !tb.isEmpty () ? tb.get (0) : null;
	}


	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Alohandes
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas VINCULADOUNIANDES, respectivamente
	 */
	public long [] limpiarAlohandes ()
	{
        log.info ("Limpiando la BD de Alohandes");
        long [] borrrados = pp.limpiarAlohandes();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}
}

