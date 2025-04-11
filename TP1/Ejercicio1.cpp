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

    // Calcular la edad
    int edad = anio - anio_nac;

    // Si aún no cumplió años en la fecha de la elección, restamos 1 a la edad
    if (mes_nac > mes || (mes_nac == mes && dia_nac >= dia)) {
        edad--;
    }

    // Verificar si puede votar
    if (edad >= 16) {
    printf("Puede votar!!\n");
    printf("Datos ingresados:\n");
    printf("Apellido: %s\n", apellido);
    printf("Nombre: %s\n", nombre);
    printf("DNI: %d\n", dni);
    } else {
        printf("No puede votar!!\n");
    printf("Datos ingresados:\n");
    printf("Apellido: %s\n", apellido);
    printf("Nombre: %s\n", nombre);
    printf("DNI: %d\n", dni);
    }

    return 0;
}
