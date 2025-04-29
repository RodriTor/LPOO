
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

/*
/*
Trabajo Practico 1 (repaso)

1) Se debe realizar un programa el cual se solicite la siguiente información al usuario    
Nombre, Apellido, Fecha de nacimiento, y DNI.  Y luego informe si puede o no votar  y los
datos de Apellido, Nombre y DNI
Sabiendo que las elecciones son el 30 de septiembre, y pueden votar los mayores de 16 años
2) Realizar una mejora al programa anterior con las siguientes condiciones
  Sabiendo que las elecciones son el 30 de septiembre,
  Menor de 16 años no puede votar
  Mayor de 16 años (inclusive) y menor de 18 años puede votar
  Mayor de 18 años (inclusive) y menor de 80 años debe votar
  Mayor de 80 años (inclusive)  puede votar
3) Se solicita realizar un software que pida al usuario los datos de los catetos de un triángulo
rectángulo y luego me informe cual es el valor de su hipotenusa
4) Realizar una modificación al programa anterior, para el ingreso de dos de cualquiera de sus
lados, y me devuelva el valor de sus catetos y de su hipotenusa
5) Al ingresar un número se debe informar si es primo o no es primo
6) Dada una lista de números informar
  a) la cantidad de números ingresada
  b) la cantidad de números que son primos
  c) la cantidad de números que no son primos
7) Realizar el mismo ejercicio anterior para los números pares e impares
8) Dado un vector con números ordenarlo con los siguientes criterios prioritarios
    a) números primos de menor a mayor
  b) números impares de mayor a menor
  c) Números pares de menor a mayor
*/
*/
