public class ClasePrimaria { // Define una clase pública llamada ClasePrimaria

	public static void main(String[] args) { // Método principal donde inicia el programa
		for(int i = 1; i <= 100; i++) { // Bucle que recorre los números del 1 al 100
			
			if(i % 2 == 0) { // Si el número es divisible por 2 (es par)
				System.out.println("PAR "); // Imprime la palabra "PAR"
			}
			else { // Si el número no es par (es impar)
				System.out.println(i); // Imprime el número impar
			}
			
		}
	}
}
