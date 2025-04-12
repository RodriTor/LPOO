#include <stdio.h>

void calcularSumaYPromedio(int *arr, int tam, int *suma, float *promedio) {
    *suma = 0; // Inicializamos la suma en 0

    // Recorremos el arreglo con punteros
    for (int i = 0; i < tam; i++) {
        *suma += *(arr + i); // Sumamos el valor actual
    }

    // Calculamos el promedio usando la suma y el tamaño
    *promedio = (float)(*suma) / tam;
}

int main() {
    int tam;

    // Pedimos al usuario el tamaño del arreglo
    printf("Ingrese la cantidad de elementos del arreglo: ");
    scanf("%d", &tam);

    // Declaramos el arreglo
    int arreglo[tam];

    // Cargamos el arreglo con los valores del usuario
    for (int i = 0; i < tam; i++) {
        printf("Ingrese el elemento %d: ", i + 1);
        scanf("%d", &arreglo[i]);
    }

    // Variables para guardar el resultado
    int suma;
    float promedio;

    // Llamamos a la función, pasando el arreglo y direcciones de suma/promedio
    calcularSumaYPromedio(arreglo, tam, &suma, &promedio);

    // Mostramos el resultado
    printf("\nLa suma de los elementos es: %d\n", suma);
    printf("El promedio de los elementos es: %.2f\n", promedio);

    return 0;
}
