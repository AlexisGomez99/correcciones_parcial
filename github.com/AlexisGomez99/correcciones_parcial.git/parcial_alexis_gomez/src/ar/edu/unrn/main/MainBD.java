package ar.edu.unrn.main;

import java.awt.EventQueue;
import java.util.List;

import ar.edu.unrn.persistencia.PersistenciaVentasBD;
import ar.edu.unrn.ui.EnviarEmail;
import ar.edu.unrn.ui.PantallaPrincipal;

public class MainBD {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Main que se utiliza una base de datos para guardar.
					PantallaPrincipal frame = new PantallaPrincipal(new PersistenciaVentasBD(),List.of(new EnviarEmail()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
