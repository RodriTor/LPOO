#include <stdio.h>
#include <string.h>

char oracion[100];
int i, opcion;

int main() {
    printf("Ingrese una oracion: ");
    fgets(oracion, sizeof(oracion), stdin);

    printf("\nElija una opcion:\n");
    printf("1) Mostrar en MAYUSCULAS\n");
    printf("2) Mostrar en minusculas\n");
    printf("3) Alternar mayuscula y minuscula\n");
    printf("4) Cada palabra con inicial mayuscula\n");
    printf("Opcion: ");
    scanf("%d", &opcion);

    printf("\nResultado:\n");

    switch(opcion) {

        case 1: // MAYUSCULAS
            for(i = 0; i < strlen(oracion); i++) {
                if(oracion[i] >= 'a' && oracion[i] <= 'z') {
                    oracion[i] = oracion[i] - 32;
                }
            }
            printf("%s", oracion);
            break;

        case 2: // minusculas
            for(i = 0; i < strlen(oracion); i++) {
                if(oracion[i] >= 'A' && oracion[i] <= 'Z') {
                    oracion[i] = oracion[i] + 32;
                }
            }
            printf("%s", oracion);
            break;

        case 3:
        	for(i = 0; i < strlen(oracion); i++) {
        if(oracion[i] >= 'a' && oracion[i] <= 'z') {
            oracion[i] = oracion[i] - 32; // minúscula ? mayúscula
        } else if(oracion[i] >= 'A' && oracion[i] <= 'Z') {
            oracion[i] = oracion[i] + 32; // mayúscula ? minúscula
        }
    }
    printf("%s", oracion);
    break;
        case 4: // Primera letra de cada palabra en mayúscula
    int nueva_palabra = 1;

    for(i = 0; i < strlen(oracion); i++) {
        if(oracion[i] == ' ') {
            nueva_palabra = 1;
        } else {
            if(nueva_palabra == 1 && oracion[i] >= 'a' && oracion[i] <= 'z') {
                oracion[i] = oracion[i] - 32;
                nueva_palabra = 0;
            } else if(nueva_palabra == 0 && oracion[i] >= 'A' && oracion[i] <= 'Z') {
                oracion[i] = oracion[i] + 32;
            }
        }
    }

    printf("%s", oracion);
    break;

}
}
