package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Acompa馻miento;

public class DAOTablaAcompa馻miento 
{

	/**
	 * Arraylits de recursos que se usan para la ejecuci贸n de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexi贸n a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaAcompa馻miento() {
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexi贸n que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	/**
	 * Metodo que, usando la conexi贸n a la base de datos, saca todos los videos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM VIDEOS;
	 * @return Arraylist con los videos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Acompa馻miento> darAcompa馻mientos() throws SQLException, Exception {
		ArrayList<Acompa馻miento> Acompa馻mientos = new ArrayList<Acompa馻miento>();

		String sql = "SELECT * FROM Acompa馻miento_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			
			Acompa馻mientos.add(new Acompa馻miento(cantidad, idRestaurante, descripcion, nombre, precio));
		}
		return Acompa馻mientos;
	}


	

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Acompa馻miento buscarAcompa馻mientoPorName(String name) throws SQLException, Exception {
		Acompa馻miento Acompa馻mientos = null;

		String sql = "SELECT * FROM Acompa馻miento_TABLA WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			Acompa馻mientos =(new Acompa馻miento(cantidad, idRestaurante, descripcion, nombre, precio));
		}

		return Acompa馻mientos;
	}

	/**
	 * Metodo que agrega el Acompa馻miento que entra como parametro a la base de datos.
	 * @param Acompa馻miento - el Acompa馻miento a agregar. Acompa馻miento !=  null
	 * <b> post: </b> se ha agregado el Acompa馻miento a la base de datos en la transaction actual. pendiente que el Acompa馻miento master
	 * haga commit para que el Acompa馻miento baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Acompa馻miento a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAcompa馻miento(Acompa馻miento Acompa馻miento) throws SQLException, Exception {

		String sql = "INSERT INTO Acompa馻miento_TABLA VALUES (";
	    sql += Acompa馻miento.getCantidad()+ ",'";
		sql += Acompa馻miento.getNombre() + "','";
		sql += Acompa馻miento.getDescripcion() + "',";
		sql += Acompa馻miento.getPrecio() + ",";
		sql += Acompa馻miento.getId_restaurante()+ ")";
		
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Acompa馻miento que entra como parametro en la base de datos.
	 * @param Acompa馻miento - el Acompa馻miento a actualizar. Acompa馻miento !=  null
	 * <b> post: </b> se ha actualizado el Acompa馻miento en la base de datos en la transaction actual. pendiente que el Acompa馻miento master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Acompa馻miento.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAcompa馻miento(Acompa馻miento Acompa馻miento) throws SQLException, Exception {

		String sql = "UPDATE Acompa馻miento_TABLA SET ";
		sql += "CANTIDAD =" + Acompa馻miento.getCantidad() + ",";
		sql += "NOMBRE ='" + Acompa馻miento.getNombre() + "',";
		sql += "DESCRIPCION ='" + Acompa馻miento.getDescripcion() + "',";
		sql += "PRECIO =" + Acompa馻miento.getPrecio() + ",";
		sql += "ID_RESTAURANTE =" + Acompa馻miento.getId_restaurante()+ " ";
		
		sql += " WHERE NOMBRE = '" + Acompa馻miento.getNombre()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Acompa馻miento que entra como parametro en la base de datos.
	 * @param Acompa馻miento - el Acompa馻miento a borrar. Acompa馻miento !=  null
	 * <b> post: </b> se ha borrado el Acompa馻miento en la base de datos en la transaction actual. pendiente que el Acompa馻miento master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Acompa馻miento.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAcompa馻miento(Acompa馻miento Acompa馻miento) throws SQLException, Exception {

		String sql = "DELETE FROM Acompa馻miento_TABLA";
		sql += " WHERE NOMBRE = '" + Acompa馻miento.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
}
