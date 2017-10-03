package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vos.Postre;



public class DAOTablaPostre 
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
	public DAOTablaPostre() {
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
	public ArrayList<Postre> darPostres() throws SQLException, Exception {
		ArrayList<Postre> Postres = new ArrayList<Postre>();

		String sql = "SELECT * FROM Postre_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			
			Postres.add(new Postre(cantidad, idRestaurante, descripcion, nombre, precio));
		}
		return Postres;
	}


	

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Postre buscarPostrePorName(String name) throws SQLException, Exception {
		Postre Postres = null;

		String sql = "SELECT * FROM Postre_TABLA WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			Postres =(new Postre(cantidad, idRestaurante, descripcion, nombre, precio));
		}

		return Postres;
	}

	/**
	 * Metodo que agrega el Postre que entra como parametro a la base de datos.
	 * @param Postre - el Postre a agregar. Postre !=  null
	 * <b> post: </b> se ha agregado el Postre a la base de datos en la transaction actual. pendiente que el Postre master
	 * haga commit para que el Postre baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Postre a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPostre(Postre Postre) throws SQLException, Exception {

		String sql = "INSERT INTO Postre_TABLA VALUES (";
	    sql += Postre.getCantidad()+ ",'";
		sql += Postre.getNombre() + "','";
		sql += Postre.getDescripcion() + "',";
		sql += Postre.getPrecio() + ",";
		sql += Postre.getId_restaurante()+ ")";
		
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Postre que entra como parametro en la base de datos.
	 * @param Postre - el Postre a actualizar. Postre !=  null
	 * <b> post: </b> se ha actualizado el Postre en la base de datos en la transaction actual. pendiente que el Postre master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Postre.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updatePostre(Postre Postre) throws SQLException, Exception {

		String sql = "UPDATE Postre_TABLA SET ";
		sql += "CANTIDAD =" + Postre.getCantidad() + ",";
		sql += "NOMBRE ='" + Postre.getNombre() + "',";
		sql += "DESCRIPCION ='" + Postre.getDescripcion() + "',";
		sql += "PRECIO =" + Postre.getPrecio() + ",";
		sql += "ID_RESTAURANTE =" + Postre.getId_restaurante()+ " ";
		
		sql += " WHERE NOMBRE = '" + Postre.getNombre()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Postre que entra como parametro en la base de datos.
	 * @param Postre - el Postre a borrar. Postre !=  null
	 * <b> post: </b> se ha borrado el Postre en la base de datos en la transaction actual. pendiente que el Postre master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Postre.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deletePostre(Postre Postre) throws SQLException, Exception {

		String sql = "DELETE FROM Postre_TABLA";
		sql += " WHERE NOMBRE = '" + Postre.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	
}
