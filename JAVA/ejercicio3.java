import java.util.Scanner; // Importa la clase Scanner para leer datos del teclado

public class ejercicio3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // Crea el objeto scanner para entrada por teclado
		int numero; // Variable para almacenar el número ingresado
		int divisores; // Contador de divisores encontrados
		
		do { // Bucle que se repite hasta que se cumpla la condición final
			System.out.println("Ingrese un numero: ");
		    numero = scanner.nextInt(); // Lee el número ingresado
			divisores = 0; // Reinicia el contador de divisores

		    if(numero > 100) { // Solo busca divisores si el número es mayor a 100
		    	for(int i = 1; i < numero / 2; i++) { // Busca divisores desde 1 hasta la mitad del número
		    		if(numero % i == 0) { // Si es divisor
		    			divisores++; // Aumenta el contador de divisores
		    		}
		    	}
		    }
		
		} while(numero < 100 || divisores > 2); // Repite si el número es menor a 100 o tiene más de 2 divisores
		
		System.out.println(numero + " Es mayor que 100 y es primo"); // Mensaje final cuando se cumple la condición
	}
}
