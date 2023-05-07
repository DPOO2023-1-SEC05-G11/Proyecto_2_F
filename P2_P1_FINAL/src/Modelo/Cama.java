package Modelo;

import java.util.HashMap;
import java.util.Map;

public class Cama {
	private int tamano;//cuantos adultos
	private String tipo;//King/Queen/Doble etc...
	private Boolean paraNinos;//true si es para niños
	
	
	private static final Map<Integer, String> tiposDeCama = new HashMap<Integer, String>() {{
		//de la forma "tipo;tamaño;paraniños"
        put(0, "");
        put(1, "King;2;false");
        put(2, "Queen;2;false");
        put(3, "Doble;2;false");
        put(4, "Sencilla;1;false");
        put(5, "Niños;1;true");
    }};
	
	public Cama(int tipoCama)
	{
		if (tipoCama == 0)
		{
	        return;//la cama debe tomar el valor null si es construido con 0 como parametro
		}
		
		String[] atributosCama = tiposDeCama.get(tipoCama).split(";");
		
		this.tipo = atributosCama[0];
		this.tamano = Integer.parseInt(atributosCama[1]);
		this.paraNinos = Boolean.parseBoolean(atributosCama[2]);
	}
	
	public int getTamano()
	{
		return this.tamano;
	}
	
	public void setTamano(int tamano)
	{
		this.tamano = tamano;
	}
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public Boolean isParaNinos()
	{
		return this.paraNinos;
	}
	
	public String stringASalvar() 
	{
		if (this.tipo == null) {
			return "0";
		}else if (this.tipo.equals("King")){
			return "1";
		}else if (this.tipo.equals("Queen")){
			return "2";
		}else if (this.tipo.equals("Doble")){
			return "3";
		}else if (this.tipo.equals("Sencilla")){
			return "4";
		}else if (this.tipo.equals("Niños")){
			return "5";
		}else {
			return "0";
		}
			
	}
	
	@Override
	public String toString()
	{
		return this.tipo;
	}
}
