#include <stdio.h>
/* 6. Contar el número de ocurrencias de un elemento en un arreglo usando
puntero y funciones */ 
int contarOcurrencias(int *arr, int tam, int elemento) {
    int contador = 0;

    for (int i = 0; i < tam; i++) {
        if (*(arr + i) == elemento) {
            contador++;
        }
    }

    return contador;
}

int main() {
    int tam, buscar;

    printf("Ingrese la cantidad de elementos: ");
    scanf("%d", &tam);

    int arreglo[tam];
  
    printf("Ingrese los elementos del arreglo:\n");
    for (int i = 0; i < tam; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &arreglo[i]);
    }

    printf("Ingrese el número que desea contar: ");
    scanf("%d", &buscar);

    int ocurrencias = contarOcurrencias(arreglo, tam, buscar);

    printf("\nEl número %d aparece %d veces en el arreglo.\n", buscar, ocurrencias);

    return 0;
}
