package vos;


import org.codehaus.jackson.annotate.*;
public class Cliente {

	
	
////Atributos

	/**
	 * Id del video
	 */
	@JsonProperty(value="id")
	private Long id;

	
	/**
	 * Nombre del Cliente
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Correo  del Cliente
	 */
	@JsonProperty(value="correo")
	private String correo;
	
	/**
	 * Cedula  del Cliente
	 */
	@JsonProperty(value="cedula")
	private Long  cedula;
	
	


	/**
	 * Metodo constructor de la clase Cliente
	 * <b>post: </b> Crea el cliente con los valores que entran como parametro
	 * @param id - Id del Cliente.
	 * @param name - Nombre del Cliente. name != null
	 * @param correo - correo del Cliente. name != null
	 * @param cedula - cedula del Cliente. name != null
	 */
	
	public Cliente(@JsonProperty(value="id")Long id,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="cedula")Long cedula,
			@JsonProperty(value="correo")String correo)
	{
		super();
		this.id = id;
		this.nombre= nombre;
		this.correo= correo;
		this.cedula= cedula;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getCorreo() {
		return correo;
	}




	public void setCorreo(String correo) {
		this.correo = correo;
	}




	public Long getCedula() {
		return cedula;
	}




	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}
	
	
	
}
