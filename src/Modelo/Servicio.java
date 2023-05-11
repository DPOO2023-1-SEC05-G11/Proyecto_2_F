package Modelo;

import java.util.HashMap;
import java.util.Map.Entry;

public class Servicio {
	
	private HashMap<String, Integer> map;
	private String nombreServicio = "";
	
	
	public Servicio(HashMap<String, Integer> map, String nombreServicio)
	{
		this.map = map;
		this.nombreServicio = nombreServicio;
	}
	
	public void mostrarOpciones() {
	    for (Entry<String, Integer> entry : map.entrySet()) {
	        System.out.println("Nombre: " + entry.getKey() + "| Precio: $COP " + entry.getValue());
	    }
	}
	
	public HashMap<String, Integer> getMap()
	{
		return map;
	}
	
	public void setMap(HashMap<String, Integer> map)
	{
		this.map = map;
	}
	
	public Integer addOpcion(String key, Integer value) {
	    return map.put(key, value);
	}

	public boolean removeOpcion(String key) {
	    if (map.containsKey(key)) {
	        map.remove(key);
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public void setName(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getName() {
		return this.nombreServicio;
	}

	
}
