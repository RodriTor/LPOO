#include <stdio.h>
#include <string.h>

int main() {
    char cadena[100], original, nuevo;
    int i, longitud;

    printf("Ingrese una cadena de caracteres: ");
    scanf("%s", cadena);

    printf("Ingrese el caracter a reemplazar: ");
    scanf(" %c", &original);

    printf("Ingrese el nuevo caracter: ");
    scanf(" %c", &nuevo);

    for(i = 0; i <  strlen(cadena); i++) {  
        if(cadena[i] == original) {
            cadena[i] = nuevo;
        }
    }
    printf("Cadena modificada: %s\n", cadena);

    return 0;
}
