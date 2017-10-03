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
import vos.Menu;

@Path("Menu")
public class MenuServices
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
	 * Metodo que expone servicio REST usando GET que da todos los Menus de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus
	 * @return Json con todos los Menus de la base de datos o json con 
     * el error que se produjo
	 */
	@GET
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
     * Metodo que expone servicio REST usando GET que busca el Menu con el id que entra como parametro
     * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus/<<id>>" para la busqueda"
     * @param name - Nombre del Menu a buscar que entra en la URL como parametro 
     * @return Json con el/los Menus encontrados con el nombre que entra como parametro o json con 
     * el error que se produjo
     */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getMenu( @PathParam( "id" ) Long id )
	{
		TM tm = new TM( getPath( ) );
		try
		{
			Menu v = tm.buscarMenuPorId( id );
			return Response.status( 200 ).entity( v ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

   

    /**
     * Metodo que expone servicio REST usando POST que agrega el Menu que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus/Menu
     * @param Menu - Menu a agregar
     * @return Json con el Menu que agrego o Json con el error que se produjo
     */
	@POST
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
     * Metodo que expone servicio REST usando POST que agrega los Menus que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus/varios
     * @param Menus - Menus a agregar. 
     * @return Json con el Menu que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/varios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMenu(List<Menu> Menus) {
		TM tm = new TM(getPath());
		try {
			tm.addMenus(Menus);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Menus).build();
	}
	
    /**
     * Metodo que expone servicio REST usando PUT que actualiza el Menu que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/MenuAndes/rest/Menus
     * @param Menu - Menu a actualizar. 
     * @return Json con el Menu que actualizo o Json con el error que se produjo
     */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMenu(Menu Menu) {
		TM tm = new TM(getPath());
		try {
			tm.updateMenu(Menu);
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
