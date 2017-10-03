package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.PreferenciaCliente;

public class DAOTablaPreferenciaCliente 
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
	public DAOTablaPreferenciaCliente() {
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
	public ArrayList<PreferenciaCliente> darPreferenciaClientes() throws SQLException, Exception {
		ArrayList<PreferenciaCliente> PreferenciaClientes = new ArrayList<PreferenciaCliente>();

		String sql = "SELECT * FROM PREFERENCIA_DE_CLIENTE";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			int idC = rs.getInt("ID_CLIENTE");
			Long idP = rs.getLong("ID_PEDIDO");
			int nOrdenes = rs.getInt("NUMERO_ORDENES");
			PreferenciaClientes.add(new PreferenciaCliente(idC, idP, nOrdenes));
		}
		return PreferenciaClientes;
	}


	
	
	

	/**
	 * Metodo que agrega el PreferenciaCliente que entra como parametro a la base de datos.
	 * @param PreferenciaCliente - el PreferenciaCliente a agregar. PreferenciaCliente !=  null
	 * <b> post: </b> se ha agregado el PreferenciaCliente a la base de datos en la transaction actual. pendiente que el PreferenciaCliente master
	 * haga commit para que el PreferenciaCliente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el PreferenciaCliente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPreferenciaCliente(PreferenciaCliente PreferenciaCliente) throws SQLException, Exception {

		String sql = "INSERT INTO PREFERENCIA_DE_CLIENTE VALUES (";
	    sql += PreferenciaCliente.getIdCliente() + ",";
		sql += PreferenciaCliente.getIdPedido() + ",";
		sql += PreferenciaCliente.getCantidad() + ")";
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	/**
	 * Metodo que elimina el PreferenciaCliente que entra como parametro en la base de datos.
	 * @param PreferenciaCliente - el PreferenciaCliente a borrar. PreferenciaCliente !=  null
	 * <b> post: </b> se ha borrado el PreferenciaCliente en la base de datos en la transaction actual. pendiente que el PreferenciaCliente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el PreferenciaCliente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deletePreferenciaCliente(PreferenciaCliente PreferenciaCliente) throws SQLException, Exception {

		String sql = "DELETE FROM PREFERENCIA_DE_CLIENTE";
		sql += " WHERE ID_CLIENTE = " + PreferenciaCliente.getIdCliente() +" AND ID_PEDIDO ="+ PreferenciaCliente.getIdPedido();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
