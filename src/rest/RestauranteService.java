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
import vos.Acompañamiento;
import vos.Bebida;
import vos.Cliente;
import vos.Entrada;
import vos.Menu;
import vos.PlatoFuerte;
import vos.Postre;
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
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Bebidas de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas
	 * @return Json con todos los Bebidas de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Bebida")
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
     * Metodo que expone servicio REST usando POST que agrega el Bebida que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas/Bebida
     * @param Bebida - Bebida a agregar
     * @return Json con el Bebida que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Bebida")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBebida(Bebida Bebida) {
		TM tm = new TM(getPath());
		try {
			tm.addBebida(Bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebida).build();
	}
	
	 /**
     * Metodo que expone servicio REST usando DELETE que elimina el Bebida que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/BebidaAndes/rest/Bebidas
     * @param Bebida - Bebida a aliminar. 
     * @return Json con el Bebida que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/Bebida")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBebida(Bebida Bebida) {
		TM tm = new TM(getPath());
		try {
			tm.deleteBebida(Bebida);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Bebida).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Acompañamientos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos
	 * @return Json con todos los Acompañamientos de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Acompañamiento")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAcompañamientos() {
		TM tm = new TM(getPath());
		List<Acompañamiento> Acompañamientos;
		try {
			Acompañamientos = tm.darAcompañamientos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamientos).build();
	}
	
	/**
     * Metodo que expone servicio REST usando POST que agrega el Acompañamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos/Acompañamiento
     * @param Acompañamiento - Acompañamiento a agregar
     * @return Json con el Acompañamiento que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Acompañamiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcompañamiento(Acompañamiento Acompañamiento) {
		TM tm = new TM(getPath());
		try {
			tm.addAcompañamiento(Acompañamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamiento).build();
	}
	
	 /**
     * Metodo que expone servicio REST usando DELETE que elimina el Acompañamiento que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/AcompañamientoAndes/rest/Acompañamientos
     * @param Acompañamiento - Acompañamiento a aliminar. 
     * @return Json con el Acompañamiento que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/Acompañamiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAcompañamiento(Acompañamiento Acompañamiento) {
		TM tm = new TM(getPath());
		try {
			tm.deleteAcompañamiento(Acompañamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Acompañamiento).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Postres de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres
	 * @return Json con todos los Postres de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Postre")
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
     * Metodo que expone servicio REST usando POST que agrega el Postre que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres/Postre
     * @param Postre - Postre a agregar
     * @return Json con el Postre que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Postre")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPostre(Postre Postre) {
		TM tm = new TM(getPath());
		try {
			tm.addPostre(Postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postre).build();
	}
	
	 /**
     * Metodo que expone servicio REST usando DELETE que elimina el Postre que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PostreAndes/rest/Postres
     * @param Postre - Postre a aliminar. 
     * @return Json con el Postre que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/Postre")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePostre(Postre Postre) {
		TM tm = new TM(getPath());
		try {
			tm.deletePostre(Postre);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Postre).build();
	}

	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los PlatoFuertes de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes
	 * @return Json con todos los PlatoFuertes de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/PlatoFuerte")
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
     * Metodo que expone servicio REST usando POST que agrega el PlatoFuerte que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes/PlatoFuerte
     * @param PlatoFuerte - PlatoFuerte a agregar
     * @return Json con el PlatoFuerte que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/PlatoFuerte")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlatoFuerte(PlatoFuerte PlatoFuerte) {
		TM tm = new TM(getPath());
		try {
			tm.addPlatoFuerte(PlatoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuerte).build();
	}
	
	 /**
     * Metodo que expone servicio REST usando DELETE que elimina el PlatoFuerte que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/PlatoFuerteAndes/rest/PlatoFuertes
     * @param PlatoFuerte - PlatoFuerte a aliminar. 
     * @return Json con el PlatoFuerte que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/PlatoFuerte")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlatoFuerte(PlatoFuerte PlatoFuerte) {
		TM tm = new TM(getPath());
		try {
			tm.deletePlatoFuerte(PlatoFuerte);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(PlatoFuerte).build();
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los Entradas de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas
	 * @return Json con todos los Entradas de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
	@Path("/Entrada")
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
     * Metodo que expone servicio REST usando POST que agrega el Entrada que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas/Entrada
     * @param Entrada - Entrada a agregar
     * @return Json con el Entrada que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/Entrada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEntrada(Entrada Entrada) {
		TM tm = new TM(getPath());
		try {
			tm.addEntrada(Entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entrada).build();
	}
	
	 /**
     * Metodo que expone servicio REST usando DELETE que elimina el Entrada que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/EntradaAndes/rest/Entradas
     * @param Entrada - Entrada a aliminar. 
     * @return Json con el Entrada que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/Entrada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEntrada(Entrada Entrada) {
		TM tm = new TM(getPath());
		try {
			tm.deleteEntrada(Entrada);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Entrada).build();
	}

}
