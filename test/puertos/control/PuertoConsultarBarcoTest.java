package puertos.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import puertos.persistencia.ListaBarcos;

/**
 * Pruebas del método consultarDatosBarco
 */
class PuertoConsultarBarcoTest {

	/**
	 * Cuando no encuentra el barco
	 */
	@Test
	void testConsultarNoRegistrado() {
		Puerto puerto = new Puerto(new ListaBarcos());
		String datosBarco = puerto.consultarDatosBarco("xyz");
		assertNull(datosBarco);
	}

	/**
	 * Consultar los datos de un velero registrado
	 */
	@Test
	void testConsultarVelero() throws BarcoException  {
		Puerto puerto = new Puerto(new ListaBarcos());
		puerto.adicionarBarco("Vel-001", "colombiana", 100, 'v', 8, false);
		puerto.adicionarBarco("Vel-002", "chilena", 150, 'v', 15, false);
		puerto.adicionarBarco("Car-001", "peruana", 500, 'c', 15, true);
		puerto.adicionarBarco("Car-002", "mexicano", 250, 'c', 25, false);
		String datosJson = puerto.consultarDatosBarco("Vel-002");
		String jsonEsperado = "{\"matricula\":\"Vel-002\","
				+ "\"nacionalidad\":\"chilena\","
				+ "\"volumen\":150.0,"
				+ "\"pasajeros\":15,"
				+ "\"tipo\":\"velero\"}";
		assertEquals(jsonEsperado, datosJson);
	}

	/**
	 * Consultar los datos de un carguero registrado
	 */
	@Test
	void testConsultarCarguero() throws BarcoException  {
		Puerto puerto = new Puerto(new ListaBarcos());
		puerto.adicionarBarco("Vel-001", "colombiana", 100, 'v', 8, false);
		puerto.adicionarBarco("Vel-002", "chilena", 150, 'v', 15, false);
		puerto.adicionarBarco("Car-001", "peruana", 500, 'c', 15, false);
		puerto.adicionarBarco("Car-002", "mexicano", 250, 'c', 25, true);
		String datosJson = puerto.consultarDatosBarco("Car-001");
		String jsonEsperado = "{\"matricula\":\"Car-001\","
				+ "\"nacionalidad\":\"peruana\","
				+ "\"volumen\":500.0,"
				+ "\"liquidos\":false,"
				+ "\"tipo\":\"carguero\"}";
		assertEquals(jsonEsperado, datosJson);
	}
	

}
