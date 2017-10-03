package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Acompañamiento;

public class DAOTablaAcompañamiento 
{

	/**
	 * Arraylits de recursos que se usan para la ejecuciÃ³n de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexiÃ³n a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaAcompañamiento() {
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexiÃ³n que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	/**
	 * Metodo que, usando la conexiÃ³n a la base de datos, saca todos los videos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM VIDEOS;
	 * @return Arraylist con los videos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Acompañamiento> darAcompañamientos() throws SQLException, Exception {
		ArrayList<Acompañamiento> Acompañamientos = new ArrayList<Acompañamiento>();

		String sql = "SELECT * FROM Acompañamiento_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			
			Acompañamientos.add(new Acompañamiento(cantidad, idRestaurante, descripcion, nombre, precio));
		}
		return Acompañamientos;
	}


	

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Acompañamiento buscarAcompañamientoPorName(String name) throws SQLException, Exception {
		Acompañamiento Acompañamientos = null;

		String sql = "SELECT * FROM Acompañamiento_TABLA WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			Acompañamientos =(new Acompañamiento(cantidad, idRestaurante, descripcion, nombre, precio));
		}

		return Acompañamientos;
	}

	/**
	 * Metodo que agrega el Acompañamiento que entra como parametro a la base de datos.
	 * @param Acompañamiento - el Acompañamiento a agregar. Acompañamiento !=  null
	 * <b> post: </b> se ha agregado el Acompañamiento a la base de datos en la transaction actual. pendiente que el Acompañamiento master
	 * haga commit para que el Acompañamiento baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Acompañamiento a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAcompañamiento(Acompañamiento Acompañamiento) throws SQLException, Exception {

		String sql = "INSERT INTO Acompañamiento_TABLA VALUES (";
	    sql += Acompañamiento.getCantidad()+ ",'";
		sql += Acompañamiento.getNombre() + "','";
		sql += Acompañamiento.getDescripcion() + "',";
		sql += Acompañamiento.getPrecio() + ",";
		sql += Acompañamiento.getId_restaurante()+ ")";
		
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Acompañamiento que entra como parametro en la base de datos.
	 * @param Acompañamiento - el Acompañamiento a actualizar. Acompañamiento !=  null
	 * <b> post: </b> se ha actualizado el Acompañamiento en la base de datos en la transaction actual. pendiente que el Acompañamiento master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Acompañamiento.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAcompañamiento(Acompañamiento Acompañamiento) throws SQLException, Exception {

		String sql = "UPDATE Acompañamiento_TABLA SET ";
		sql += "CANTIDAD =" + Acompañamiento.getCantidad() + ",";
		sql += "NOMBRE ='" + Acompañamiento.getNombre() + "',";
		sql += "DESCRIPCION ='" + Acompañamiento.getDescripcion() + "',";
		sql += "PRECIO =" + Acompañamiento.getPrecio() + ",";
		sql += "ID_RESTAURANTE =" + Acompañamiento.getId_restaurante()+ " ";
		
		sql += " WHERE NOMBRE = '" + Acompañamiento.getNombre()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Acompañamiento que entra como parametro en la base de datos.
	 * @param Acompañamiento - el Acompañamiento a borrar. Acompañamiento !=  null
	 * <b> post: </b> se ha borrado el Acompañamiento en la base de datos en la transaction actual. pendiente que el Acompañamiento master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Acompañamiento.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAcompañamiento(Acompañamiento Acompañamiento) throws SQLException, Exception {

		String sql = "DELETE FROM Acompañamiento_TABLA";
		sql += " WHERE NOMBRE = '" + Acompañamiento.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
}
