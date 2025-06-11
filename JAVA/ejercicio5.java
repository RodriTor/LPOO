import java.util.Scanner;

public class ejercicio5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero;
		
		System.out.println("Ingrese un numero (1 al 10): ");
	    numero = scanner.nextInt();

	    System.out.println("La tabla de multiplicar del numero " + numero + " es: ");
	    for(int i=1; i<=10; i++) {
	    	int multi = numero * i;
	    	System.out.println(i + "x" + numero + " = " + multi ); 
	    }
	    scanner.close();
	}

}
