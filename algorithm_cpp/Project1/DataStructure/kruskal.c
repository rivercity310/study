#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTICES 100
#define INF 1e9

static int parent[MAX_VERTICES];

static void set_init(int n) {
	for (int i = 0; i < n; i++)
		parent[i] = -1;
}

static int set_find(int curr) {
	if (parent[curr] == -1)
		return curr;    // Root

	while (parent[curr] != -1)
		curr = parent[curr];

	return curr;
}

static void set_union(int a, int b) {
	int ra = set_find(a);
	int rb = set_find(b);

	if (ra != rb)
		parent[ra] = rb;
}

struct Edge {
	int start;
	int end;
	int weight;
};

typedef struct gType {
	int n;     // 간선의 개수
	struct Edge edges[2 * MAX_VERTICES];
} GraphType;

/* 그래프 초기화 */
static void graph_init(GraphType* g) {
	g->n = 0;
	
	for (int i = 0; i < 2 * MAX_VERTICES; i++) {
		g->edges[i].start = 0;
		g->edges[i].end = 0;
		g->edges[i].weight = INF;
	}
}

/* 간선 삽입 */
static void insert_edge(GraphType* g, int start, int end, int weight) {
	g->edges[g->n].start = start;
	g->edges[g->n].end = end;
	g->edges[g->n].weight = weight;
	
	g->n++;
}

/* qsort()에 사용되는 비교함수 */
static int compare(const void* a, const void* b) {
	struct Edge* x = (struct Edge*)a;
	struct Edge* y = (struct Edge*)b;

	return x->weight - y->weight;
}

static void kruskal(GraphType* g) {
	int edge_accepted = 0;         // 현재까지 선택된 간선의 수
	int uset, vset;                // 정점 u와 v의 집합 번호
	struct Edge e;

	set_init(g->n);
	qsort(g->edges, g->n, sizeof(struct Edge), compare);

	puts("[ Kruskal MST Algorithm ]");

	int idx = 0;
	while (edge_accepted < (g->n - 1)) {
		e = g->edges[idx];
		uset = set_find(e.start);
		vset = set_find(e.end);

		if (uset != vset) {
			printf("간선 (%d, %d) %d 선택\n", e.start, e.end, e.weight);
			set_union(uset, vset);
			edge_accepted++;
			
			for (int i = 0; i <= 6; i++) {
				printf("  [%d] %2d ", i, parent[i]);
			}

			putchar('\n');
		}
		idx++;
	}
}

void kruskal_mst_test() {
	GraphType* g;

	if ((g = (GraphType*)malloc(sizeof(GraphType)))) {
		graph_init(g);

		insert_edge(g, 0, 1, 29);
		insert_edge(g, 1, 2, 16);
		insert_edge(g, 2, 3, 12);
		insert_edge(g, 3, 4, 22);
		insert_edge(g, 4, 5, 27);
		insert_edge(g, 5, 0, 10);
		insert_edge(g, 6, 1, 15);
		insert_edge(g, 6, 3, 18);
		insert_edge(g, 6, 4, 25);

		kruskal(g);
		free(g);
	}
}