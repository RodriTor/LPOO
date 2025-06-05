public class Ejercicio2 {
    public static void main(String[] args) {
    	for (int i = 50; i <= 100; i++) {
    	    boolean esPrimo = true;

    	        for (int j = 2; j <= i/2; j++) {
    	            if (i % j == 0) {
    	                esPrimo = false;
    	                break;
    	            }
    	        }
    	    

    	    if (esPrimo) {
    	        System.out.println(i + " Es primo");
    	    } else {
    	        System.out.println(i + " Tiene divisores: ");
    	        for (int j = 1; j <= i; j++) {
    	            if (i % j == 0) {
    	                System.out.print(j + " ");
    	            }
    	        }
    	        System.out.println();
    	    }
    	}

}
}
