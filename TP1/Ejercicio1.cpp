#include <stdio.h>
#include <math.h>

// Multiplicación por sumas reiteradas
int multiplicacion(int a, int b) {
    int resultado = 0;
    for (int i = 0; i < b; i++) {
        resultado += a;
    }
    return resultado;
}

// Raíz cuadrada (racional)
float raiz(float *num) {
    return sqrt(*num);
}

// Suma de n vectores
void sumaVectores(int n) {
    int vector[100] = {0};
    int temp[100];
    int tam;

    printf("Ingrese la cantidad de elementos de los vectores: ");
    scanf("%d", &tam);

    for (int i = 0; i < n; i++) {
        printf("Vector %d:\n", i + 1);
        for (int j = 0; j < tam; j++) {
            printf("Elemento %d: ", j + 1);
            scanf("%d", &temp[j]);
            vector[j] += temp[j];
        }
    }

    printf("Resultado de la suma:\n");
    for (int i = 0; i < tam; i++) {
        printf("%d ", vector[i]);
    }
    printf("\n");
}

// Multiplicación escalar
void multiplicacionEscalar(int n) {
    int v1[100], v2[100];
    int resultado = 0;

    printf("Ingrese los elementos del primer vector:\n");
    for (int i = 0; i < n; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &v1[i]);
    }

    printf("Ingrese los elementos del segundo vector:\n");
    for (int i = 0; i < n; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &v2[i]);
    }

    for (int i = 0; i < n; i++) {
        resultado += v1[i] * v2[i];
    }

    printf("Resultado de la multiplicación escalar: %d\n", resultado);
}

int main() {
    int opcion;

    printf("Seleccione una opción:\n");
    printf("1. Multiplicación de Nº Natural (por sumas reiteradas)\n");
    printf("2. Raíz cuadrada de un Nº racional\n");
    printf("3. Suma de n vectores\n");
    printf("4. Multiplicación escalar de 2 vectores\n");
    printf("Opción: ");
    scanf("%d", &opcion);

    switch (opcion) {
        case 1: {
            int a, b;
            printf("Ingrese dos números naturales: ");
            scanf("%d %d", &a, &b);
            int res = multiplicacion(a, b);
            printf("Resultado: %d\n", res);
            break;
        }
        case 2: {
            float num;
            printf("Ingrese un número racional: ");
            scanf("%f", &num);
            printf("Raíz: %.2f\n", raiz(&num));
            break;
        }
        case 3: {
            int cantidad;
            printf("¿Cuántos vectores desea sumar?: ");
            scanf("%d", &cantidad);
            sumaVectores(cantidad);
            break;
        }
        case 4: {
            int n;
            printf("Ingrese la cantidad de elementos en los vectores: ");
            scanf("%d", &n);
            multiplicacionEscalar(n);
            break;
        }
        default:
            printf("Opción no válida.\n");
            break;
    }

    return 0;
}


#include <stdio.h>
#include <math.h>

int multiplicacion(int a, int b) {
    int resultado = 0;
    for (int i = 0; i < b; i++) {
        resultado += a;
    }
    return resultado;
}

float raiz(float *num) {
    return sqrt(*num);
}

void sumaVectores(int n) {
    int vector[100] = {0};
    int temp[100];
    int tam;

    printf("Ingrese la cantidad de elementos de los vectores: ");
    scanf("%d", &tam);

    for (int i = 0; i < n; i++) {
        printf("Vector %d:\n", i + 1);
        for (int j = 0; j < tam; j++) {
            printf("Elemento %d: ", j + 1);
            scanf("%d", &temp[j]);
            vector[j] += temp[j];
        }
    }

    printf("Resultado de la suma:\n");
    for (int i = 0; i < tam; i++) {
        printf("%d ", vector[i]);
    }
    printf("\n");
}

void multiplicacionEscalar(int n) {
    int v1[100], v2[100];
    int resultado = 0;

    printf("Ingrese los elementos del primer vector:\n");
    for (int i = 0; i < n; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &v1[i]);
    }

    printf("Ingrese los elementos del segundo vector:\n");
    for (int i = 0; i < n; i++) {
        printf("Elemento %d: ", i + 1);
        scanf("%d", &v2[i]);
    }

    for (int i = 0; i < n; i++) {
        resultado += v1[i] * v2[i];
    }

    printf("Resultado de la multiplicación escalar: %d\n", resultado);
}

int main

#include <stdio.h>
#include <string.h>

#define MAX_LINEAS 100
#define MAX_TEXTO 200

int contarPalabras(char *linea) {
    int cuenta = 0, enPalabra = 0;
    for (int i = 0; linea[i] != '\0'; i++) {
        if (linea[i] != ' ' && linea[i] != '\n') {
            if (!enPalabra) {
                cuenta++;
                enPalabra = 1;
            }
        } else {
            enPalabra = 0;
        }
    }
    return cuenta;
}

int main() {
    char lineas[MAX_LINEAS][MAX_TEXTO];
    char lineaMax[MAX_TEXTO] = "";
    int totalPalabras = 0, maxPalabras = 0;
    int cantidadLineas = 0;

    printf("Ingrese líneas de texto (escriba 'FIN' para terminar):\n");

    while (cantidadLineas < MAX_LINEAS) {
        fgets(lineas[cantidadLineas], MAX_TEXTO, stdin);

        if (strncmp(lineas[cantidadLineas], "FIN", 3) == 0)
            break;

        int palabras = contarPalabras(lineas[cantidadLineas]);
        totalPalabras += palabras;

        if (palabras > maxPalabras) {
            maxPalabras = palabras;
            strcpy(lineaMax, lineas[cantidadLineas]);
        }

        cantidadLineas++;
    }

    printf("\nCantidad total de palabras: %d\n", totalPalabras);
    printf("Línea con más palabras: %s\n", lineaMax);

    return 0;
}


#include <stdio.h>
#include <string.h>

int contarPalabras(char *linea) {
    int cuenta = 0, enPalabra = 0;
    for (int i = 0; linea[i] != '\0'; i++) {
        if (linea[i] != ' ' && linea[i] != '\n') {
            if (!enPalabra) {
                cuenta++;
                enPalabra = 1;
            }
        } else {
            enPalabra = 0;
        }
    }
    return cuenta;
}

int main() {
    FILE *archivo;
    char linea[200];
    int totalPalabras = 0;
    int maxPalabras = 0;
    char lineaMax[200] = "";

    archivo = fopen("datos.txt", "r");
    if (archivo == NULL) {
        printf("No se pudo abrir el archivo.\n");
        return 1;
    }

    while (fgets(linea, sizeof(linea), archivo)) {
        int cantidad = contarPalabras(linea);
        totalPalabras += cantidad;
        if (cantidad > maxPalabras) {
            maxPalabras = cantidad;
            strcpy(lineaMax, linea);
        }
    }

    fclose(archivo);

    printf("\nCantidad total de palabras: %d\n", totalPalabras);
    printf("Línea con más palabras: %s\n", lineaMax);

    return 0;
}


#include <stdio.h>
#include <string.h>

int contarPalabras(char *linea) {
    int cuenta = 0;
    int enPalabra = 0;

    for (int i = 0; linea[i] != '\0'; i++) {
        if (linea[i] != ' ' && linea[i] != '\n') {
            if (!enPalabra) {
                cuenta++;
                enPalabra = 1;
            }
        } else {
            enPalabra = 0;
        }
    }
    return cuenta;
}

int main() {
    FILE *archivo;
    char linea[200];
    int totalPalabras = 0;
    int maxPalabras = 0;
    char lineaMax[200];

    archivo = fopen("datos.txt", "r");
    if (archivo == NULL) {
        printf("No se pudo abrir el archivo.\n");
        return 1;
    }

    printf("Contenido del archivo:\n");
    while (fgets(linea, sizeof(linea), archivo)) {
        printf("%s", linea);
        int palabras = contarPalabras(linea);
        totalPalabras += palabras;
        if (palabras > maxPalabras) {
            maxPalabras = palabras;
            strcpy(lineaMax, linea);
        }
    }

    fclose(archivo);

    printf("\nTotal de palabras: %d\n", totalPalabras);
    printf("Línea con más palabras: %s", lineaMax);

    return 0;
}

#include <stdio.h>
#include <string.h>

void ordenarDescendente(char palabras[][20], int n) {
    char aux[20];
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (strcmp(palabras[i], palabras[j]) < 0) {
                strcpy(aux, palabras[i]);
                strcpy(palabras[i], palabras[j]);
                strcpy(palabras[j], aux);
            }
        }
    }
}

int main() {
    char oracion[200];
    char palabras[50][20];
    int cantidad = 0;

    printf("Ingrese una oración: ");
    fgets(oracion, sizeof(oracion), stdin);

    char *palabra = strtok(oracion, " \n");
    while (palabra != NULL) {
        strcpy(palabras[cantidad], palabra);
        cantidad++;
        palabra = strtok(NULL, " \n");
    }

    ordenarDescendente(palabras, cantidad);

    printf("Oración ordenada alfabéticamente descendente:\n");
    for (int i = 0; i < cantidad; i++) {
        printf("%s ", palabras[i]);
    }
    printf("\n");

    return 0;
}
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
