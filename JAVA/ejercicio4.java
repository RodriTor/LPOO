import java.util.Scanner;

public class ejercicio4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero;
		
		System.out.println("Ingrese un numero: ");
	    numero = scanner.nextInt(); // Lee el número hasta donde se quiere sumar
	    
	    int SumaPares = 0; // Acumulador para la suma de pares
	    int SumaImpares = 0; // Acumulador para la suma de impares
	    
	    for(int i = 1; i <= numero; i++) { // Recorre desde 1 hasta el número ingresado
	    	if(i % 2 == 0) { // Si el número es par
	    		SumaPares += i; // Lo suma al acumulador de pares
	    	}
	    	else { // Si es impar
	    		SumaImpares += i; // Lo suma al acumulador de impares
	    	}
	    }
	    
	    // Muestra los resultados finales de las sumas
	    System.out.println("La suma de numero Pares de 1 hasta " + numero + " : " + SumaPares);
	    System.out.println("La suma de numero Impares de 1 hasta " + numero + " : " + SumaImpares);
	    
	    scanner.close(); // Cierra el objeto scanner
	}
}

