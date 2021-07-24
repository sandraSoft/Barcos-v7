package puertos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Un barco deportivo, que lleva pasajeros, no tiene mucha capacida de carga.
 * @version 3.0
 */
@Entity
@DiscriminatorValue("velero")
public class Velero extends Barco {
	private int pasajeros;

	/**
	 * @see puertos.entidades.Barco#Barco(String, String, double)
	 * @param pasajeros	la cantidad de pasajeros que lleva el barco
	 */
	public Velero(String matricula, String nacionalidad, double volumen, int pasajeros) {
		super(matricula, nacionalidad, volumen);
		this.pasajeros = pasajeros;
	}

	public Velero() {}

	public int getPasajeros() {
		return this.pasajeros;
	}

	public void setPasajeros(int pasajeros) {
		this.pasajeros = pasajeros;
	}

	@Override
	public double calcularCapacidad() {
		double capacidad = getVolumen() * 0.5;
		if (this.pasajeros > 10) {
			capacidad-=10;
		}
		return (capacidad<0) ? 0 : capacidad;
	}
	
	@Override
	public String obtenerDatosJson() {
		String datosBarco = super.obtenerDatosJson();
		datosBarco = datosBarco.substring(0, datosBarco.length()-1);
		String tipo = ",\"tipo\":\"velero\"}";
		return datosBarco+tipo;
	}
}
