#include <stdio.h>


void intercambiar(int *a, int *b) {
    int aux;

    aux = *a;
    *a = *b;
    *b = aux;
}

int main() {
    int x, y;


    printf("Ingrese el valor de x: ");
    scanf("%d", &x);

    printf("Ingrese el valor de y: ");
    scanf("%d", &y);


    printf("\nAntes del intercambio:\n");
    printf("x = %d, y = %d\n", x, y);


    intercambiar(&x, &y);

    // Mostrar después del intercambio
    printf("\nDespues del intercambio:\n");
    printf("x = %d, y = %d\n", x, y);

    return 0;
}
