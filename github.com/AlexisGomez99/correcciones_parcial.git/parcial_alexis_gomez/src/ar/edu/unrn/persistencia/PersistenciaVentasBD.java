package ar.edu.unrn.persistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.modelo.Combustible;
import ar.edu.unrn.modelo.RepositorioVentas;
import ar.edu.unrn.modelo.Venta;
import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;


public class PersistenciaVentasBD implements RepositorioVentas{
	private VentasWeb ventas= new VentasWeb();
	@Override
	public boolean agregarVenta(Venta venta)
			throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {
		boolean x=false;
		ventas.registrarVenta(venta);
		x=true;
		return x;
	}

	@Override
	public List<Venta> obtenerVentas() throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {

		return ventas.obtenerVentas();
	}

	@Override
	public void registrarResumen(String resumen) {
	
	}

	@Override
	public boolean registrarVenta(String venta) {
		return false;
	}
	
	

}
