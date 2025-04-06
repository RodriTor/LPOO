#include <stdio.h>
#include <string.h>
int i, total, contador=0;
char cadena[100];
main(){
printf("Ingrese una cadena de caracteres: ");
scanf("%s", cadena);

for(i=0; i< strlen(cadena); i++){
if(cadena[i] == 'a' || cadena[i] == 'e' || cadena[i] == 'i' || cadena[i] == 'o' || cadena[i] == 'u' ||
   cadena[i] == 'A' || cadena[i] == 'E' || cadena[i] == 'I' || cadena[i] == 'O' || cadena[i] == 'U' ){
   contador++;  
}
}
  printf("El numero de vocales son: %d", contador);
}
