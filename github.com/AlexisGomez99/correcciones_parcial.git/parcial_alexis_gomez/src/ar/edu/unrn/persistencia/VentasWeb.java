package ar.edu.unrn.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import ar.edu.unrn.modelo.Comun;
import ar.edu.unrn.modelo.RepositorioVentasWeb;
import ar.edu.unrn.modelo.Super;
import ar.edu.unrn.modelo.Venta;
import ar.edu.unrn.modeloexceptions.DataEmptyException;
import ar.edu.unrn.modeloexceptions.NotNullException;
import ar.edu.unrn.modeloexceptions.NotNumbreException;



public class VentasWeb implements RepositorioVentasWeb{

	@Override
	public void registrarVenta(Venta venta) {
		Connection myConexion;
		try {
			myConexion = ConexionBD.conexion();
			Statement sent;
			sent= myConexion.createStatement();
			int id=0;
			String combustible= venta.combustible().getClass().getSimpleName();
			String fecha2 = venta.fecha().toString();
			String cantidadDeLitros= ""+venta.cantidadDeLitros();
			sent.executeUpdate("INSERT INTO `venta`(`IDVenta`,`Total`, `Combustible`, `Fecha`, `CantidadLitros`,`Descuento` ) "
					+ "VALUES ("+id+","+venta.calcularTotal()+",'"+combustible+"','"+fecha2+"','"+cantidadDeLitros+"',"+venta.descuento()+")");
			
			sent.close();
			myConexion.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new RuntimeException("Surgio un problema con la base de datos.", e);
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new RuntimeException("No hay conexion.", e);
		}
	}

	@Override
	public List<Venta> obtenerVentas() throws RuntimeException, NotNullException, DataEmptyException, NotNumbreException {
		Connection myConexion;
		List<Venta> ventas= new ArrayList<>();
		try {
			myConexion = ConexionBD.conexion();
			Statement sent;
			sent= myConexion.createStatement();
			ResultSet resul= sent.executeQuery("Select * from venta v");
			while(resul.next()) {
				LocalDateTime fecha= LocalDateTime.parse(resul.getString("v.Fecha").toString());
				LocalDateTime fecha2= LocalDateTime.of(fecha.getYear(), fecha.getMonthValue(), fecha.getDayOfMonth(), fecha.getHour(),fecha.getMinute());
				System.out.println(fecha2.toString());
				if(resul.getString("v.Combustible").equalsIgnoreCase("Comun")) {
					ventas.add(new Venta(new Comun(resul.getString("v.Combustible")), resul.getString("v.CantidadLitros"),fecha2,null,null,null,null));
				}
				else
				{
					ventas.add(new Venta(new Super(resul.getString("v.Combustible")), resul.getString("v.CantidadLitros"),fecha2,null,null,null,null));
				}
			}
			resul.close();
			sent.close();
			myConexion.close();
			return ventas;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ventas;
		
		
		
	}

}
