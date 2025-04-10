#include <stdio.h>
#include <string.h>

int main() {
    char cadena[99], invertida[99];
    int longitud, i;

    printf("Ingrese una cadena de caracteres: ");
    scanf("%s", cadena);

    longitud = strlen(cadena);

    for(i = 0; i < longitud; i++) {
        invertida[i] = cadena[longitud - 1 - i];
    }
    invertida[longitud] = '\0';

    if(strcmp(cadena, invertida) == 0) { 
        printf("La cadena es un palindromo.\n");
    } else {
        printf("La cadena no es un palindromo.\n");
    }
    return 0;

}


