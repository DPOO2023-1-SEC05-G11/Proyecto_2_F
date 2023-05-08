package Modelo;

public class Huesped {
	
	private String nombre;
	private String documento;
	private String email;
	private String telefono;
	
	public Huesped(String nombre, String documento, String email, String telefono) {
		//super();
		this.nombre = nombre;
		this.documento = documento;
		this.email = email;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public String guardarString() {
		return nombre+"-"+documento+"-"+email+"-"+telefono;
	}
	
	@Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Documento: " + documento +
                ", Email: " + email +
                ", Teléfono: " + telefono;
    }
	
}

