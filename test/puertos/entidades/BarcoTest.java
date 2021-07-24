package puertos.entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pruebas al método de obtener datos como cadena JSon
 */
class BarcoTest {

	@Test
	void testObtenerDatosJsonVelero() {
		Barco velero = new Velero("vel-878","italiana",150,10);
		String datosJson = velero.obtenerDatosJson();
		String jsonEsperado = "{\"matricula\":\"vel-878\","
				+ "\"nacionalidad\":\"italiana\","
				+ "\"volumen\":150.0,"
				+ "\"pasajeros\":10,"
				+ "\"tipo\":\"velero\"}";
		assertEquals(jsonEsperado, datosJson);
	}
	
	@Test
	void testObtenerDatosJsonCarguero() {
		Barco carguero = new Carguero("car-225","rusa",800,true);
		String datosJson = carguero.obtenerDatosJson();
		String jsonEsperado = "{\"matricula\":\"car-225\","
				+ "\"nacionalidad\":\"rusa\","
				+ "\"volumen\":800.0,"
				+ "\"liquidos\":true,"
				+ "\"tipo\":\"carguero\"}";
		assertEquals(jsonEsperado, datosJson);
	}

}
