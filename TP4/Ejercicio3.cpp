#include <stdio.h>
#include <string.h>

int comprobarArchivo(FILE *f);
void registrarPersona(FILE *f);
int existeDNI(FILE *f, int dni);
void buscarPorDNI(FILE *f);
void buscarPorNombreYApellido(FILE *f);

int main() {
    FILE *archivo;
    int opcion;

    do {
        printf("\n--- MENU PRINCIPAL ---\n");
        printf("1. Registrar nueva persona\n");
        printf("2. Buscar persona por DNI\n");
        printf("3. Buscar persona por nombre y apellido\n");
        printf("4. Salir\n");
        printf("Ingrese una opcion: ");
        scanf("%d", &opcion);

        while (opcion < 1 || opcion > 4) {
            printf("Opcion invalida. Intente de nuevo: ");
            scanf("%d", &opcion);
        }

        switch (opcion) {
            case 1:
                archivo = fopen("registro.txt", "a+");
                rewind(archivo);
                if (!comprobarArchivo(archivo)) {
                    registrarPersona(archivo);
                    fclose(archivo);
                }
                break;

            case 2:
                archivo = fopen("registro.txt", "r");
                if (!comprobarArchivo(archivo)) {
                    buscarPorDNI(archivo);
                    fclose(archivo);
                }
                break;

            case 3:
                archivo = fopen("registro.txt", "r");
                if (!comprobarArchivo(archivo)) {
                    buscarPorNombreYApellido(archivo);
                    fclose(archivo);
                }
                break;

            case 4:
                printf("\nCerrando programa.\n");
                break;
        }

    } while (opcion != 4);

    return 0;
}

int comprobarArchivo(FILE *f) {
    if (f == NULL) {
        printf("\nError al acceder al archivo.\n");
        return 1;
    }
    return 0;
}

void registrarPersona(FILE *f) {
    char nombre[50], apellido[50];
    int dni;

    printf("\nIngrese nombre: ");
    scanf("%s", nombre);
    printf("Ingrese apellido: ");
    scanf("%s", apellido);
    printf("Ingrese DNI: ");
    scanf("%d", &dni);

    if (!existeDNI(f, dni)) {
        fprintf(f, "Nombre: %s Apellido: %s DNI: %d\n", nombre, apellido, dni);
        printf("\nPersona registrada exitosamente.\n");
    } else {
        printf("\nYa existe una persona registrada con ese DNI.\n");
    }
}

int existeDNI(FILE *f, int dni) {
    char nombre[50], apellido[50];
    int dniArchivo;

    while (fscanf(f, "Nombre: %s Apellido: %s DNI: %d\n", nombre, apellido, &dniArchivo) == 3) {
        if (dniArchivo == dni) {
            return 1;
        }
    }
    return 0;
}

void buscarPorDNI(FILE *f) {
    int dniBuscado, dniArchivo;
    char nombre[50], apellido[50];
    int encontrado = 0;

    printf("\nIngrese el DNI a buscar: ");
    scanf("%d", &dniBuscado);

    while (fscanf(f, "Nombre: %s Apellido: %s DNI: %d\n", nombre, apellido, &dniArchivo) == 3) {
        if (dniArchivo == dniBuscado) {
            printf("\nPersona encontrada:\nNombre: %s\nApellido: %s\n", nombre, apellido);
            encontrado = 1;
            break;
        }
    }

    if (!encontrado) {
        printf("\nNo se encontro una persona con ese DNI.\n");
    }
}

void buscarPorNombreYApellido(FILE *f) {
    char nombreBuscado[50], apellidoBuscado[50];
    char nombre[50], apellido[50];
    int dniArchivo;
    int encontrado = 0;

    printf("\nIngrese nombre a buscar: ");
    scanf("%s", nombreBuscado);
    printf("Ingrese apellido a buscar: ");
    scanf("%s", apellidoBuscado);

    while (fscanf(f, "Nombre: %s Apellido: %s DNI: %d\n", nombre, apellido, &dniArchivo) == 3) {
        if (strcmp(nombre, nombreBuscado) == 0 && strcmp(apellido, apellidoBuscado) == 0) {
            printf("\nPersona encontrada:\nNombre: %s\nApellido: %s\nDNI: %d\n", nombre, apellido, dniArchivo);
            encontrado = 1;
            break;
        }
    }

    if (!encontrado) {
        printf("\nNo se encontro ninguna persona con esos datos.\n");
    }
}
