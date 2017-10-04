package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaAcompañamiento;
import dao.DAOTablaAdministrador;
import dao.DAOTablaBebida;
import dao.DAOTablaCliente;
import dao.DAOTablaEntrada;
import dao.DAOTablaMenu;
import dao.DAOTablaPlatoFuerte;
import dao.DAOTablaPostre;
import dao.DAOTablaPreferenciaCliente;
import dao.DAOTablaServicioProducto;
import vos.Acompañamiento;
import vos.Administrador;
import vos.Bebida;
import vos.Cliente;
import vos.Entrada;
import vos.Menu;
import vos.PlatoFuerte;
import vos.Postre;
import vos.PreferenciaCliente;
import vos.ServicioProducto;



public class TM {

	
	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * conexion a la base de datos
	 */
	private Connection conn;


	/**
	 * Metodo constructor de la clase ClienteAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logica de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto ClienteAndesTM, se inicializa el path absoluto del archivo de conexion y se
	 * inicializa los atributos que se usan par la conexion a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public TM(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/**
	 * Metodo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexion a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que  retorna la conexion a la base de datos
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////


	/**
	 * Metodo que modela la transaccion que retorna todos los Clientes de la base de datos.
	 * @return ListaClientes - objeto que modela  un arreglo de Clientes. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Cliente> darClientes() throws Exception {
		List<Cliente> Clientes;
		DAOTablaCliente daoClientes = new DAOTablaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoClientes.setConn(conn);
			Clientes = daoClientes.darClientes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Clientes;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los Administradors de la base de datos.
	 * @return ListaAdministradors - objeto que modela  un arreglo de Administradors. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Administrador> darAdministradors() throws Exception {
		List<Administrador> Administradors;
		DAOTablaAdministrador daoAdministradors = new DAOTablaAdministrador();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAdministradors.setConn(conn);
			Administradors = daoAdministradors.darAdministradors();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradors.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Administradors;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los Menus de la base de datos.
	 * @return ListaMenus - objeto que modela  un arreglo de Menus. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Menu> darMenus() throws Exception {
		List<Menu> Menus;
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			Menus = daoMenus.darMenus();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Menus;
	}
	
	
	/**
	 * Metodo que modela la transaccion que retorna todos los Postres de la base de datos.
	 * @return ListaPostres - objeto que modela  un arreglo de Postres. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Postre> darPostres() throws Exception {
		List<Postre> Postres;
		DAOTablaPostre daoPostres = new DAOTablaPostre();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPostres.setConn(conn);
			Postres = daoPostres.darPostres();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPostres.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Postres;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los PlatoFuertes de la base de datos.
	 * @return ListaPlatoFuertes - objeto que modela  un arreglo de PlatoFuertes. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<PlatoFuerte> darPlatoFuertes() throws Exception {
		List<PlatoFuerte> PlatoFuertes;
		DAOTablaPlatoFuerte daoPlatoFuertes = new DAOTablaPlatoFuerte();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPlatoFuertes.setConn(conn);
			PlatoFuertes = daoPlatoFuertes.darPlatoFuertes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPlatoFuertes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return PlatoFuertes;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los Entradas de la base de datos.
	 * @return ListaEntradas - objeto que modela  un arreglo de Entradas. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Entrada> darEntradas() throws Exception {
		List<Entrada> Entradas;
		DAOTablaEntrada daoEntradas = new DAOTablaEntrada();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEntradas.setConn(conn);
			Entradas = daoEntradas.darEntradas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEntradas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Entradas;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los Bebidas de la base de datos.
	 * @return ListaBebidas - objeto que modela  un arreglo de Bebidas. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Bebida> darBebidas() throws Exception {
		List<Bebida> Bebidas;
		DAOTablaBebida daoBebidas = new DAOTablaBebida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoBebidas.setConn(conn);
			Bebidas = daoBebidas.darBebidas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoBebidas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Bebidas;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los Acompañamientos de la base de datos.
	 * @return ListaAcompañamientos - objeto que modela  un arreglo de Acompañamientos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Acompañamiento> darAcompañamientos() throws Exception {
		List<Acompañamiento> Acompañamientos;
		DAOTablaAcompañamiento daoAcompañamientos = new DAOTablaAcompañamiento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAcompañamientos.setConn(conn);
			Acompañamientos = daoAcompañamientos.darAcompañamientos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAcompañamientos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Acompañamientos;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los ServicioProductos de la base de datos.
	 * @return ListaServicioProductos - objeto que modela  un arreglo de ServicioProductos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<ServicioProducto> darServicioProductos() throws Exception {
		List<ServicioProducto> ServicioProductos;
		DAOTablaServicioProducto daoServicioProductos = new DAOTablaServicioProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoServicioProductos.setConn(conn);
			ServicioProductos = daoServicioProductos.darServicioProductos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoServicioProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ServicioProductos;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todos los PreferenciaClientes de la base de datos.
	 * @return ListaPreferenciaClientes - objeto que modela  un arreglo de PreferenciaClientes. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<PreferenciaCliente> darPreferenciaClientes() throws Exception {
		List<PreferenciaCliente> PreferenciaClientes;
		DAOTablaPreferenciaCliente daoPreferenciaClientes = new DAOTablaPreferenciaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferenciaClientes.setConn(conn);
			PreferenciaClientes = daoPreferenciaClientes.darPreferenciaClientes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferenciaClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return PreferenciaClientes;
	}
	
	
	

	/**
	 * Metodo que modela la transaccion que busca el/los Clientes en la base de datos con el nombre entra como parametro.
	 * @param name - Nombre del Cliente a buscar. name != null
	 * @return ListaClientes - objeto que modela  un arreglo de Clientes. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Cliente> buscarClientesPorName(String name) throws Exception {
		List<Cliente> Clientes;
		DAOTablaCliente daoClientes = new DAOTablaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoClientes.setConn(conn);
			Clientes = daoClientes.buscarClientePorName(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Clientes;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el/los Postre en la base de datos con el nombre entra como parametro.
	 * @param name - Nombre del Cliente a buscar. name != null
	 * @return ListaPostre - objeto que modela  un arreglo de Postre. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Postre buscarPostrePorName(String name) throws Exception {
		Postre Postre = null;;
		DAOTablaPostre daoPostre = new DAOTablaPostre();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPostre.setConn(conn);
			Postre = daoPostre.buscarPostrePorName(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPostre.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Postre;
	}
	
	
	/**
	 * Metodo que modela la transaccion que busca el/los PlatoFuerte en la base de datos con el nombre entra como parametro.
	 * @param name - Nombre del Cliente a buscar. name != null
	 * @return ListaPlatoFuerte - objeto que modela  un arreglo de PlatoFuerte. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public PlatoFuerte buscarPlatoFuertePorName(String name) throws Exception {
		PlatoFuerte PlatoFuerte = null;;
		DAOTablaPlatoFuerte daoPlatoFuerte = new DAOTablaPlatoFuerte();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPlatoFuerte.setConn(conn);
			PlatoFuerte = daoPlatoFuerte.buscarPlatoFuertePorName(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPlatoFuerte.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return PlatoFuerte;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el/los Entrada en la base de datos con el nombre entra como parametro.
	 * @param name - Nombre del Cliente a buscar. name != null
	 * @return ListaEntrada - objeto que modela  un arreglo de Entrada. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Entrada buscarEntradaPorName(String name) throws Exception {
		Entrada Entrada = null;;
		DAOTablaEntrada daoEntrada = new DAOTablaEntrada();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEntrada.setConn(conn);
			Entrada = daoEntrada.buscarEntradaPorName(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEntrada.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Entrada;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el/los Bebida en la base de datos con el nombre entra como parametro.
	 * @param name - Nombre del Cliente a buscar. name != null
	 * @return ListaBebida - objeto que modela  un arreglo de Bebida. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Bebida buscarBebidaPorName(String name) throws Exception {
		Bebida Bebida = null;;
		DAOTablaBebida daoBebida = new DAOTablaBebida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoBebida.setConn(conn);
			Bebida = daoBebida.buscarBebidaPorName(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoBebida.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Bebida;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el/los Acompañamiento en la base de datos con el nombre entra como parametro.
	 * @param name - Nombre del Cliente a buscar. name != null
	 * @return ListaAcompañamiento - objeto que modela  un arreglo de Acompañamiento. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Acompañamiento buscarAcompañamientoPorName(String name) throws Exception {
		Acompañamiento Acompañamiento = null;;
		DAOTablaAcompañamiento daoAcompañamiento = new DAOTablaAcompañamiento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAcompañamiento.setConn(conn);
			Acompañamiento = daoAcompañamiento.buscarAcompañamientoPorName(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAcompañamiento.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Acompañamiento;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el Cliente en la base de datos con el id que entra como parametro.
	 * @param name - Id del Cliente a buscar. name != null
	 * @return Cliente - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Cliente buscarClientePorId(Long id) throws Exception {
		Cliente Cliente;
		DAOTablaCliente daoClientes = new DAOTablaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoClientes.setConn(conn);
			Cliente = daoClientes.buscarClientePorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Cliente;
	}
	
	/**
	 * Metodo que modela la transaccion que busca el Administrador en la base de datos con el id que entra como parametro.
	 * @param name - Id del Administrador a buscar. name != null
	 * @return Administrador - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Administrador buscarAdministradorPorId(Long id) throws Exception {
		Administrador Administrador;
		DAOTablaAdministrador daoAdministradors = new DAOTablaAdministrador();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAdministradors.setConn(conn);
			Administrador = daoAdministradors.buscarAdministradorPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradors.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Administrador;
	}
	
	
	/**
	 * Metodo que modela la transaccion que busca el Cliente en la base de datos con el id que entra como parametro.
	 * @param name - Id del Cliente a buscar. name != null
	 * @return Cliente - Resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Menu buscarMenuPorId(Long id) throws Exception {
		Menu Menu;
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			Menu = daoMenus.buscarMenuPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Menu;
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega un solo Cliente a la base de datos.
	 * <b> post: </b> se ha agregado el Cliente que entra como parametro
	 * @param Cliente - el Cliente a agregar. Cliente != null
	 * @throws Exception - cualquier error que se genere agregando el Cliente
	 */
	public void addCliente(Cliente Cliente) throws Exception {
		DAOTablaCliente daoClientes = new DAOTablaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoClientes.setConn(conn);
			daoClientes.addCliente(Cliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	
	/**
	 * Metodo que modela la transaccion que agrega un solo Administrador a la base de datos.
	 * <b> post: </b> se ha agregado el Administrador que entra como parametro
	 * @param Administrador - el Administrador a agregar. Administrador != null
	 * @throws Exception - cualquier error que se genere agregando el Administrador
	 */
	public void addAdministrador(Administrador Administrador) throws Exception {
		DAOTablaAdministrador daoAdministradors = new DAOTablaAdministrador();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAdministradors.setConn(conn);
			daoAdministradors.addAdministrador(Administrador);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradors.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega un solo Postre a la base de datos.
	 * <b> post: </b> se ha agregado el Postre que entra como parametro
	 * @param Postre - el Postre a agregar. Postre != null
	 * @throws Exception - cualquier error que se genere agregando el Postre
	 */
	public void addPostre(Postre Postre) throws Exception {
		DAOTablaPostre daoPostres = new DAOTablaPostre();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPostres.setConn(conn);
			daoPostres.addPostre(Postre);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPostres.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo PlatoFuerte a la base de datos.
	 * <b> post: </b> se ha agregado el PlatoFuerte que entra como parametro
	 * @param PlatoFuerte - el PlatoFuerte a agregar. PlatoFuerte != null
	 * @throws Exception - cualquier error que se genere agregando el PlatoFuerte
	 */
	public void addPlatoFuerte(PlatoFuerte PlatoFuerte) throws Exception {
		DAOTablaPlatoFuerte daoPlatoFuertes = new DAOTablaPlatoFuerte();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPlatoFuertes.setConn(conn);
			daoPlatoFuertes.addPlatoFuerte(PlatoFuerte);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPlatoFuertes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo Entrada a la base de datos.
	 * <b> post: </b> se ha agregado el Entrada que entra como parametro
	 * @param Entrada - el Entrada a agregar. Entrada != null
	 * @throws Exception - cualquier error que se genere agregando el Entrada
	 */
	public void addEntrada(Entrada Entrada) throws Exception {
		DAOTablaEntrada daoEntradas = new DAOTablaEntrada();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEntradas.setConn(conn);
			daoEntradas.addEntrada(Entrada);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEntradas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo Bebida a la base de datos.
	 * <b> post: </b> se ha agregado el Bebida que entra como parametro
	 * @param Bebida - el Bebida a agregar. Bebida != null
	 * @throws Exception - cualquier error que se genere agregando el Bebida
	 */
	public void addBebida(Bebida Bebida) throws Exception {
		DAOTablaBebida daoBebidas = new DAOTablaBebida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoBebidas.setConn(conn);
			daoBebidas.addBebida(Bebida);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoBebidas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo Acompañamiento a la base de datos.
	 * <b> post: </b> se ha agregado el Acompañamiento que entra como parametro
	 * @param Acompañamiento - el Acompañamiento a agregar. Acompañamiento != null
	 * @throws Exception - cualquier error que se genere agregando el Acompañamiento
	 */
	public void addAcompañamiento(Acompañamiento Acompañamiento) throws Exception {
		DAOTablaAcompañamiento daoAcompañamientos = new DAOTablaAcompañamiento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAcompañamientos.setConn(conn);
			daoAcompañamientos.addAcompañamiento(Acompañamiento);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAcompañamientos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que agrega un solo Menu a la base de datos.
	 * <b> post: </b> se ha agregado el Menu que entra como parametro
	 * @param Menu - el Menu a agregar. Menu != null
	 * @throws Exception - cualquier error que se genere agregando el Menu
	 */
	public void addMenu(Menu Menu) throws Exception {
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.addMenu(Menu);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo ServicioProducto a la base de datos.
	 * <b> post: </b> se ha agregado el ServicioProducto que entra como parametro
	 * @param ServicioProducto - el ServicioProducto a agregar. ServicioProducto != null
	 * @throws Exception - cualquier error que se genere agregando el ServicioProducto
	 */
	public void addServicioProducto(ServicioProducto ServicioProducto) throws Exception {
		DAOTablaServicioProducto daoServicioProductos = new DAOTablaServicioProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoServicioProductos.setConn(conn);
			daoServicioProductos.addServicioProducto(ServicioProducto);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoServicioProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega un solo PreferenciaCliente a la base de datos.
	 * <b> post: </b> se ha agregado el PreferenciaCliente que entra como parametro
	 * @param PreferenciaCliente - el PreferenciaCliente a agregar. PreferenciaCliente != null
	 * @throws Exception - cualquier error que se genere agregando el PreferenciaCliente
	 */
	public void addPreferenciaCliente(PreferenciaCliente PreferenciaCliente) throws Exception {
		DAOTablaPreferenciaCliente daoPreferenciaClientes = new DAOTablaPreferenciaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferenciaClientes.setConn(conn);
			daoPreferenciaClientes.addPreferenciaCliente(PreferenciaCliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferenciaClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega los Clientes que entran como parametro a la base de datos.
	 * <b> post: </b> se han agregado los Clientes que entran como parametro
	 * @param Clientes - objeto que modela una lista de Clientes y se estos se pretenden agregar. Clientes != null
	 * @throws Exception - cualquier error que se genera agregando los Clientes
	 */
	public void addClientes(List<Cliente> Clientes) throws Exception {
		DAOTablaCliente daoClientes = new DAOTablaCliente();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoClientes.setConn(conn);
			Iterator<Cliente> it = Clientes.iterator();
			while(it.hasNext())
			{
				daoClientes.addCliente(it.next());
			}
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que agrega los Menus que entran como parametro a la base de datos.
	 * <b> post: </b> se han agregado los Menus que entran como parametro
	 * @param Menus - objeto que modela una lista de Menus y se estos se pretenden agregar. Menus != null
	 * @throws Exception - cualquier error que se genera agregando los Menus
	 */
	public void addMenus(List<Menu> Menus) throws Exception {
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoMenus.setConn(conn);
			Iterator<Menu> it = Menus.iterator();
			while(it.hasNext())
			{
				daoMenus.addMenu(it.next());
			}
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el Cliente que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el Cliente que entra como parametro
	 * @param Cliente - Cliente a actualizar. Cliente != null
	 * @throws Exception - cualquier error que se genera actualizando los Clientes
	 */
	public void updateCliente(Cliente Cliente) throws Exception {
		DAOTablaCliente daoClientes = new DAOTablaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoClientes.setConn(conn);
			daoClientes.updateCliente(Cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el Postre que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el Postre que entra como parametro
	 * @param Postre - Postre a actualizar. Postre != null
	 * @throws Exception - cualquier error que se genera actualizando los Postres
	 */
	public void updatePostre(Postre Postre) throws Exception {
		DAOTablaPostre daoPostres = new DAOTablaPostre();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPostres.setConn(conn);
			daoPostres.updatePostre(Postre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPostres.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que actualiza el PlatoFuerte que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el PlatoFuerte que entra como parametro
	 * @param PlatoFuerte - PlatoFuerte a actualizar. PlatoFuerte != null
	 * @throws Exception - cualquier error que se genera actualizando los PlatoFuertes
	 */
	public void updatePlatoFuerte(PlatoFuerte PlatoFuerte) throws Exception {
		DAOTablaPlatoFuerte daoPlatoFuertes = new DAOTablaPlatoFuerte();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPlatoFuertes.setConn(conn);
			daoPlatoFuertes.updatePlatoFuerte(PlatoFuerte);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPlatoFuertes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que actualiza el Entrada que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el Entrada que entra como parametro
	 * @param Entrada - Entrada a actualizar. Entrada != null
	 * @throws Exception - cualquier error que se genera actualizando los Entradas
	 */
	public void updateEntrada(Entrada Entrada) throws Exception {
		DAOTablaEntrada daoEntradas = new DAOTablaEntrada();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEntradas.setConn(conn);
			daoEntradas.updateEntrada(Entrada);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEntradas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que actualiza el Bebida que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el Bebida que entra como parametro
	 * @param Bebida - Bebida a actualizar. Bebida != null
	 * @throws Exception - cualquier error que se genera actualizando los Bebidas
	 */
	public void updateBebida(Bebida Bebida) throws Exception {
		DAOTablaBebida daoBebidas = new DAOTablaBebida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoBebidas.setConn(conn);
			daoBebidas.updateBebida(Bebida);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoBebidas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que actualiza el Acompañamiento que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el Acompañamiento que entra como parametro
	 * @param Acompañamiento - Acompañamiento a actualizar. Acompañamiento != null
	 * @throws Exception - cualquier error que se genera actualizando los Acompañamientos
	 */
	public void updateAcompañamiento(Acompañamiento Acompañamiento) throws Exception {
		DAOTablaAcompañamiento daoAcompañamientos = new DAOTablaAcompañamiento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAcompañamientos.setConn(conn);
			daoAcompañamientos.updateAcompañamiento(Acompañamiento);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAcompañamientos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	
	/**
	 * Metodo que modela la transaccion que actualiza el Menu que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el Menu que entra como parametro
	 * @param Menu - Menu a actualizar. Menu != null
	 * @throws Exception - cualquier error que se genera actualizando los Menus
	 */
	public void updateMenu(Menu Menu) throws Exception {
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.updateMenu(Menu);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que elimina el Cliente que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el Cliente que entra como parametro
	 * @param Cliente - Cliente a eliminar. Cliente != null
	 * @throws Exception - cualquier error que se genera actualizando los Clientes
	 */
	public void deleteCliente(Cliente Cliente) throws Exception {
		DAOTablaCliente daoClientes = new DAOTablaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoClientes.setConn(conn);
			daoClientes.deleteCliente(Cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el Administrador que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el Administrador que entra como parametro
	 * @param Administrador - Administrador a eliminar. Administrador != null
	 * @throws Exception - cualquier error que se genera actualizando los Administradors
	 */
	public void deleteAdministrador(Administrador Administrador) throws Exception {
		DAOTablaAdministrador daoAdministradors = new DAOTablaAdministrador();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAdministradors.setConn(conn);
			daoAdministradors.deleteAdministrador(Administrador);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradors.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el Menu que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el Menu que entra como parametro
	 * @param Menu - Menu a eliminar. Menu != null
	 * @throws Exception - cualquier error que se genera actualizando los Menus
	 */
	public void deleteMenu(Menu Menu) throws Exception {
		DAOTablaMenu daoMenus = new DAOTablaMenu();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoMenus.setConn(conn);
			daoMenus.deleteMenu(Menu);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoMenus.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el Postre que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el Postre que entra como parametro
	 * @param Postre - Postre a eliminar. Postre != null
	 * @throws Exception - cualquier error que se genera actualizando los Postres
	 */
	public void deletePostre(Postre Postre) throws Exception {
		DAOTablaPostre daoPostres = new DAOTablaPostre();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPostres.setConn(conn);
			daoPostres.deletePostre(Postre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPostres.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el PlatoFuerte que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el PlatoFuerte que entra como parametro
	 * @param PlatoFuerte - PlatoFuerte a eliminar. PlatoFuerte != null
	 * @throws Exception - cualquier error que se genera actualizando los PlatoFuertes
	 */
	public void deletePlatoFuerte(PlatoFuerte PlatoFuerte) throws Exception {
		DAOTablaPlatoFuerte daoPlatoFuertes = new DAOTablaPlatoFuerte();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPlatoFuertes.setConn(conn);
			daoPlatoFuertes.deletePlatoFuerte(PlatoFuerte);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPlatoFuertes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el Entrada que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el Entrada que entra como parametro
	 * @param Entrada - Entrada a eliminar. Entrada != null
	 * @throws Exception - cualquier error que se genera actualizando los Entradas
	 */
	public void deleteEntrada(Entrada Entrada) throws Exception {
		DAOTablaEntrada daoEntradas = new DAOTablaEntrada();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoEntradas.setConn(conn);
			daoEntradas.deleteEntrada(Entrada);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEntradas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el Bebida que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el Bebida que entra como parametro
	 * @param Bebida - Bebida a eliminar. Bebida != null
	 * @throws Exception - cualquier error que se genera actualizando los Bebidas
	 */
	public void deleteBebida(Bebida Bebida) throws Exception {
		DAOTablaBebida daoBebidas = new DAOTablaBebida();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoBebidas.setConn(conn);
			daoBebidas.deleteBebida(Bebida);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoBebidas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el Acompañamiento que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el Acompañamiento que entra como parametro
	 * @param Acompañamiento - Acompañamiento a eliminar. Acompañamiento != null
	 * @throws Exception - cualquier error que se genera actualizando los Acompañamientos
	 */
	public void deleteAcompañamiento(Acompañamiento Acompañamiento) throws Exception {
		DAOTablaAcompañamiento daoAcompañamientos = new DAOTablaAcompañamiento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoAcompañamientos.setConn(conn);
			daoAcompañamientos.deleteAcompañamiento(Acompañamiento);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAcompañamientos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el PreferenciaCliente que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el PreferenciaCliente que entra como parametro
	 * @param PreferenciaCliente - PreferenciaCliente a eliminar. PreferenciaCliente != null
	 * @throws Exception - cualquier error que se genera actualizando los PreferenciaClientes
	 */
	public void deletePreferenciaCliente(PreferenciaCliente PreferenciaCliente) throws Exception {
		DAOTablaPreferenciaCliente daoPreferenciaClientes = new DAOTablaPreferenciaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferenciaClientes.setConn(conn);
			daoPreferenciaClientes.deletePreferenciaCliente(PreferenciaCliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferenciaClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * Metodo que modela la transaccion que elimina el ServicioProducto que entra como parametro a la base de datos.
	 * <b> post: </b> se ha eliminado el ServicioProducto que entra como parametro
	 * @param ServicioProducto - ServicioProducto a eliminar. ServicioProducto != null
	 * @throws Exception - cualquier error que se genera actualizando los ServicioProductos
	 */
	public void deleteServicioProducto(ServicioProducto ServicioProducto) throws Exception {
		DAOTablaServicioProducto daoServicioProductos = new DAOTablaServicioProducto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoServicioProductos.setConn(conn);
			daoServicioProductos.deleteServicioProducto(ServicioProducto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoServicioProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
}
