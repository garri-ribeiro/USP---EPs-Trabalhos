// -------------------------------------------//
// Gabriel Ribeiro da Silva |' nUSP - 9277812 //
// -------------------------------------------//

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <stdbool.h>

// Graph structure
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
    int* visited;
    int* ant;
    int* cost;
    struct AdjacencyList *adjList;
} Graph;

// Heap structure
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

// Graph Methods
AdjacencyListNode *newAdjacencyListNode(int destination, int weight);
Graph *createGraph(int size);
void addEdge(Graph *graph, int source, int destination, int weight);
void addNodeInAdjacencyList(Graph *graph, int adjListVertex, int vertex, int weight);
Graph *createAndPopulateGraphFromFile(char *filenameIn);

// Heap methods
HeapNode *newHeapNode(int value, int key);
Heap *createMinHeap(int capacity);
void swapHeapNode(HeapNode **a, HeapNode **b);
void minHeapify(Heap *minHeap, int idx);
int isMinHeapEmpty(Heap *minHeap);
bool insideOfMinHeap(Heap *minHeap, int value);
void decreaseKey(Heap *minHeap, int value, int key);
HeapNode *extractMin(Heap *minHeap);

//file method
bool isFileExists(FILE *file, char *filename);
void writeMSTTotalCost(int cost, char *filenameOut);
void writeMSTEdges(int vertex_1, int vertex_2, char *filenameOut);

// main method
void printMSTwithDFS(Graph *graph, int vertex, char *fileName);
void printEdgesMST(Graph *graph, char *filenameOut);
Graph* prim(Graph *graph);

void main(int argc, char *argv[]){
    char *filenameIn = argv[1];
    char *filenameOut = argv[2];

    Graph* graph = createAndPopulateGraphFromFile(filenameIn);
    

    graph = prim(graph);
    printEdgesMST(graph, filenameOut);
}

// main method
Graph* prim(Graph *graph)
{
    int size = graph->size;
    int *parent = graph->ant;
    int *cost = graph->cost;

    Heap *minHeap = createMinHeap(size);

    for (int i = 1; i < size; ++i)
    {
        minHeap->array[i] = newHeapNode(i, cost[i]);
        minHeap->position[i] = i;
    }

    cost[0] = 0;
    minHeap->array[0] = newHeapNode(0, cost[0]);
    minHeap->position[0] = 0;
    minHeap->size = size;

    while (!isMinHeapEmpty(minHeap)) {
        HeapNode *minHeapNode = extractMin(minHeap);
        int vertex_U = minHeapNode->value; //vertex u

        AdjacencyListNode *adjVertex_U = graph->adjList[vertex_U].head;
        while (adjVertex_U != NULL)
        {
            int vertex_V = adjVertex_U->vertex;

            if (insideOfMinHeap(minHeap, vertex_V) && adjVertex_U->weight < cost[vertex_V])
            {
                cost[vertex_V] = adjVertex_U->weight;
                parent[vertex_V] = vertex_U;
                decreaseKey(minHeap, vertex_V, cost[vertex_V]);
            }
            adjVertex_U = adjVertex_U->next;
        }
    }

    return graph;
}
void printEdgesMST(Graph *graph, char* filenameOut) {
    int* edges = graph->ant;
    int* cost = graph->cost;
    int size = graph->size;
    int totalCost = 0;
    for (int i = 1; i < size; ++i) {
        totalCost = totalCost + cost[i];
    }
    writeMSTTotalCost(totalCost, filenameOut);

    Graph* graphPrint = createGraph(size);
    for(int i=1; i<size; i++) {
        addEdge(graphPrint, i, edges[i], 0);
    }
    printMSTwithDFS(graphPrint, 0, filenameOut);
}

void printMSTwithDFS(Graph *graph, int vertex, char* fileName) {
    AdjacencyListNode *adjVertex_U = graph->adjList[vertex].head;
    graph->visited[vertex] = 1;

    while (adjVertex_U != NULL) {
        int vertex_V = adjVertex_U->vertex;
        if (graph->visited[vertex_V] == 0)
        {
            writeMSTEdges(vertex,vertex_V, fileName);
            printMSTwithDFS(graph, vertex_V, fileName);
        }
        adjVertex_U = adjVertex_U->next;
    }
}

// Graph methods
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

// non-directed graph
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

Graph *createAndPopulateGraphFromFile(char* filenameIn)
{
     FILE* file = fopen(filenameIn, "r");
     isFileExists(file, filenameIn);
     
     int sizeGraph, totalEdges;
     fscanf(file, "%d %d", &sizeGraph, &totalEdges);
     Graph *graph = createGraph(sizeGraph);

     int vertex_1, vertex_2, weight;
     for (int i = 0; i < totalEdges; i++)
     {
         fscanf(file, "%d %d %d", &vertex_1, &vertex_2, &weight);
         addEdge(graph, vertex_1, vertex_2, weight);
    }

    fclose(file);
    return graph;
}

// Heap methods
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

void swapHeapNode(HeapNode **a, HeapNode **b)
{
    HeapNode *t = *a;
    *a = *b;
    *b = t;
}

void minHeapify(Heap *minHeap, int idx) {
    int smallest = idx;
    int left = 2 * idx + 1;
    int right = 2 * idx + 2;

    if (left < minHeap->size && minHeap->array[left]->key < minHeap->array[smallest]->key)
        smallest = left;

    if (right < minHeap->size && minHeap->array[right]->key < minHeap->array[smallest]->key)
        smallest = right;

    if (smallest != idx)
    {
        HeapNode *smallestNode = minHeap->array[smallest];
        HeapNode *idxNode = minHeap->array[idx];

        minHeap->position[smallestNode->value] = idx;
        minHeap->position[idxNode->value] = smallest;

        swapHeapNode(&minHeap->array[smallest], &minHeap->array[idx]);

        minHeapify(minHeap, smallest);
    }
}

HeapNode *extractMin(Heap *minHeap) {
    if (isMinHeapEmpty(minHeap))
        return NULL;

    HeapNode *root = minHeap->array[0];

    HeapNode *lastNode = minHeap->array[minHeap->size - 1];
    minHeap->array[0] = lastNode;

    minHeap->position[root->value] = minHeap->size - 1;
    minHeap->position[lastNode->value] = 0;

    minHeap->size = minHeap->size -1;
    minHeapify(minHeap, 0);

    return root;
}

int isMinHeapEmpty(Heap *minHeap) {
    return minHeap->size == 0;
}

bool insideOfMinHeap(Heap *minHeap, int value) {
    if (minHeap->position[value] < minHeap->size)
        return true;
    return false;
}

void decreaseKey(Heap *minHeap, int value, int key) {
    int i = minHeap->position[value];

    minHeap->array[i]->key = key;

    while (i && minHeap->array[i]->key < minHeap->array[(i - 1) / 2]->key)
    {
        minHeap->position[minHeap->array[i]->value] = (i - 1) / 2;
        minHeap->position[minHeap->array[(i - 1) / 2]->value] = i;
        swapHeapNode(&minHeap->array[i], &minHeap->array[(i - 1) / 2]);

        i = (i - 1) / 2;
    }
}

// file method
bool isFileExists(FILE *file, char *filename ){
    if (file == NULL) {
        printf("Could not open file %s", filename);
        exit(1);
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