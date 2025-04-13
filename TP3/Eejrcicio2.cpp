#include <stdio.h>

void calcularSumaYPromedio(int *arr, int tam, int *suma, float *promedio) {
    *suma = 0; // Inicializamos la suma en 0


    for (int i = 0; i < tam; i++) {
        *suma += *(arr + i); 
    }

    *promedio = (float)(*suma) / tam;
}

int main() {
    int tam;

    printf("Ingrese la cantidad de elementos del arreglo: ");
    scanf("%d", &tam);

    int arreglo[tam];


    for (int i = 0; i < tam; i++) {
        printf("Ingrese el elemento %d: ", i + 1);
        scanf("%d", &arreglo[i]);
    }

    int suma;
    float promedio;


    calcularSumaYPromedio(arreglo, tam, &suma, &promedio);
    printf("\nLa suma de los elementos es: %d\n", suma);
    printf("El promedio de los elementos es: %.2f\n", promedio);

    return 0;
}
