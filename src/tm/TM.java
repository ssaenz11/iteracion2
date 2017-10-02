package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaCliente;
import vos.Cliente;



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
	
}
