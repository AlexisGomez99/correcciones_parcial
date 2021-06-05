package ar.edu.unrn.ui;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.modelo.Combustible;
import ar.edu.unrn.modelo.Comun;
import ar.edu.unrn.modelo.Observer;
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
import java.util.List;
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
	private JTextField textAsunto;
	private JTextField textTextEmail;
	private JTextField textEmail;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	public PantallaCargarCombustible(RepositorioVentas api, List<Observer> observable) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBox = new JComboBox();
		comboBox.setBounds(10, 133, 73, 22);
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
							venta= new Venta(new Comun("Comun"),textCantidadLitros.getText(),LocalDateTime.now(),observable,textEmail.getText(),textAsunto.getText(),textTextEmail.getText());
							}
						else {
							venta= new Venta(new Super("Super"),textCantidadLitros.getText(),LocalDateTime.now(),observable,textEmail.getText(),textAsunto.getText(),textTextEmail.getText());
						}
						JOptionPane.showMessageDialog(null,"El valor total es: " + venta.calcularTotal());
					} catch (NotNullException | DataEmptyException | NotNumbreException e1) {
						e1.printStackTrace();
					}
					api.registrarResumen("El valor total es: "+ venta.calcularTotal()+"\n");
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnCalcular.setBounds(10, 195, 147, 23);
		contentPane.add(btnCalcular);
		
		textCantidadLitros = new JTextField();
		textCantidadLitros.setBounds(10, 65, 73, 20);
		contentPane.add(textCantidadLitros);
		textCantidadLitros.setColumns(10);
		
		btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venta venta= null;
				try {
					if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Comun")) {
						venta= new Venta(new Comun("Comun"),textCantidadLitros.getText(),LocalDateTime.now(),observable,textEmail.getText(),textAsunto.getText(),textTextEmail.getText());
						api.agregarVenta(venta);
					}
					else {
						venta= new Venta(new Super("Super"),textCantidadLitros.getText(),LocalDateTime.now(),observable,textEmail.getText(),textAsunto.getText(),textTextEmail.getText());
						api.agregarVenta(venta);
					}
					venta.notificar();
					api.registrarVenta("El valor total es: "+ venta.calcularTotal()+ " el dia : "+ LocalDate.now()+"\n");
					JOptionPane.showMessageDialog(null,"Se registro una venta.");
				} catch (RuntimeException | DataEmptyException | NotNumbreException | NotNullException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCargar.setBounds(298, 227, 136, 23);
		contentPane.add(btnCargar);
		
		textAsunto = new JTextField();
		textAsunto.setBounds(171, 65, 251, 20);
		contentPane.add(textAsunto);
		textAsunto.setColumns(10);
		
		textTextEmail = new JTextField();
		textTextEmail.setBounds(171, 109, 251, 71);
		contentPane.add(textTextEmail);
		textTextEmail.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(171, 31, 251, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(111, 34, 46, 22);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Asunto:");
		lblNewLabel_3.setBounds(111, 68, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Texto:");
		lblNewLabel_4.setBounds(111, 137, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		
	}
}
