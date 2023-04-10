package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Habitacion;
import uniandes.isis2304.parranderos.negocio.Reserva;
import uniandes.isis2304.parranderos.negocio.VinculadoUniandes;

public class PersistenciaAlohandes {
    /* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla VINCULADO de la base de datos
	 */
	private SQLVinculadoUniandes sqlVinculadoUniandes;


	/**
	 * Atributo para el acceso a la tabla HABITACION de la base de datos
	 */
	private SQLHabitacion sqlHabitacion;

	/**
	 * Atributo para el acceso a la tabla RESERVA de la base de datos
	 */
	private SQLReserva sqlReserva;




	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/
 
    private PersistenciaAlohandes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Parranderos_sequence");
		tablas.add ("VINCULADOUNIANDES");
		tablas.add ("HABITACION");
		tablas.add ("RESERVA");
    }


	

    /**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

    /**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes ();
		}
		return instance;
	}

    /**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
		}
		return instance;
	}

    /**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

    /**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	

    /**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlVinculadoUniandes = new SQLVinculadoUniandes(this);	
		sqlHabitacion = new SQLHabitacion(this);
		sqlReserva = new SQLReserva(this);		
		sqlUtil = new SQLUtil(this);
	}

    /**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqAlohandes ()
	{
		return tablas.get (0);
	}

    /**
	 * @return La cadena de caracteres con el nombre de la tabla de VinculadoUniandes de alohandes
	 */
	public String darTablaVinculadoUniandes ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Habitacion de alohandes
	 */
	public String darTablaHabitacion ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Habitacion de alohandes
	 */
	public String darTablaReserva ()
	{
		return tablas.get (3);
	}





    /**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }

    /**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}


    /* ****************************************************************
	 * 			Métodos para manejar los VINCULADOSUNINANDES
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla VinculadoUniandes
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de vinculado
     * @param tipo - El tipo del vinculado ('Estudiante', 'Egresado', 'Empleado', 'Acudiente', 'Invitado')
	 * @return El objeto VinculadoUniandes adicionado. null si ocurre alguna Excepción
	 */
	public VinculadoUniandes adicionarVinculadoUniandes(String nombre, String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idVinculado = nextval ();
            long tuplasInsertadas = sqlVinculadoUniandes.adicionarVinculadoUniandes(pm, idVinculado, nombre, tipo);
            tx.commit();
            
            log.trace ("Inserción deL vinculado: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new VinculadoUniandes(idVinculado,nombre,tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla VINCULADOUNIANDES, dado el nombre del vinculado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del vinculado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarVinculadoPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlVinculadoUniandes.eliminarVinculadosPorNombre(pm, nombre);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla VINCULADOUNIANDES, dado el identificador del vinculado
	 * Adiciona entradas al log de la aplicación
	 * @param idVinculado - El identificador del vinculado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarVinculadoPorId (long idVinculado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlVinculadoUniandes.eliminarVinculadoPorId(pm, idVinculado);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla VINCULADOUNIANDES
	 * @return La lista de objetos VinculadoUniandes, construidos con base en las tuplas de la tabla VINCULADOUNIANDES
	 */
	public List<VinculadoUniandes> darVinculados ()
	{
		return sqlVinculadoUniandes.darVinculados(pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla VinculadoUniandes que tienen el nombre dado
	 * @param nombre - El nombre del vinculado
	 * @return La lista de objetos VinculadoUniandes, construidos con base en las tuplas de la tabla VINCULADOUNIANDES
	 */
	public List<VinculadoUniandes> darVinculadosporNombre (String nombre)
	{
		return sqlVinculadoUniandes.darVinculadosPorNombre(pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla VinculadoUniandes con un identificador dado
	 * @param idVinculado - El identificador del vinculado
	 * @return El objeto VinculadoUniandes, construido con base en las tuplas de la tabla VINCULADOUNIANDES con el identificador dado
	 */
	public VinculadoUniandes darVinculadoPorId (long idVinculado)
	{
		return sqlVinculadoUniandes.darVinculadoPorId(pmf.getPersistenceManager(), idVinculado);
	}







    /* ****************************************************************
	 * 			Métodos para manejar las HABITACIONES
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Habitacion
	 * Adiciona entradas al log de la aplicación
     * @param tipo - El tipo de habitacion ('Hotel', 'Hostal', 'Fenicia', 'AlquilaMes', 'AlquilaDia', 'ResidenciaUniversitaria')
	 * @return El objeto Habitacion adicionado. null si ocurre alguna Excepción
	 */
	public Habitacion adicionarHabitacion(String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idHabitacion = nextval ();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, idHabitacion, tipo);
            tx.commit();
            
            log.trace ("Inserción de la habitacion: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Habitacion(idHabitacion,tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla HABITACION, dado el identificador de habitacion
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacion - El identificador de habitacion
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionPorId (long idHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.eliminarHabitacionPorId(pm, idHabitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla HABITACION
	 * @return La lista de objetos Habitacion, construidos con base en las tuplas de la tabla HABITACION
	 */
	public List<Habitacion> darhHabitaciones ()
	{
		return sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
	}
 
 
	/**
	 * Método que consulta todas las tuplas en la tabla Habitacion con un identificador dado
	 * @param idHabitacion - El identificador de la habitacion
	 * @return El objeto Habitacion, construido con base en las tuplas de la tabla HABITACION con el identificador dado
	 */
	public Habitacion darHabitacionPorId (long idHabitacion)
	{
		return sqlHabitacion.darHabitacionPorId(pmf.getPersistenceManager(), idHabitacion);
	}




/* ****************************************************************
	 * 			Métodos para manejar las RESERVAS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Reserva
	 * Adiciona entradas al log de la aplicación
	 * @param idCliente - El identificador del cliente. Debe existir un cliente con dicho identificador
	 * @param idHabitacion - El identificador de la habitacion. Debe exixtir una habitacion con dicho identificador
	 * @param fechaReserva - La fecha en la cual se realiza la reserva
     * @param fechaCancelacionOportuna - La fecha en la cual se puede cancelar la reserva
     * @param fechaCancelacion - La fecha en la cual se cancela la reserva
	 * @return El objeto VinculadoUniandes adicionado. null si ocurre alguna Excepción
	 */
	public Reserva adicionarReserva(long idCliente,long idHabitacion, Timestamp fechaReserva, 
    Timestamp fechaCancelacionOportuna, Timestamp fechaCancelacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, id, idCliente, idHabitacion, fechaReserva, 
			fechaCancelacionOportuna, fechaCancelacion);
            tx.commit();
            
            log.trace ("Inserción de reserva: " + idCliente + idHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Reserva(id, idCliente, idHabitacion, fechaReserva,fechaCancelacionOportuna, fechaCancelacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla VINCULADOUNIANDES, dado el identificador de la reserva
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador de la reserva
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReservaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaporId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla RESERVA
	 * @return La lista de objetos VinculadoUniandes, construidos con base en las tuplas de la tabla VINCULADOUNIANDES
	 */
	public List<Reserva> darReservas ()
	{
		return sqlReserva.darReservas(pmf.getPersistenceManager());
	}
 
 
	/**
	 * Método que consulta todas las tuplas en la tabla Reserva con un identificador dado
	 * @param id - El identificador de la reserva
	 * @return El objeto VinculadoUniandes, construido con base en las tuplas de la tabla RESERVA con el identificador dado
	 */
	public Reserva darReservaPorId (long id)
	{
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(), id);
	}




    /**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Alohandes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 1 números que indican el número de tuplas borradas en las tablas VINCULADO respectivamente
	 */
	public long [] limpiarAlohandes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarAlohandes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}

}
