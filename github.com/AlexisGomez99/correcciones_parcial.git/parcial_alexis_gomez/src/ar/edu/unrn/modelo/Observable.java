package ar.edu.unrn.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

	private List<Observer> observadores;
	
	public Observable() {
		observadores = new ArrayList<>();
	}
	
	public void agregarObservador(Observer obs) {
		this.observadores.add(obs);
	}
	
	protected void notificar(String email, String asunto, String textoEmail) {
		for (Observer observer : observadores) {
			observer.enviarEmail(email,asunto,textoEmail);
		}
	}
}