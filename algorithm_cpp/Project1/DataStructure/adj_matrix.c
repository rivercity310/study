#include <stdio.h>
#include <stdlib.h>

#define MAX 50
typedef struct gType {
	int n;		// 정점의 개수
	int adj_mat[MAX][MAX];
} GraphType;

static void init_graph(GraphType* g) {
	g->n = 0;

	for (int row = 0; row < MAX; row++)
		for (int col = 0; col < MAX; col++)
			g->adj_mat[row][col] = 0;
}

/* 정점 삽입 연산 */
static void insert_vertex(GraphType* g, int v) {
	if ((g->n) + 1 > MAX) {
		fprintf(stderr, "Graph: Exceeds Vertex Size");
		exit(1);
	}

	g->n++;
}

/* 간선 삽입 연산 */
static void insert_edge(GraphType* g, int start, int end) {
	if (start >= g->n || end >= g->n) {
		fprintf(stderr, "Graph: Vertex Number Error");
		return;
	}

	g->adj_mat[start][end] = 1;
	g->adj_mat[end][start] = 1;
}

static void print_graph(GraphType* g) {
	int size = g->n;

	for (int row = 0; row < size; row++) {
		for (int col = 0; col < size; col++) {
			printf("%-5d", g->adj_mat[row][col]);
		}

		putchar('\n');
	}
}

static void dfs_mat(GraphType* g, int v, int visited[]) {
	visited[v] = 1;
	printf("%d -> ", v);
	
	for (int w = 0; w < g->n; w++)
		if (g->adj_mat[v][w] && !visited[w])
			dfs_mat(g, w, visited);
}

void adj_matrix_test() {
	GraphType* g = (GraphType*)malloc(sizeof(GraphType));
	init_graph(g);

	for (int i = 0; i < 5; i++)
		insert_vertex(g, i);

	insert_edge(g, 0, 1);
	insert_edge(g, 0, 2);
	insert_edge(g, 0, 4);
	insert_edge(g, 1, 2);
	insert_edge(g, 2, 3);
	insert_edge(g, 2, 4);
	insert_edge(g, 3, 4);

	print_graph(g);

	int visited[MAX] = { 0, };
	dfs_mat(g, 0, visited);

	free(g);
}