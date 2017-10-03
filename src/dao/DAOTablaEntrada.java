package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Entrada;

public class DAOTablaEntrada {

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
	public DAOTablaEntrada() {
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
	public ArrayList<Entrada> darEntradas() throws SQLException, Exception {
		ArrayList<Entrada> Entradas = new ArrayList<Entrada>();

		String sql = "SELECT * FROM Entrada_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			
			Entradas.add(new Entrada(cantidad, idRestaurante, descripcion, nombre, precio));
		}
		return Entradas;
	}


	

	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Entrada buscarEntradaPorName(String name) throws SQLException, Exception {
		Entrada Entradas = null;

		String sql = "SELECT * FROM Entrada_TABLA WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int cantidad = rs.getInt("CANTIDAD");
			String nombre = rs.getString("NOMBRE");
			String descripcion = rs.getString("DESCRIPCION");
			double precio = rs.getLong("PRECIO");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			Entradas =(new Entrada(cantidad, idRestaurante, descripcion, nombre, precio));
		}

		return Entradas;
	}

	/**
	 * Metodo que agrega el Entrada que entra como parametro a la base de datos.
	 * @param Entrada - el Entrada a agregar. Entrada !=  null
	 * <b> post: </b> se ha agregado el Entrada a la base de datos en la transaction actual. pendiente que el Entrada master
	 * haga commit para que el Entrada baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Entrada a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addEntrada(Entrada Entrada) throws SQLException, Exception {

		String sql = "INSERT INTO Entrada_TABLA VALUES (";
	    sql += Entrada.getCantidad()+ ",'";
		sql += Entrada.getNombre() + "','";
		sql += Entrada.getDescripcion() + "',";
		sql += Entrada.getPrecio() + ",";
		sql += Entrada.getId_restaurante()+ ")";
		
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Entrada que entra como parametro en la base de datos.
	 * @param Entrada - el Entrada a actualizar. Entrada !=  null
	 * <b> post: </b> se ha actualizado el Entrada en la base de datos en la transaction actual. pendiente que el Entrada master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Entrada.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateEntrada(Entrada Entrada) throws SQLException, Exception {

		String sql = "UPDATE Entrada_TABLA SET ";
		sql += "CANTIDAD =" + Entrada.getCantidad() + ",";
		sql += "NOMBRE ='" + Entrada.getNombre() + "',";
		sql += "DESCRIPCION ='" + Entrada.getDescripcion() + "',";
		sql += "PRECIO =" + Entrada.getPrecio() + ",";
		sql += "ID_RESTAURANTE =" + Entrada.getId_restaurante()+ " ";
		
		sql += " WHERE NOMBRE = '" + Entrada.getNombre()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Entrada que entra como parametro en la base de datos.
	 * @param Entrada - el Entrada a borrar. Entrada !=  null
	 * <b> post: </b> se ha borrado el Entrada en la base de datos en la transaction actual. pendiente que el Entrada master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Entrada.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteEntrada(Entrada Entrada) throws SQLException, Exception {

		String sql = "DELETE FROM Entrada_TABLA";
		sql += " WHERE NOMBRE = '" + Entrada.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
}
