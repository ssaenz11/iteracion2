package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto 
{


	//// Atributos

	/**
	 * Id del producto
	 */
	@JsonProperty(value="cantidad")
	private int  cantidad;

	/**
	 * id_restaurante del producto
	 */
	@JsonProperty(value="id_restaurante")
	private int id_restaurante;

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
	
	/**
	 * tipo de producto 
	 */
	
	@JsonProperty(value = "tipo")
	private String tipo;
	
	@JsonProperty(value = "equ")
	private String equi;
	
	
	/**
	 * 
	 * @param id
	 * @param id_restaurante
	 * @param descripcion
	 * @param nombre
	 * @param precio
	 */

	public Producto(@JsonProperty(value="cantidad")int CANTIDAD,
			@JsonProperty(value="id_restaurante")int id_restaurante,
			@JsonProperty(value="descripcion")String descripcion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="precio")double precio,
			@JsonProperty(value="tipo")String tipo,
			@JsonProperty(value="equ")String  nombreEqu)
	{
		super();
		this.cantidad= CANTIDAD;
		this.id_restaurante= id_restaurante;
		this.descripcion = descripcion;
		this.nombre= nombre;
		this.precio= precio;
		this.tipo = tipo;
		this.equi = nombreEqu;


	}

	

	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getEqui() {
		return equi;
	}



	public void setEqui(String equi) {
		this.equi = equi;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public int getId_restaurante() {
		return id_restaurante;
	}

	public void setId_restaurante(int id_restaurante) {
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
