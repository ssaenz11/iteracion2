package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ServicioProducto;

public class DAOTablaServicioProducto 
{

	
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
	public DAOTablaServicioProducto() {
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
	public ArrayList<ServicioProducto> darServicioProductos() throws SQLException, Exception {
		ArrayList<ServicioProducto> ServicioProductos = new ArrayList<ServicioProducto>();

		String sql = "SELECT * FROM SERVCIO_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int idC = rs.getInt("ID_CLIENTE");
			Long idP = rs.getLong("ID_PEDIDO");
			
			ServicioProductos.add(new ServicioProducto(idC, idP));
		}
		return ServicioProductos;
	}


	
	
	

	/**
	 * Metodo que agrega el ServicioProducto que entra como parametro a la base de datos.
	 * @param ServicioProducto - el ServicioProducto a agregar. ServicioProducto !=  null
	 * <b> post: </b> se ha agregado el ServicioProducto a la base de datos en la transaction actual. pendiente que el ServicioProducto master
	 * haga commit para que el ServicioProducto baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el ServicioProducto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addServicioProducto(ServicioProducto ServicioProducto) throws SQLException, Exception {

		String sql = "INSERT INTO SERVCIO_TABLA VALUES (";
	    sql += ServicioProducto.getIdCliente() + ",";
		sql += ServicioProducto.getIdPedido() + ")";
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	/**
	 * Metodo que elimina el ServicioProducto que entra como parametro en la base de datos.
	 * @param ServicioProducto - el ServicioProducto a borrar. ServicioProducto !=  null
	 * <b> post: </b> se ha borrado el ServicioProducto en la base de datos en la transaction actual. pendiente que el ServicioProducto master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el ServicioProducto.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteServicioProducto(ServicioProducto ServicioProducto) throws SQLException, Exception {

		String sql = "DELETE FROM SERVCIO_TABLA";
		sql += " WHERE ID_CLIENTE = " + ServicioProducto.getIdCliente() +" AND ID_PEDIDO ="+ ServicioProducto.getIdPedido();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
