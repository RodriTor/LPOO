
public class ClasePrimaria {

	public static void main(String[] args) {
		for(int i=1; i<=100; i++) {			
			if(i % 2 == 0) {
				System.out.println("PAR ");
			}
			else {
				System.out.println( i);
			}
			
		}
	}
}

classDiagram
    class Libro {
        -String titulo
        -String autor
        -String isbn
        +void mostrarInformacion()
    }

    class Socio {
        -String nombre
        -String direccion
        -String numeroSocio
        +void mostrarInformacion()
    }

    class Prestamo {
        -Date fechaPrestamo
        -Date fechaDevolucion
        +void registrarPrestamo()
        +void registrarDevolucion()
    }

    Socio "1" -- "0..*" Prestamo : realiza
    Libro "1" -- "0..1" Prestamo : es prestado
