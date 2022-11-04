#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define MAX_VERTICES 50
#define INF 1e3

#define TRUE 1
#define FALSE 0

int step = 1;

typedef struct gType {
	int n;
	int weight[MAX_VERTICES][MAX_VERTICES];
} GraphType;

static int distance[MAX_VERTICES];
static int visited[MAX_VERTICES];

static int choose(int n) {
	int i, min, minpos;
	min = INT_MAX;
	minpos = -1;

	for (i = 0; i < n; i++) {
		if (distance[i] < min && !visited[i]) {
			min = distance[i];
			minpos = i;
		}
	}

	return minpos;
}

static void print_status(GraphType* g) {
	printf("[ STEP %d ]\n", step++);
	printf("distance: ");
	for (int j = 0; j < g->n; j++) {
		if (distance[j] == INF)
			printf("* ");
		else
			printf("%d ", distance[j]);
	}
	putchar('\n');

	printf("visited: ");
	for (int j = 0; j < g->n; j++)
		printf("%d ", visited[j]);

	puts("\n");
}

static void shortest_path(GraphType* g, int start) {
	int i, u, w;

	/* √ ±‚»≠ */
	for (i = 0; i < g->n; i++) {
		distance[i] = g->weight[start][i];
		visited[i] = FALSE;
	}

	visited[start] = TRUE;
	distance[start] = 0;

	for (i = 0; i < g->n - 1; i++) {
		print_status(g);
		u = choose(g->n);
		visited[u] = TRUE;

		for (w = 0; w < g->n; w++) 
			if (!visited[w])
				if (distance[u] + g->weight[u][w] < distance[w])
					distance[w] = distance[u] + g->weight[u][w];
	}
}

void dijkstra_test() {
	GraphType g = { 7,
	{
		{0, 7, INF, INF, 3, 10, INF},
		{7, 0, 4, 10, 2, 6, INF},
		{INF, 4, 0, 2, INF, INF, INF},
		{INF, 10, 2, 0, 11, 9, 4},
		{3, 2, INF, 11, 0, INF, 5},
		{10, 6, INF, 9, INF, 0, INF},
		{INF, INF, INF, 4, 5, INF, 0}
	} };

	shortest_path(&g, 0);
}