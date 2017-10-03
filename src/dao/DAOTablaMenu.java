package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Menu;

public class DAOTablaMenu 
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
	public DAOTablaMenu() {
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
	public ArrayList<Menu> darMenus() throws SQLException, Exception {
		ArrayList<Menu> Menus = new ArrayList<Menu>();

		String sql = "SELECT * FROM Menu_TABLA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			
			double precio = rs.getDouble("PRECIO");
			Long ID = rs.getLong("ID");
			Long entrada = rs.getLong("ID_ENTRADA");
			Long postre = rs.getLong("ID_POSTRE");
			Long bebida = rs.getLong("ID_BEBIDA");
			Long platoFuerte = rs.getLong("ID_PLATO_FUERTE");
			Menus.add(new Menu(ID, bebida, entrada, platoFuerte, postre, precio));
		}
		return Menus;
	}


	
	/**
	 * Metodo que busca el video con el id que entra como parametro.
	 * @param name - Id de el video a buscar
	 * @return Video encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Menu buscarMenuPorId(Long id) throws SQLException, Exception 
	{
		Menu Menu = null;

		String sql = "SELECT * FROM Menu_TABLA WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			double precio = rs.getDouble("PRECIO");
			Long ID = rs.getLong("ID");
			Long entrada = rs.getLong("ID_ENTRADA");
			Long postre = rs.getLong("ID_POSTRE");
			Long bebida = rs.getLong("ID_BEBIDA");
			Long platoFuerte = rs.getLong("ID_PLATO_FUERTE");
			Menu =(new Menu(ID, bebida, entrada, platoFuerte, postre, precio));
		}

		return Menu;
	}

	/**
	 * Metodo que agrega el Menu que entra como parametro a la base de datos.
	 * @param Menu - el Menu a agregar. Menu !=  null
	 * <b> post: </b> se ha agregado el Menu a la base de datos en la transaction actual. pendiente que el Menu master
	 * haga commit para que el Menu baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Menu a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addMenu(Menu Menu) throws SQLException, Exception {

		String sql = "INSERT INTO Menu_TABLA VALUES (";
	    sql += Menu.getId()+ ",";
		sql += Menu.getId_entrada() + ",";
		sql += Menu.getValor() + ",";
		sql += Menu.getId_postre() + ",";
		sql += Menu.getId_bebida()+ ",";
		sql += Menu.getId_plato_fuerte()+ ")";
		

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Menu que entra como parametro en la base de datos.
	 * @param Menu - el Menu a actualizar. Menu !=  null
	 * <b> post: </b> se ha actualizado el Menu en la base de datos en la transaction actual. pendiente que el Menu master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Menu.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateMenu(Menu Menu) throws SQLException, Exception {

		String sql = "UPDATE Menu_TABLA SET ";
		sql += "ID_ENTRADA =" + Menu.getId_entrada() + ",";
		sql += "PRECIO =" + Menu.getValor() + ",";
		sql += "ID_POSTRE =" + Menu.getId_postre() + ",";
		sql += "ID_BEBIDA =" + Menu.getId_bebida() + ",";
		sql += "ID_PLATO_FUERTE =" + Menu.getId_plato_fuerte()+ " ";
		
		sql += " WHERE ID = " + Menu.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Menu que entra como parametro en la base de datos.
	 * @param Menu - el Menu a borrar. Menu !=  null
	 * <b> post: </b> se ha borrado el Menu en la base de datos en la transaction actual. pendiente que el Menu master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Menu.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteMenu(Menu Menu) throws SQLException, Exception {

		String sql = "DELETE FROM Menu_TABLA";
		sql += " WHERE ID = " + Menu.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
}
