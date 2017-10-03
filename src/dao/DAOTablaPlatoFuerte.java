package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.PlatoFuerte;

public class DAOTablaPlatoFuerte {

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
	public DAOTablaPlatoFuerte() {
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
	public ArrayList<PlatoFuerte> darPlatoFuertes() throws SQLException, Exception {
		ArrayList<PlatoFuerte> PlatoFuertes = new ArrayList<PlatoFuerte>();

		String sql = "SELECT * FROM PlatoFuerte_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			
			PlatoFuertes.add(new PlatoFuerte(cantidad, idRestaurante, descripcion, nombre, precio));
		}
		return PlatoFuertes;
	}


	

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public PlatoFuerte buscarPlatoFuertePorName(String name) throws SQLException, Exception {
		PlatoFuerte PlatoFuertes = null;

		String sql = "SELECT * FROM PlatoFuerte_TABLA WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			PlatoFuertes =(new PlatoFuerte(cantidad, idRestaurante, descripcion, nombre, precio));
		}

		return PlatoFuertes;
	}

	/**
	 * Metodo que agrega el PlatoFuerte que entra como parametro a la base de datos.
	 * @param PlatoFuerte - el PlatoFuerte a agregar. PlatoFuerte !=  null
	 * <b> post: </b> se ha agregado el PlatoFuerte a la base de datos en la transaction actual. pendiente que el PlatoFuerte master
	 * haga commit para que el PlatoFuerte baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el PlatoFuerte a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPlatoFuerte(PlatoFuerte PlatoFuerte) throws SQLException, Exception {

		String sql = "INSERT INTO PlatoFuerte_TABLA VALUES (";
	    sql += PlatoFuerte.getCantidad()+ ",'";
		sql += PlatoFuerte.getNombre() + "','";
		sql += PlatoFuerte.getDescripcion() + "',";
		sql += PlatoFuerte.getPrecio() + ",";
		sql += PlatoFuerte.getId_restaurante()+ ")";
		
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el PlatoFuerte que entra como parametro en la base de datos.
	 * @param PlatoFuerte - el PlatoFuerte a actualizar. PlatoFuerte !=  null
	 * <b> post: </b> se ha actualizado el PlatoFuerte en la base de datos en la transaction actual. pendiente que el PlatoFuerte master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el PlatoFuerte.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updatePlatoFuerte(PlatoFuerte PlatoFuerte) throws SQLException, Exception {

		String sql = "UPDATE PlatoFuerte_TABLA SET ";
		sql += "CANTIDAD =" + PlatoFuerte.getCantidad() + ",";
		sql += "NOMBRE ='" + PlatoFuerte.getNombre() + "',";
		sql += "DESCRIPCION ='" + PlatoFuerte.getDescripcion() + "',";
		sql += "PRECIO =" + PlatoFuerte.getPrecio() + ",";
		sql += "ID_RESTAURANTE =" + PlatoFuerte.getId_restaurante()+ " ";
		
		sql += " WHERE NOMBRE = '" + PlatoFuerte.getNombre()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el PlatoFuerte que entra como parametro en la base de datos.
	 * @param PlatoFuerte - el PlatoFuerte a borrar. PlatoFuerte !=  null
	 * <b> post: </b> se ha borrado el PlatoFuerte en la base de datos en la transaction actual. pendiente que el PlatoFuerte master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el PlatoFuerte.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deletePlatoFuerte(PlatoFuerte PlatoFuerte) throws SQLException, Exception {

		String sql = "DELETE FROM PlatoFuerte_TABLA";
		sql += " WHERE NOMBRE = '" + PlatoFuerte.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
}
