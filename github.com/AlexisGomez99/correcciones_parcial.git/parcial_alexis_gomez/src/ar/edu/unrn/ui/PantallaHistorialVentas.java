package ar.edu.unrn.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.modelo.RepositorioVentas;
import ar.edu.unrn.modelo.RepositorioVentasWeb;
import ar.edu.unrn.modelo.Venta;
import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PantallaHistorialVentas extends JFrame {

	private JPanel contentPane;
	private JTable tableVentas;
	private JTextField textDiaInicio;
	private JTextField textMesInicio;
	private JTextField textAnioInicio;
	private JTextField textDiaFin;
	private JTextField textMesFin;
	private JTextField textAnioFin;
	private DefaultTableModel modelo;
	
	public PantallaHistorialVentas(RepositorioVentas api) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] titulos = { "DIA", "COMBUSTIBLE", "LITROS", "TOTAL" };
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(540, 227, 89, 23);
		contentPane.add(btnSalir);
		
		tableVentas = new JTable();
		modelo = new DefaultTableModel(new Object[][] {}, titulos){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableVentas.setBackground(Color.LIGHT_GRAY);
		tableVentas.setBounds(10, 11, 412, 239);
		contentPane.add(tableVentas);
		tableVentas.setModel(modelo);
		tableVentas.setVisible(true);

		modelo.addRow(new Object[] { "DIA", "COMBUSTIBLE", "LITROS", "TOTAL"});
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin;
				try {
					diaInicio= Integer.parseInt(textDiaInicio.getText());
					mesInicio= Integer.parseInt(textMesInicio.getText());
					anioInicio= Integer.parseInt(textAnioInicio.getText());
					
					diaFin= Integer.parseInt(textDiaFin.getText());
					mesFin= Integer.parseInt(textMesFin.getText());
					anioFin= Integer.parseInt(textAnioFin.getText());
					
					LocalDateTime fecha1= LocalDateTime.of(anioInicio, mesInicio, diaInicio,0,0);
					LocalDateTime fecha2= LocalDateTime.of(anioFin, mesFin, diaFin,0,0);
					actualizar(api,titulos,fecha1,fecha2);
				} catch (NotNullException | RuntimeException  e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NotNumbreException e1) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un numero.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (DataEmptyException e1) {
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(432, 227, 98, 23);
		contentPane.add(btnNewButton);
		
		textDiaInicio = new JTextField();
		textDiaInicio.setBounds(432, 61, 44, 20);
		contentPane.add(textDiaInicio);
		textDiaInicio.setColumns(10);
		
		textMesInicio = new JTextField();
		textMesInicio.setBounds(486, 61, 44, 20);
		contentPane.add(textMesInicio);
		textMesInicio.setColumns(10);
		
		textAnioInicio = new JTextField();
		textAnioInicio.setBounds(540, 61, 89, 20);
		contentPane.add(textAnioInicio);
		textAnioInicio.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Desde:");
		lblNewLabel.setBounds(432, 11, 198, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hasta:");
		lblNewLabel_1.setBounds(432, 123, 198, 14);
		contentPane.add(lblNewLabel_1);
		
		textDiaFin = new JTextField();
		textDiaFin.setBounds(432, 181, 44, 20);
		contentPane.add(textDiaFin);
		textDiaFin.setColumns(10);
		
		textMesFin = new JTextField();
		textMesFin.setBounds(486, 181, 44, 20);
		contentPane.add(textMesFin);
		textMesFin.setColumns(10);
		
		textAnioFin = new JTextField();
		textAnioFin.setBounds(540, 181, 89, 20);
		contentPane.add(textAnioFin);
		textAnioFin.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Dia:");
		lblNewLabel_2.setBounds(432, 36, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Dia:");
		lblNewLabel_3.setBounds(432, 148, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mes:");
		lblNewLabel_4.setBounds(486, 36, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Mes:");
		lblNewLabel_5.setBounds(486, 148, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("A\u00F1o:");
		lblNewLabel_6.setBounds(540, 36, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("A\u00F1o:");
		lblNewLabel_7.setBounds(540, 148, 46, 14);
		contentPane.add(lblNewLabel_7);
	}
	
	private void actualizar(RepositorioVentas api, String[] titulos, LocalDateTime fecha1, LocalDateTime fecha2) throws NotNullException, RuntimeException, DataEmptyException, NotNumbreException{
		DefaultTableModel modeloActualizado= new DefaultTableModel(new Object[][] {}, titulos){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addRow(new Object[] { "DIA", "COMBUSTIBLE", "LITROS", "TOTAL"});
		tableVentas.setModel(modelo);
		for (Venta u : api.obtenerVentas()) {
			
			if(fecha1.isBefore(u.fecha()) && fecha2.isAfter(u.fecha()) ) {
				modeloActualizado.addRow(new Object[] { u.fecha(), u.combustible().getClass().getSimpleName(),u.cantidadDeLitros(),u.calcularTotal() });
			
			}	
		}
		
		tableVentas.setModel(modeloActualizado);
		this.contentPane.add(tableVentas);
		
	}
}
