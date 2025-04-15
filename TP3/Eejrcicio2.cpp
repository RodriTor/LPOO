#include <stdio.h>
//2. Encontrar la suma y el promedio de los elementos de un arreglo usando punteros y funciones.
void SumaYPromedio (int *suma, int *promedio, int tam, int *arreglo){
	*suma = 0;
	
	for(int i=0; i < tam; i++){
	*suma +=  *(arreglo + i);
}
	*promedio = (*suma)/tam;
	
}
main(){
	int tam, suma, promedio;
	printf("Ingrese el tamaño del arreglo: ");
	scanf("%d", &tam);
	
	int arreglo[tam];
	
	for(int i=0; i < tam; i++){
		printf("Ingrese numero: ");
		scanf("%d", &arreglo[i]);
	}
	
	SumaYPromedio(&suma, &promedio, tam, arreglo);
	
	printf("La suma de todos los elementos es: %d\n", suma);
	printf("El promedio de todos los elementos es %d\n", promedio);
}
