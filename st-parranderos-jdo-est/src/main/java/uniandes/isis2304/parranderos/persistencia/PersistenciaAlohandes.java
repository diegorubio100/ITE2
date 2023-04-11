package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;
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
import uniandes.isis2304.parranderos.negocio.ResidenciaUniversitaria;
import uniandes.isis2304.parranderos.negocio.Disponibilidad;
import uniandes.isis2304.parranderos.negocio.Hotel;
import uniandes.isis2304.parranderos.negocio.Hostal;
import uniandes.isis2304.parranderos.negocio.Fenicia;
import uniandes.isis2304.parranderos.negocio.AlquilaDia;
import uniandes.isis2304.parranderos.negocio.AlquilaMes;

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

	/**
	 * Atributo para el acceso a la tabla DISPONIBILIDAD de la base de datos
	 */
	private SQLDisponibilidad sqlDisponibilidad;

	/**
	 * Atributo para el acceso a la tabla HOTEL de la base de datos
	 */
	private SQLHotel sqlHotel;

	/**
	 * Atributo para el acceso a la tabla HOSTAL de la base de datos
	 */
	private SQLHostal sqlHostal;

	/**
	 * Atributo para el acceso a la tabla FENICIA de la base de datos
	 */
	private SQLFenicia sqlFenicia;

	/**
	 * Atributo para el acceso a la tabla ALQUILAMES de la base de datos
	 */
	private SQLAlquilaMes sqlAlquilaMes;

	/**
	 * Atributo para el acceso a la tabla ALQUILADIA de la base de datos
	 */
	private SQLAlquilaDia sqlAlquilaDia;

	/**
	 * Atributo para el acceso a la tabla RESIDENCIAUNIVERSITARIA de la base de
	 * datos
	 */
	private SQLResidenciaUniversitaria sqlResidenciaUniversitaria;

	/**
	 * Atributo para el acceso a la tabla HABITACIONHOTEL de la base de datos
	 */
	private SQLHabitacionHotel sqlHabitacionHotel;

	/**
	 * Atributo para el acceso a la tabla HABITACIONHOSTAL de la base de datos
	 */
	private SQLHabitacionHostal sqlHabitacionHostal;

	/**
	 * Atributo para el acceso a la tabla HABITACIONFENICIA de la base de datos
	 */
	private SQLHabitacionFenicia sqlHabitacionFenicia;

	/**
	 * Atributo para el acceso a la tabla APARTAMENTOCOMUNIDAD de la base de datos
	 */
	private SQLApartamentoComunidad sqlApartamentoComunidad;

	/**
	 * Atributo para el acceso a la tabla VIVIENDA de la base de datos
	 */
	private SQLVivienda sqlVivienda;

	/**
	 * Atributo para el acceso a la tabla HABITACIONUNIVERSITARIA de la base de
	 * datos
	 */
	private SQLHabitacionUniversitaria sqlHabitacionUniversitaria;



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
		tablas.add("DISPONIBILIDAD");
		tablas.add("HOTEL");
		tablas.add("HOSTAL");
		tablas.add("FENICIA");
		tablas.add("ALQUILAMES");
		tablas.add("ALQUILADIA");
		tablas.add("RESIDENCIAUNIVERSITARIA");
		tablas.add("HABITACIONHOTEL");
		tablas.add("HABITACIONHOSTAL");
		tablas.add("HABITACIONFENICIA");
		tablas.add("APARTAMENTOCOMUNIDAD");
		tablas.add("VIVIENDA");
		tablas.add("HABITACIONUNIVERSITARIA");
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
		sqlDisponibilidad = new SQLDisponibilidad(this);
		sqlHotel = new SQLHotel(this);
		sqlHostal = new SQLHostal(this);
		sqlFenicia = new SQLFenicia(this);
		sqlAlquilaMes = new SQLAlquilaMes(this);
		sqlAlquilaDia = new SQLAlquilaDia(this);
		sqlResidenciaUniversitaria = new SQLResidenciaUniversitaria(this);
		sqlHabitacionHotel = new SQLHabitacionHotel(this);
		sqlHabitacionHostal = new SQLHabitacionHostal(this);
		sqlHabitacionFenicia = new SQLHabitacionFenicia(this);
		sqlApartamentoComunidad = new SQLApartamentoComunidad(this);
		sqlVivienda = new SQLVivienda(this);
		sqlHabitacionUniversitaria = new SQLHabitacionUniversitaria(this);	
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
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         Disponibilidad de alohandes
	 */
	public String darTablaDisponibilidad() {
		return tablas.get(4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         Hotel de alohandes
	 */
	public String darTablaHotel() {
		return tablas.get(5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         Hostal de alohandes
	 */
	public String darTablaHostal() {
		return tablas.get(6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         Fenicia de alohandes
	 */
	public String darTablaFenicia() {
		return tablas.get(7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         AlquilaMes de alohandes
	 */
	public String darTablaAlquilaMes() {
		return tablas.get(8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         AlquilaDia de alohandes
	 */
	public String darTablaAlquilaDia() {
		return tablas.get(9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         ResidenciaUniversitaria de alohandes
	 */
	public String darTablaResidenciaUniversitaria() {
		return tablas.get(10);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         HabitacionHotel de alohandes
	 */
	public String darTablaHabitacionHotel() {
		return tablas.get(11);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         HabitacionHostal de alohandes
	 */
	public String darTablaHabitacionHostal() {
		return tablas.get(12);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         HabitacionFenicia de alohandes
	 */
	public String darTablaHabitacionFenicia() {
		return tablas.get(13);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         ApartamentoComunidad de alohandes
	 */
	public String darTablaApartamentoComunidad() {
		return tablas.get(14);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         Vivienda de alohandes
	 */
	public String darTablaVivienda() {
		return tablas.get(15);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         HabitacionUniversitaria de alohandes
	 */
	public String darTablaHabitacionUniversitaria() {
		return tablas.get(16);
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
	 * @return El objeto Reserva, construido con base en las tuplas de la tabla RESERVA con el identificador dado
	 */
	public Reserva darReservaPorId (long id)
	{
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(), id);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Reserva con un identificador de habitacion dado
	 * @param idHabitacion - El identificador de la habitacion
	 * @return El objeto Reserva, construido con base en las tuplas de la tabla RESERVA con el identificador dado
	 */
	public List<Reserva> darReservasPorIdHabitacion (long idHabitacion)
	{
		return sqlReserva.darReservasPorIdHabitacion(pmf.getPersistenceManager(), idHabitacion);
	}




	/*
	 * ****************************************************************
	 * Métodos para manejar DISPONIBILIDAD
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla
	 * Disponibilidad
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param fecha        - El nombre de vinculado
	 * @param idHabitacion - El nombre de vinculado
	 * @param idReserva    - El nombre de vinculado
	 * @param disponible   - El tipo del vinculado ('Estudiante', 'Egresado',
	 *                     'Empleado',
	 *                     'Acudiente', 'Invitado')
	 * @return El objeto Disponibilidad adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public Disponibilidad adicionarDisponibilidad(Timestamp fecha, long idHabitacion, long idReserva,
			String disponible) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			// long idVinculado = nextval();
			long tuplasInsertadas = sqlDisponibilidad.adicionarDisponibilidad(pm, fecha, idHabitacion, idReserva,
					disponible);
			tx.commit();

			log.trace("Inserción de Disponibilidad: " + fecha + "-" + idHabitacion + ": " + tuplasInsertadas
					+ " tuplas insertadas");

			return new Disponibilidad(fecha, idHabitacion, idReserva, disponible);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * DISPONIBILIDAD, dado la fecha y idhabitacion
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param fecha        - El nombre del vinculado
	 * @param idHabitacion - El nombre del vinculado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarDisponibilidad(Timestamp fecha, long idHabitacion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlDisponibilidad.eliminarDisponibilidad(pm, fecha, idHabitacion);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que cambia, de manera transaccional, una tupla en la tabla
	 * DISPONIBILIDAD, dado idhabitacion
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idHabitacion - El nombre del vinculado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarDisponiblePorIdHabitacion(String disponible, long idHabitacion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlDisponibilidad.cambiarDisponiblePorIdHabitacion(pm, disponible, idHabitacion);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que cambia, de manera transaccional, una tupla en la tabla
	 * DISPONIBILIDAD, dado idReserva
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idReserva - El nombre del vinculado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarDisponiblePorIdReserva(String disponible, long idReserva) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlDisponibilidad.cambiarDisponiblePorIdReserva(pm, disponible, idReserva);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla DISPONIBILIDAD
	 * 
	 * @return La lista de objetos Disponibilidad, construidos con base en las
	 *         tuplas de la tabla DISPONIBILIDAD
	 */
	public List<Disponibilidad> darDisponibilidades() {
		return sqlDisponibilidad.darDisponibilidades(pmf.getPersistenceManager());
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar HOTEL
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla
	 * Hotel
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre      - El nombre de vinculado
	 * @param restaurante - ('Y', 'N')
	 * @param piscina     - ('Y', 'N')
	 * @param parqueadero - ('Y', 'N')
	 * @param wifi        - ('Y', 'N')
	 * @param tv          - ('Y', 'N')
	 * @param recepcion   - ('Y', 'N')
	 * 
	 * @return El objeto Hotel adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public Hotel adicionarHotel(String nombre, String restaurante, String piscina, String parqueadero, String internet,
			String tv, String recepcion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idHotel = nextval();
			long tuplasInsertadas = sqlHotel.adicionarHotel(pm, idHotel, nombre, restaurante, piscina, parqueadero,
					internet, tv, recepcion);
			tx.commit();

			log.trace("Inserción deL vinculado: " + idHotel + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Hotel(idHotel, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * HOTEL, dado el nombre del hotel
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del hotel
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHotelPorNombre(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlHotel.eliminarHotelPorNombre(pm, nombre);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * HOTEL, dado el id del hotel
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idHotel - El id del hotel
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHotelPorId(long idHotel) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlHotel.eliminarHotelPorId(pm, idHotel);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Hotel con un
	 * identificador dado
	 * 
	 * @param idHotel - El identificador del vinculado
	 * @return El objeto Hotel, construido con base en las tuplas de la
	 *         tabla HOTEL con el identificador dado
	 */
	public Hotel darHotelPorId(long idHotel) {
		return sqlHotel.darHotelPorId(pmf.getPersistenceManager(), idHotel);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Hotel con un
	 * nombre dado
	 * 
	 * @param nombre - El nombre del vinculado
	 * @return El objeto Hotel, construido con base en las tuplas de la
	 *         tabla HOTEL con el nombre dado
	 */
	public List<Hotel> darHotelesPorNombre(String nombre) {
		return sqlHotel.darHotelesPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla HOTEL
	 * 
	 * @return La lista de objetos Hotel, construidos con base en las
	 *         tuplas de la tabla HOTEL
	 */
	public List<Hotel> darHoteles() {
		return sqlHotel.darHoteles(pmf.getPersistenceManager());
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar HOSTAL
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla
	 * Hostal
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre       - El nombre de vinculado
	 * @param horaApertura
	 * @param horaCierre
	 * 
	 * @return El objeto Hostal adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public Hostal adicionarHostal(String nombre, String restaurante, LocalTime horaApertura, LocalTime horaCierre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idHostal = nextval();
			long tuplasInsertadas = sqlHostal.adicionarHostal(pm, idHostal, nombre, horaApertura, horaCierre);
			tx.commit();

			log.trace("Inserción deL vinculado: " + idHostal + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Hostal(idHostal, nombre, horaApertura, horaCierre);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * Hostal, dado el nombre del Hostal
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del Hostal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHostalPorNombre(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlHostal.eliminarHostalPorNombre(pm, nombre);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * Hostal, dado el nombre del Hostal
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idHostal - El nombre del Hostal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHostalPorId(long idHostal) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlHostal.eliminarHostalPorId(pm, idHostal);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Hostal con un
	 * identificador dado
	 * 
	 * @param idHostal - El identificador del vinculado
	 * @return El objeto Hostal, construido con base en las tuplas de la
	 *         tabla Hostal con el identificador dado
	 */
	public Hostal darHostalPorId(long idHostal) {
		return sqlHostal.darHostalPorId(pmf.getPersistenceManager(), idHostal);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Hostal con un
	 * nombre dado
	 * 
	 * @param nombre - El nombre del vinculado
	 * @return El objeto Hostal, construido con base en las tuplas de la
	 *         tabla Hostal con el nombre dado
	 */
	public List<Hostal> darHostalesPorNombre(String nombre) {
		return sqlHostal.darHostalesPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Hostal
	 * 
	 * @return La lista de objetos Hostal, construidos con base en las
	 *         tuplas de la tabla Hostal
	 */
	public List<Hostal> darHostales() {
		return sqlHostal.darHostales(pmf.getPersistenceManager());
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar Fenicia
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla
	 * Fenicia
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre de vinculado
	 * 
	 * @return El objeto Fenicia adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public Fenicia adicionarFenicia(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idFenicia = nextval();
			long tuplasInsertadas = sqlFenicia.adicionarFenicia(pm, idFenicia, nombre);
			tx.commit();

			log.trace("Inserción deL vinculado: " + idFenicia + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Fenicia(idFenicia, nombre);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * Fenicia, dado el nombre del Fenicia
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del Fenicia
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarFeniciaPorNombre(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlFenicia.eliminarFeniciaPorNombre(pm, nombre);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * Fenicia, dado el nombre del Fenicia
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idFenicia - El nombre del Fenicia
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarFeniciaPorId(long idFenicia) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlFenicia.eliminarFeniciaPorId(pm, idFenicia);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Fenicia con un
	 * identificador dado
	 * 
	 * @param idFenicia - El identificador del vinculado
	 * @return El objeto Fenicia, construido con base en las tuplas de la
	 *         tabla Fenicia con el identificador dado
	 */
	public Fenicia darFeniciaPorId(long idFenicia) {
		return sqlFenicia.darFeniciaPorId(pmf.getPersistenceManager(), idFenicia);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Fenicia con un
	 * nombre dado
	 * 
	 * @param nombre - El nombre del vinculado
	 * @return El objeto Fenicia, construido con base en las tuplas de la
	 *         tabla Fenicia con el nombre dado
	 */
	public List<Fenicia> darFeniciaesPorNombre(String nombre) {
		return sqlFenicia.darFeniciasPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Fenicia
	 * 
	 * @return La lista de objetos Fenicia, construidos con base en las
	 *         tuplas de la tabla Fenicia
	 */
	public List<Fenicia> darFeniciaes() {
		return sqlFenicia.darFenicias(pmf.getPersistenceManager());
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar AlquilaMes
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla
	 * AlquilaMes
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre    - El nombre de vinculado
	 * @param idMiembro
	 * 
	 * @return El objeto AlquilaMes adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public AlquilaMes adicionarAlquilaMes(String nombre, long idMiembro) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idAlquilaMes = nextval();
			long tuplasInsertadas = sqlAlquilaMes.adicionarAlquilaMes(pm, idAlquilaMes, nombre, idMiembro);
			tx.commit();

			log.trace("Inserción deL vinculado: " + idAlquilaMes + ": " + tuplasInsertadas + " tuplas insertadas");

			return new AlquilaMes(idAlquilaMes, nombre, idMiembro);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * AlquilaMes, dado el nombre del AlquilaMes
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del AlquilaMes
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlquilaMesPorNombre(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlAlquilaMes.eliminarAlquilaMesPorNombre(pm, nombre);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * AlquilaMes, dado el nombre del AlquilaMes
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlquilaMes - El nombre del AlquilaMes
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlquilaMesPorId(long idAlquilaMes) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlAlquilaMes.eliminarAlquilaMesPorId(pm, idAlquilaMes);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaMes con un
	 * identificador dado
	 * 
	 * @param idAlquilaMes - El identificador del vinculado
	 * @return El objeto AlquilaMes, construido con base en las tuplas de la
	 *         tabla AlquilaMes con el identificador dado
	 */
	public AlquilaMes darAlquilaMesPorId(long idAlquilaMes) {
		return sqlAlquilaMes.darAlquilaMesPorId(pmf.getPersistenceManager(), idAlquilaMes);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaMes con un
	 * nombre dado
	 * 
	 * @param nombre - El nombre del vinculado
	 * @return El objeto AlquilaMes, construido con base en las tuplas de la
	 *         tabla AlquilaMes con el nombre dado
	 */
	public List<AlquilaMes> darAlquilaMesesPorNombre(String nombre) {
		return sqlAlquilaMes.darAlquilaMesesPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaMes con un
	 * identificador dado
	 * 
	 * @param idMiembro - El identificador del vinculado
	 * @return El objeto AlquilaMes, construido con base en las tuplas de la
	 *         tabla AlquilaMes con el identificador dado
	 */
	public AlquilaMes darAlquilaMesPorIdMiembro(long idMiembro) {
		return sqlAlquilaMes.darAlquilaMesPorIdMiembro(pmf.getPersistenceManager(), idMiembro);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaMes
	 * 
	 * @return La lista de objetos AlquilaMes, construidos con base en las
	 *         tuplas de la tabla AlquilaMes
	 */
	public List<AlquilaMes> darAlquilaMeses() {
		return sqlAlquilaMes.darAlquilaMeses(pmf.getPersistenceManager());
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar AlquilaDia
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla
	 * AlquilaDia
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre    - El nombre de vinculado
	 * @param idMiembro
	 * 
	 * @return El objeto AlquilaDia adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public AlquilaDia adicionarAlquilaDia(String nombre, long idMiembro) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idAlquilaDia = nextval();
			long tuplasInsertadas = sqlAlquilaDia.adicionarAlquilaDia(pm, idAlquilaDia, nombre, idMiembro);
			tx.commit();

			log.trace("Inserción deL vinculado: " + idAlquilaDia + ": " + tuplasInsertadas + " tuplas insertadas");

			return new AlquilaDia(idAlquilaDia, nombre, idMiembro);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * AlquilaDia, dado el nombre del AlquilaDia
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del AlquilaDia
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlquilaDiaPorNombre(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlAlquilaDia.eliminarAlquilaDiaPorNombre(pm, nombre);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * AlquilaDia, dado el nombre del AlquilaDia
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlquilaDia - El nombre del AlquilaDia
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlquilaDiaPorId(long idAlquilaDia) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlAlquilaDia.eliminarAlquilaDiaPorId(pm, idAlquilaDia);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaDia con un
	 * identificador dado
	 * 
	 * @param idAlquilaDia - El identificador del vinculado
	 * @return El objeto AlquilaDia, construido con base en las tuplas de la
	 *         tabla AlquilaDia con el identificador dado
	 */
	public AlquilaDia darAlquilaDiaPorId(long idAlquilaDia) {
		return sqlAlquilaDia.darAlquilaDiaPorId(pmf.getPersistenceManager(), idAlquilaDia);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaDia con un
	 * nombre dado
	 * 
	 * @param nombre - El nombre del vinculado
	 * @return El objeto AlquilaDia, construido con base en las tuplas de la
	 *         tabla AlquilaDia con el nombre dado
	 */
	public List<AlquilaDia> darAlquilaDiasPorNombre(String nombre) {
		return sqlAlquilaDia.darAlquilaDiasPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaDia con un
	 * identificador dado
	 * 
	 * @param idMiembro - El identificador del vinculado
	 * @return El objeto AlquilaDia, construido con base en las tuplas de la
	 *         tabla AlquilaDia con el identificador dado
	 */
	public AlquilaDia darAlquilaDiaPorIdMiembro(long idMiembro) {
		return sqlAlquilaDia.darAlquilaDiaPorIdMiembro(pmf.getPersistenceManager(), idMiembro);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla AlquilaDia
	 * 
	 * @return La lista de objetos AlquilaDia, construidos con base en las
	 *         tuplas de la tabla AlquilaDia
	 */
	public List<AlquilaDia> darAlquilaDias() {
		return sqlAlquilaDia.darAlquilaDias(pmf.getPersistenceManager());
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar RESIDENCIAUNIVERSITARIA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla
	 * ResidenciaUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre            - El nombre de vinculado
	 * @param restaurante       - ('Y', 'N')
	 * @param salaEstudio       - ('Y', 'N')
	 * @param salaEsparcimiento - ('Y', 'N')
	 * @param gimnasio          - ('Y', 'N')
	 * 
	 * @return El objeto ResidenciaUniversitaria adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public ResidenciaUniversitaria adicionarResidenciaUniversitaria(String nombre, String restaurante,
			String salaEstudio, String salaEsparcimiento, String gimnasio) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idResidenciaUniversitaria = nextval();
			long tuplasInsertadas = sqlResidenciaUniversitaria.adicionarResidenciaUniversitaria(pm,
					idResidenciaUniversitaria, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio);
			tx.commit();

			log.trace("Inserción deL vinculado: " + idResidenciaUniversitaria + ": " + tuplasInsertadas
					+ " tuplas insertadas");

			return new ResidenciaUniversitaria(idResidenciaUniversitaria, nombre, restaurante, salaEstudio,
					salaEsparcimiento, gimnasio);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * ResidenciaUniversitaria, dado el nombre del ResidenciaUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del ResidenciaUniversitaria
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarResidenciaUniversitariaPorNombre(String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlResidenciaUniversitaria.eliminarResidenciaUniversitariaPorNombre(pm, nombre);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla
	 * ResidenciaUniversitaria, dado el id del ResidenciaUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idResidenciaUniversitaria - El id del ResidenciaUniversitaria
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarResidenciaUniversitariaPorId(long idResidenciaUniversitaria) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = sqlResidenciaUniversitaria.eliminarResidenciaUniversitariaPorId(pm, idResidenciaUniversitaria);
			tx.commit();
			return resp;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ResidenciaUniversitaria con
	 * un
	 * identificador dado
	 * 
	 * @param idResidenciaUniversitaria - El identificador del vinculado
	 * @return El objeto ResidenciaUniversitaria, construido con base en las tuplas
	 *         de la
	 *         tabla ResidenciaUniversitaria con el identificador dado
	 */
	public ResidenciaUniversitaria darResidenciaUniversitariaPorId(long idResidenciaUniversitaria) {
		return sqlResidenciaUniversitaria.darResidenciaUniversitariaPorId(pmf.getPersistenceManager(),
				idResidenciaUniversitaria);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ResidenciaUniversitaria con
	 * un
	 * nombre dado
	 * 
	 * @param nombre - El nombre del vinculado
	 * @return El objeto ResidenciaUniversitaria, construido con base en las tuplas
	 *         de la
	 *         tabla ResidenciaUniversitaria con el nombre dado
	 */
	public List<ResidenciaUniversitaria> darResidenciaUniversitariasPorNombre(String nombre) {
		return sqlResidenciaUniversitaria.darResidenciaUniversitariasPorNombre(pmf.getPersistenceManager(), nombre);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ResidenciaUniversitaria
	 * 
	 * @return La lista de objetos ResidenciaUniversitaria, construidos con base en
	 *         las
	 *         tuplas de la tabla ResidenciaUniversitaria
	 */
	public List<ResidenciaUniversitaria> darResidenciaUniversitarias() {
		return sqlResidenciaUniversitaria.darResidenciaUniversitarias(pmf.getPersistenceManager());
	}

	/*
	 * *******************************************
	 * Limpiar Alohandes
	 ********************************************/
	


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
