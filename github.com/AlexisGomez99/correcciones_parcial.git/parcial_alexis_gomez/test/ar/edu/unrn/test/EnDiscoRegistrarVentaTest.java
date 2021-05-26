package ar.edu.unrn.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unrn.modelo.RepositorioVentas;
import ar.edu.unrn.persistencia.EnDiscoRegistrarVenta;

public class EnDiscoRegistrarVentaTest {

	@Test
	public void verificarQueSeAgregoLosDatosAUnArchivo() {
		//setup
		RepositorioVentas api= new EnDiscoRegistrarVenta("C:\\Users\\Alexis\\Desktop\\Ventas.txt");
		String venta= "2021-05-08|Comun|33| $2310.0 ... Venta.\n";
		//exercise
		assertTrue(api.registrarVenta(venta));
		
	}
}
