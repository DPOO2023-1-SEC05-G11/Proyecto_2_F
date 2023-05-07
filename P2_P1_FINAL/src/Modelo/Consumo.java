package Modelo;

import java.util.Scanner;

public class Consumo {

	private Servicio servicio;
	private int precioTotal = 0;
	private String tipo; //individual/grupal/habitacional
	private Huesped huesped;
	private String huespedPrincipalDocumento;
	private Boolean yaPagado;
	
	public Consumo(Servicio serv, Boolean yaPagado, Huesped huesped, String huespedPrincipalDocumento, String tipo)
	{
		this.servicio = serv;
		this.yaPagado = yaPagado;
		this.huesped = huesped;
		this.huespedPrincipalDocumento = huespedPrincipalDocumento;
		this.tipo = tipo;
	}

	
	public void addProductos() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        Boolean loop = true;
        do {
        	
        	mostrarOpciones();
            userInput = scanner.nextLine();

            if (userInput.equals("0")) {
                loop = false;
            }else {
	            try {
		            precioTotal += servicio.getMap().get(userInput);
		        } catch (NullPointerException e) {
		            System.out.println("Invalid option");
		        }
            }
        } while (loop);
    }
	
	private void mostrarOpciones()
	{
		System.out.println("Aqui estan los productos a agregar:");
        servicio.mostrarOpciones();
        System.out.println("Ingrese el nombre del producto a agregar.");
        System.out.println("Ingrese '0' para no agregar mas productos.");
	}
	
	public Boolean yaPagado()
	{
		return this.yaPagado;
	}
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public Huesped getHuesped()
	{
		return this.huesped;
	}
	
	public String getHuespedPrincipal()
	{
		return this.huespedPrincipalDocumento;
	}
	
	public int getPrecioTotal()
	{
		return this.precioTotal;
	}
	
	public void setPrecioTotal(int precioTotal)
	{
		this.precioTotal = precioTotal;
	}
	
	public Servicio getServicio()
	{
		return this.servicio;
	}



	public String guardarString() {
		return servicio.getName() + "|" + yaPagado + "|" + huesped.guardarString() + "|" + huespedPrincipalDocumento + "|" + tipo + "|" + precioTotal;
	}
	
	@Override
	public String toString()
	{
		return "Name="+servicio.getName() + ", Pagado=" + yaPagado + ", Huesped=" + huesped.guardarString() + ", DocumentoHuespedPrincipal=" + huespedPrincipalDocumento + ", tipo=" + tipo + ", precioTotal=" + precioTotal;
	}

}

