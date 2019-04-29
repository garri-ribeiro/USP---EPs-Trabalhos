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
Graph *CreateAndPopulateGraphByFile(FILE *file);
void printGraph(Graph *graph);

// Heap methods
HeapNode *newHeapNode(int value, int key);
Heap *createMinHeap(int capacity);
void swapHeapNode(HeapNode **a, HeapNode **b);
void minHeapify(Heap *minHeap, int idx);
int isMinHeapEmpty(Heap *minHeap);
bool insideOfMinHeap(Heap *minHeap, int value);
void decreaseKey(Heap *minHeap, int value, int key);
HeapNode *extractMin(Heap *minHeap);

// main method
void writeEdgesMSTinFile(int *array, int* cost, int size, char* filenameOut);
int* Prim(Graph *graph, char* filename);

bool isFileExists(FILE* file, char* filename) {
    if(file == NULL) {
        printf("Could not open file %s", filename);
        exit(1);
    }
}
void main(int argc, char *argv[]){
    FILE *file;
    char *filenameIn = argv[1];
    char *filenameOut = argv[2];
    file = fopen(filenameIn, "r");
    isFileExists(file, filenameIn);

    Graph* graph = CreateAndPopulateGraphByFile(file);
    fclose(file);

    // printGraph(graph);
    Prim(graph, filenameOut);
}

// main method
int* Prim(Graph *graph, char* filename)
{
    int size = graph->size;
    int parent[size];
    int cost[size];

    Heap *minHeap = createMinHeap(size);

    for (int i = 1; i < size; ++i)
    {
        parent[i] = -1;
        cost[i] = INT_MAX;
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

    writeEdgesMSTinFile(parent, cost, size, filename);
}
void writeEdgesMSTinFile(int *edges,int* cost, int size, char* filenameOut)
{
    FILE* file = fopen(filenameOut, "w");
    int totalCost = 0;
    for (int i = 1; i < size; ++i)
        totalCost = totalCost + cost[i];
    
    fprintf(file, "%d\n", totalCost);

    for (int i = 1; i < size; ++i)
        fprintf(file, "%d %d\n", edges[i], i);

    fclose(file);
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
    for (int i = 0; i < size; i++)
    {
        graph->adjList[i].head = NULL;
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

Graph *CreateAndPopulateGraphByFile(FILE *file)
{
    int sizeGraph, totalEdges;
    fscanf(file, "%d %d", &sizeGraph, &totalEdges);
    Graph *graph = createGraph(sizeGraph);

    int vertex_1, vertex_2, weight;
    for (int i = 0; i < totalEdges; i++)
    {
        fscanf(file, "%d %d %d", &vertex_1, &vertex_2, &weight);
        addEdge(graph, vertex_1, vertex_2, weight);
    }

    return graph;
}

void printGraph(Graph *graph)
{
    int size = graph->size;
    for (int i = 0; i < size; i++)
    {
        printf("Adjacency list of %d |", i);
        AdjacencyListNode *node = graph->adjList[i].head;

        while (node != NULL)
        {
            printf(" %d", node->vertex);
            node = node->next;
        }
        printf("\n");
    }
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

void minHeapify(Heap *minHeap, int idx)
{
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

HeapNode *extractMin(Heap *minHeap)
{
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

int isMinHeapEmpty(Heap *minHeap)
{
    return minHeap->size == 0;
}

bool insideOfMinHeap(Heap *minHeap, int value)
{
    if (minHeap->position[value] < minHeap->size)
        return true;
    return false;
}

void decreaseKey(Heap *minHeap, int value, int key)
{
    int i = minHeap->position[value];

    minHeap->array[i]->key = key;

    while (i && minHeap->array[i]->key < minHeap->array[(i - 1) / 2]->key)
    {
        // Swap this node with its parent
        minHeap->position[minHeap->array[i]->value] = (i - 1) / 2;
        minHeap->position[minHeap->array[(i - 1) / 2]->value] = i;
        swapHeapNode(&minHeap->array[i], &minHeap->array[(i - 1) / 2]);

        i = (i - 1) / 2;
    }
}