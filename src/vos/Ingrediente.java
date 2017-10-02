package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ingrediente {

	///Atributos///
	/**
	 * nombre del ingrediente
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * descripcion del ingrediente.
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	/**
	 * traduccion en ingles de la descripción.
	 */
	@JsonProperty(value="traduccion")
	private String traduccion;
	
	/**
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param traduccion
	 */
	public Ingrediente(@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="descripcion")String descripcion,
			@JsonProperty(value="traduccion")String traduccion)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.traduccion = traduccion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the traduccion
	 */
	public String getTraduccion() {
		return traduccion;
	}

	/**
	 * @param traduccion the traduccion to set
	 */
	public void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}


}
