#include <stdio.h>

void buscarMayorYMenor(int *arr, int tam, int *mayor, int *menor) {
 
    *mayor = *arr;
    *menor = *arr;


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

    printf("Ingrese la cantidad de elementos: ");
    scanf("%d", &tam);

    int arreglo[tam];

    for (int i = 0; i < tam; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &arreglo[i]);
    }


    int mayor, menor;

    buscarMayorYMenor(arreglo, tam, &mayor, &menor);


    printf("\nEl valor mayor es: %d\n", mayor);
    printf("El valor menor es: %d\n", menor);

    return 0;
}
