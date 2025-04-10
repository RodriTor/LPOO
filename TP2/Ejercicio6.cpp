#include <stdio.h>
#include <string.h>

char cadena[100], palabras[20][20];
int i, j = 0, palabra = 0;

int main() {
    printf("Ingrese una cadena: ");
    fgets(cadena, sizeof(cadena), stdin);

    for(i = 0; i < strlen(cadena); i++) {
        if(cadena[i] != ' ' && cadena[i] != '\n') {
            palabras[palabra][j] = cadena[i];
            j++;
        } else {
            palabras[palabra][j] = '\0';
            palabra++;
            j = 0;
        }
    }
    palabras[palabra][j] = '\0';
    palabra++;

    for(i = palabra - 1; i >= 0; i--) {
        printf("%s ", palabras[i]);
    }

    printf("\n");
    return 0;
}




