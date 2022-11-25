#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTICES 50
#define INF 1e3

#define TRUE 1
#define FALSE 0

typedef struct gType {
	int n;
	int weight[MAX_VERTICES][MAX_VERTICES];
} GraphType;

static int selected[MAX_VERTICES];
static int distance[MAX_VERTICES];

static int get_min_vertex(int n) {
	int v = -1;

	for (int i = 0; i < n; i++) {
		if (!selected[i]) {
			v = i;
			break;
		}
	}

	for (int i = 0; i < n; i++)
		if (!selected[i] && (distance[i] < distance[v]))
			v = i;

	return v;
}

static void prim(GraphType* g, int start) {
	int i, u, v;

	for (u = 0; u < g->n; u++)
		distance[u] = INF;

	distance[start] = 0;

	for (i = 0; i < g->n; i++)
		u = get_min_vertex(g->n);

	selected[u] = TRUE;

	if (distance[u] == INF)
		return;

	for (i = 0; i < g->n; i++)
		printf(" [%d] %2d %d", i, distance[i], selected[i]);
	putchar('\n');

	for (v = 0; v < g->n; v++) 
		if (g->weight[u][v] != INF) 
			if (!selected[v] && g->weight[u][v] < distance[v])
				distance[v] = g->weight[u][v];
}

void prim_mst_test() {
	GraphType g = { 7,
		{{0, 29, INF, INF, INF, 10, INF},
		{29, 0, 16, INF, INF, INF, 15},
		{INF, 16, 0, 12, INF, INF, INF},
		{INF, INF, 12, 0, 22, INF, 18},
		{INF, INF, INF, 22, 0, 27, 25},
		{10, INF, INF, INF, 27, 0, INF},
		{INF, 15, INF, 18, 25, INF, 0}}
	};

	prim(&g, 0);
}