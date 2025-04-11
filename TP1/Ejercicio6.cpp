#include <stdio.h>

int main() {
    int numero, primos = 0, noPrimos = 0, cantidad = 0;

    printf("Ingrese un numero (0 para terminar): ");
    scanf("%d", &numero);

    while (numero != 0) {
        cantidad++;
        int contador = 0;

        for (int i = 1; i <= numero; i++) {
            if (numero % i == 0) {
                contador++;
            }
        }

        if (contador > 2) {
            noPrimos++; 
        } else {
            primos++; 
        }

        printf("Ingrese otro número (0 para terminar): ");
        scanf("%d", &numero);
    }

    printf("\nResultados:\n");
    printf("Cantidad de numeros ingresados: %d\n", cantidad);
    printf("Cantidad de numeros primos: %d\n", primos);
    printf("Cantidad de numeros no primos: %d\n", noPrimos);

    return 0;
}
