package ar.edu.unrn.test;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import ar.edu.unrn.modelo.RepositorioVentas;
import ar.edu.unrn.modelo.Super;
import ar.edu.unrn.modelo.Venta;
import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;
import ar.edu.unrn.persistencia.PersistenciaVentasBD;

public class PersistenciaVentasBDTest {
	@Test
	public void verificarQueSeAgregoLosDatosAUnaBaseDeDatos() throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {
		//setup
		RepositorioVentas api= new PersistenciaVentasBD();
		LocalDateTime diaSabado= LocalDateTime.of(2021, 5, 8,0,0);
		Super combustible = new Super("Super");
		Venta venta = new Venta(combustible,"20",diaSabado,null,null,null,null);
		//exercise
		assertTrue("No hay coneccion a base de datos.",api.agregarVenta(venta));
		
	}
}
