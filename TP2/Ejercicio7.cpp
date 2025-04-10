#include <stdio.h>
#include <string.h>

char cadena1[100], cadena2[100];
int letras1[256] = {0}, letras2[256] = {0};
int i;

int main() {
    printf("Ingrese la primera palabra: ");
    scanf("%s", cadena1);

    printf("Ingrese la segunda palabra: ");
    scanf("%s", cadena2);

    if(strlen(cadena1) != strlen(cadena2)) {
        printf("No son anagramas (distinto largo).\n");
        return 0;
    }

    for(i = 0; cadena1[i] != '\0'; i++) {
        letras1[cadena1[i]]++;
        letras2[cadena2[i]]++;
    }

    for(i = 0; i < 256; i++) {
        if(letras1[i] != letras2[i]) {
            printf("No son anagramas.\n");
            return 0;
        }
    }

    printf("Son anagramas.\n");
    return 0;
}




