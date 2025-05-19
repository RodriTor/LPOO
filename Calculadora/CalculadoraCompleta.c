/* Nombre y apellido de los integrantes del grupo: Carlos Saca Cano - Julian Mazzone - Rodrigo Torres  */
#include <stdio.h>
#include <math.h>

void sumaarr(int* ptr, int tam){
    int suma = 0;
    for(int i=0; i<tam; i++){
        suma += *(ptr + i);
    }
    printf("la suma es: %d\n", suma);
}

void resarr(int* ptr, int tam){
    int rest = *(ptr);
    for(int i=1; i<tam; i++){
        rest -= *(ptr + i);
    }
    printf("la resta es: %d\n", rest);
}

void multarr(int *ptr, int tam){
    int multi=1;
    for(int i=0; i<tam; i++){
        multi *= *(ptr + i);
    }
    printf("la multiplicacion es: %d\n", multi);
}

void pote(double num , double po){
    double resu = pow(num, po);
    printf("El resultado es: %.2lf\n", resu);
}

void raiz(double num, double ra){
    double resul = pow(num, 1.0/ra);
    printf("El resultado es: %.2lf\n", resul);
}

void sumvec(double *arr, double *arr1, double *resu, int tam){
    for (int i=0; i<tam; i++){
        resu[i] = arr[i] + arr1[i];
    }
    printf("El resultado de la suma de los vectores es: \n");
    for(int i=0; i<tam; i++){
        printf("Elementos %d: %.2lf\n", i+1, resu[i]);
    }
}

void resvec(double *arr, double *arr1, double *resu, int tam){
    for (int i=0; i<tam; i++){
        resu[i] = arr[i] - arr1[i];
    }
    printf("El resultado de la resta de los vectores es: \n");
    for(int i=0; i<tam; i++){
        printf("Elementos %d: %.2lf\n", i+1, resu[i]);
    }
}

void mulvec(double *arr, double *arr1, double *resu, int tam){
    for (int i=0; i<tam; i++){
        resu[i] = arr[i] * arr1[i];
    }
    printf("El resultado de la multiplicacion de los vectores es: \n");
    for(int i=0; i<tam; i++){
        printf("Elementos %d: %.2lf\n", i+1, resu[i]);
    }
}

void esca(double *arr, double *arr1, int tam, int *resu){
    *resu = 0;
    for(int i = 0; i < tam; i++){
        *resu += arr[i] * arr1[i];
    }
    printf("Producto escalar: %d\n", *resu);
}

void provec(double arr[], double arr1[], double resu[]){
    resu[0] = arr[1]*arr1[2] - arr[2]*arr1[1];
    resu[1] = arr[2]*arr1[0] - arr[0]*arr1[2];
    resu[2] = arr[0]*arr1[1] - arr[1]*arr1[0];
}

void sis22(double a1, double b1, double c1, double a2, double b2, double c2) {
    double factor = a2 / a1;
    b2 = b2 - factor * b1;
    c2 = c2 - factor * c1;

    if (fabs(b2) < 1e-9) {
        if (fabs(c2) < 1e-9) {
            printf("Infinitas soluciones.\n");
        } else {
            printf("No hay solucion.\n");
        }
        return;
    }

    double y = c2 / b2;
    double x = (c1 - b1 * y) / a1;

    printf("Solucion:\n");
    printf("x = %.5lf\n", x);
    printf("y = %.5lf\n", y);
}

void sis33(double A[3][4]) {
    int i, j, k;
    double factor;

    for (i = 0; i < 3; i++) {

        if (fabs(A[i][i]) < 1e-9) {
            printf("No se puede resolver (pivote cero en fila %d).\n", i);
            return;
        }

        factor = A[i][i];
        for (j = 0; j < 4; j++)
            A[i][j] /= factor;

        for (k = i + 1; k < 3; k++) {
            factor = A[k][i];
            for (j = 0; j < 4; j++)
                A[k][j] -= factor * A[i][j];
        }
    }

    // Sustitución hacia atrás para obtener z, y y x
    double z = A[2][3];
    double y = A[1][3] - A[1][2] * z;
    double x = A[0][3] - A[0][1] * y - A[0][2] * z;

    printf("\nSolución del sistema 3x3:\n");
    printf("x = %.5lf\n", x);
    printf("y = %.5lf\n", y);
    printf("z = %.5lf\n", z);
}

void ingresarMatriz(int matriz[10][10], int filas, int columnas) {
    printf("Ingrese los elementos de la matriz %dx%d: \n", filas, columnas);
    for(int i = 0; i < filas; i++) {
        for(int j = 0; j < columnas; j++) {
            printf("Elemento [%d][%d]: ", i+1, j+1);
            scanf("%d", &matriz[i][j]);
        }
    }
}

void mostrarMatriz(int matriz[10][10], int filas, int columnas) {
    printf("La matriz es: \n");
    for(int i = 0; i < filas; i++) {
        for(int j = 0; j < columnas; j++) {
            printf("%d ", matriz[i][j]);
        }
        printf("\n");
    }
}

void SumaMatrices(int matrizA[10][10], int matrizB[10][10], int filas, int columnas) {
	int resultado[10][10], i, j;
	for(int i = 0; i < filas; i++) {
        for(int j = 0; j < columnas; j++) {
        	resultado[i][j] = matrizA[i][j] + matrizB[i][j];
		}
}
    printf("La suma de las 2 Matrices da: \n");
    mostrarMatriz(resultado, filas, columnas);
}

void RestaMatrices(int matrizA[10][10], int matrizB[10][10], int filas, int columnas) {
    int resultado[10][10];
    for(int i = 0; i < filas; i++) {
        for(int j = 0; j < columnas; j++) {
            resultado[i][j] = matrizA[i][j] - matrizB[i][j];
        }
    }
    printf("Resultado de la resta: \n");
    mostrarMatriz(resultado, filas, columnas);
}

void MultiplicacionMatriz(int matriz[10][10], int filas, int columnas, int escalar) {
	int resultado[10][10];
    for(int i = 0; i < filas; i++) {
        for(int j = 0; j < columnas; j++) {
            resultado[i][j] = matriz[i][j] * escalar;
        }
    }
    printf("Resultado de la multiplicacion por el escalar %d: \n", escalar);
    mostrarMatriz(resultado, filas, columnas);
}

void MultiplicacionEntreMatrices(int matrizA[10][10], int filasA, int columnasA, int matrizB[10][10], int filasB, int columnasB) {
	int resultado[10][10];
    
    for (int i = 0; i < filasA; i++) {
        for (int j = 0; j < columnasB; j++) {
            resultado[i][j] = 0;
        }
    }
    for (int i = 0; i < filasA; i++) {
        for (int j = 0; j < columnasB; j++) {
            for (int k = 0; k < columnasA; k++) {
                resultado[i][j] += matrizA[i][k] * matrizB[k][j];
            }
        }
    }

    printf("Resultado de la multiplicacion de las matrices: \n");
    mostrarMatriz(resultado, filasA, columnasB);
}

void determinante2x2(int matriz[10][10]) {
    int det = (matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]);
    printf("Determinante de la matriz 2x2: %d\n", det);
}

void determinante3x3(int matriz[10][10]) {
    int det = (matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1])) -
              (matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0])) +
              (matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]));
    printf("Determinante de la matriz 3x3: %d\n", det);
}

void Vector(int vector[10], int *longitud) {
    printf("Ingrese la cantidad de elementos del vector (max 10): ");
    scanf("%d", longitud);

    printf("Ingrese los elementos del vector:\n");
    for (int i = 0; i < *longitud; i++) {
        printf("Elemento [%d]: ", i + 1);
        scanf("%d", &vector[i]);
    }
}

void MultiplicarVector(int vector[10], int longitud, int escalar) {
    int resultado[10];
    for (int i = 0; i < longitud; i++) {
        resultado[i] = vector[i] * escalar;
    }

    printf("Resultado del vector multiplicado por el escalar %d:\n", escalar);
    for (int i = 0; i < longitud; i++) {
        printf("%d ", resultado[i]);
    }
    printf("\n");
}


void inversa2x2(int matriz[10][10]) {
    float a = (float)matriz[0][0];
    float b = (float)matriz[0][1];
    float c = (float)matriz[1][0];
    float d = (float)matriz[1][1];

    float det = a * d - b * c;
	
	if (det != 0) {
        float inversa[2][2];
        inversa[0][0] = d / det;
        inversa[0][1] = -b / det;
        inversa[1][0] = -c / det;
        inversa[1][1] = a / det;

        printf("La matriz inversa del 2x2 es:\n");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                printf("%f ", inversa[i] [j]);
            }
            printf("\n");
        }
        
        } else {
        	printf("La matriz no tiene inversa (determinante = 0)\n");
		}

}

void inversa3x3(int matriz[10][10]) {
    float det;
    

    det = (matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1])) -
          (matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0])) +
          (matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]));
          
    if(det != 0) {
        float inversa[3][3];
        
   
        inversa[0][0] =  (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1]);
        inversa[0][1] = -(matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0]);
        inversa[0][2] =  (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);

        inversa[1][0] = -(matriz[0][1] * matriz[2][2] - matriz[0][2] * matriz[2][1]);
        inversa[1][1] =  (matriz[0][0] * matriz[2][2] - matriz[0][2] * matriz[2][0]);
        inversa[1][2] = -(matriz[0][0] * matriz[2][1] - matriz[0][1] * matriz[2][0]);

        inversa[2][0] =  (matriz[0][1] * matriz[1][2] - matriz[0][2] * matriz[1][1]);
        inversa[2][1] = -(matriz[0][0] * matriz[1][2] - matriz[0][2] * matriz[1][0]);
        inversa[2][2] =  (matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0]);
        
        
        float adjunto[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                adjunto[i][j] = inversa[j][i];
            }
        }
        
        
        float MatrizInversa[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                MatrizInversa[i][j] = adjunto[i][j] / det;
            }
        }

       
        printf("La matriz inversa del 3x3 es:\n");
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                printf("%f ", MatrizInversa[i][j]);
            }
            printf("\n");
        }
    } else {
        printf("La matriz no tiene inversa (determinante = 0)\n");
    }
}



int main(){
    int op;

    do{
        printf("\n      -- MENU --\n");
        printf("1)  Suma\n");
        printf("2)  Resta\n");
        printf("3)  Multiplicacion\n");
        printf("4)  Divicion\n");
        printf("5)  Potencias\n");
        printf("6)  Raices\n");
        printf("7)  Suma de vectores\t(los vectores tienen que ser de la mismas dimenciones)\n");
        printf("8)  Resta de vectores\t(los vectores tienen que ser de la mismas dimenciones)\n");
        printf("9)  Multiplicacion de escalar por vector\n");
        printf("10) Producto escalar\n");
        printf("11) Producto vectorial\t(solo para vectores de 3 dimensiones)\n");
        printf("12) Suma de Matrices\n");
        printf("13) Resta de Matrices\n");
        printf("14) Multiplicar matriz por un escalar\n");
        printf("15) Multiplicacion entre matrices\n");
        printf("16) Multiplicacion de escalar por vector\n");
        printf("17) Determinante de una matriz\n");
        printf("18) Inversa de una matriz\n");
        printf("19) División de matrices (multiplicacion de una matriz por la inversa de otra matriz)\n");
        printf("20) Ingresar un sistema de ecuaciones y resolver Sistema de 2x2\n");
        printf("21) Ingresar un sistema de ecuaciones y resolver Sistema de 3x3\n");
        printf("0)  Salir\n");
        scanf("%i", &op);

        switch (op){
            case 1:{
                int tam, i;
                printf("introduzca la cantidad de elementos que desee sumar: ");
                scanf("%d", &tam);

                int  arr[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero a sumar : ", i+1);
                    scanf("%d", &arr[i]);
                }
                sumaarr(arr, tam);
                break;
            }

            case 2:{
                int tam, i;
                printf("introduzca la cantidad de elementos que desee restar: ");
                scanf("%d", &tam);

                int  arr[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero a restar : ", i+1);
                    scanf("%d", &arr[i]);
                }
                resarr(arr, tam);

                break;
            }
            case 3:{
                int tam, i;

                printf("ingrese la cantidad de numeros a multiplicar: ");
                scanf("%d", &tam);

                int  arr[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero a multipliar : ", i+1);
                    scanf("%d", &arr[i]);
                }
                multarr(arr, tam);
                break;
            }
            case 4:{
                int n1,di,div;
                printf("ingrese numero a dividir\n");
                scanf("%d", & n1);
                printf("\ningrese valor divisor para dividir:%d\n", n1);
                scanf("%d", & di);
                if(di == 0){
                    printf("Error: division por cero.\n");
                } else {
                    div=n1/di;
                    printf("el resultado es:%d\n", div);
                }
                break;
            }
            case 5:{
                double num, po;

                printf("ingrese el numero al cual se le quiera averiguar la potencia: ");
                scanf("%lf", &num);

                printf("ingrese la potencia de dicho numero: ");
                scanf("%lf", &po);

                pote(num, po);

                break;
            }
            case 6:{
                double num, ra;

                printf("ingrese el numero del cual se quiere saber la raiz: ");
                scanf("%lf", &num);

                printf("ingrese la raiz del numero: ");
                scanf("%lf", &ra);

                if(ra == 0){
                    printf("Error: raiz cero no es valida.\n");
                } else {
                    raiz(num, ra);
                }
                break;
            }
            case 7:{
                int tam, i;
                printf("introduzca la cantidad de elementos de los vectores: ");
                scanf("%d", &tam);

                double  arr[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero del vector: ", i+1);
                    scanf("%lf", &arr[i]);
                }

                double  arr1[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero del  segundo vector vector: ", i+1);
                    scanf("%lf", &arr1[i]);
                }
                double resu[tam];

                sumvec(arr, arr1, resu, tam);
                break;
            }
            case 8:{
                int tam, i;
                printf("introduzca la cantidad de elementos de los vectores: ");
                scanf("%d", &tam);

                double  arr[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero del vector: ", i+1);
                    scanf("%lf", &arr[i]);
                }

                double  arr1[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero del  segundo vector vector: ", i+1);
                    scanf("%lf", &arr1[i]);
                }
                double resu[tam];

                resvec(arr, arr1, resu, tam);
                break;
            }
            case 9:{
                int tam, i;
                printf("introduzca la cantidad de elementos de los vectores: ");
                scanf("%d", &tam);

                double  arr[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero del vector: ", i+1);
                    scanf("%lf", &arr[i]);
                }

                double  arr1[tam];
                for(i=0; i<tam; i++){
                    printf("ingrese el %d numero del  segundo vector vector: ", i+1);
                    scanf("%lf", &arr1[i]);
                }
                double resu[tam];

                mulvec(arr, arr1, resu, tam);
                break;
            }

            case 10:{
                int tam, i;
                printf("introduzca la cantidad de elementos de los vectores: ");
                scanf("%d", &tam);

                double arr[tam];
                for(i = 0; i < tam; i++){
                    printf("ingrese el %d numero del vector: ", i+1);
                    scanf("%lf", &arr[i]);
                }

                double arr1[tam];
                for(i = 0; i < tam; i++){
                    printf("ingrese el %d numero del segundo vector: ", i+1);
                    scanf("%lf", &arr1[i]);
                }

                int resu = 0;
                esca(arr, arr1, tam, &resu);
                break;
            }

            case 11: {
                double arr[3], arr1[3], resu[3];

                for(int i = 0; i < 3; i++) {
                    printf("Ingrese el elemento %d del primer vector: ", i + 1);
                    scanf("%lf", &arr[i]);
                }

                for(int i = 0; i < 3; i++) {
                    printf("Ingrese el elemento %d del segundo vector: ", i + 1);
                    scanf("%lf", &arr1[i]);
                }

                provec(arr, arr1, resu);

                printf("\nEl producto vectorial es: (%.2lf, %.2lf, %.2lf)\n", resu[0], resu[1], resu[2]);

                break;
                
                        }
                
                case 12: {
                int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];  

                printf("-Ingrese el numero de filas de la primera matriz (N): ");
    scanf("%d", &filasA);
    printf("--Ingrese el numero de columnas de la primera matriz (N): ");
    scanf("%d", &columnasA);
    ingresarMatriz(matrizA, filasA, columnasA);
    mostrarMatriz(matrizA, filasA, columnasA);

    printf("-Ingrese el numero de filas de la segunda matriz (M): ");
    scanf("%d", &filasB);
    printf("--Ingrese el numero de columnas de la segunda matriz (N): ");
    scanf("%d", &columnasB);
    ingresarMatriz(matrizB, filasB, columnasB);
    mostrarMatriz(matrizB, filasB, columnasB);
    if (filasA == filasB && columnasA == columnasB) {
                    printf("-Suma de matrices:\n");
                    SumaMatrices(matrizA, matrizB, filasA, columnasA);
                } 
				else {
                    printf("No se pueden sumar.\n");
				}
                
					break;
				}
				
				case 13: {
					int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];  

                printf("-Ingrese el numero de filas de la primera matriz (N): ");
    scanf("%d", &filasA);
    printf("--Ingrese el numero de columnas de la primera matriz (N): ");
    scanf("%d", &columnasA);
    ingresarMatriz(matrizA, filasA, columnasA);
    mostrarMatriz(matrizA, filasA, columnasA);

    printf("-Ingrese el numero de filas de la segunda matriz (M): ");
    scanf("%d", &filasB);
    printf("--Ingrese el numero de columnas de la segunda matriz (N): ");
    scanf("%d", &columnasB);
    ingresarMatriz(matrizB, filasB, columnasB);
    mostrarMatriz(matrizB, filasB, columnasB);
    
    if (filasA == filasB && columnasA == columnasB) {
                    printf("-Resta de matrices:\n");
                    RestaMatrices(matrizA, matrizB, filasA, columnasA);
                } else {
                    printf("No se pueden restar.\n");
                }
					
					break;
				}
				
				
				case 14: {
					int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];  

                printf("-Ingrese el numero de filas de la primera matriz (N): ");
    scanf("%d", &filasA);
    printf("--Ingrese el numero de columnas de la primera matriz (N): ");
    scanf("%d", &columnasA);
    ingresarMatriz(matrizA, filasA, columnasA);
    mostrarMatriz(matrizA, filasA, columnasA);

    printf("-Ingrese el numero de filas de la segunda matriz (M): ");
    scanf("%d", &filasB);
    printf("--Ingrese el numero de columnas de la segunda matriz (N): ");
    scanf("%d", &columnasB);
    ingresarMatriz(matrizB, filasB, columnasB);
    mostrarMatriz(matrizB, filasB, columnasB);
					int escalar;
            	printf("Ingrese el escalar para multiplicar: ");
            	scanf("%d", &escalar);
            	printf("MatrizA x %d ", escalar);
            	MultiplicacionMatriz(matrizA, filasA, columnasA, escalar);
            	printf("MatrizB x %d ", escalar);
            	MultiplicacionMatriz(matrizB, filasB, columnasB, escalar);
            	break;
					
					
					
					break;
				}
				
				case 15: {
					int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];  

                printf("-Ingrese el numero de filas de la primera matriz (N): ");
    scanf("%d", &filasA);
    printf("--Ingrese el numero de columnas de la primera matriz (N): ");
    scanf("%d", &columnasA);
    ingresarMatriz(matrizA, filasA, columnasA);
    mostrarMatriz(matrizA, filasA, columnasA);

    printf("-Ingrese el numero de filas de la segunda matriz (M): ");
    scanf("%d", &filasB);
    printf("--Ingrese el numero de columnas de la segunda matriz (N): ");
    scanf("%d", &columnasB);
    ingresarMatriz(matrizB, filasB, columnasB);
    mostrarMatriz(matrizB, filasB, columnasB);
					printf("-Multiplicando matrices-\n");
                MultiplicacionEntreMatrices(matrizA, filasA, columnasA, matrizB, filasB, columnasB);
					
					
					break;
				}
                
                case 16: {
                	int vector[10], escalar, longitud;
                	printf("-Multiplicacion de escalar por vector-\n");
                Vector(vector, &longitud);
                printf("Ingrese el escalar para multiplicar: ");
                scanf("%d", &escalar);
                MultiplicarVector(vector, longitud, escalar);
                	
                	
					break;
				}
				
				case 17: {
						int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];  

                printf("-Ingrese el numero de filas de la primera matriz (N): ");
    scanf("%d", &filasA);
    printf("--Ingrese el numero de columnas de la primera matriz (N): ");
    scanf("%d", &columnasA);
    ingresarMatriz(matrizA, filasA, columnasA);
    mostrarMatriz(matrizA, filasA, columnasA);

    printf("-Ingrese el numero de filas de la segunda matriz (M): ");
    scanf("%d", &filasB);
    printf("--Ingrese el numero de columnas de la segunda matriz (N): ");
    scanf("%d", &columnasB);
    ingresarMatriz(matrizB, filasB, columnasB);
    mostrarMatriz(matrizB, filasB, columnasB);
					
					
					printf("La determinante de la primera matriz\n");
					printf("-Determinante de la matriz:\n");
            	if (filasA == columnasA) {
                    if (filasA == 2) {
                         determinante2x2(matrizA);
                    }
					else if (filasA == 3) {
                        determinante3x3(matrizA);
                }
            }
                else {
                    printf("No se puede calcular el determinante. La matriz no es cuadrada.\n");
                    }
					
            
					
					break;
				}
				
				case 18: {
					int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];  

                printf("-Ingrese el numero de filas de la primera matriz (N): ");
    scanf("%d", &filasA);
    printf("--Ingrese el numero de columnas de la primera matriz (N): ");
    scanf("%d", &columnasA);
    ingresarMatriz(matrizA, filasA, columnasA);
    mostrarMatriz(matrizA, filasA, columnasA);

    printf("-Ingrese el numero de filas de la segunda matriz (M): ");
    scanf("%d", &filasB);
    printf("--Ingrese el numero de columnas de la segunda matriz (N): ");
    scanf("%d", &columnasB);
    ingresarMatriz(matrizB, filasB, columnasB);
    mostrarMatriz(matrizB, filasB, columnasB);
					printf("Inversa de la primera matriz \n");
					printf("-Inversa de la matriz:\n");
            	if (filasA == columnasA) {
                    if (filasA == 2) {
                        inversa2x2(matrizA);
                    }
                    else if (filasA == 3) {
                    	inversa3x3(matrizA);
					}
        }
                else {
                    printf("No se puede calcular la inversa. La matriz no es cuadrada.\n");
                    }
            
					break;
				}
				
				case 19: {
					int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];  

                printf("-Ingrese el numero de filas de la primera matriz (N): ");
    scanf("%d", &filasA);
    printf("--Ingrese el numero de columnas de la primera matriz (N): ");
    scanf("%d", &columnasA);
    ingresarMatriz(matrizA, filasA, columnasA);
    mostrarMatriz(matrizA, filasA, columnasA);

    printf("-Ingrese el numero de filas de la segunda matriz (M): ");
    scanf("%d", &filasB);
    printf("--Ingrese el numero de columnas de la segunda matriz (N): ");
    scanf("%d", &columnasB);
    ingresarMatriz(matrizB, filasB, columnasB);
    mostrarMatriz(matrizB, filasB, columnasB);
					
					
					if (filasB != columnasB) {
        printf("La matriz B no es cuadrada, no se puede calcular la inversa.\n");
        break;
    }

    if (columnasA != filasB) {
        printf("Las dimensiones no permiten multiplicar A * inversa(B).\n");
        break;
    }

    if (filasB == 2) {
        float inversaB[2][2];
        float a = (float)matrizB[0][0];
        float b = (float)matrizB[0][1];
        float c = (float)matrizB[1][0];
        float d = (float)matrizB[1][1];
        float det = a * d - b * c;

        if (det == 0) {
            printf("La matriz B no tiene inversa (determinante = 0).\n");
            break;
        }

        inversaB[0][0] = d / det;
        inversaB[0][1] = -b / det;
        inversaB[1][0] = -c / det;
        inversaB[1][1] = a / det;

        float resultado[10][10];
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < 2; j++) {
                resultado[i][j] = 0;
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += matrizA[i][k] * inversaB[k][j];
                }
            }
        }

        printf("Resultado de A * inversa(B):\n");
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < 2; j++) {
                printf("%.2f ", resultado[i][j]);
            }
            printf("\n");
        }

    } else if (filasB == 3) {
        float det = (matrizB[0][0] * (matrizB[1][1] * matrizB[2][2] - matrizB[1][2] * matrizB[2][1])) -
                    (matrizB[0][1] * (matrizB[1][0] * matrizB[2][2] - matrizB[1][2] * matrizB[2][0])) +
                    (matrizB[0][2] * (matrizB[1][0] * matrizB[2][1] - matrizB[1][1] * matrizB[2][0]));

        if (det == 0) {
            printf("La matriz B no tiene inversa (determinante = 0).\n");
            break;
        }

        float cof[3][3], adj[3][3], invB[3][3];
        cof[0][0] =  (matrizB[1][1] * matrizB[2][2] - matrizB[1][2] * matrizB[2][1]);
        cof[0][1] = -(matrizB[1][0] * matrizB[2][2] - matrizB[1][2] * matrizB[2][0]);
        cof[0][2] =  (matrizB[1][0] * matrizB[2][1] - matrizB[1][1] * matrizB[2][0]);

        cof[1][0] = -(matrizB[0][1] * matrizB[2][2] - matrizB[0][2] * matrizB[2][1]);
        cof[1][1] =  (matrizB[0][0] * matrizB[2][2] - matrizB[0][2] * matrizB[2][0]);
        cof[1][2] = -(matrizB[0][0] * matrizB[2][1] - matrizB[0][1] * matrizB[2][0]);

        cof[2][0] =  (matrizB[0][1] * matrizB[1][2] - matrizB[0][2] * matrizB[1][1]);
        cof[2][1] = -(matrizB[0][0] * matrizB[1][2] - matrizB[0][2] * matrizB[1][0]);
        cof[2][2] =  (matrizB[0][0] * matrizB[1][1] - matrizB[0][1] * matrizB[1][0]);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                adj[i][j] = cof[j][i];
                invB[i][j] = adj[i][j] / det;
            }
        }

        float resultado[10][10];
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < 3; j++) {
                resultado[i][j] = 0;
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += matrizA[i][k] * invB[k][j];
                }
            }
        }

        printf("Resultado de A * inversa(B):\n");
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < 3; j++) {
                printf("%.2f ", resultado[i][j]);
            }
            printf("\n");
        }
    } else {
        printf("Solo se admite la inversa de matrices 2x2 o 3x3.\n");
    }
					
					break;
				}
					
					
					 

        case 20:{
            double a1,b1,c1,a2,b2,c2;
            printf("ingrese coeficiente a1: ");
            scanf("%lf", &a1);
            printf("ingrese coeficiente b1: ");
            scanf("%lf", &b1);
            printf("ingrese termino independiente c1: ");
            scanf("%lf", &c1);

            printf("ingrese coeficiente a2: ");
            scanf("%lf", &a2);
            printf("ingrese coeficiente b2: ");
            scanf("%lf", &b2);
            printf("ingrese termino independiente c2: ");
            scanf("%lf", &c2);

            sis22(a1,b1,c1,a2,b2,c2);
            break;
        }

        case 21:{
            double A[3][4];
            printf("Ingrese los coeficientes y términos independientes del sistema 3x3:\n");
            for(int i=0; i<3; i++){
                for(int j=0; j<4; j++){
                    if(j<3)
                        printf("Ingrese coeficiente A[%d][%d]: ", i+1, j+1);
                    else
                        printf("Ingrese término independiente A[%d]: ", i+1);
                    scanf("%lf", &A[i][j]);
                }
            }
            sis33(A);
            break;
        }

        case 0:
            printf("Saliendo...\n");
            break;

        default:
            printf("Opcion invalida.\n");
            break;
    }

} while(op != 0);

}
