#include <stdio.h>
#include <math.h>

int main() {
    int opcion;
    float lado1, lado2, hipotenusa, cateto1;

    do {
        printf("\n===== MENU DE CALCULO =====\n");
        printf("1 - Calcular hipotenusa (ingresando dos catetos)\n");
        printf("2 - Calcular cateto (ingresando hipotenusa y un cateto)\n");
        printf("0 - Salir\n");
        printf("Ingrese una opcion: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1:
                printf("Ingrese el valor del primer cateto: ");
                scanf("%f", &lado1);
                printf("Ingrese el valor del segundo cateto: ");
                scanf("%f", &lado2);

                hipotenusa = sqrt(lado1 * lado1 + lado2 * lado2);
                printf("La hipotenusa es: %f\n", hipotenusa);
                break;

            case 2:
                printf("Ingrese el valor de la hipotenusa: ");
                scanf("%f", &lado1);
                printf("Ingrese el valor de un cateto: ");
                scanf("%f", &lado2);

                if (lado2 >= lado1) {
                    printf("Error: La hipotenusa debe ser mayor que el cateto.\n");
                } else {
                    cateto1 = sqrt(lado1 * lado1 - lado2 * lado2);
                    printf("El otro cateto es: %f\n", cateto1);
                }
                break;

            case 0:
                printf("Saliendo del programa...\n");
                break;
        }

    } while (opcion != 0);

    return 0;
}
