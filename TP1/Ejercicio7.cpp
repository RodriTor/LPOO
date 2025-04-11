#include <stdio.h>

int main() {
    int numero, pares = 0, impares = 0, cantidad = 0;

    printf("Ingrese un numero (0 para terminar): ");
    scanf("%d", &numero);

    while (numero != 0) {
        cantidad++; 


        if (numero % 2 == 0) {
            pares++;
        } else {
            impares++; 
        }

        printf("Ingrese otro número (0 para terminar): ");
        scanf("%d", &numero);
    }

    printf("\nResultados:\n");
    printf("Cantidad de numeros ingresados: %d\n", cantidad);
    printf("Cantidad de numeros pares: %d\n", pares);
    printf("Cantidad de numeros impares: %d\n", impares);

    return 0;
}
