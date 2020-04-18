//--------------------------------------------------------------
// NOMES DOS RESPONSÁVEIS: Gabriel Ribeiro da Silva / Henrique Bispo dos Santos
//--------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <malloc.h>

// ######### ESCREVA O NRO. DA SUA TURMA (02 OU 03)
char* turma() {
    return("03");
}

// ######### ESCREVA O NROUSP DO PRIMEIRO INTEGRANTE AQUI
char* nroUSP1() {
    return("9277812");
}

// ######### ESCREVA O NROUSP DO SEGUNDO INTEGRANTE AQUI (OU DEIXE COM ZERO)
char* nroUSP2() {
    return("7990811");
}


// elemento da lista
typedef struct estr {
    int digito;
    struct estr *prox;
} NO;

NO* ultimoElemLista(NO *p) {
    while(p->prox) p = p->prox;
    return(p);
}

NO* enesimoElemLista(NO *p, int n) {
    int i = 1;
    if(p)
        while((p->prox)&&(i<n)) {
            p = p->prox;
            i++;
        }
    if(i != n) return(NULL);
    else return(p);
}

int tamanhoLista(NO *p) {
    int tam = 0;
    while (p) {
        tam++;
        p = p->prox;
    }
    return(tam);
}

void anexarElemLista(int ch, NO *p) {
    NO* novo;
    NO* ant;
    ant = ultimoElemLista(p);
    novo = (NO *) malloc(sizeof(NO));
    novo->digito = ch;
    novo->prox = NULL;
    if(ant) ant->prox = novo;
}

void inverterLista(NO *p, NO *inv) {
    int tam = tamanhoLista(p);
    while (tam>0) {
        NO* i = enesimoElemLista(p, tam);
        anexarElemLista(i->digito, inv);
        tam--;
    }
}



// funcao principal
NO* soma(NO* p1, NO *p2);

//------------------------------------------
// O EP consiste em implementar esta funcao
//------------------------------------------
NO* soma(NO* p1, NO *p2) {

    NO* resp;
    resp = NULL;

    // Garri começando
    NO* p1inv = NULL;
    NO* p2inv = NULL;

    inverterLista(p1,p1inv); // inverter p1
    inverterLista(p2,p2inv); // inverter p2

    NO* p3inv = NULL; // inicializa p3 invertido
    int vai1 = 0; // guarda varivel para proxima soma

    //COMEÇAR FUNÇÃO SOMA <<<TO BRISANDO>>>>

    while ((p1inv->prox != NULL) && (p2inv->prox != NULL)){ // sai do laço quando um dos dois irem pra null


        int resparcial;

        resparcial = p1inv->digito + p2inv->digito + vai1; //somando digitos

        if(resparcial >= 10) {
                vai1 = 1;
                resparcial = resparcial - 10;
        }
        else vai1= 0;
        anexarElemLista(resparcial, p3inv); //anexar

        p1inv = p1inv->prox;
        p2inv = p1inv->prox;
    }

    // APOS UMA DAS LISTAS ACABAREM

    if(p1inv->prox) { // p1 continua
        while(p1inv->prox){
            if(p1inv->digito+vai1 == 10) {
                anexarElemLista(0, p3inv);
                vai1 = 1;
            }
            else {
                anexarElemLista(p1inv->digito+vai1, p3inv);
                vai1 = 0;
            }
            p1inv = p1inv->prox;

        }
         if(vai1== 1)  anexarElemLista(1, p3inv);;// caso tenha que aumentar um casa (ex: 9999 + 1)
    }

    if (p2inv->prox) { // p2 continua
          while(p2inv->prox){
            if(p2inv->digito+vai1 == 10) {
                anexarElemLista(0, p3inv);
                vai1 = 1;
            }
            else {
                anexarElemLista(p1inv->digito+vai1, p3inv);
                vai1 = 0;
            }
            p2inv = p2inv->prox;

        }
        if(vai1== 1)  anexarElemLista(1, p3inv);;// caso tenha que aumentar um casa (ex: 9999 + 1)
    }

    // Desinverter p3inv para resp
    inverterLista( p3inv, resp);

    // Garri terminando

    return resp;
}



//---------------------------------------------------------
// use main() para fazer chamadas de teste ao seu programa
//---------------------------------------------------------
int main() {
    NO* p1 = NULL;
    NO* p2 = NULL;
    // o EP sera testado com chamadas deste tipo
    NO* teste = NULL;
    teste = soma(p1,p2);




}

// por favor nao inclua nenhum código abaixo da função main()a
