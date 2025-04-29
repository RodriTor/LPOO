#include <stdio.h>
#include <string.h>

int verificarArchivo(FILE *archivo);
void agregarPersona(FILE *archivo);
void buscarPorDNI(FILE *archivo);
void buscarPorNombreApellido(FILE *archivo);

int main() {
    FILE *archivo;
    int opcion;

    do {
        printf("\n--- MENU PRINCIPAL ---\n");
        printf("1. Agregar nueva persona\n");
        printf("2. Buscar persona por DNI\n");
        printf("3. Buscar persona por nombre y apellido\n");
        printf("4. Salir\n");
        printf("Seleccione una opcion: ");
        scanf("%d", &opcion);

        while (opcion < 1 || opcion > 4) {
            printf("Opcion invalida. Ingrese nuevamente: ");
            scanf("%d", &opcion);
        }

        switch (opcion) {
            case 1:
                archivo = fopen("personas.txt", "a");
                if (!verificarArchivo(archivo)) {
                    agregarPersona(archivo);
                    fclose(archivo);
                }
                break;

            case 2:
                archivo = fopen("personas.txt", "r");
                if (!verificarArchivo(archivo)) {
                    buscarPorDNI(archivo);
                    fclose(archivo);
                }
                break;

            case 3:
                archivo = fopen("personas.txt", "r");
                if (!verificarArchivo(archivo)) {
                    buscarPorNombreApellido(archivo);
                    fclose(archivo);
                }
                break;

            case 4:
                printf("\nSaliendo del programa...\n");
                break;
        }

    } while (opcion != 4);

    return 0;
}

int verificarArchivo(FILE *archivo) {
    if (archivo == NULL) {
        printf("\nError: no se pudo acceder al archivo.\n");
        return 1;
    }
    return 0;
}

void agregarPersona(FILE *archivo) {
    char nombre[50], apellido[50];
    int dni;

    printf("\nIngrese nombre: ");
    scanf("%s", nombre);
    printf("Ingrese apellido: ");
    scanf("%s", apellido);
    printf("Ingrese DNI: ");
    scanf("%d", &dni);

    fprintf(archivo, "Nombre: %s Apellido: %s DNI: %d\n", nombre, apellido, dni);
    printf("\nPersona registrada correctamente.\n");
}

void buscarPorDNI(FILE *archivo) {
    char nombre[50], apellido[50];
    int dniArchivo, dniBuscado, encontrado = 0;

    printf("\nIngrese el DNI a buscar: ");
    scanf("%d", &dniBuscado);

    while (fscanf(archivo, "Nombre: %s Apellido: %s DNI: %d\n", nombre, apellido, &dniArchivo) == 3) {
        if (dniArchivo == dniBuscado) {
            printf("\nPersona encontrada:\nNombre: %s\nApellido: %s\n", nombre, apellido);
            encontrado = 1;
            break;
        }
    }

    if (!encontrado) {
        printf("\nNo se encontro ninguna persona con ese DNI.\n");
    }
}

void buscarPorNombreApellido(FILE *archivo) {
    char nombreBuscado[50], apellidoBuscado[50];
    char linea[150];
    int encontrado = 0;

    printf("\nIngrese el nombre: ");
    scanf("%s", nombreBuscado);
    printf("Ingrese el apellido: ");
    scanf("%s", apellidoBuscado);

    while (fgets(linea, sizeof(linea), archivo) != NULL) {
        if (strstr(linea, nombreBuscado) && strstr(linea, apellidoBuscado)) {
            printf("\nRegistro encontrado:\n%s", linea);
            encontrado = 1;
            break;
        }
    }

    if (!encontrado) {
        printf("\nNo se encontro ningun registro con esos datos.\n");
    }
}
