package puertos.entidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Información de un barco que llega a un puerto,
 * y del que se desea conocer su capacidad de carga
 * @version 3.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Barco {
	@Id
	private String matricula;
	private String nacionalidad;
	private double volumen;

	/**
	 * @param matricula	el número de matrícula del barco, que lo identifica
	 * @param nacionalidad	la nacionalidad del barco (dada por el país de origen)
	 * @param volumen	el espacio total del barco, en m3
	 */
	public Barco(String matricula, String nacionalidad, double volumen) {
		this.matricula = matricula;
		this.nacionalidad = nacionalidad;
		this.volumen = volumen;
	}
	
	public Barco() {}
	
	public String getMatricula() {
		return this.matricula;
	}

	public double getVolumen() {
		return this.volumen;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	
	/**
	 * Calcula la capacidad de carga del barco.
	 * @return	La capacidad de carga, en metros cúbicos
	 */
	public abstract double calcularCapacidad();
	
	/**
	 * Permite consultar el objeto, pero obtiendo los datos
	 *  como cadena (en formato JSON).
	 *  Para esto se usa la librería Jackson
	 * @return Toda la información del barco, en formato JSON
	 */
	public String obtenerDatosJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		String datosEnCadena;
		try {
			datosEnCadena = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "{\"matricula\":\""+matricula+"\""
					+ ",\"nacionalidad\":\""+nacionalidad+"\""
					+ ",\"volumen\":\""+volumen+"\"}";
		}
		return datosEnCadena;
	}
}
