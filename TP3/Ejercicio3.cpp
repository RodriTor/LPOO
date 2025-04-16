#include <stdio.h>
// 3. Encontrar el mayor y el menor elemento de un arreglo usando punteros y funciones.
void MayorYMenor(int *arreglo, int *mayor, int *menor, int tam){
	*mayor = *arreglo;
	*menor = *arreglo;
	
	for(int i=0; i<tam; i++){
		if(*(arreglo + i) > *mayor){
			*mayor = *(arreglo +i);
		}
		if(*(arreglo + i) < *menor){
			*menor = *(arreglo +i);
		}
	}
	
}
main(){
	int tam;
	int mayor, menor;
	
	printf("Ingrese el tamaño del arreglo: ");
	scanf("%d", &tam);
	
	int arreglo[tam];
	 
	for(int i=0; i<tam; i++){
		printf("Ingrese elemento: ");
		scanf("%d", &arreglo[i]);
	}
	
	MayorYMenor(arreglo, &mayor, &menor, tam);
	
	printf("El elemento mayor del arreglo es: %d\n", mayor);
	printf("El elemento menor del arreglo es: %d", menor);
}
