import java.util.Scanner;

public class ejercicio5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero;
		
		System.out.println("Ingrese un numero (1 al 10): ");
	    numero = scanner.nextInt(); // Lee el número para generar su tabla

	    System.out.println("La tabla de multiplicar del numero " + numero + " es: ");
	    
	    for(int i = 1; i <= 10; i++) { // Recorre los valores del 1 al 10
	    	int multi = numero * i; // Calcula la multiplicación actual
	    	System.out.println(i + "x" + numero + " = " + multi); // Imprime el resultado en formato de tabla
	    }

	    scanner.close(); // Cierra el objeto scanner
	}
}
