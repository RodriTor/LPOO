#include <stdio.h>

void concatenarCadenas(char *cadena1, char *cadena2, char *resultado) {
//Copio las 2 cadenas;
    while (*cadena1 != '\0') {
        *resultado = *cadena1;
        resultado++;
        cadena1++;
    }


    while (*cadena2 != '\0') {
        *resultado = *cadena2;
        resultado++;
        cadena2++;
    }


    *resultado = '\0';
}

int main() {
    char cadena1[100], cadena2[100], resultado[200];

    printf("Ingrese la primera cadena: ");
    scanf(" %[^\n]", cadena1);

    getchar();

    printf("Ingrese la segunda cadena: ");
    scanf(" %[^\n]", cadena2);

    concatenarCadenas(cadena1, cadena2, resultado);

    printf("\nCadena concatenada: %s\n", resultado);

    return 0;
}
