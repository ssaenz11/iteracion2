package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;
import vos.Zona;

public class DAOTablaZona {


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
	public DAOTablaZona() {
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
	public ArrayList<Zona> darZonas() throws SQLException, Exception {
		ArrayList<Zona> zonas = new ArrayList<Zona>();

		String sql = "SELECT * FROM ZONA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			String name = rs.getString("NOMBRE");
			int capacidad = rs.getInt("CAPACIDAD");
			String destapado = rs.getString("DESTAPADO");
			String apropiadoDisc = rs.getString("apropiadoDisc");
			String condicionesTecnicas = rs.getString("condicionesTec");
			zonas.add(new Zona(name, capacidad, destapado, apropiadoDisc, condicionesTecnicas));
		}
		return zonas;
	}


	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Zona> buscarZonaPorName(String name) throws SQLException, Exception {
		ArrayList<Zona> zonas = new ArrayList<Zona>();

		String sql = "SELECT * FROM ZONA WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name2 = rs.getString("NOMBRE");
			int capacidad = rs.getInt("CAPACIDAD");
			String destapado = rs.getString("DESTAPADO");
			String apropiadoDisc = rs.getString("apropiadoDisc");
			String condicionesTecnicas = rs.getString("condicionesTec");
			
			zonas.add(new Zona(name2, capacidad, destapado, apropiadoDisc, condicionesTecnicas));
		}

		return zonas;
	}
	

	/**
	 * Metodo que agrega el Cliente que entra como parametro a la base de datos.
	 * @param Cliente - el Cliente a agregar. Cliente !=  null
	 * <b> post: </b> se ha agregado el Cliente a la base de datos en la transaction actual. pendiente que el Cliente master
	 * haga commit para que el Cliente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Cliente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addZona(Zona zona) throws SQLException, Exception {

		String sql = "INSERT INTO ZONA VALUES ('";
	    sql += zona.getNombre() + "',";
		sql += zona.getCapacidad() + ",'";

		sql += zona.isDestapado() + "','";
		sql += zona.isApropiadoDisc() + "','";
		sql += zona.getCondicionesTecnicas() + "')";
		
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
	public void updateZona(Zona zona) throws SQLException, Exception {

		String sql = "UPDATE ZONA SET ";
		sql += "CAPACIDAD =" + zona.getCapacidad() + ",";
		sql += "DESTAPADO ='" + zona.isDestapado()+ "',";
		sql += "APROPIADODISC ='" + zona.isApropiadoDisc()+ "',";
		sql += "condicionesTec ='" + zona.getCondicionesTecnicas()+ "',";
		sql += " WHERE NOMBRE = '" + zona.getNombre()+ "'";


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
	public void deleteZona(Zona zona) throws SQLException, Exception {

		String sql = "DELETE FROM ZONA";
		sql += " WHERE NOMBRE = '" + zona.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
