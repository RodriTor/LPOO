#include <stdio.h>
#include <math.h>

void potencia(float *base, int *exponente) {
    printf("Resultado: %.2f\n", pow(*base, *exponente));
}

void raiz(float *numero) {
    printf("Resultado: %.2f\n", sqrt(*numero));
}

void sumaVectores() {
    int a[3], b[3], resultado[3];
    printf("Ingrese 3 números para el vector A:\n");
    for (int i = 0; i < 3; i++) scanf("%d", &a[i]);
    printf("Ingrese 3 números para el vector B:\n");
    for (int i = 0; i < 3; i++) scanf("%d", &b[i]);
    for (int i = 0; i < 3; i++) resultado[i] = a[i] + b[i];
    printf("Vector resultado: ");
    for (int i = 0; i < 3; i++) printf("%d ", resultado[i]);
    printf("\n");
}

void multiplicacionEscalar() {
    int a[3], b[3], producto = 0;
    printf("Ingrese 3 números para el vector A:\n");
    for (int i = 0; i < 3; i++) scanf("%d", &a[i]);
    printf("Ingrese 3 números para el vector B:\n");
    for (int i = 0; i < 3; i++) scanf("%d", &b[i]);
    for (int i = 0; i < 3; i++) producto += a[i] * b[i];
    printf("Resultado del producto escalar: %d\n", producto);
}

int main() {
    int opcion;
    float num;
    int exp;

    do {
        printf("\n1. Potencia\n2. Raíz\n3. Suma de vectores\n4. Producto escalar\n5. Salir\n");
        printf("Elija una opción: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1:
                printf("Ingrese base: ");
                scanf("%f", &num);
                printf("Ingrese exponente: ");
                scanf("%d", &exp);
                potencia(&num, &exp);
                break;
            case 2:
                printf("Ingrese número: ");
                scanf("%f", &num);
                raiz(&num);
                break;
            case 3:
                sumaVectores();
                break;
            case 4:
                multiplicacionEscalar();
                break;
            case 5:
                printf("Programa finalizado.\n");
                break;
            default:
                printf("Opción inválida.\n");
        }
    } while (opcion != 5);

    return 0;
}










#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    char oracion[200], *palabras[50];
    int cant = 0;

    printf("Ingrese una oración: ");
    fgets(oracion, sizeof(oracion), stdin);

    char *token = strtok(oracion, " \n");
    while (token != NULL) {
        palabras[cant++] = token;
        token = strtok(NULL, " \n");
    }

    for (int i = 0; i < cant - 1; i++) {
        for (int j = i + 1; j < cant; j++) {
            if (strcmp(palabras[i], palabras[j]) > 0) {
                char *aux = palabras[i];
                palabras[i] = palabras[j];
                palabras[j] = aux;
            }
        }
    }

    printf("Oración ordenada: ");
    for (int i = 0; i < cant; i++) {
        printf("%s ", palabras[i]);
    }
    printf("\n");

    return 0;
}






#include <stdio.h>

int esPrimo(int n) {
    if (n < 2) return 0;
    for (int i = 2; i * i <= n; i++)
        if (n % i == 0) return 0;
    return 1;
}

void ordenarAsc(int v[], int n) {
    for (int i = 0; i < n-1; i++)
        for (int j = i+1; j < n; j++)
            if (v[i] > v[j]) {
                int aux = v[i];
                v[i] = v[j];
                v[j] = aux;
            }
}

void ordenarDesc(int v[], int n) {
    for (int i = 0; i < n-1; i++)
        for (int j = i+1; j < n; j++)
            if (v[i] < v[j]) {
                int aux = v[i];
                v[i] = v[j];
                v[j] = aux;
            }
}

int main() {
    int v[50], primos[50], noPrimos[50];
    int n, p = 0, np = 0;

    printf("Ingrese cantidad de elementos: ");
    scanf("%d", &n);

    printf("Ingrese los números:\n");
    for (int i = 0; i < n; i++) {
        scanf("%d", &v[i]);
        if (esPrimo(v[i]))
            primos[p++] = v[i];
        else
            noPrimos[np++] = v[i];
    }

    ordenarAsc(primos, p);
    ordenarDesc(noPrimos, np);

    printf("Vector ordenado: ");
    for (int i = 0; i < p; i++) printf("%d ", primos[i]);
    for (int i = 0; i < np; i++) printf("%d ", noPrimos[i]);
    printf("\n");

    return 0;
}



























#include <stdio.h>
#include <string.h>

int main() {
    FILE *archivo = fopen("datos.txt", "r");
    char linea[200];
    int lineas = 0, palabras = 0;

    if (archivo == NULL) {
        printf("No se pudo abrir el archivo.\n");
        return 1;
    }

    while (fgets(linea, sizeof(linea), archivo)) {
        lineas++;
        char *token = strtok(linea, " \n");
        while (token != NULL) {
            palabras++;
            token = strtok(NULL, " \n");
        }
    }

    fclose(archivo);

    printf("Cantidad de líneas: %d\n", lineas);
    printf("Cantidad de palabras: %d\n", palabras);

    return 0;
}


/*

1) Realizar un programa, con funciones y punteros, que pida al usuario el ingreso de los
elementos correspondientes y luego según su opción elegida realice
a. Potencia o de un Nº racional
b. Raiz de un Nº racional
c. Suma de 2 vectores
d. Multiplicación escalar de 2 vectores (3 elementos)
2) Realizar un programa que le pida al usuario que ingrese una oración y luego ordene
las palabras en una nueva oración pero ordenada alfabéticamente
3) Ingresar un vector numérico y luego mostrar el vector de manera que estén primero
los números primos ordenados de menor a mayor y luego de manera descendente los
no primos,
4) • Crea un archivo de texto llamado datos.txt con varias líneas de texto.

Escribe un programa en C que abra el archivo datos.txt, lea su contenido línea
por línea y realice las siguientes estadísticas:
a. Contar el número total de líneas.
b. Contar el número total de palabras (asume que las palabras están separadas
por espacios).
c. Imprime los resultados en la consola.

    */
