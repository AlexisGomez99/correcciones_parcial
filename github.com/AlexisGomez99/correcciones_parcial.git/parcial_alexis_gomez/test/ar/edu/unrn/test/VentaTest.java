package ar.edu.unrn.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import ar.edu.unrn.modelo.Combustible;
import ar.edu.unrn.modelo.Comun;
import ar.edu.unrn.modelo.Super;
import ar.edu.unrn.modelo.Venta;
import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;

public class VentaTest {
	@Test
	public void verificarQueNoSeRealizaElDescuentoDeCombustibleComun() throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {//Esta fuera de rango
		//set up
		Venta venta= new Venta(new Comun("Comun"), "20",LocalDateTime.of(2021,5,6,0,0));
		int total= 70*20;
		//exercise
		assertEquals("Fallo porque si hizo el descuento.",total,(int)venta.calcularTotal());
		
		
	}
	
	@Test
	public void verificarSiHaceElDescuentoDelSabadoMayorAVeinteLitros() throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {
		//set up
		LocalDateTime hoy= LocalDateTime.of(2021,5,8,0,0);
		Venta venta= new Venta(new Super("Super"), "20", hoy);
		int total= (int)(90*20)-((90*20)*12/100);
		//exercise
		assertEquals(total,(int)venta.calcularTotal());
		
		
	}
	@Test
	public void verificarSiHaceElDescuentoDelDomingo() throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {
		//set up
		LocalDateTime hoy= LocalDateTime.of(2021,5,9,0,0);
		Venta venta= new Venta(new Super("Super"), "20", hoy);
		int total= (int)(90*20)-((90*20)*15/100);
		//exercise
		assertEquals(total,(int)venta.calcularTotal());
		
		
	}
}
