package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Administrador;

public class DAOTablaAdministrador 
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
	public DAOTablaAdministrador () {
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
	public ArrayList<Administrador> darAdministradors() throws SQLException, Exception {
		ArrayList<Administrador> Administradors = new ArrayList<Administrador>();

		String sql = "SELECT * FROM ADMINISTRADOR_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			String name = rs.getString("NOMBRE");
			int cedula = rs.getInt("CEDULA");
			String correo = rs.getString("CORREO");
			Administradors.add(new Administrador(name, cedula, correo));
		}
		return Administradors;
	}


	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Administrador> buscarAdministradorPorName(String name) throws SQLException, Exception {
		ArrayList<Administrador> Administradors = new ArrayList<Administrador>();

		String sql = "SELECT * FROM ADMINISTRADOR_TABLA WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name2 = rs.getString("Nombre");
			
			int cedula = rs.getInt("CEDULA");
			String correo = rs.getString("CORREO");
			Administradors.add(new Administrador( name2, cedula, correo));
		}

		return Administradors;
	}
	
	/**
	 * Metodo que busca el video con el id que entra como parametro.
	 * @param name - Id de el video a buscar
	 * @return Video encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Administrador buscarAdministradorPorId(Long id) throws SQLException, Exception 
	{
		Administrador Administrador = null;

		String sql = "SELECT * FROM ADMINISTRADOR_TABLA WHERE CEDULA =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name2 = rs.getString("Nombre");
			
			int cedula = rs.getInt("CEDULA");
			String correo = rs.getString("CORREO");
			Administrador =(new Administrador( name2, cedula, correo));
		}

		return Administrador;
	}

	/**
	 * Metodo que agrega el Administrador que entra como parametro a la base de datos.
	 * @param Administrador - el Administrador a agregar. Administrador !=  null
	 * <b> post: </b> se ha agregado el Administrador a la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que el Administrador baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Administrador a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAdministrador(Administrador Administrador) throws SQLException, Exception {

		String sql = "INSERT INTO ADMINISTRADOR_TABLA VALUES (";
	    sql += Administrador.getCedula() + ",'";
		sql += Administrador.getNombre() + "','";
		sql += Administrador.getCorreo() + "')";
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Administrador que entra como parametro en la base de datos.
	 * @param Administrador - el Administrador a actualizar. Administrador !=  null
	 * <b> post: </b> se ha actualizado el Administrador en la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAdministrador(Administrador Administrador) throws SQLException, Exception {

		String sql = "UPDATE ADMINISTRADOR_TABLA SET ";
		sql += "NOMBRE ='" + Administrador.getNombre() + "',";
		sql += "CORREO ='" + Administrador.getCorreo()+ "'";
		
		sql += " WHERE CEDULA = " + Administrador.getCedula();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Administrador que entra como parametro en la base de datos.
	 * @param Administrador - el Administrador a borrar. Administrador !=  null
	 * <b> post: </b> se ha borrado el Administrador en la base de datos en la transaction actual. pendiente que el Administrador master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Administrador.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAdministrador(Administrador Administrador) throws SQLException, Exception {

		String sql = "DELETE FROM ADMINISTRADOR_TABLA";
		sql += " WHERE CEDULA = " + Administrador.getCedula();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}
