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

import vos.Cliente;

import vos.Menu;

import vos.Restaurante;

@Path("Restaurantes")
public class RestauranteService {

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
	public Response getRestaurantes() {
		TM tm = new TM(getPath());
		List<Restaurante> restaurante;
		try {
			restaurante = tm.darRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
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
//	public Response getRestauranteName( @QueryParam("nombre") String name) {
//		TM tm = new TM(getPath());
//		List<Restaurante> restaurantes;
//		try {
//			if (name == null || name.length() == 0)
//				throw new Exception("Nombre del Cliente no valido");
//			restaurantes = tm.buscarRestaurantePorName(name);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(restaurantes).build();
//	}


    /**
     * Metodo que expone servicio REST usando POST que agrega el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/Cliente
     * @param restaurante - Cliente a agregar
     * @return Json con el Cliente que agrego o Json con el error que se produjo
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRestaurante(Restaurante restaurante) {
		TM tm = new TM(getPath());
		try {
			tm.addRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}
	
	
	
//    /**
//     * Metodo que expone servicio REST usando POST que agrega los Clientes que recibe en Json
//     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes/varios
//     * @param restaurante - Clientes a agregar. 
//     * @return Json con el Cliente que agrego o Json con el error que se produjo
//     */
//	@POST
//	@Path("/varios")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addRestaurante(List<Restaurante> restaurante) {
//		TM tm = new TM(getPath());
//		try {
//			tm.addRestaurantes(restaurante);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(restaurante).build();
//	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param restaurante - Cliente a actualizar. 
     * @return Json con el Cliente que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRestaurante(Restaurante restaurante) {
		TM tm = new TM(getPath());
		try {
			tm.updateRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}
	
	/**
     * Metodo que expone servicio REST usando PUT que actualiza el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param restaurante - Cliente a actualizar. 
     * @return Json con el Cliente que actualizo o Json con el error que se produjo
     */
	@PUT
	@Path("/surtimientoRestaurante /nombreRestaurante/{nombre}/cantidad/{num}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response surtirRestaurante(@javax.ws.rs.PathParam("nombre") String nombre, @javax.ws.rs.PathParam("num") int cantidad) {
		TM tm = new TM(getPath());
		try {
			tm.surtirRestaurante(nombre, cantidad);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(nombre).build(); // funcionará???
	}
	
	
	
	
	
	
    /**
     * Metodo que expone servicio REST usando DELETE que elimina el Cliente que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/ClienteAndes/rest/Clientes
     * @param restaurante - Cliente a aliminar. 
     * @return Json con el Cliente que elimino o Json con el error que se produjo
     */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRestaurante(Restaurante restaurante) {
		TM tm = new TM(getPath());
		try {
			tm.deleteRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
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
	
	
	/**
     * Metodo que expone servicio REST usando POST que agrega el Menu que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus/Menu
     * @param Menu - Menu a agregar
     * @return Json con el Menu que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Menu")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMenu(Menu Menu) {
		TM tm = new TM(getPath());
		try {
			tm.addMenu(Menu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Menu).build();
	}
	
	/**
     * Metodo que expone servicio REST usando DELETE que elimina el Menu que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus
     * @param Menu - Menu a aliminar. 
     * @return Json con el Menu que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/Menu")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMenu(Menu Menu) {
		TM tm = new TM(getPath());
		try {
			tm.deleteMenu(Menu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Menu).build();
	}
	
	
	
	
	
	

}
