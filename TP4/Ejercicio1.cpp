#include <stdio.h>

int main() {
    FILE *archivo;
    char nombre[50], apellido[50];
    int opcion, dni, dniBuscar, encontrado;

    do {
        printf("\n--- Menu ---\n");
        printf("1. Agregar persona\n");
        printf("2. Buscar persona por DNI\n");
        printf("3. Salir\n");
        printf("Seleccione una opcion: ");
        scanf("%d", &opcion);

        while (opcion < 1 || opcion > 3) {
            printf("Opcion invalida. Intente de nuevo: ");
            scanf("%d", &opcion);
        }

        switch (opcion) {
            case 1:
                archivo = fopen("personas.txt", "a");
                if (archivo == NULL) {
                    printf("No se pudo abrir el archivo para escritura.\n");
                    return 1;
                }

                printf("\nIngrese el nombre: ");
                scanf("%s", nombre);
                printf("Ingrese el apellido: ");
                scanf("%s", apellido);
                printf("Ingrese el DNI: ");
                scanf("%d", &dni);

                fprintf(archivo, "Nombre: %s\nApellido: %s\nDNI: %d\n", nombre, apellido, dni);
                fclose(archivo);

                printf("Datos guardados exitosamente.\n");
                break;

            case 2:
                archivo = fopen("personas.txt", "r");
                if (archivo == NULL) {
                    printf("No se pudo abrir el archivo para lectura.\n");
                    return 1;
                }

                printf("\nIngrese el DNI que desea buscar: ");
                scanf("%d", &dniBuscar);

                encontrado = 0;

                while (fscanf(archivo, "Nombre: %s\nApellido: %s\nDNI: %d\n", nombre, apellido, &dni) == 3) {
                    if (dni == dniBuscar) {
                        printf("\nPersona encontrada:\n");
                        printf("Nombre: %s\nApellido: %s\n", nombre, apellido);
                        encontrado = 1;
                        break;
                    }
                }

                if (!encontrado) {
                    printf("\nNo se encontro una persona con ese DNI.\n");
                }

                fclose(archivo);
                break;

            case 3:
                printf("\nSaliendo del programa...\n");
                break;
        }

    } while (opcion != 3);

    return 0;
}
