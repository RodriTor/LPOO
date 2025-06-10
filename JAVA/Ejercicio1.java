
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

classDiagram
    class Cliente {
        -String idCliente
        -String nombre
        -String email
        -String direccion
        +void actualizarInformacion()
    }

    class Producto {
        -String idProducto
        -String nombre
        -String descripcion
        -double precio
        +void mostrarDetalles()
    }

    class Pedido {
        -String idPedido
        -Date fechaPedido
        +double calcularTotal()
        +void agregarDetalle(DetallePedido detalle)
    }

    class DetallePedido {
        -int cantidad
        +double calcularSubtotal()
    }

    Cliente "1" -- "0..*" Pedido : realiza
    Pedido "1" -- "1..*" DetallePedido : contiene
    Producto "1" -- "0..*" DetallePedido : asociado a

classDiagram
    class Empleado {
        <<abstract>>
        -String nombre
        -String id
        -double salario
        +double calcularSalarioAnual()
    }

    class EmpleadoAsalariado {
        -double salarioMensual
        +double calcularSalarioAnual()
    }

    class EmpleadoPorHora {
        -double tarifaPorHora
        -int horasTrabajadas
        +double calcularSalarioAnual()
    }

    Empleado <|-- EmpleadoAsalariado
    Empleado <|-- EmpleadoPorHora
