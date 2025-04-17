#include <stdio.h>

// Función para copiar los elementos de un arreglo a otro
void copiarArreglo(int *origen, int *destino, int tam) {
    for (int i = 0; i < tam; i++) {
        *(destino + i) = *(origen + i);
    }
}

int main() {
    int tam;

    // Pedir tamaño del arreglo
    printf("Ingrese la cantidad de elementos del arreglo: ");
    scanf("%d", &tam);

    int arreglo1[tam], arreglo2[tam];

    // Cargar arreglo original
    printf("Ingrese los elementos del arreglo original:\n");
    for (int i = 0; i < tam; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &arreglo1[i]);
    }

    // Llamar a la función para copiar
    copiarArreglo(arreglo1, arreglo2, tam);

    // Mostrar el arreglo copiado
    printf("\nArreglo copiado:\n");
    for (int i = 0; i < tam; i++) {
        printf("Elemento %d: %d\n", i + 1, arreglo2[i]);
    }

    return 0;
}
