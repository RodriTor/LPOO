#include <stdio.h>

// Función para intercambiar valores usando punteros
void intercambiar(int *a, int *b) {
    int aux;

    aux = *a;
    *a = *b;
    *b = aux;
}

int main() {
    int x, y;

    // Pedir al usuario que ingrese los valores
    printf("Ingrese el valor de x: ");
    scanf("%d", &x);

    printf("Ingrese el valor de y: ");
    scanf("%d", &y);

    // Mostrar antes del intercambio
    printf("\nAntes del intercambio:\n");
    printf("x = %d, y = %d\n", x, y);

    // Llamar a la función para intercambiar los valores
    intercambiar(&x, &y);

    // Mostrar después del intercambio
    printf("\nDespues del intercambio:\n");
    printf("x = %d, y = %d\n", x, y);

    return 0;
}
