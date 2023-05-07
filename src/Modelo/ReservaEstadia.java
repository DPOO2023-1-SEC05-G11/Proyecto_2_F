package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaEstadia {

	private ArrayList<Consumo> consumos;
	private ArrayList<Huesped> grupo;
	private ArrayList<Habitacion> habitaciones;
	
	private Boolean checked_in;
	
	private Huesped huespedPrincipal;
	
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private int duracionEstadia;
	
	public ReservaEstadia(LocalDate fecha, int duracionEstadia, Huesped huespedPrincipal, ArrayList<Habitacion> habitacionesReservadas) {
		this.fechaInicial = fecha;
		this.duracionEstadia = duracionEstadia;
		this.huespedPrincipal = huespedPrincipal;
		this.habitaciones = habitacionesReservadas;
		this.fechaFinal = fechaInicial.plusDays(duracionEstadia-1);
		this.checked_in = false;
		
		//Agregar todas las fechas en las listas de fechasocupadas.
		for (Habitacion habitacion : habitaciones) {
			for (LocalDate date : getFechasOcupadas())
			{
				habitacion.addFechaOcupada(date);
			}
	    }
		
	}
	

	public void checkIn(ArrayList<Huesped> huespedes)
	{
		this.grupo = huespedes;
		this.consumos = new ArrayList<>();
		//for habs, hab-->ocupado
		for (Habitacion hab : habitaciones)
		{
			if (hab.isOcupado())
			{
				System.out.println("There is a room in this reservation that is already occupied.");
			}
			//Ocupa las habitaciones de la reserva
			hab.updateOcupado(true);
			hab.setReservaActual(this);
		}
		this.checked_in = true;
	}
	
	public void checkOut()
	{
		//for habs, hab-->no ocupado
		for (Habitacion hab : habitaciones)
		{
			hab.updateOcupado(false);
			hab.setReservaActual(null);
		}
	}
	
	public void cancelarReserva()
	{
		for (Habitacion habitacion : habitaciones) {
			for (LocalDate date : getFechasOcupadas())
			{
				habitacion.removeFechaOcupada(date);
			}
	    }
	}
	
	public ArrayList<Consumo> getConsumos()
	{
		return this.consumos;
	}
	
	public void setConsumos(ArrayList<Consumo> consumos)
	{
		this.consumos = consumos;
	}
	
	public void addConsumo(Consumo consumo)
	{
		consumos.add(consumo);
	}
	
	public Huesped getHuespedPrincipal()
	{
		return this.huespedPrincipal;
	}

	public ArrayList<Huesped> getHuespedes()
	{
		return this.grupo;
	}
	
	public LocalDate getFechaInicial()
	{
		return this.fechaInicial;
	}
	
	public LocalDate getFechaFinal()
	{
		return this.fechaFinal;
	}
	
	public int getDuracion()
	{
		return this.duracionEstadia;
	}
	
	public ArrayList<Habitacion> getHabitaciones()
	{
		return this.habitaciones;
	}
	
	public Boolean isCheckedIn()
	{
		return this.checked_in;
	}
	
	private List<LocalDate> getFechasOcupadas() {
	    List<LocalDate> fechasOcupadas = new ArrayList<>();
	    LocalDate currentDate = fechaInicial;
	    while (!currentDate.isAfter(fechaFinal)) {
	        fechasOcupadas.add(currentDate);
	        currentDate = currentDate.plusDays(1);
	    }
	    return fechasOcupadas;
	}
	
	 @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Fecha Inicial: ").append(fechaInicial).append("\n");
	        sb.append("Fecha Final: ").append(fechaFinal).append("\n");
	        sb.append("Duración Estadia: ").append(duracionEstadia).append(" días").append("\n");
	        sb.append("Huesped Principal: ").append(huespedPrincipal).append("\n");
	        sb.append("Grupo de Huespedes:\n");
	        for (Huesped huesped : grupo) {
	            sb.append(huesped).append("\n");
	        }
	        sb.append("Habitaciones Reservadas:\n");
	        for (Habitacion habitacion : habitaciones) {
	            sb.append(habitacion).append("\n");
	        }
	        sb.append("Consumos:\n");
	        for (Consumo consumo : consumos) {
	            sb.append(consumo).append("\n");
	        }
	        return sb.toString();
	    }
	
	
}
