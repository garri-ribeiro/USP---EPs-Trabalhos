#include <stdio.h>
#include <stdlib.h>

int *concatena(int tam1, int *vetor1, int tam2, int *vetor2) {

    int k,m,n; // k -> tam vetor2 | m -> tam vetor2 | n -> tam vetor resultante
    int *resultante = (int *) malloc((tam1+tam2+1)*sizeof(int));
    k = 0;  m = 0; n=0;

    // Casos os os dois vetores nao tenham terminado
    while(k<tam1 &&  m<tam2) {
        if(resultante[n-1] == vetor1[k]) k++;
        else if (resultante[n-1] == vetor2[m]) m++;
        else {
            if (vetor1[k] < vetor2[m]) { // Se v1 menor, colocar no resultante
                resultante[n] = vetor1[k];
                k++;
            }
            else if(vetor1[k] > vetor2[m]) { // Se v2 menor, colocar no resultante
                resultante[n] = vetor2[m];
                m++;
            }
            else {// Se iguais, colocar qualquer um no resultante (o meu é v1)
                resultante[n] = vetor1[k];
                k++; m++;
            }
            n++;
        }

    }

    // Caso o vetor 2 tenha terminado
    while(k<tam1) { // Colocar valores de v1 no resultante
        if(resultante[n-1] == vetor1[k]) k++;
        else {
            resultante[n] = vetor1[k];
            k++; n++;
        }
    }

    // Caso o vetor1 tenha temrinado
    while(m<tam2) {  // Colocar valores de v2 no resultante
        if(resultante[n-1] == vetor2[m]) m++;
        else {
            resultante[n] = vetor2[m];
            m++; n++;
        }
    }

    resultante[n++] = '\0'; // Adicionar caracter nulo para avisar que e fim do array

    return resultante;
};

////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

int main (int argc, char *argv[]) { // irá receber dois arquivos

    // Inicializacao de variaveis
    FILE *arq1, *arq2;
    int tam1, tam2;
    int vetor1[100000], vetor2[100000];
    int numeroArq;
    int *resultado;
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

    // Função concatena
    resultado = concatena(tam1, vetor1, tam2, vetor2);


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
