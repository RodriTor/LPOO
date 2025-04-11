#include <stdio.h>

char nombre[15], apellido[15];
int dia_nac, mes_nac, anio_nac, dni, edad;

const int dia = 30;
const int mes = 9;
const int anio = 2025;

int main() {
    printf("Ingrese nombre: ");
    scanf("%s", nombre);
    printf("Ingrese apellido: ");
    scanf("%s", apellido);
    printf("Ingrese dia de nacimiento: ");
    scanf("%d", &dia_nac);
    printf("Ingrese mes de nacimiento: ");
    scanf("%d", &mes_nac);
    printf("Ingrese año de nacimiento: ");
    scanf("%d", &anio_nac);
    printf("Ingrese DNI: ");
    scanf("%d", &dni);

    int edad = anio - anio_nac;


    if (mes_nac > mes || (mes_nac == mes && dia_nac >= dia)) {
        edad--;
    }


    if (edad < 16) {
        printf("No puede votar.\n");
    }
	else if (edad >= 16 && edad < 18) {
        printf("Puede votar, pero no es obligatorio.\n");
    } 
	else if (edad >= 18 && edad < 80) {
        printf("Esta obligado a votar.\n");
}
    else if(edad > 80){
	  	printf("Puede votar pero no esta obligado.\n");
	  }
	  
	else {
    printf("Puede votar.\n");
    }
    
    printf("\nDatos ingresados:\n");
    printf("Apellido: %s\n", apellido);
    printf("Nombre: %s\n", nombre);
    printf("DNI: %d\n", dni);

    return 0;
}
