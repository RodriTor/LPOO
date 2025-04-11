#include <stdio.h>
#include <math.h> 
int cateto1, cateto2;
float hipotenusa;
main() {

    printf("Ingrese el valor del primer cateto: ");
    scanf("%d", &cateto1);
    printf("Ingrese el valor del segundo cateto: ");
    scanf("%d", &cateto2);

    hipotenusa = sqrt(cateto1 * cateto1 + cateto2 * cateto2);

    printf("La hipotenusa es: %f\n", hipotenusa);

}
