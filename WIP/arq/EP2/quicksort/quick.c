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

};
int *concatena(int tam1, int *vetor1, int tam2, int *vetor2) {
    int *resultante = (int *) malloc((tam1+tam2+1)*sizeof(int));
    int i;

    for(i=0; i<tam1; i++) {
        resultante[i] = vetor1[i];
    }
    for(int k =0; k<tam2; k++) {
        resultante[k+tam1] = vetor2[k];
    }
    return resultante;
}

////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

int main (int argc, char *argv[]) { // irá receber dois arquivos

    // Inicializacao de variaveis
    FILE *arq1, *arq2;
    int tam1, tam2;
    int vetor1[100000], vetor2[100000];
    int numeroArq;
    tam1 = 0; tam2 = 0;

    //  Abertura de arquivos
    arq1 = fopen(argv[1], "r");
    arq2 = fopen(argv[2], "r");

    // Caso os arquivos nao existam
    if(arq1==NULL || arq2 == NULL) {
        printf("O arquivo não pode ser aberto\n");
        exit(1);
    }
    // Cria vetor com dados do arquivo e guarda tamanho dos vetores
    while(fscanf(arq1,"%d", &numeroArq) != EOF) {
        vetor1[tam1] = numeroArq;
        tam1++;
    }
    while(fscanf(arq2,"%d", &numeroArq) != EOF) {
        vetor2[tam2] = numeroArq;
        tam2++;
    }

    //Fecha arquivos de leitura
    fclose(arq1);
    fclose(arq2);

    // Função mergeSort
    int *resultado = (int *) malloc(tam1+tam2*sizeof(int));
    resultado = concatena(tam1,vetor1,tam2,vetor2);
    quickSort(resultado,tam1+tam2);
    resultado[tam1+tam2] = '\0';


    // Cria novo arquivo de escrita
    arq1 = fopen(argv[3], "w");
    int i = 0;
    while(1){
        if(resultado[i] == '\0') break;
        fprintf(arq1, "%d \n",resultado[i]);
        i++;
    }
    fclose(arq1); // Fechar arquivo de escrita


    exit(0); // finaliza programa
}
