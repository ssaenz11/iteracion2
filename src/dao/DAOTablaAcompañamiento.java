package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Acompa�amiento;

public class DAOTablaAcompa�amiento 
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
	public DAOTablaAcompa�amiento() {
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
	public ArrayList<Acompa�amiento> darAcompa�amientos() throws SQLException, Exception {
		ArrayList<Acompa�amiento> Acompa�amientos = new ArrayList<Acompa�amiento>();

		String sql = "SELECT * FROM Acompa�amiento_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			
			Acompa�amientos.add(new Acompa�amiento(cantidad, idRestaurante, descripcion, nombre, precio));
		}
		return Acompa�amientos;
	}


	

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Acompa�amiento buscarAcompa�amientoPorName(String name) throws SQLException, Exception {
		Acompa�amiento Acompa�amientos = null;

		String sql = "SELECT * FROM Acompa�amiento_TABLA WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			Acompa�amientos =(new Acompa�amiento(cantidad, idRestaurante, descripcion, nombre, precio));
		}

		return Acompa�amientos;
	}

	/**
	 * Metodo que agrega el Acompa�amiento que entra como parametro a la base de datos.
	 * @param Acompa�amiento - el Acompa�amiento a agregar. Acompa�amiento !=  null
	 * <b> post: </b> se ha agregado el Acompa�amiento a la base de datos en la transaction actual. pendiente que el Acompa�amiento master
	 * haga commit para que el Acompa�amiento baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Acompa�amiento a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAcompa�amiento(Acompa�amiento Acompa�amiento) throws SQLException, Exception {

		String sql = "INSERT INTO Acompa�amiento_TABLA VALUES (";
	    sql += Acompa�amiento.getCantidad()+ ",'";
		sql += Acompa�amiento.getNombre() + "','";
		sql += Acompa�amiento.getDescripcion() + "',";
		sql += Acompa�amiento.getPrecio() + ",";
		sql += Acompa�amiento.getId_restaurante()+ ")";
		
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Acompa�amiento que entra como parametro en la base de datos.
	 * @param Acompa�amiento - el Acompa�amiento a actualizar. Acompa�amiento !=  null
	 * <b> post: </b> se ha actualizado el Acompa�amiento en la base de datos en la transaction actual. pendiente que el Acompa�amiento master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Acompa�amiento.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAcompa�amiento(Acompa�amiento Acompa�amiento) throws SQLException, Exception {

		String sql = "UPDATE Acompa�amiento_TABLA SET ";
		sql += "CANTIDAD =" + Acompa�amiento.getCantidad() + ",";
		sql += "NOMBRE ='" + Acompa�amiento.getNombre() + "',";
		sql += "DESCRIPCION ='" + Acompa�amiento.getDescripcion() + "',";
		sql += "PRECIO =" + Acompa�amiento.getPrecio() + ",";
		sql += "ID_RESTAURANTE =" + Acompa�amiento.getId_restaurante()+ " ";
		
		sql += " WHERE NOMBRE = '" + Acompa�amiento.getNombre()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Acompa�amiento que entra como parametro en la base de datos.
	 * @param Acompa�amiento - el Acompa�amiento a borrar. Acompa�amiento !=  null
	 * <b> post: </b> se ha borrado el Acompa�amiento en la base de datos en la transaction actual. pendiente que el Acompa�amiento master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Acompa�amiento.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAcompa�amiento(Acompa�amiento Acompa�amiento) throws SQLException, Exception {

		String sql = "DELETE FROM Acompa�amiento_TABLA";
		sql += " WHERE NOMBRE = '" + Acompa�amiento.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
}
