#include <stdio.h>
#include <stdlib.h>

#define MAX 50

typedef struct stk {
	int arr[MAX];
	int tos;
} Stack;

static void init_queue(Stack* q) {
	q->tos = 0;
}

static int Empty(Stack* q) {
	return q->tos <= 0;
}

static void StackPush(Stack* q, int data) {
	if (q->tos > MAX) {
		fprintf(stderr, "Stack Full\n");
		return;
	}

	q->arr[q->tos++] = data;
}

static int StackPop(Stack* q) {
	if (q->tos < 0) {
		fprintf(stderr, "Stack Empty\n");
		exit(1);
	}

	return q->arr[q->tos--];
}


// ------------------------------------------------------------------------------------------------

typedef struct gNode {
	int vertex;
	struct gNode* next;
} GraphNode;

typedef struct gType {
	int n;
	GraphNode* adj_list[MAX];
} GraphType;

static void init_graph(GraphType* g) {
	g->n = 0;

	for (int v = 0; v < MAX; v++)
		g->adj_list[v] = NULL;
}

static void insert_vertex(GraphType* g, int v) {
	if ((g->n) + 1 > MAX) {
		fprintf(stderr, "Graph: Exceeds Vertex Size");
		return;
	}

	g->n++;
}

static void insert_edge(GraphType* g, int u, int v) {
	if (u >= g->n || v >= g->n) {
		fprintf(stderr, "Graph: Vertex Number Error");
		return;
	}

	GraphNode* node = (GraphNode*)malloc(sizeof(GraphNode));
	
	if (node) {
		node->vertex = v;
		node->next = g->adj_list[u];
		g->adj_list[u] = node;

		/*
		if (g->adj_list[u] == NULL) {
			node->vertex = v;
			node->next = g->adj_list[u];
			g->adj_list[u] = node;
		}

		else {
			node->vertex = v;
			GraphNode* tmp = g->adj_list[u];

			while (tmp->next)
				tmp = tmp->next;

			tmp->next = node;
		}
		*/
	}

	else {
		fprintf(stderr, "Memory Allocation Error");
		return;
	}
}

static void print_graph(GraphType* g) {
	int size = g->n;

	for (int i = 0; i < size; i++) {
		GraphNode* tmp = g->adj_list[i];

		while (tmp) {
			printf("%d ", tmp->vertex);
			tmp = tmp->next;
		}

		putchar('\n');
	}
}

static void terminate_graph(GraphType* g) {
	int size = g->n;

	for (int i = 0; i < size; i++) {
		GraphNode* tmp = g->adj_list[i];

		if (tmp) {
			GraphNode* p = tmp;

			while (p) {
				tmp = p->next;
				printf("Delete %d\n", p->vertex);
				free(p);
				p = tmp;
			}

			putchar('\n');
		}

		putchar('\n');
	}
}

static void dfs_list(GraphType* g, int v, int visited[]) {
	visited[v] = 1;
	printf("%d -> ", v);

	for (GraphNode* tmp = g->adj_list[v]; tmp; tmp = tmp->next) 
		if (!visited[tmp->vertex])
			dfs_list(g, tmp->vertex, visited);
}

static void bfs_list(GraphType* g, int v, int visited[]) {
	Stack* q = (Stack*)malloc(sizeof(Stack));
	init_queue(q);

    StackPush(q, v);
	visited[v] = 1;

	while (!Empty(q)) {
		int vt = StackPop(q);

		printf("%d -> ", vt);

		GraphNode* tmp = g->adj_list[vt];
		while (tmp) {
			if (!visited[tmp->vertex]) {
                StackPush(q, tmp->vertex);
				visited[tmp->vertex] = 1;
			}

			tmp = tmp->next;
		}
	}
}

void adj_list_test() {
	GraphType* g = (GraphType*)malloc(sizeof(GraphType));
	init_graph(g);

	for (int i = 0; i < 6; i++)
		insert_vertex(g, i);

	insert_edge(g, 0, 1);
	insert_edge(g, 0, 2);
	insert_edge(g, 0, 4);
	insert_edge(g, 1, 2);
	insert_edge(g, 2, 3);
	insert_edge(g, 2, 4);
	insert_edge(g, 3, 4);
	insert_edge(g, 4, 5);

	print_graph(g);
	puts("\n");

	for (int i = 0; i < 2; i++) {
		int visited[MAX] = { 0, };
		if (i == 0) {
			puts("[ Depth First Search ]");
			dfs_list(g, 0, visited);
		}
		else {
			puts("[ Breath First Search ]");
			bfs_list(g, 0, visited);
		}

		putchar('\n');
	}

	puts("\n");

	terminate_graph(g);
	free(g);
}
