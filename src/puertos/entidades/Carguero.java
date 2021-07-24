package puertos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Un barco que transporta carga entre puertos, tiene buena capacidad de carga
 * @version 3.0
 */
@Entity
@DiscriminatorValue("carguero")
public class Carguero extends Barco {
	
	private boolean liquidos;

	/**
	 * @see puertos.entidades.Barco#Barco(String, String, double)
	 * @param liquidos	indicación (true/false) de si puede llevar líquidos o no
	 */
	public Carguero(String matricula, String nacionalidad, double volumen, boolean liquidos) {
		super(matricula, nacionalidad, volumen);
		this.liquidos = liquidos;
	}
	
	public Carguero() {}

	public boolean getLiquidos() {
		return this.liquidos;
	}
	
	public void setLiquidos(boolean liquidos) {
		this.liquidos = liquidos;
	}

	@Override
	public double calcularCapacidad() {
		double capacidad = getVolumen() * 0.8;
		if (this.liquidos) {
			capacidad-=40;
		}
		return (capacidad<0) ? 0 : capacidad;
	}
	
	@Override
	public String obtenerDatosJson() {
		String datosBarco = super.obtenerDatosJson();
		datosBarco = datosBarco.substring(0, datosBarco.length()-1);
		String tipo = ",\"tipo\":\"carguero\"}";
		return datosBarco+tipo;
	}
	
}
