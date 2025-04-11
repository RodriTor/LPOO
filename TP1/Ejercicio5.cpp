#include <stdio.h>

int main() {
    int num, i;
    int es_primo = 1; 
    
    printf("Ingrese un numero: ");
    scanf("%d", &num);

    if (num < 2) {
        es_primo = 0;
    }

    for (i = 2; i < num; i++) {
        if (num % i == 0) {
            es_primo = 0; 
            break;
        }
    }


    if ( es_primo ) {
        printf("El numero %d es primo.\n", num);
    } else {
        printf("El número %d no es primo.\n", num);
    }

    return 0;
}
