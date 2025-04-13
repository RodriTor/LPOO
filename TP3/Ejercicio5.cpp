#include <stdio.h>

// Función para concatenar dos cadenas usando punteros
void concatenarCadenas(char *cadena1, char *cadena2, char *resultado) {
    // Copiar cadena1 al resultado
    while (*cadena1 != '\0') {
        *resultado = *cadena1;
        resultado++;
        cadena1++;
    }

    // Copiar cadena2 al resultado, justo después
    while (*cadena2 != '\0') {
        *resultado = *cadena2;
        resultado++;
        cadena2++;
    }

    // Agregar el carácter nulo al final ('\0')
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
