// -------------------------------------------// 
// Gabriel Ribeiro da Silva |' nUSP - 9277812 //
// -------------------------------------------// 

#include <stdio.h> 
#include <stdlib.h>

typedef struct AdjacencyListNode { 
	int vertex; 
	int weight; 
	struct AdjListNode* next; 
} AdjacencyListNode;

typedef struct AdjacencyList { 
	struct AdjacencyListNode* head;
} AdjacencyList;

typedef struct Graph { 
    int size; 
    struct AdjacencyList* adjList; 
} Graph;

AdjacencyListNode *newAdjacencyListNode(int destination, int weight) {
    AdjacencyListNode *newNode = malloc(sizeof(AdjacencyListNode));
    newNode->vertex = destination;
    newNode->weight = weight;
    newNode->next = NULL;
    return newNode;
}
Graph* createGraph(int size) {
    Graph* graph = malloc(sizeof(Graph));
    graph->size = size;

    graph->adjList = malloc(size * sizeof(AdjacencyList));
    for(int i=0; i<size; i++) {
        graph->adjList[i].head = NULL;
    }
    return graph;
}

// non-directed graph
void addEdge(Graph* graph, int source, int destination, int weight) {
    addNodeInAdjacencyList(&graph, source, destination, weight);
    addNodeInAdjacencyList(&graph, destination, source, weight);
}

void addNodeInAdjacencyList(Graph *graph, int adjListVertex, int vertex, int weight) {
    AdjacencyListNode *newNode = newAdjacencyListNode(vertex, weight);
    newNode->next = graph->adjList[adjListVertex].head;
    graph->adjList[adjListVertex].head = newNode;
}

void printGraph(Graph* graph, int size) {
    for(int i = 0; i<size; i++) {
        printf("Adjacency list of %d |", i);
        AdjacencyListNode* node = graph->adjList[i].head;

        while (node != NULL) {
            printf(" %d", node->vertex);
            node = node->next;
        }
        printf("\n");
    }
}

void main() {
    int sizeGraph = 9;
    int totalEdges = 10;

    Graph* graph = createGraph(sizeGraph);

    addEdge(graph, 0, 1, 4);
    // addEdge(graph, 0, 7, 8);
    // addEdge(graph, 1, 2, 8);
    // addEdge(graph, 1, 7, 11);
    // addEdge(graph, 2, 3, 7);
    // addEdge(graph, 2, 8, 2);
    // addEdge(graph, 2, 5, 4);
    // addEdge(graph, 3, 4, 9);
    // addEdge(graph, 3, 5, 14);
    // addEdge(graph, 4, 5, 10);
    // addEdge(graph, 5, 6, 2);
    // addEdge(graph, 6, 7, 1);
    // addEdge(graph, 6, 8, 6);
    // addEdge(graph, 7, 8, 7);
    // for(int i =0; i < totalEdges; i++ ) {
    //     addEdge(1, 2, 3);
    // }
}