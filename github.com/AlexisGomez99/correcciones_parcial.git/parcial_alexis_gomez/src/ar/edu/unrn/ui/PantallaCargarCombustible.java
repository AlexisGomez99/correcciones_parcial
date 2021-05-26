package ar.edu.unrn.ui;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.modelo.Combustible;
import ar.edu.unrn.modelo.Comun;
import ar.edu.unrn.modelo.RepositorioVentas;
import ar.edu.unrn.modelo.Super;
import ar.edu.unrn.modelo.Venta;
import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class PantallaCargarCombustible extends JFrame {

	private JPanel contentPane;
	private JTextField textCantidadLitros;
	private JComboBox comboBox;
	private float total=0;
	private JButton btnCargar;
	private JLabel lblLitros;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public PantallaCargarCombustible(RepositorioVentas api) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBox = new JComboBox();
		comboBox.setBounds(10, 133, 232, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Comun");
		comboBox.addItem("Super");
		
		lblLitros = new JLabel("Litros:");
		lblLitros.setBounds(10, 38, 46, 14);
		contentPane.add(lblLitros);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(10, 227, 147, 23);
		contentPane.add(btnCancelar);
		
		JButton btnCalcular = new JButton("Calcular total");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venta venta=null;;
				try {
					try {
						if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Comun")) {
							venta= new Venta(new Comun("Comun"),textCantidadLitros.getText(),LocalDateTime.now());
							}
						else {
							venta= new Venta(new Super("Super"),textCantidadLitros.getText(),LocalDateTime.now());
						}
						JOptionPane.showMessageDialog(null,"El valor total es: " + venta.calcularTotal());
					} catch (NotNullException | DataEmptyException | NotNumbreException e1) {
						e1.printStackTrace();
					}
					api.registrarResumen("El valor total es: "+ venta.calcularTotal());
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnCalcular.setBounds(10, 195, 147, 23);
		contentPane.add(btnCalcular);
		
		textCantidadLitros = new JTextField();
		textCantidadLitros.setBounds(10, 65, 232, 20);
		contentPane.add(textCantidadLitros);
		textCantidadLitros.setColumns(10);
		
		btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venta venta= null;
				try {
					if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Comun")) {
						venta= new Venta(new Comun("Comun"),textCantidadLitros.getText(),LocalDateTime.now());
						api.agregarVenta(venta);
					}
					else {
						venta= new Venta(new Super("Super"),textCantidadLitros.getText(),LocalDateTime.now());
						api.agregarVenta(venta);
					}
					api.registrarVenta("El valor total es: "+ venta.calcularTotal()+ " el dia : "+ LocalDate.now()+"\n");
					JOptionPane.showMessageDialog(null,"Se registro una venta.");
				} catch (RuntimeException | DataEmptyException | NotNumbreException | NotNullException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCargar.setBounds(298, 227, 136, 23);
		contentPane.add(btnCargar);
		
		
	}
}
