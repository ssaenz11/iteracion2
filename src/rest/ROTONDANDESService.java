package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TM;

import vos.Cliente;

import vos.Menu;
import vos.Pedido;

import vos.PreferenciaCliente;
import vos.Producto;
import vos.ServicioProducto;

@Path("RotonAndes")
public class ROTONDANDESService 
{

	
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	

	/**
	 * Metodo que expone servicio REST usando GET que da todos los ServicioProductos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ServicioProductoAndes/rest/ServicioProductos
	 * @return Json con todos los ServicioProductos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("ServiciosHechos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getServicioProductos() {
		TM tm = new TM(getPath());
		List<ServicioProducto> ServicioProductos;
		try {
			ServicioProductos = tm.darServicioProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ServicioProductos).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Clientes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
	 * @return Json con todos los Clientes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("Clientes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClientes() {
		TM tm = new TM(getPath());
		List<Cliente> Clientes;
		try {
			Clientes = tm.darClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Clientes).build();
	}
	
	
	// consulta de los clientes
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darClienteId( @PathParam( "id" ) Long id )
	{
		TM tm = new TM( getPath( ) );
		try
		{
			Cliente cliente = tm.buscarClientePorId(id);
			ArrayList<Pedido> orden =tm.buscarPedidoPorIdCliente(id);
			
			
			ArrayList c = new ArrayList();
			c.add(cliente); c.addAll(orden); 
			return Response.status( 200 ).entity( c ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	// consulta de  productos
	
	
//	@GET
//	@Path( "pedidos" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response darProductos( )
//	{
//		TM tm = new TM( getPath( ) );
//		try
//		{
//			List<Acompañamiento> cliente = tm.darAcompañamientos();
//			List<Postre> cliente1 = tm.darPostres();
//			List<Menu> cliente2 = tm.darMenus();
//			List<Bebida> cliente3 = tm.darBebidas();
//			List<Entrada> cliente4 = tm.darEntradas();
//			List<PlatoFuerte> cliente5 = tm.darPlatoFuertes();
//			
//			
//			
//			ArrayList c = new ArrayList();
//			 c.addAll(cliente); c.addAll(cliente1); c.addAll(cliente2);c.addAll(cliente3);c.addAll(cliente4);c.addAll(cliente5);
//			return Response.status( 200 ).entity( c ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
	
//	
//	@GET
//	@Path("ProductosMasOfrecidos")
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response darProdMasOfrecidos()
//	{
//		TM tm = new TM( getPath( ) );
//		try
//		{
//			ArrayList<Menu> menus = (ArrayList<Menu>) tm.darMenus();
//			double masVeces = 0;
//			double actualVeces1 = 0;
//			double actualVeces2 = 0;
//			double actualVeces3 = 0;
//			double actualVeces4 = 0;
//			Boolean noNull = true;
//			ArrayList<Producto> productosMax = null;
//			for (int i = 0; i < menus.size(); i++) {
//				
//				String bebidaActual = menus.get(i).getId_bebida();
//				String entradaActual =menus.get(i).getId_entrada();
//				String platoFuerteActual =menus.get(i).getId_plato_fuerte();
//				String postreActual =menus.get(i).getId_postre();
//				
//				for (int j = 0; j < menus.size(); j++) {
//					
//					if(menus.get(j).getId_bebida()==bebidaActual) {
//						actualVeces1++;
//					}
//					if(menus.get(j).getId_entrada()==entradaActual) {
//						actualVeces2++;
//					}
//					if(menus.get(j).getId_plato_fuerte()==platoFuerteActual) {
//						actualVeces3++;
//					}
//					if(menus.get(j).getId_postre()==postreActual) {
//						actualVeces4++;
//					}
//				}
//				if(actualVeces1==masVeces) {
//					productosMax.add(tm.buscarBebidaPorName(menus.get(i).getId_bebida()));
//				}
//				if(actualVeces2==masVeces) {
//					productosMax.add(tm.buscarEntradaPorName(menus.get(i).getId_entrada()));
//				}
//				if(actualVeces3==masVeces) {
//					productosMax.add(tm.buscarPlatoFuertePorName(menus.get(i).getId_plato_fuerte()));
//				}
//				if(actualVeces4==masVeces) {
//					productosMax.add(tm.buscarPostrePorName(menus.get(i).getId_postre()));
//				}
//				if(actualVeces1>masVeces) {
//					masVeces=actualVeces1;
//					productosMax = null;
//					productosMax.add(tm.buscarBebidaPorName(menus.get(i).getId_bebida()));
//				}
//				if(actualVeces2>masVeces) {
//					masVeces=actualVeces2;
//					productosMax = null;
//					productosMax.add(tm.buscarEntradaPorName(menus.get(i).getId_entrada()));
//				}
//				if(actualVeces3>masVeces) {
//					masVeces=actualVeces3;
//					productosMax = null;
//					productosMax.add(tm.buscarPlatoFuertePorName(menus.get(i).getId_plato_fuerte()));
//				}
//				if(actualVeces4>masVeces) {
//					masVeces=actualVeces4;
//					productosMax = null;
//					productosMax.add(tm.buscarPostrePorName(menus.get(i).getId_postre()));
//				}
//				actualVeces1 = 0;
//				actualVeces2 = 0;
//				actualVeces3 = 0;
//				actualVeces4 = 0;
//				
//			}
//
//			return Response.status( 200 ).entity( productosMax ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
//	

	
	
   
}
