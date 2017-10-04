package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.AdministradorRestaurante;

public class DAOTablaAdministradorRestaurante
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
	public DAOTablaAdministradorRestaurante () {
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
	public ArrayList<AdministradorRestaurante> darAdministradorRestaurantes() throws SQLException, Exception {
		ArrayList<AdministradorRestaurante> AdministradorRestaurantes = new ArrayList<AdministradorRestaurante>();

		String sql = "SELECT * FROM AdministradorRestaurante_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			String name = rs.getString("NOMBRE");
			int cedula = rs.getInt("CEDULA");
			String correo = rs.getString("CORREO");
			int restauranteID = rs.getInt("RESTAURANTE_ID");
			AdministradorRestaurantes.add(new AdministradorRestaurante(name, cedula, restauranteID, correo));
		}
		return AdministradorRestaurantes;
	}


	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<AdministradorRestaurante> buscarAdministradorRestaurantePorName(String name) throws SQLException, Exception {
		ArrayList<AdministradorRestaurante> AdministradorRestaurantes = new ArrayList<AdministradorRestaurante>();

		String sql = "SELECT * FROM AdministradorRestaurante_TABLA WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name2 = rs.getString("Nombre");
			
			int cedula = rs.getInt("CEDULA");
			String correo = rs.getString("CORREO");
			int restauranteID = rs.getInt("RESTAURANTE_ID");
			AdministradorRestaurantes.add(new AdministradorRestaurante(name2, cedula, restauranteID, correo));
		}

		return AdministradorRestaurantes;
	}
	
	/**
	 * Metodo que busca el video con el id que entra como parametro.
	 * @param name - Id de el video a buscar
	 * @return Video encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public AdministradorRestaurante buscarAdministradorRestaurantePorId(Long id) throws SQLException, Exception 
	{
		AdministradorRestaurante AdministradorRestaurante = null;

		String sql = "SELECT * FROM AdministradorRestaurante_TABLA WHERE CEDULA =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name2 = rs.getString("Nombre");
			
			int cedula = rs.getInt("CEDULA");
			String correo = rs.getString("CORREO");
			int restauranteID = rs.getInt("RESTAURANTE_ID");
			AdministradorRestaurante =(new AdministradorRestaurante(name2, cedula, restauranteID, correo));
		}

		return AdministradorRestaurante;
	}

	/**
	 * Metodo que agrega el AdministradorRestaurante que entra como parametro a la base de datos.
	 * @param AdministradorRestaurante - el AdministradorRestaurante a agregar. AdministradorRestaurante !=  null
	 * <b> post: </b> se ha agregado el AdministradorRestaurante a la base de datos en la transaction actual. pendiente que el AdministradorRestaurante master
	 * haga commit para que el AdministradorRestaurante baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el AdministradorRestaurante a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAdministradorRestaurante(AdministradorRestaurante AdministradorRestaurante) throws SQLException, Exception {

		String sql = "INSERT INTO AdministradorRestaurante_TABLA VALUES (";
	    sql += AdministradorRestaurante.getCedula() + ",'";
		sql += AdministradorRestaurante.getNombre() + "','";
		sql += AdministradorRestaurante.getCorreo() + "',";
		sql += AdministradorRestaurante.getRestaurante() +")";
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el AdministradorRestaurante que entra como parametro en la base de datos.
	 * @param AdministradorRestaurante - el AdministradorRestaurante a actualizar. AdministradorRestaurante !=  null
	 * <b> post: </b> se ha actualizado el AdministradorRestaurante en la base de datos en la transaction actual. pendiente que el AdministradorRestaurante master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el AdministradorRestaurante.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAdministradorRestaurante(AdministradorRestaurante AdministradorRestaurante) throws SQLException, Exception {

		String sql = "UPDATE AdministradorRestaurante_TABLA SET ";
		sql += "NOMBRE ='" + AdministradorRestaurante.getNombre() + "',";
		sql += "CORREO ='" + AdministradorRestaurante.getCorreo()+ "'";
		sql += "RESTAURANTE_ID =" +AdministradorRestaurante.getRestaurante();
		
		sql += " WHERE CEDULA = " + AdministradorRestaurante.getCedula();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el AdministradorRestaurante que entra como parametro en la base de datos.
	 * @param AdministradorRestaurante - el AdministradorRestaurante a borrar. AdministradorRestaurante !=  null
	 * <b> post: </b> se ha borrado el AdministradorRestaurante en la base de datos en la transaction actual. pendiente que el AdministradorRestaurante master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el AdministradorRestaurante.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAdministradorRestaurante(AdministradorRestaurante AdministradorRestaurante) throws SQLException, Exception {

		String sql = "DELETE FROM AdministradorRestaurante_TABLA";
		sql += " WHERE CEDULA = " + AdministradorRestaurante.getCedula();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
