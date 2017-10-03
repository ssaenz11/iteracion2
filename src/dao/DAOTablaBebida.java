package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Bebida;

public class DAOTablaBebida {

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
	public DAOTablaBebida() {
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
	public ArrayList<Bebida> darBebidas() throws SQLException, Exception {
		ArrayList<Bebida> Bebidas = new ArrayList<Bebida>();

		String sql = "SELECT * FROM Bebida_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			
			Bebidas.add(new Bebida(cantidad, idRestaurante, descripcion, nombre, precio));
		}
		return Bebidas;
	}


	

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Bebida buscarBebidaPorName(String name) throws SQLException, Exception {
		Bebida Bebidas = null;

		String sql = "SELECT * FROM Bebida_TABLA WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			Bebidas =(new Bebida(cantidad, idRestaurante, descripcion, nombre, precio));
		}

		return Bebidas;
	}

	/**
	 * Metodo que agrega el Bebida que entra como parametro a la base de datos.
	 * @param Bebida - el Bebida a agregar. Bebida !=  null
	 * <b> post: </b> se ha agregado el Bebida a la base de datos en la transaction actual. pendiente que el Bebida master
	 * haga commit para que el Bebida baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Bebida a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addBebida(Bebida Bebida) throws SQLException, Exception {

		String sql = "INSERT INTO Bebida_TABLA VALUES (";
	    sql += Bebida.getCantidad()+ ",'";
		sql += Bebida.getNombre() + "','";
		sql += Bebida.getDescripcion() + "',";
		sql += Bebida.getPrecio() + ",";
		sql += Bebida.getId_restaurante()+ ")";
		
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Bebida que entra como parametro en la base de datos.
	 * @param Bebida - el Bebida a actualizar. Bebida !=  null
	 * <b> post: </b> se ha actualizado el Bebida en la base de datos en la transaction actual. pendiente que el Bebida master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Bebida.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateBebida(Bebida Bebida) throws SQLException, Exception {

		String sql = "UPDATE Bebida_TABLA SET ";
		sql += "CANTIDAD =" + Bebida.getCantidad() + ",";
		sql += "NOMBRE ='" + Bebida.getNombre() + "',";
		sql += "DESCRIPCION ='" + Bebida.getDescripcion() + "',";
		sql += "PRECIO =" + Bebida.getPrecio() + ",";
		sql += "ID_RESTAURANTE =" + Bebida.getId_restaurante()+ " ";
		
		sql += " WHERE NOMBRE = '" + Bebida.getNombre()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Bebida que entra como parametro en la base de datos.
	 * @param Bebida - el Bebida a borrar. Bebida !=  null
	 * <b> post: </b> se ha borrado el Bebida en la base de datos en la transaction actual. pendiente que el Bebida master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Bebida.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteBebida(Bebida Bebida) throws SQLException, Exception {

		String sql = "DELETE FROM Bebida_TABLA";
		sql += " WHERE NOMBRE = '" + Bebida.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
}
