#include <stdio.h>

// Función que busca el mayor y menor en un arreglo
void buscarMayorYMenor(int *arr, int tam, int *mayor, int *menor) {
    // Inicializamos ambos con el primer valor del arreglo
    *mayor = *arr;
    *menor = *arr;

    // Recorremos el arreglo desde el segundo elemento
    for (int i = 1; i < tam; i++) {
        if (*(arr + i) > *mayor) {
            *mayor = *(arr + i);
        }
        if (*(arr + i) < *menor) {
            *menor = *(arr + i);
        }
    }
}

int main() {
    int tam;

    // Pedimos el tamaño del arreglo
    printf("Ingrese la cantidad de elementos: ");
    scanf("%d", &tam);

    int arreglo[tam];

    // Pedimos al usuario los elementos
    for (int i = 0; i < tam; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &arreglo[i]);
    }

    // Variables para guardar los resultados
    int mayor, menor;

    // Llamamos a la función
    buscarMayorYMenor(arreglo, tam, &mayor, &menor);

    // Mostramos los resultados
    printf("\nEl valor mayor es: %d\n", mayor);
    printf("El valor menor es: %d\n", menor);

    return 0;
}
