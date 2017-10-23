package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.net.aso.r;
import vos.Producto;
import vos.Restaurante;

public class DAOTablaProducto {
	
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
	public DAOTablaProducto() {
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
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Producto> darProductos() throws SQLException, Exception {
		ArrayList<Producto> Productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM Producto_tabla ";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name2 = rs.getString("NOMBRE");
			int cantiad = rs.getInt("CANTIDAD");
			String descripción = rs.getString("DESCRIPCION");
			double precio = rs.getDouble("PRECIO");
			int idrest = rs.getInt("ID_RESTAURANTE");
			String nombreEqu = rs.getString("NOMBRE_PRODUCTO_EQU");
			String tipo = rs.getString("tipo");
			Productos.add(new Producto(cantiad, idrest, descripción, name2, precio, tipo, nombreEqu));
		}

		return Productos;
	}
	


	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Producto buscarProductoPorName(String name) throws SQLException, Exception {
		Producto Productos = null;

		String sql = "SELECT * FROM Producto_tabla WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name2 = rs.getString("NOMBRE");
			int cantiad = rs.getInt("CANTIDAD");
			String descripción = rs.getString("DESCRIPCION");
			double precio = rs.getDouble("PRECIO");
			int idrest = rs.getInt("ID_RESTAURANTE");
			String nombreEqu = rs.getString("NOMBRE_PRODUCTO_EQU");
			String tipo = rs.getString("tipo");
			Productos =new Producto(cantiad, idrest, descripción, name2, precio, tipo, nombreEqu);
		}

		return Productos;
	}
	
	
	/**
	 * Metodo que actualiza el Cliente que entra como parametro en la base de datos.
	 * @param Cliente - el Cliente a actualizar. Cliente !=  null
	 * <b> post: </b> se ha actualizado el Cliente en la base de datos en la transaction actual. pendiente que el Cliente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Cliente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateProducto(Producto Producto) throws SQLException, Exception {

		String sql = "UPDATE Producto_tabla SET ";
		
		sql += "NOMBRE ='" + Producto.getNombre()+ "' ";
		sql += "Descripcion ='" + Producto.getDescripcion()+ "' ";
		sql += "precio =" + Producto.getPrecio()+ " ";
		sql += "id_restaurante =" + Producto.getId_restaurante()+ " ";
		sql += "nombre_producto_equ ='" + Producto.getEqui()+ "'";
		sql += "tipo ='" + Producto.getTipo()+ "'";
		sql += "cantidad=" + Producto.getCantidad()+ " ";
		
		sql += " WHERE NOMBRE = " + Producto.getNombre()+ "'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que agrega el Cliente que entra como parametro a la base de datos.
	 * @param Cliente - el Cliente a agregar. Cliente !=  null
	 * <b> post: </b> se ha agregado el Cliente a la base de datos en la transaction actual. pendiente que el Cliente master
	 * haga commit para que el Cliente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Cliente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addProducto(Producto Producto) throws SQLException, Exception {

		String sql = "INSERT INTO Producto_tabla VALUES ('";
	    
		sql += Producto.getNombre() + "','";
		
	    sql += Producto.getDescripcion() + "',";
	    
	    sql += Producto.getPrecio() + ",";
	    
	    sql += Producto.getId_restaurante() + ",'";
	    
	    sql += Producto.getEqui() + "','";
		
		sql += Producto.getTipo() + "',";
		
		sql += Producto.getCantidad() + ")";

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
	public void deleteProducto(Producto Producto) throws SQLException, Exception {

		String sql = "DELETE FROM Producto_tabla";
		sql += " WHERE NOMBRE = '" + Producto.getNombre()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
