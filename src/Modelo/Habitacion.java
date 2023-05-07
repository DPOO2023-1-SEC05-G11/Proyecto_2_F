package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Habitacion {
	
	private int id;
	private String tipo;
	private boolean balcon;
	private boolean vista;
	private boolean cocina;
	private ArrayList<Cama> camas;
	private Double tarifa;
	private boolean ocupado;
	private int espacioAdultos;
	private int espacioNinos;
	
	
	private ReservaEstadia reservaActual = null;
	
	private ArrayList<LocalDate> fechasOcupadas = new ArrayList<LocalDate>();
	
	/**
	 * @param id
	 * @param tipo
	 * @param balcon
	 * @param vista
	 * @param cocina
	 * @param camas
	 * @param tarifa
	 * @param ocupada
	 */
	public Habitacion(int id, String tipo, Boolean balcon, Boolean vista, Boolean cocina, ArrayList<Cama> camas, Double tarifa, Boolean ocupada) {

		this.id = id;
		this.tipo = tipo;
		this.balcon = balcon;
		this.vista = vista;
		this.cocina = cocina;
		this.camas = camas;
		this.tarifa = tarifa;
		this.ocupado = ocupada;
		
		this.espacioAdultos = espacioAdultos();
		this.espacioNinos = espacioNinos();
	}

	public int getId(){
		return this.id;
	}

	public String getTipo()
	{
		return this.tipo;
	}

	public Boolean tieneBalcon(){
		return this.balcon;
	}
	
	public Boolean tieneVista(){
		return this.vista;
	}
	
	public Boolean tieneCocina(){
		return this.cocina;
	}
	
	public ArrayList<Cama> getCamas(){
		return this.camas;
	}
	
	public Double getTarifa()
	{
		return this.tarifa;
	}

	public Boolean isOcupado()
	{
		return this.ocupado;
	}
	public void updateOcupado(boolean b) {
		this.ocupado = b;
	}

	public void updateTarifa(Double nuevaTarifa) 
	{
		this.tarifa = nuevaTarifa;
	}
	
	public ReservaEstadia getReservaActual()
	{
		return this.reservaActual;
	}
	
	public void setReservaActual(ReservaEstadia reserva)
	{
		this.reservaActual = reserva;
	}
	
	public int getEspacioAdultos()
	{
		return this.espacioAdultos;
	}
	
	public int getEspacioNinos()
	{
		return this.espacioNinos;
	}
	
	public Boolean libreEntre(LocalDate fechaInicial, int duracion) {
		LocalDate fechaFinal = fechaInicial.plusDays(duracion-1);
	    for (LocalDate fechaOcupada : fechasOcupadas) {
	        if (fechaOcupada.compareTo(fechaInicial) >= 0 && fechaOcupada.compareTo(fechaFinal) <= 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public void addFechaOcupada(LocalDate fecha)
	{
		this.fechasOcupadas.add(fecha);
	}
	
	public void removeFechaOcupada(LocalDate fecha)
	{
		this.fechasOcupadas.remove(fecha);
	}
	
	public int espacioTotal()
	{
		int total = 0;
		for (Cama cama : camas)
		{
			total += cama.getTamano();
		}
		return total;
	}
	
	public int espacioAdultos()
	{
		int total = 0;
		for (Cama cama : camas)
		{
			if (!cama.isParaNinos()) {
				total += cama.getTamano();
			}
		}
		return total;
	}
	
	public int espacioNinos()
	{
		int total = 0;
		for (Cama cama : camas)
		{
			if (cama.isParaNinos()) {
				total += cama.getTamano();
			}
		}
		return total;
	}
	
	//Metodos para sacar infos de las camas de la habitacion, si lo hacemos con la clase cama
	
	
	public String toString() {
	    String camaString = "";

	    for (int i = 0; i < 3; i++) {
	        if (i < camas.size()) {
	            camaString += "Cama " + (i + 1) + ": " + camas.get(i) + ", ";
	        }
	    }
	    return "Habitacion [id=" + id + ", tipo=" + tipo + ", balcon=" + balcon + 
	    		", vista=" + vista + ", cocina=" + cocina + ", espacio adultos="+espacioAdultos+", espacio ninos="+espacioNinos
	    		+", camas=" + camaString + "tarifa=" + tarifa + ", ocupado=" + ocupado + "]";
	}
	
}
