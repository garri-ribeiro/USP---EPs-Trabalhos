#include <stdio.h>
#include <stdlib.h>

void quickSort(int *vetor, int tamtototal) {

    int i, j, p, t;
    if (tamtototal < 2)
    return;
    p = vetor[tamtototal / 2];
    for (i = 0, j = tamtototal - 1;; i++, j--) {
        while (vetor[i] < p)
        i++;
        while (p < vetor[j])
        j--;
        if (i >= j)
        break;
        t = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = t;
    }
    quickSort(vetor, i);
    quickSort(vetor + i, tamtototal - i);


    // vetor[tamtototal++] = '\0'; // Adicionar caracter nulo para avisar que e fim do array

    // return resultante;
};
int *concatena(int tam1, int *vetor1, int tam2, int *vetor2) {
    int *resultante = (int *) malloc((tam1+tam2+1)*sizeof(int));
    int i;

    for(i=0; i<tam1; i++) {
        resultante[i] = vetor1[i];
        printf("%d ", resultante[i]);
    }
    printf(" | ");
    for(int k =0; k<tam2; k++) {
        resultante[k+tam1] = vetor2[k];
        printf("%d ", resultante[k+tam1]);
    }
    return resultante;
}

////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

int main (int argc, char *argv[]) { // irá receber dois arquivos

    int a1[] = {1,2,5,9,10,11};
    int a2[] = {1,2,3,4,5,6,7,8,22};

    int *a3 = (int *) malloc(15*sizeof(int));
    a3 = concatena(6,a1,9,a2);
    quickSort(a3,15);
    printf("RESULTANTE\n");
    for(int i = 0; i < 15; i++) {
        printf("%d ", a3[i]);
    }

    exit(0); // finaliza programa
}
