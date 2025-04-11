#include <stdio.h>

int main() {
    int vector[100], nuevo[100];
    int n = 0, i, j, aux, num, opcion;

    printf("Ingrese numeros (termina con 0):\n");
    while (1) {
        scanf("%d", &num);
        if (num == 0) break;
        vector[n++] = num;
    }

    do {
        printf("\n1 - Primos de menor a mayor\n");
        printf("2 - Impares de mayor a menor\n");
        printf("3 - Pares de menor a mayor\n");
        printf("0 - Salir\n");
        printf("Ingrese una opcion: ");
        scanf("%d", &opcion);

        int m = 0;

        switch (opcion) {
            case 1: // Primos
                for (i = 0; i < n; i++) {
                    int primo = 1;
                    if (vector[i] < 2) primo = 0;
                    for (j = 2; j < vector[i]; j++) {
                        if (vector[i] % j == 0) {
                            primo = 0;
                            break;
                        }
                    }
                    if (primo) nuevo[m++] = vector[i];
                }
                for (i = 0; i < m - 1; i++)
                    for (j = i + 1; j < m; j++)
                        if (nuevo[i] > nuevo[j]) {
                            aux = nuevo[i];
                            nuevo[i] = nuevo[j];
                            nuevo[j] = aux;
                        }
                break;

            case 2: // Impares
                for (i = 0; i < n; i++) {
                    if (vector[i] % 2 != 0)
                        nuevo[m++] = vector[i];
                }
                for (i = 0; i < m - 1; i++)
                    for (j = i + 1; j < m; j++)
                        if (nuevo[i] < nuevo[j]) {
                            aux = nuevo[i];
                            nuevo[i] = nuevo[j];
                            nuevo[j] = aux;
                        }
                break;

            case 3: // Pares
                for (i = 0; i < n; i++) {
                    if (vector[i] % 2 == 0)
                        nuevo[m++] = vector[i];
                }
                for (i = 0; i < m - 1; i++)
                    for (j = i + 1; j < m; j++)
                        if (nuevo[i] > nuevo[j]) {
                            aux = nuevo[i];
                            nuevo[i] = nuevo[j];
                            nuevo[j] = aux;
                        }
                break;

            case 0:
                break;

            default:
                printf("Opcion invalida.\n");
                break;
        }

        for (i = 0; i < m; i++) {
            printf("%d ", nuevo[i]);
        }
        if (m > 0) printf("\n");

    } while (opcion != 0);

    return 0;
}
