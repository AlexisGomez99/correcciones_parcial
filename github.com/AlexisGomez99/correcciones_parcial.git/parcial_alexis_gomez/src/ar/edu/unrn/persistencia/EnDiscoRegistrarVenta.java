package ar.edu.unrn.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

import ar.edu.unrn.modelo.Combustible;
import ar.edu.unrn.modelo.RepositorioVentas;
import ar.edu.unrn.modelo.Venta;
import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;


public class EnDiscoRegistrarVenta implements RepositorioVentas{
	private String path;
	public EnDiscoRegistrarVenta(String path) {
		this.path=path;
	}
	@Override
	public boolean registrarVenta(String venta) {
		boolean x=false;
		verificar();
		try {
			Files.write(Paths.get(path), venta.getBytes(),
					StandardOpenOption.APPEND);
			x=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public void registrarResumen(String resumen) {
		verificar();
		try {
			Files.write(Paths.get(path), resumen.getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void verificar() {
		File file = new File(path);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException("Surgio un error al buscar el fichero...", e);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	@Override
	public boolean agregarVenta(Venta venta){
		return false;
	}

	@Override
	public List<Venta> obtenerVentas()
			throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {
		return null;
	}

}
