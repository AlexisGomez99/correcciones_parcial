package ar.edu.unrn.modelo;

import java.util.List;

import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;

public interface RepositorioVentas {
	
	boolean agregarVenta(Venta venta) 
			throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException;

	List<Venta> obtenerVentas() throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException;
	
	
	
	void registrarResumen(String resumen);
	
	boolean registrarVenta(String venta);
}
