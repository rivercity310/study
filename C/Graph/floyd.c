#include <stdio.h>
#include <stdlib.h>
#define min(a,b)  (((a) < (b)) ? (a) : (b))

#define MAX_VERTICES 50
#define INF 1e3

typedef struct gType {
	int n;
	int weight[MAX_VERTICES][MAX_VERTICES];
} GraphType;

static int distance[MAX_VERTICES];
static int visited[MAX_VERTICES];

static void floyd(GraphType* g) {
	int i, j, k;

	for (k = 0; k < g->n; k++)
		for (i = 0; i < g->n; i++)
			for (j = 0; j < g->n; j++)
				g->weight[i][j] 
					= min(g->weight[i][k] + g->weight[k][j], g->weight[i][j]);
}

void floyd_test() {
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

	floyd(&g);

	int size = g.n;

	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			if (g.weight[i][j] == INF)
				printf("* ");
			else
				printf("%d ", g.weight[i][j]);
		}
		putchar('\n');
	}
}