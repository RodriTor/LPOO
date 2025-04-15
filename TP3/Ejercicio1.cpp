#include <stdio.h>
//1. Intercambiar el valor de dos variables usando punteros y funciones. Mostrando los valores iniciales y valores finales.
void cambio(int *x, int *y){
	int auxiliar;
	
	auxiliar = *x;
	*x = *y;
	*y = auxiliar;
	
	}

main(){
	int x, y;
	printf("Ingrese variable1: ");
	scanf("%d", &x);
	printf("Ingrese variable2: ");
	scanf("%d", &y);
	
	printf("Valores iniciales: \n");
	printf("x = %d, y = %d\n", x, y);
	
    cambio(&x ,&y);
	
	printf("Valores Finales: \n");
	printf("x = %d, y = %d", x, y);
	
	
