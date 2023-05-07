package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

import Persistencia.Hotel;

public class Factura {
	private int id;
	private String tipo;
	private String tipoServ;
	private Double precioTotal = 0.0;
	private HashMap<String, Double> preciosHuespedes = new HashMap<String, Double>();
	private HashMap<Integer, Double> preciosHabs = new HashMap<Integer, Double>();
	private HashMap<String, Double> preciosHabitacionales = new HashMap<String, Double>();
	private String documentoHuesped = null;
	private Double precioTotalGrupal = 0.0;
	
	
	
	public Factura(ReservaEstadia reserva, int id)
	{
		if (id == 0)
		{
			this.id = Hotel.contadorFacturas();
		}else
		{
			this.id = id;
		}
		
		this.tipo = "reserva";
		this.documentoHuesped = reserva.getHuespedPrincipal().getDocumento();
		for (Habitacion hab : reserva.getHabitaciones())
		{
			preciosHabs.put(hab.getId(), hab.getTarifa());
			precioTotal += hab.getTarifa();
		}
		for (Consumo consumo : reserva.getConsumos())
		{
			if (consumo.getTipo().equals("individual"))
			{
				if (preciosHuespedes.containsKey(consumo.getHuesped().getNombre()))
				{
					preciosHuespedes.put(consumo.getHuesped().getNombre(), preciosHuespedes.get(consumo.getHuesped().getNombre()) + (double) consumo.getPrecioTotal());
				}else {
					preciosHuespedes.put(consumo.getHuesped().getNombre(), (double) consumo.getPrecioTotal());
				}
			}else if (consumo.getTipo().equals("grupo"))
			{
				precioTotalGrupal += (double) consumo.getPrecioTotal();
			}else if (consumo.getTipo().equals("habitacion"))
			{
				if (preciosHabitacionales.containsKey(consumo.getHuesped().getNombre()))
				{
					preciosHabitacionales.put(consumo.getHuesped().getNombre(), preciosHabitacionales.get(consumo.getHuesped().getNombre()) + (double) consumo.getPrecioTotal());
				}else {
					preciosHabitacionales.put(consumo.getHuesped().getNombre(), (double) consumo.getPrecioTotal());
				}
			}
			precioTotal += (double) consumo.getPrecioTotal();
		}
	}
	
	public Factura(Consumo consumo, int id)
	{
		if (id == 0)
		{
			this.id = Hotel.contadorFacturas();
		}else
		{
			this.id = id;
		}
		this.tipo = "consumo";
		this.tipoServ = consumo.getServicio().getName();
		this.precioTotal = (double) consumo.getPrecioTotal();
		this.documentoHuesped = consumo.getHuesped().getDocumento();
	}
	
	public String guardarStringConsumo() {
		//Format: facturaID;facturaType;precioTotal;documentoHuesped;tipoServicio
		
		StringBuilder facturaBuilder = new StringBuilder();
		
		facturaBuilder.append("facturaID=").append(this.id).append(";");
	    facturaBuilder.append("facturaTipo=").append(this.tipo).append(";");
	    facturaBuilder.append("documentoHuesped=").append(this.documentoHuesped).append(";");
	    facturaBuilder.append("precioTotal=").append(this.precioTotal).append(";");
	    facturaBuilder.append("tipoServicio=").append(this.tipoServ);
	    
	    return facturaBuilder.toString();
	}
	
	
	public String guardarStringReserva() {
		//Format: facturaID;facturaType;documentoHuespPrinc;precioTotal;room1ID:room1Tarifa,room2ID:room2Tarifa,...;
		//        NombreHuesped1:precioTotHuesp1,NombreHuesped2:precioTotHuesp2,...;
		//        NombreHuesp1:precioTotHab,NombreHuesp2:precioTotHab,...;precioTotGrup
		
	    StringBuilder facturaBuilder = new StringBuilder();

	    facturaBuilder.append("facturaID=").append(this.id).append(";");
	    facturaBuilder.append("facturaTipo=").append(this.tipo).append(";");
	    facturaBuilder.append("huespedPrincipalDocumento=").append(this.documentoHuesped).append(";");
	    facturaBuilder.append("precioTotal=").append(this.precioTotal).append(";");

	    facturaBuilder.append("habitaciones(id:precio)=");
	    if (this.preciosHabs.size() == 0)
	    {
	    	facturaBuilder.append("null");
	    }else {
	    	for (Integer habitacionId : this.preciosHabs.keySet()) {
	        facturaBuilder.append(habitacionId).append(":").append(this.preciosHabs.get(habitacionId)).append(",");
	    	}
	    	facturaBuilder.deleteCharAt(facturaBuilder.length() - 1);
	    }
	    
	    facturaBuilder.append(";");
	    
	    facturaBuilder.append("Consumos Individuales=");
	    if (this.preciosHuespedes.size() == 0)
	    {
	    	facturaBuilder.append("null");
	    }else {
		    for (String nombreHuesped : this.preciosHuespedes.keySet()) {
		        facturaBuilder.append(nombreHuesped).append(":").append(this.preciosHuespedes.get(nombreHuesped)).append(",");
		    }
		    facturaBuilder.deleteCharAt(facturaBuilder.length() - 1);
	    }
	    
	    facturaBuilder.append(";");

	    facturaBuilder.append("Consumos por Habitacion=");
	    if (this.preciosHabitacionales.size() == 0)
	    {
	    	facturaBuilder.append("null");
	    }else {
		    for (String nombreHuesped : this.preciosHabitacionales.keySet()) {
		        facturaBuilder.append(nombreHuesped).append(":").append(this.preciosHabitacionales.get(nombreHuesped)).append(",");
		    }
		    facturaBuilder.deleteCharAt(facturaBuilder.length() - 1);
	    }
	    facturaBuilder.append(";");

	    facturaBuilder.append("precioTotal Consumos Grupales=");
	    facturaBuilder.append(this.precioTotalGrupal).append("\n");

	    return facturaBuilder.toString();
	}
	
}
