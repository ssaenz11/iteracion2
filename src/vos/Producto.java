package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto 
{


	//// Atributos

	/**
	 * Id del producto
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * id_restaurante del producto
	 */
	@JsonProperty(value="id_restaurante")
	private Long id_restaurante;

	/**
	 * precio del producto
	 */
	@JsonProperty(value="precio")
	private double precio;

	/**
	 * nombre del producto
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * descripcion del producto
	 */
	@JsonProperty(value="descripcion")
	private String descripcion  ;

	public Producto(@JsonProperty(value="id")Long id,
			@JsonProperty(value="id_restaurante")Long id_restaurante,
			@JsonProperty(value="descripcion")String descripcion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="precio")double precio)
	{
		super();
		this.id= id;
		this.id_restaurante= id_restaurante;
		this.descripcion = descripcion;
		this.nombre= nombre;
		this.precio= precio;


	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_restaurante() {
		return id_restaurante;
	}

	public void setId_restaurante(Long id_restaurante) {
		this.id_restaurante = id_restaurante;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}
