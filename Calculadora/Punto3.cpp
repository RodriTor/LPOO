//Punto 3 Calculadora
/*
Ingreso de matrices de tamaño MxN o NxN según corresponda, y realización
de las siguientes operaciones
a. Suma y resta de matrices
b. Multiplicación de escalar por una matriz
c. Multiplicación de matrices
d. Multiplicación de escalar por vector
e. Determinante de una matriz
f. Inversa de una Matriz
g. División de matrices (multiplicación de una matriz por la inversa de
otra matriz)
*/

#include <stdio.h>

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

int filasA, columnasA, matrizA[10][10];
int filasB, columnasB, matrizB[10][10];
int opcion;
int vector[10], longitud;

main(){
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
    
    do {
    	printf("| MENU | \n");
        printf("1. Sumar Matrices\n");
        printf("2. Restar matrices\n");
        printf("3. Multiplicar matriz por un escalar\n");
        printf("4. Multiplicacion entre matrices\n");
        printf("5. Multiplicación de escalar por vector\n");
        printf("6. Determinante de una matriz\n");
        printf("7. Inversa de una matriz\n");
        printf("8. División de matrices (multiplicación de una matriz por la inversa de otra matriz)");
        printf("0. Salir\n");
        
        printf("Seleccione una opcion: ");
        scanf("%d", &opcion);
        
        switch(opcion) {
        	case 1:
			if (filasA == filasB && columnasA == columnasB) {
                    printf("-Suma de matrices:\n");
                    SumaMatrices(matrizA, matrizB, filasA, columnasA);
                } 
				else {
                    printf("No se pueden sumar.\n");
				}
        	break;
        	
        	case 2:
        	if (filasA == filasB && columnasA == columnasB) {
                    printf("-Resta de matrices:\n");
                    RestaMatrices(matrizA, matrizB, filasA, columnasA);
                } else {
                    printf("No se pueden restar.\n");
                }
                break;
            
            case 3:
            	int escalar;
            	printf("Ingrese el escalar para multiplicar: ");
            	scanf("%d", &escalar);
            	printf("MatrizA x %d ", escalar);
            	MultiplicacionMatriz(matrizA, filasA, columnasA, escalar);
            	printf("MatrizB x %d ", escalar);
            	MultiplicacionMatriz(matrizB, filasB, columnasB, escalar);
            	break;
            	
        	case 4:
                printf("-Multiplicando matrices-\n");
                MultiplicacionEntreMatrices(matrizA, filasA, columnasA, matrizB, filasB, columnasB);
                break;
            
            case 5:
                printf("-Multiplicacion de escalar por vector-\n");
                Vector(vector, &longitud);
                printf("Ingrese el escalar para multiplicar: ");
                scanf("%d", &escalar);
                MultiplicarVector(vector, longitud, escalar);
                break;
                
            case 6:
            	printf("-Determinante de la matriz:\n");
            	if (filasA == columnasA) {
                    if (filasA == 2) {
                         determinante2x2(matrizA);
                    }
					else if (filasA == 3) {
                        determinante3x3(matrizA);
                }
                else {
                    printf("No se puede calcular el determinante. La matriz no es cuadrada.\n");
                    }
            }
                break;
                
            case 7: 
                printf("-Inversa de la matriz:\n");
            	if (filasA == columnasA) {
                    if (filasA == 2) {
                        inversa2x2(matrizA);
                    }
                    else if (filasA == 3) {
                    	inversa3x3(matrizA);
					}
                    
                else {
                    printf("No se puede calcular la inversa. La matriz no es cuadrada.\n");
                    }
            }
                break;
                
        	case 0:
                printf("Saliendo del programa...\n");
                break;
            default: printf("Opcion invalida. Intente de nuevo.\n");
        }
		
    	
	}while(opcion !=0);    
    
    
    
    
    
    
}
