package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TM;
import vos.Acompaņamiento;
import vos.Bebida;
import vos.Cliente;
import vos.Entrada;
import vos.Menu;
import vos.Pedido;
import vos.PlatoFuerte;
import vos.Postre;



@Path("Clientes")
public class Services {


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
	 * Metodo que expone servicio REST usando GET que da todos los Clientes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
	 * @return Json con todos los Clientes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
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

	 /**
     * Metodo que expone servicio REST usando GET que busca el Cliente con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/<<id>>" para la busqueda"
     * @param name - Nombre del Cliente a buscar que entra en la URL como parametro 
     * @return Json con el/los Clientes encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getCliente( @PathParam( "id" ) Long id )
	{
		TM tm = new TM( getPath( ) );
		try
		{
			Cliente v = tm.buscarClientePorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

//    /**
//     * Metodo que expone servicio REST usando GET que busca el Cliente con el nombre que entra como parametro
//     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/nombre/nombre?nombre=<<nombre>>" para la busqueda"
//     * @param name - Nombre del Cliente a buscar que entra en la URL como parametro 
//     * @return Json con el/los Clientes encontrados con el nombre que entra como parametro o json con 
//     * el error que se produjo
//     */
//	@GET
//	@Path( "{nombre}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response getClienteName( @QueryParam("nombre") String name) {
//		TM tm = new TM(getPath());
//		List<Cliente> Clientes;
//		try {
//			if (name == null || name.length() == 0)
//				throw new Exception("Nombre del Cliente no valido");
//			Clientes = tm.buscarClientesPorName(name);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(Clientes).build();
//	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/Cliente
     * @param Cliente - Cliente a agregar
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(Cliente Cliente) {
		TM tm = new TM(getPath());
		try {
			tm.addCliente(Cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Cliente).build();
	}
	
    /**
     * Metodo que expone servicio REST usando POST que agrega los Clientes que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/varios
     * @param Clientes - Clientes a agregar. 
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(List<Cliente> Clientes) {
		TM tm = new TM(getPath());
		try {
			tm.addClientes(Clientes);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Clientes).build();
	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param Cliente - Cliente a actualizar. 
     * @return Json con el Cliente que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCliente(Cliente Cliente) {
		TM tm = new TM(getPath());
		try {
			tm.updateCliente(Cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Cliente).build();
	}
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param Cliente - Cliente a aliminar. 
     * @return Json con el Cliente que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(Cliente Cliente) {
		TM tm = new TM(getPath());
		try {
			tm.deleteCliente(Cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Cliente).build();
	}
	
	

	/**
	 * Metodo que expone servicio REST usando GET que da todos los Clientes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
	 * @return Json con todos los Clientes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Pedidos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPedidos() {
		TM tm = new TM(getPath());
		List<Pedido> pedidos;
		try {
			pedidos = tm.darPedidos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pedidos).build();
	}

	 

    /**
     * Metodo que expone servicio REST usando POST que agrega el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/Cliente
     * @param pedido - Cliente a agregar
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Pedidos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPedido(Pedido pedido) {
		TM tm = new TM(getPath());
		try {
			tm.addPedido(pedido);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pedido).build();
	}
	
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Postres de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres
	 * @return Json con todos los Postres de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Postres")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPostres() {
		TM tm = new TM(getPath());
		List<Postre> Postres;
		try {
			Postres = tm.darPostres();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postres).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los PlatoFuertes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes
	 * @return Json con todos los PlatoFuertes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/PlatosFuertes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPlatoFuertes() {
		TM tm = new TM(getPath());
		List<PlatoFuerte> PlatoFuertes;
		try {
			PlatoFuertes = tm.darPlatoFuertes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuertes).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Entradas de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas
	 * @return Json con todos los Entradas de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Entradas")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEntradas() {
		TM tm = new TM(getPath());
		List<Entrada> Entradas;
		try {
			Entradas = tm.darEntradas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entradas).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Bebidas de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas
	 * @return Json con todos los Bebidas de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Bebidas")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBebidas() {
		TM tm = new TM(getPath());
		List<Bebida> Bebidas;
		try {
			Bebidas = tm.darBebidas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebidas).build();
	}
   
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Acompaņamientos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/AcompaņamientoAndes/rest/Acompaņamientos
	 * @return Json con todos los Acompaņamientos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Acompaņamiento")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAcompaņamientos() {
		TM tm = new TM(getPath());
		List<Acompaņamiento> Acompaņamientos;
		try {
			Acompaņamientos = tm.darAcompaņamientos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompaņamientos).build();
	}

	/**
	 * Metodo que expone servicio REST usando GET que da todos los Menus de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus
	 * @return Json con todos los Menus de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Menu")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMenus() {
		TM tm = new TM(getPath());
		List<Menu> Menus;
		try {
			Menus = tm.darMenus();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Menus).build();
	}

}
