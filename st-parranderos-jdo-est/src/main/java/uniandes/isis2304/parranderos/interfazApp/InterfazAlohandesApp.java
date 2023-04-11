/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import java.time.format.DateTimeFormatter;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.parranderos.negocio.Alohandes;
import uniandes.isis2304.parranderos.negocio.VOHabitacion;
import uniandes.isis2304.parranderos.negocio.VOReserva;
import uniandes.isis2304.parranderos.negocio.VOVinculadoUniandes;


@SuppressWarnings("serial")

public class InterfazAlohandesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazAlohandesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private Alohandes alohandes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazAlohandesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        alohandes = new Alohandes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Alohandes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Alohandes APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	/* ****************************************************************
	 * 			CRUD de VinculadoUniandes
	 *****************************************************************/
    /**
     * Adiciona un vinculadouniandes con la información dada por el usuario
     * Se crea una nueva tupla de vinculadoUniandes en la base de datos, si un vinculado con ese nombre no existía
     */
    public void adicionarVinculadoUniandes( )
    {
    	try 
    	{
    		String nombreVinculado = JOptionPane.showInputDialog (this, "Nombre del vinculado?", "Adicionar vinculadoUniandes", JOptionPane.QUESTION_MESSAGE);
    		if (nombreVinculado != null)
    		{
				String tipoVinculado = JOptionPane.showInputDialog (this, "Tipo del vinculado (Estudiante, Egresado, Empleado, Acudiente, Invitado)", "Adicionar vinculadoUniandes", JOptionPane.QUESTION_MESSAGE);
        		VOVinculadoUniandes tb = alohandes.adicionarVinculadoUniandes(nombreVinculado, tipoVinculado);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un vinculado con nombre: " + nombreVinculado);
        		}
        		String resultado = "En adicionarVinculadoUniandes\n\n";
        		resultado += "VinculadoUniandes adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    /**
     * Consulta en la base de datos los vinculados existentes y los muestra en el panel de datos de la aplicación
     */
    public void listarVinculadoUniandes( )
    {
    	try 
    	{
			List <VOVinculadoUniandes> lista = alohandes.darVOVinculadosUniandes();

			String resultado = "En listarVinculadosUniandes";
			resultado +=  "\n" + listarVinculadosUniandes(lista); 
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    /**
     * Borra de la base de datos el vinculado con el identificador dado po el usuario
     * Cuando dicho vinculado no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarVinculadoUniandesPorId( )
    {
    	try 
    	{
    		String idVinculadoStr = JOptionPane.showInputDialog (this, "Id del vinculado?", "Borrar vinculado por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idVinculadoStr != null)
    		{
    			long idVinculado = Long.valueOf (idVinculadoStr);
    			long tbEliminados = alohandes.eliminarVinculadoPorId (idVinculado);

    			String resultado = "En eliminar VinculadoUniandes\n\n";
    			resultado += tbEliminados + " Vinculado Uniandes eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    /**
     * Busca el tipo de bebida con el nombre indicado por el usuario y lo muestra en el panel de datos
     */
    public void buscarVinculadoPorNombre( )
    {
    	try 
    	{
    		String nombreVu = JOptionPane.showInputDialog (this, "Nombre del vinculado?", "Buscar vinculado por nombre", JOptionPane.QUESTION_MESSAGE);
    		if (nombreVu != null)
    		{
    			VOVinculadoUniandes vinculadoUniandes = alohandes.darVinculadosPorNombre(nombreVu);
    			String resultado = "En buscar VinculadoUniandes por nombre\n\n";
    			if (vinculadoUniandes != null)
    			{
        			resultado += "El vinculado es: " + vinculadoUniandes;
    			}
    			else
    			{
        			resultado += "Un vinculado con nombre: " + nombreVu + " NO EXISTE\n";    				
    			}
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }






	/* ****************************************************************
	 * 			CRUD de Habitacion
	 *****************************************************************/
    /**
     * Adiciona un vinculadouniandes con la información dada por el usuario
     * Se crea una nueva tupla de vinculadoUniandes en la base de datos, si un vinculado con ese nombre no existía
     */
    public void adicionarHabitacion( )
    {
    	try 
    	{
    		String tipoHabitacion = JOptionPane.showInputDialog (this, "Tipo de habitación ('Hotel', 'Hostal', 'Fenicia', 'AlquilaMes', 'AlquilaDia', 'ResidenciaUniversitaria')?", "Adicionar Habitacion", JOptionPane.QUESTION_MESSAGE);
    		if (tipoHabitacion != null)
    		{
        		VOHabitacion tb = alohandes.adicionarHabitacion(tipoHabitacion);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear una habitacion con tipo: " + tipoHabitacion);
        		}
        		String resultado = "En adicionarHabitacion\n\n";
        		resultado += "Habitacion adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    /**
     * Consulta en la base de datos las habitaciones existentes y los muestra en el panel de datos de la aplicación
     */
    public void listarHabitacion( )
    {
    	try 
    	{
			List <VOHabitacion> lista = alohandes.darVOHabitaciones();

			String resultado = "En listarHabitaciones";
			resultado +=  "\n" + listarHabitaciones(lista); 
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    /**
     * Borra de la base de datos la habitacion con el identificador dado po el usuario
     * Cuando dicho vinculado no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarHabitacionPorId( )
    {
    	try 
    	{
    		String idHabitacionStr = JOptionPane.showInputDialog (this, "Id de la habitacion?", "Borrar habitacion por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idHabitacionStr != null)
    		{
    			long idHabitacion = Long.valueOf (idHabitacionStr);
    			long tbEliminados = alohandes.eliminarHabitacionPorId(idHabitacion);

    			String resultado = "En eliminar Habitacion\n\n";
    			resultado += tbEliminados + " Hbaitacion eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	

	/* ****************************************************************
	 * 			CRUD de RESERVA
	 *****************************************************************/
    /**
     * Adiciona una reserva on la información dada por el usuario
     * Se crea una nueva tupla de vinculadoUniandes en la base de datos, si un vinculado con ese nombre no existía
     */
    public void adicionarReserva( )
    {
    	try 
    	{
    		String idClienteStr = JOptionPane.showInputDialog(this, "ID del cliente?", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
			String idHabitacionStr = JOptionPane.showInputDialog(this, "ID de la habitación?", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
			if (idClienteStr != null || idHabitacionStr !=  null) 
			{
				String fechaReservaStr = JOptionPane.showInputDialog(this, "Fecha de reserva?(FORMATO YYYY-MM-DD)", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
				String fechaCancelacionOportunaStr = JOptionPane.showInputDialog(this, "Fecha de cancelación oportuna?(FORMATO YYYY-MM-DD)", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
				String fechaCancelacionStr = JOptionPane.showInputDialog(this, "Fecha de cancelación?(SI NO CANCELA DEJE VACIO)", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
				long idCliente = Long.valueOf(idClienteStr);
				long idHabitacion = Long.valueOf(idHabitacionStr);
			
				java.sql.Date fechaReservaSql = java.sql.Date.valueOf(fechaReservaStr);
				Timestamp fechaReservaTimeStamp = new Timestamp(fechaReservaSql.getTime());

				java.sql.Date fechaCancelacionOportunaSql = java.sql.Date.valueOf(fechaCancelacionOportunaStr);
				Timestamp fechaCancelacionOportunaTimeStamp = new Timestamp(fechaCancelacionOportunaSql.getTime());
				
				java.sql.Date fechaCancelacionSql = null;
				Timestamp fechaCancelacionTimeStamp = null;

				if (!fechaCancelacionStr.isEmpty()) {
					fechaCancelacionSql = java.sql.Date.valueOf(fechaCancelacionStr);
					fechaCancelacionTimeStamp = new Timestamp(fechaCancelacionSql.getTime());
				}
				
        		VOReserva tb = alohandes.adicionarReserva(idCliente,idHabitacion,fechaReservaTimeStamp,fechaCancelacionOportunaTimeStamp,fechaCancelacionTimeStamp);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear una reserva con idCliente, idHabitacion: " + idCliente + " " + idHabitacion);
        		}
        		String resultado = "En adicionarReserva\n\n";
        		resultado += "Reserva adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    /**
     * Consulta en la base de datos las reservas existentes y los muestra en el panel de datos de la aplicación
     */
    public void listarReserva( )
    {
    	try 
    	{
			List <VOReserva> lista = alohandes.darVOReservas();

			String resultado = "En listarReservas";
			resultado +=  "\n" + listarReservas(lista); 
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    /**
     * Borra de la base de datos la habitacion con el identificador dado po el usuario
     * Cuando dicho vinculado no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarReservaPorId( )
    {
    	try 
    	{
    		String idReservaStr = JOptionPane.showInputDialog (this, "Id de la reserva?", "Borrar reserva por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idReservaStr != null)
    		{
    			long idReserva = Long.valueOf (idReservaStr);
    			long tbEliminados = alohandes.eliminarReservaPorId(idReserva);

    			String resultado = "En eliminar Reserva\n\n";
    			resultado += tbEliminados + " Reserva eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

     /**
     * Busca el tipo de bebida con el nombre indicado por el usuario y lo muestra en el panel de datos
     */
    public void buscarReservasPorIdHabitacion( )
    {
    	try 
    	{
    		String idHabStr = JOptionPane.showInputDialog (this, "Id de la habitación?", "Buscar reserva por habitacion", JOptionPane.QUESTION_MESSAGE);
    		if (idHabStr != null)
    		{
				long idHab= Long.valueOf(idHabStr);
    			List<VOReserva> reservas = alohandes.darVOReservasPorIdHabitacion(idHab);
    			String resultado = "En listarReservas";
				System.out.println(reservas);
    			if (reservas.size()!=0)
    			{
        			resultado +=  "\n" + listarReservas(reservas);
					panelDatos.actualizarInterfaz(resultado);
					resultado += "\n Operación terminada"; 
    			}
    			else
    			{
        			resultado += " \n NO hay reservas con ese id habitacion, puede eliminar en la OPCION HABITACION CON EL MISMO ID";		
    			}
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
		
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	

	/* ****************************************************************
	 * 			Métodos para REQUERIMIENTOS DE CONSULTA
	 *****************************************************************/
	public void RequerimientoConsulta1( )
    {
    	try 
    	{	
			 // Obtener la fecha actual
			 LocalDate fechaActual = LocalDate.now();

			 // Crear un formateador de fecha con el patrón "yyyy-MM-dd"
			 DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 
			 // Formatear la fecha actual como una cadena en el formato deseado
			 String fechaFormateada = fechaActual.format(formateador);
			 System.out.println(fechaFormateada);

			 List<Object []> resp = alohandes.darDineroProveedorActualCorrido(fechaFormateada);
			 System.out.println(resp);
			 String mensaje = "Estos son";
			 for (Object [] tupla: resp){
				mensaje += tupla;
			 }
    		panelDatos.actualizarInterfaz(mensaje);
    		
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	public void RequerimientoConsulta2( )
    {
    	try 
    	{	
			 List<Object > resp = alohandes.darOfertasMasPopulares();
			 System.out.println(resp);
			 String mensaje = "Estos son";
			 for (Object  tupla: resp){
				mensaje += tupla;
			 }
    		panelDatos.actualizarInterfaz(mensaje);
    		
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	
	public void RequerimientoConsulta3( )
    {
    	try 
    	{	
			 List<Object []> resp = alohandes.darIndiceOcupacionOfertas();
			 System.out.println(resp);
			 String mensaje = "Estos son";
			 for (Object [] tupla: resp){
				mensaje += tupla;
			 }
    		panelDatos.actualizarInterfaz(mensaje);
    		
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }




























	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = alohandes.limpiarAlohandes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Vinculados eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
    }
    

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
    /**
     * Genera una cadena de caracteres con la lista de los tipos de vinculados
     * @param lista - La lista con los tipos de vinculados
     * @return La cadena con una líea para cada vinculado recibido
     */
    private String listarVinculadosUniandes(List<VOVinculadoUniandes> lista) 
    {
    	String resp = "Los vinculados son:\n";
    	int i = 1;
        for (VOVinculadoUniandes tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}

	 /**
     * Genera una cadena de caracteres con la lista de las habitaciones
     * @param lista - La lista con las habitaciones
     * @return La cadena con una línea para cada habitacion recibido
     */
    private String listarHabitaciones(List<VOHabitacion> lista) 
    {
    	String resp = "Las habitaciones son:\n";
    	int i = 1;
        for (VOHabitacion tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}


	 /**
     * Genera una cadena de caracteres con la lista de reservas
     * @param lista - La lista con las reservas
     * @return La cadena con una línea para cada reserva recibida
     */
    private String listarReservas(List<VOReserva> lista) 
    {
    	String resp = "Las reservas son:\n";
    	int i = 1;
        for (VOReserva tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}



    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazAlohandesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazAlohandesApp interfaz = new InterfazAlohandesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
