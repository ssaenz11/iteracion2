package dao;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;

	



/**
	 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
	 * @author grupo 2_20
	 */
	

public class DAOTablaCliente {

	
	


		/**
		 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
		 */
		private ArrayList<Object> recursos;

		/**
		 * Atributo que genera la conexión a la base de datos
		 */
		private Connection conn;

		/**
		 * Metodo constructor que crea DAOVideo
		 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
		 */
		public DAOTablaCliente() {
			recursos = new ArrayList<Object>();
		}

		/**
		 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
		 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
		 */
		public void cerrarRecursos() {
			for(Object ob : recursos){
				if(ob instanceof PreparedStatement)
					try {
						((PreparedStatement) ob).close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		}

		/**
		 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
		 * @param con  - connection a la base de datos
		 */
		public void setConn(Connection con){
			this.conn = con;
		}


		/**
		 * Metodo que, usando la conexión a la base de datos, saca todos los videos de la base de datos
		 * <b>SQL Statement:</b> SELECT * FROM VIDEOS;
		 * @return Arraylist con los videos de la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public ArrayList<Cliente> darClientes() throws SQLException, Exception {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			String sql = "SELECT * FROM CLIENTE_TABLA1";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				
				String name = rs.getString("NOMBRE");
				int cedula = rs.getInt("CEDULA");
				String correo = rs.getString("CORREO");
				clientes.add(new Cliente(name, cedula, correo));
			}
			return clientes;
		}


		/**
		 * Metodo que busca el/los videos con el nombre que entra como parametro.
		 * @param name - Nombre de el/los videos a buscar
		 * @return ArrayList con los videos encontrados
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public ArrayList<Cliente> buscarClientePorName(String name) throws SQLException, Exception {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			String sql = "SELECT * FROM CLIENTE_TABLA1 WHERE NAME ='" + name + "'";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				String name2 = rs.getString("Nombre");
				
				int cedula = rs.getInt("CEDULA");
				String correo = rs.getString("CORREO");
				clientes.add(new Cliente( name2, cedula, correo));
			}

			return clientes;
		}
		
		/**
		 * Metodo que busca el video con el id que entra como parametro.
		 * @param name - Id de el video a buscar
		 * @return Video encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public Cliente buscarClientePorId(Long id) throws SQLException, Exception 
		{
			Cliente cliente = null;

			String sql = "SELECT * FROM CLIENTE_TABLA1 WHERE CEDULA =" + id;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				String name2 = rs.getString("Nombre");
				
				int cedula = rs.getInt("CEDULA");
				String correo = rs.getString("CORREO");
				cliente =(new Cliente( name2, cedula, correo));
			}

			return cliente;
		}

		/**
		 * Metodo que agrega el Cliente que entra como parametro a la base de datos.
		 * @param Cliente - el Cliente a agregar. Cliente !=  null
		 * <b> post: </b> se ha agregado el Cliente a la base de datos en la transaction actual. pendiente que el Cliente master
		 * haga commit para que el Cliente baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Cliente a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void addCliente(Cliente Cliente) throws SQLException, Exception {

			String sql = "INSERT INTO CLIENTE_TABLA1 VALUES (";
		    sql += Cliente.getCedula() + ",'";
			sql += Cliente.getNombre() + "','";
			sql += Cliente.getCorreo() + "')";
			

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		/**
		 * Metodo que actualiza el Cliente que entra como parametro en la base de datos.
		 * @param Cliente - el Cliente a actualizar. Cliente !=  null
		 * <b> post: </b> se ha actualizado el Cliente en la base de datos en la transaction actual. pendiente que el Cliente master
		 * haga commit para que los cambios bajen a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Cliente.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void updateCliente(Cliente Cliente) throws SQLException, Exception {

			String sql = "UPDATE CLIENTE_TABLA1 SET ";
			sql += "NOMBRE ='" + Cliente.getNombre() + "',";
			sql += "CORREO ='" + Cliente.getCorreo()+ "'";
			
			sql += " WHERE CEDULA = " + Cliente.getCedula();


			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}

		/**
		 * Metodo que elimina el Cliente que entra como parametro en la base de datos.
		 * @param Cliente - el Cliente a borrar. Cliente !=  null
		 * <b> post: </b> se ha borrado el Cliente en la base de datos en la transaction actual. pendiente que el Cliente master
		 * haga commit para que los cambios bajen a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Cliente.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void deleteCliente(Cliente Cliente) throws SQLException, Exception {

			String sql = "DELETE FROM CLIENTE_TABLA1";
			sql += " WHERE CEDULA = " + Cliente.getCedula();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
}
