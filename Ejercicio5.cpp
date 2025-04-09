#include <stdio.h>
#include <string.h>

int i, contador = 1;  
char cadena[100];

main() {
    printf("Ingrese una cadena de caracteres: ");
    fgets(cadena, sizeof(cadena), stdin); 
    
    for(i = 0; i < strlen(cadena); i++){
	if(cadena[i] == ' '){
		contador++;
	}
	}
	 printf("El numero de palabras es: %d\n", contador);
	}
