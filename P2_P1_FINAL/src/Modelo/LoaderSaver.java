package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class LoaderSaver {
	
/////////////////////////////Carga de Habitaciones////////////////////////////////////////

	public static ArrayList<Habitacion> updateHabitaciones(File f) {
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] atributos = line.split(";");		
				int id = Integer.parseInt(atributos[0]);
				String tipo = atributos[1];
				boolean balcon = Boolean.parseBoolean(atributos[2]);
				boolean vista = Boolean.parseBoolean(atributos[3]);
				boolean cocina = Boolean.parseBoolean(atributos[4]);
				
				ArrayList<Cama> camas = new ArrayList<Cama>();
				for (int i=5;i<=7;i++)
				{
					if (Integer.parseInt(atributos[i]) != 0)
					{
						camas.add(new Cama(Integer.parseInt(atributos[i])));
					}
				}
				
				
				Double tarifa = Double.parseDouble(atributos[8]);
				boolean ocupado = Boolean.parseBoolean(atributos[9]);
				
				Habitacion room = new Habitacion(id, tipo, balcon, vista, cocina, camas, tarifa, ocupado);
				habitaciones.add(room);
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return habitaciones;
	}
	
	public static ArrayList<Habitacion> cargarHabitaciones()
	{
		File f = new File("data/habitaciones.txt");
		return updateHabitaciones(f);
	}

	public static void salvarHabitaciones(ArrayList<Habitacion> habitaciones) {
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/habitaciones.txt"));
            for (Habitacion h : habitaciones) {
                String camasString = "";
                List<Cama> camas = h.getCamas();
                
                camasString += camas.size() > 0 ? camas.get(0).stringASalvar() : "0";
                camasString += ";";
                camasString += camas.size() > 1 ? camas.get(1).stringASalvar() : "0";
                camasString += ";";
                camasString += camas.size() > 2 ? camas.get(2).stringASalvar() : "0";

                writer.write(h.getId() + ";" + h.getTipo() + ";" + h.tieneBalcon() + ";" + h.tieneVista() + ";" + h.tieneCocina() + ";" + camasString + ";" + h.getTarifa() + ";" + h.isOcupado() + "\n");
            }

            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}



/////////////////////////////Carga de Servicios////////////////////////////////////////

	

	public static HashMap<String, Integer> updateServicio(File f) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] atributos = line.split(";");
				map.put(atributos[0], Integer.parseInt(atributos[1]));
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		return map;
	}
	
	public static HashMap<String, Integer> cargarServicio(String file)
	{
		File f = new File("data/"+file+".txt");
		return updateServicio(f);
	}

	

	public static void salvarServicio(HashMap<String, Integer> map, String file) {
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/"+file+".txt"));
            for (Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + ";"  + entry.getValue() + "\n");
            }
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}

	public static void removeServicio(String nombreServicio) {
		File f = new File("data/"+nombreServicio+".txt");
		deleteFile(f);
	}
	
	public static void deleteFile(File f) {
	    if (f.delete()) {
	        System.out.println("File deleted successfully.");
	    } else {
	        System.out.println("Failed to delete file.");
	    }
	}	
	
	public static String[] serviciosACargar()
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("data/servicios.txt")))) {
			String line = reader.readLine();
			return line.split(";");
				
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
			return null;
		}		
	}
	
	public static void salvarServiciosDoc(HashMap<String, Servicio> servicios)
	{
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/servicios.txt"));
            for (String servicio : servicios.keySet()) {
                writer.write(servicio + ";");
            }
            writer.close();
            System.out.println("Hotel services saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}
	
	
/////////////////////////////Carga de Reservas////////////////////////////////////////
	
	
	public static ArrayList<ReservaEstadia> cargarReservas(ArrayList<Habitacion> allHabitaciones, HashMap<String, Servicio> allServicios) {
		ArrayList<ReservaEstadia> reservas = new ArrayList<ReservaEstadia>();
		try (BufferedReader reader = new BufferedReader(new FileReader("data/reservas.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] atributos = line.split(";");		
				LocalDate fechaInicio = LocalDate.parse(atributos[0]);//fecha inicial
				int duracion = Integer.parseInt(atributos[1]);//duracion
				
				String[] HPrincipal = atributos[2].split("-");//Huesped principal
				Huesped huespedPrincipal = new Huesped(HPrincipal[0], HPrincipal[1], HPrincipal[2], HPrincipal[3]);
				
				String[] Habs = atributos[3].split(",");//Habitaciones
				ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
				for (String s : Habs)
				{
					for (Habitacion h : allHabitaciones)
					{
						if (h.getId() == Integer.parseInt(s))
						{
							habitaciones.add(h);
							break;
						}
					}
				}
								
				ReservaEstadia reserva = new ReservaEstadia(fechaInicio, duracion, huespedPrincipal, habitaciones);
								
				Boolean isCheckedIn = Boolean.parseBoolean(atributos[4]);//CheckedIn
				
				if (isCheckedIn)
				{
					ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
					String[] Huesps = atributos[5].split(",");//Huespedes
					for (String huesp : Huesps)
					{
						String[] Huesp = huesp.split("-");
						huespedes.add(new Huesped(Huesp[0], Huesp[1], Huesp[2], Huesp[3]));
					}
					reserva.checkIn(huespedes);
					
					if (!atributos[6].equals("null"))
					{
						String[] Consums = atributos[6].split(",");//Consumos
						for (String cons : Consums)
						{
							String[] Cons = cons.split("\\|");
							Servicio servicio = allServicios.get(Cons[0]);
							Boolean yaPagado = Boolean.parseBoolean(Cons[1]);
							String[] HuespCons = Cons[2].split("-");
							Huesped huespedConsumo = new Huesped(HuespCons[0], HuespCons[1], HuespCons[2], HuespCons[3]);
							String huespedPrincipalConsumoDocumento = Cons[3];
							String tipo = Cons[4];
							int precioTotal = Integer.parseInt(Cons[5]);
							
							Consumo consumo = new Consumo(servicio, yaPagado, huespedConsumo, huespedPrincipalConsumoDocumento, tipo);
							consumo.setPrecioTotal(precioTotal);
							
							reserva.addConsumo(consumo);
						}
					}
				}
				
				reservas.add(reserva);
				
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return reservas;
	}
	
	
	public static void salvarReservas(ArrayList<ReservaEstadia> reservas) {
		try {
			//Escribe en la forma fechaInicial;duracion;HuespedPrincipal;h,a,b,i,t,a,c,i,o,n,e,s;checkedInBool;h,u,e,s,p,e,d,e,s;c,o,n,s,u,m,o,s
			// Huespedes en la forma nombre-documento-email-telefono
			// Habitaciones en la forma id. Se buscan dentro de la lista de habitaciones.
			//Consumos en la forma servicio(string)|yaPagado|Huesped|HuespedPrincipal(documento)|tipo|precioTotal
			
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/reservas.txt"));
            
            for (ReservaEstadia reserva : reservas){
            	
            	StringBuilder habitaciones = new StringBuilder();
                for (Habitacion habitacion : reserva.getHabitaciones()) {
                    habitaciones.append(habitacion.getId()).append(",");
                }
                habitaciones.deleteCharAt(habitaciones.length() - 1); 
                
                StringBuilder huespedes = new StringBuilder();
                if (reserva.isCheckedIn()) {
                	for (Huesped huesped : reserva.getHuespedes()) {
                    huespedes.append(huesped.guardarString()).append(",");
                	}
                	huespedes.deleteCharAt(huespedes.length() - 1); 
                }else {
                	huespedes.append("null");
                }
                
                
                StringBuilder consumos = new StringBuilder();
                if (reserva.getConsumos() == null || reserva.getConsumos().size() == 0) {
                	consumos.append("null");
                }else {
                	for (Consumo consumo : reserva.getConsumos()) {
                    consumos.append(consumo.guardarString()).append(",");
                    }
                	consumos.deleteCharAt(consumos.length() - 1);
                }
                
            	
                writer.write(reserva.getFechaInicial() + ";"  + reserva.getDuracion() + ";"  + reserva.getHuespedPrincipal().guardarString() + ";"  +
                habitaciones + ";" + reserva.isCheckedIn() + ";" + huespedes + ";" + consumos + "\n");
            }
            
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}
	
	
/////////////////////////////Carga de Facturas////////////////////////////////////////
	
	public static ArrayList<String> cargarFacturas() {
		ArrayList<String> facturas = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader("data/facturas.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				facturas.add(line);
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		return facturas;
	}
	
	public static void salvarFacturas(ArrayList<String> facturas)
	{
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/facturas.txt"));
            for (String servicio : facturas) {
            	if (! (servicio == null || servicio.equals("")))
            	{
            		writer.write(servicio+"\n");
            	}
                
            }
            writer.close();
            System.out.println("Hotel services saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}
	
}

