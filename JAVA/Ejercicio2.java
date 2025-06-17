public class Ejercicio2 {
    public static void main(String[] args) {
    	for (int i = 50; i <= 100; i++) { // Bucle que recorre los números del 50 al 100
    	    boolean esPrimo = true; // Variable que asume que el número es primo

    	    // Bucle para verificar si el número tiene divisores (excluyendo 1 y él mismo)
    	    for (int j = 2; j <= i / 2; j++) { // Solo hasta la mitad del número
    	        if (i % j == 0) { // Si encuentra un divisor exacto
    	            esPrimo = false; // No es primo
    	            break; // Sale del bucle para no seguir comprobando
    	        }
    	    }

    	    if (esPrimo) { // Si no se encontraron divisores
    	        System.out.println(i + " Es primo"); // Imprime que es primo
    	    } else { // Si tiene divisores
    	        System.out.println(i + " Tiene divisores: "); // Muestra mensaje
    	        
    	        for (int j = 1; j <= i; j++) { // Bucle para buscar y mostrar todos los divisores
    	            if (i % j == 0) { // Si j divide exactamente a i
    	                System.out.print(j + " "); // Imprime ese divisor
    	            }
    	        }
    	        System.out.println(); // Salto de línea
    	    }
    	}
    }
}
