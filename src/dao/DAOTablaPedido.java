package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vos.Cliente;
import vos.Pedido;

public class DAOTablaPedido {


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
	public DAOTablaPedido() {
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
	public ArrayList<Pedido> darPedidos() throws SQLException, Exception {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		String sql = "SELECT * FROM PEDIDO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			Long id = rs.getLong("ID");
			String fecha = rs.getString("FECHA");
			
			Long id_cliente = rs.getLong("fk_cliente");
			String nombre_Rest = rs.getString("fk_nomRest");
			Long id_prod = rs.getLong("fk_producto");
			pedidos.add(new Pedido(id, fecha,  id_cliente, nombre_Rest, id_prod));
		}
		return pedidos;
	}


	
	/**
	 * Metodo que busca el video con el id que entra como parametro.
	 * @param name - Id de el video a buscar
	 * @return Video encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Pedido buscarPedidoPorId(Long id) throws SQLException, Exception 
	{
		Pedido pedido = null;

		String sql = "SELECT * FROM PEDIDO WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {

			String fecha = rs.getString("FECHA");
			
			Long id_cliente = rs.getLong("fk_cliente");
			String nombre_Rest = rs.getString("fk_nomRest");
			Long id_prod = rs.getLong("fk_producto");
			pedido =new Pedido(id, fecha,  id_cliente, nombre_Rest, id_prod);
		}

		return pedido;
	}

	/**
	 * Metodo que agrega el Cliente que entra como parametro a la base de datos.
	 * @param Cliente - el Cliente a agregar. Cliente !=  null
	 * <b> post: </b> se ha agregado el Cliente a la base de datos en la transaction actual. pendiente que el Cliente master
	 * haga commit para que el Cliente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Cliente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPedido(Pedido pedido) throws SQLException, Exception {

		String sql = "INSERT INTO PEDIDO VALUES (";
	    sql += pedido.getId() + ", '";
//	    String modi = new SimpleDateFormat("yyyy-MM-dd").format(pedido.getFecha());
		sql += pedido.getFecha() + "',";
		
		
		sql += pedido.getId_prod() + ",";
		sql += pedido.getId_cliente() + ",'";
		sql += pedido.getNombre_Rest() + "')";
		
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
	public void updatePedido(Pedido pedido) throws SQLException, Exception {

		String sql = "UPDATE PEDIDO SET ";

		sql += "FECHA ='" + pedido.getFecha() + "',";
		
		sql += "fk_cliente =" + pedido.getId_cliente() + ",";
		sql += "fk_nomRest ='" + pedido.getNombre_Rest()+ "',";
		sql += "fk_producto ='" + pedido.getId_prod()+ "',";
	
		sql += " WHERE ID = " + pedido.getId();


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
	public void deletePedido(Pedido pedido) throws SQLException, Exception {

		String sql = "DELETE FROM PEDIDO";
		sql += " WHERE ID = " + pedido.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
