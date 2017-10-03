package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PlatoFuerte extends Producto{

	public PlatoFuerte(@JsonProperty(value="cantidad")int id,
			@JsonProperty(value="id_restaurante")Long id_restaurante,
			@JsonProperty(value="descripcion")String descripcion,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="precio")double precio) {
		super(id, id_restaurante, descripcion, nombre, precio);
		// TODO Auto-generated constructor stub
	}

	


}
