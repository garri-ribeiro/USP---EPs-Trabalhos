// -------------------------------------------//
// Gabriel Ribeiro da Silva |' nUSP - 9277812 //
// -------------------------------------------//

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <stdbool.h>

// Estruturas para Grafo
typedef struct AdjacencyListNode
{
    int vertex;
    int weight;
    struct AdjacencyListNode *next;
} AdjacencyListNode;

typedef struct AdjacencyList
{
    struct AdjacencyListNode *head;
} AdjacencyList;

typedef struct Graph
{
    int size;
    int *visited;
    int *ant;
    int *cost;
    struct AdjacencyList *adjList;
} Graph;

// Estruturas para Heap
typedef struct HeapNode
{
    int value;
    int key;
} HeapNode;

typedef struct Heap
{
    int size;
    int capacity;
    int *position;
    HeapNode **array;
} Heap;

// Metodos para manipulação de Grafo
AdjacencyListNode *newAdjacencyListNode(int destination, int weight);
Graph *createGraph(int size);
void addEdge(Graph *graph, int source, int destination, int weight);
void addNodeInAdjacencyList(Graph *graph, int adjListVertex, int vertex, int weight);
Graph *createAndPopulateGraphFromFile(char *filenameIn);

// Metodos para manipulação de Heap
HeapNode *newHeapNode(int value, int key);
Heap *createMinHeap(int capacity);
void swapHeapNode(HeapNode **a, HeapNode **b);
void minHeapify(Heap *minHeap, int idx);
int isMinHeapEmpty(Heap *minHeap);
bool insideOfMinHeap(Heap *minHeap, int value);
void decreaseCost(Heap *minHeap, int value, int key);
HeapNode *extractMin(Heap *minHeap);

//Metodos para manipulação de arquivos
bool isFileExists(FILE *file, char *filename);
void writeMSTTotalCost(int cost, char *filenameOut);
void writeMSTEdges(int vertex_1, int vertex_2, char *filenameOut);

// Metodos principais do EP
void printMSTwithDFS(Graph *graph, int vertex, char *fileName);
void printEdgesMST(Graph *graph, char *filenameOut);
Graph *prim(Graph *graph);

// METODO MAIN
void main(int argc, char *argv[])
{
    char *filenameIn = argv[1];
    char *filenameOut = argv[2];

    //cria e popula grafo
    Graph *graph = createAndPopulateGraphFromFile(filenameIn);

    //roda o algoritimo prim
    graph = prim(graph);

    //escreve no arquivo de saida a AGM e seu custo
    printEdgesMST(graph, filenameOut);

    exit(0); // sucesso
}

// METODOS PRINCIPAIS

// Aplica o algoritimo Prim usando MinHeap para extrair o vertice de menor custo
Graph *prim(Graph *graph)
{
    int size = graph->size;
    int *ant = graph->ant;
    int *cost = graph->cost;

    Heap *minHeap = createMinHeap(size);

    for (int i = 1; i < size; ++i) // inicializa variaveis
    {
        minHeap->array[i] = newHeapNode(i, cost[i]);
        minHeap->position[i] = i;
    }

    // começa a partir do vertice 0
    cost[0] = 0;
    minHeap->array[0] = newHeapNode(0, cost[0]);
    minHeap->position[0] = 0;
    minHeap->size = size; // inicializa tamanho do heap

    while (!isMinHeapEmpty(minHeap))
    {                                                // roda enquanto existir vertice no heap
        HeapNode *minHeapNode = extractMin(minHeap); // busca vertice com menor custo
        int vertex_U = minHeapNode->value;           //vertice U

        AdjacencyListNode *adjVertex_U = graph->adjList[vertex_U].head;
        // foreach vizinho W adjacentes de U
        while (adjVertex_U != NULL)
        {
            int vertex_W = adjVertex_U->vertex; //vertice W

            // se custo[w]> p(u, w) entao
            if (insideOfMinHeap(minHeap, vertex_W) && cost[vertex_W] > adjVertex_U->weight)
            {
                cost[vertex_W] = adjVertex_U->weight;
                ant[vertex_W] = vertex_U;
                decreaseCost(minHeap, vertex_W, cost[vertex_W]);
            }
            adjVertex_U = adjVertex_U->next;
        }
    }

    return graph;
}

// Escreve no arquivo o custo da AGM e a AGM utilizando busca em
// profundidade para printar a partir do nó raiz
void printEdgesMST(Graph *graph, char *filenameOut)
{
    int *edges = graph->ant;
    int *cost = graph->cost;
    int size = graph->size;
    
    int totalCost = 0;
    for (int i = 1; i < size; ++i) // calcula custo da AGM
    {
        totalCost = totalCost + cost[i];
    }
    writeMSTTotalCost(totalCost, filenameOut);

    // Cria grafo da MST para fazer a busca em profundidade para printar
    Graph *graphPrint = createGraph(size); 
    for (int i = 1; i < size; i++)
    {
        addEdge(graphPrint, i, edges[i], 0);
    }

    // printar a partir do nó raiz da AGM
    printMSTwithDFS(graphPrint, 0, filenameOut);
}

// Busca em profundidade e escrevendo no arquivo de saida
void printMSTwithDFS(Graph *graph, int vertex, char *fileName)
{
    AdjacencyListNode *adjVertex_U = graph->adjList[vertex].head;
    graph->visited[vertex] = 1; //visitado

    // foreach vizinho W adjacentes de U
    while (adjVertex_U != NULL)
    {
        int vertex_W = adjVertex_U->vertex;
        if (graph->visited[vertex_W] == 0)
        {
            writeMSTEdges(vertex, vertex_W, fileName); // escreve no arquivo
            printMSTwithDFS(graph, vertex_W, fileName);
        }
        adjVertex_U = adjVertex_U->next;
    }
}

// Metodos para manipulação de grafos (grafo não direcionado)

AdjacencyListNode *newAdjacencyListNode(int destination, int weight)
{
    AdjacencyListNode *newNode = malloc(sizeof(AdjacencyListNode));
    newNode->vertex = destination;
    newNode->weight = weight;
    newNode->next = NULL;

    return newNode;
}

Graph *createGraph(int size)
{
    Graph *graph = malloc(sizeof(Graph));
    graph->size = size;

    graph->adjList = malloc(size * sizeof(AdjacencyList));
    graph->visited = malloc(size * sizeof(int));
    graph->ant = malloc(size * sizeof(int));
    graph->cost = malloc(size * sizeof(int));
    for (int i = 0; i < size; i++)
    {
        graph->adjList[i].head = NULL;
        graph->visited[i] = 0;
        graph->ant[i] = -1;
        graph->cost[i] = INT_MAX;
    }

    return graph;
}

void addEdge(Graph *graph, int source, int destination, int weight)
{
    addNodeInAdjacencyList(graph, source, destination, weight);
    addNodeInAdjacencyList(graph, destination, source, weight);
}

void addNodeInAdjacencyList(Graph *graph, int adjListVertex, int vertex, int weight)
{
    AdjacencyListNode *newNode = newAdjacencyListNode(vertex, weight);
    newNode->next = graph->adjList[adjListVertex].head;
    graph->adjList[adjListVertex].head = newNode;
}

// Método para criar e adicionar arestas a partir dos dados do arquivo de entrada
Graph *createAndPopulateGraphFromFile(char *filenameIn)
{
    FILE *file = fopen(filenameIn, "r");
    isFileExists(file, filenameIn);

    int sizeGraph, totalEdges;
    fscanf(file, "%d %d", &sizeGraph, &totalEdges);
    Graph *graph = createGraph(sizeGraph); // cria grafo

    int vertex_1, vertex_2, weight;
    for (int i = 0; i < totalEdges; i++) // cria arestas
    {
        fscanf(file, "%d %d %d", &vertex_1, &vertex_2, &weight);
        addEdge(graph, vertex_1, vertex_2, weight);
    }
    fclose(file);

    return graph;
}

// Heap methods (Heap minino)
HeapNode *newHeapNode(int value, int key)
{
    HeapNode *heapNode = malloc(sizeof(HeapNode));
    heapNode->value = value;
    heapNode->key = key;

    return heapNode;
}

Heap *createMinHeap(int capacity)
{
    Heap *minHeap = malloc(sizeof(Heap));
    minHeap->position = malloc(capacity * sizeof(int));
    minHeap->size = 0;
    minHeap->capacity = capacity;
    minHeap->array = malloc(capacity * sizeof(HeapNode *));

    return minHeap;
}
// troca posiçoes de dois nos
void swapHeapNode(HeapNode **node_A, HeapNode **node_B)
{
    HeapNode *node_TEMP = *node_A;
    *node_A = *node_B;
    *node_B = node_TEMP;
}

// Atualiza as posiçoes do nós
void minHeapify(Heap *minHeap, int index)
{
    int smallest = index;
    int left = 2 * index + 1;
    int right = 2 * index + 2;

    if (left < minHeap->size && minHeap->array[left]->key < minHeap->array[smallest]->key)
    {
        smallest = left;
    }
    if (right < minHeap->size && minHeap->array[right]->key < minHeap->array[smallest]->key)
    {
        smallest = right;
    }
    if (smallest != index)
    {
        HeapNode *smallestNode = minHeap->array[smallest];
        HeapNode *indexNode = minHeap->array[index];
        // troca posiçoes
        minHeap->position[smallestNode->value] = index;
        minHeap->position[indexNode->value] = smallest;

        // troca posiçao dos nós
        swapHeapNode(&minHeap->array[smallest], &minHeap->array[index]);
        minHeapify(minHeap, smallest); // O(log n)
    }
}

// Aplica o heapify, retorna o vertice com menor custo.
HeapNode *extractMin(Heap *minHeap)
{
    if (isMinHeapEmpty(minHeap))
    {
        return NULL;
    }
    HeapNode *root = minHeap->array[0]; // no raiz

    HeapNode *lastNode = minHeap->array[minHeap->size-1];
    minHeap->array[0] = lastNode;

    minHeap->position[root->value] = minHeap->size-1;
    minHeap->position[lastNode->value] = 0;

    minHeap->size = minHeap->size - 1; // a cada chamada, diminui o range de ordenação do heap
    minHeapify(minHeap, 0);

    return root;
}

int isMinHeapEmpty(Heap *minHeap)
{
    return minHeap->size == 0;
}

// verifica  se o vertice esta no heap
bool insideOfMinHeap(Heap *minHeap, int value)
{
    if (minHeap->position[value] < minHeap->size) {
        return true;
    }

    return false;
}

// Diminui o custo da aresta de um vertice e atualiza as posições no heap
void decreaseCost(Heap *minHeap, int value, int key)
{
    int index = minHeap->position[value];

    minHeap->array[index]->key = key;
    
    while (index &&
           minHeap->array[index]->key < minHeap->array[(index - 1) / 2]->key) //O(log n)
    {
        minHeap->position[minHeap->array[index]->value] = (index - 1) / 2;
        minHeap->position[minHeap->array[(index - 1) / 2]->value] = index;
        swapHeapNode(&minHeap->array[index], &minHeap->array[(index - 1) / 2]);

        index = (index - 1) / 2; // move pro index do pai
    }
}

// Metodos para manipulação de arquivos
bool isFileExists(FILE *file, char *filename)
{
    if (file == NULL)
    {
        printf("Could not open file %s", filename);
        exit(1); // erro
    }
}

void writeMSTTotalCost(int cost, char *filenameOut)
{
    FILE *file = fopen(filenameOut, "w");
    fprintf(file, "%d\n", cost);
    fclose(file);
}

void writeMSTEdges(int vertex_1, int vertex_2, char *filenameOut)
{
    FILE *file = fopen(filenameOut, "a");
    fprintf(file, "%d %d\n", vertex_1, vertex_2);
    fclose(file);
}