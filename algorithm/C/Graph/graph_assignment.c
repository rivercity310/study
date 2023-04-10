#include <stdio.h>
#include <stdlib.h>

// bfs�� ���� ������ ť ����
#define MAX 50
#define INF 1e9

typedef struct qType {
	int front;
	int rear;
	int arr[MAX];
} QueueType;

static void init_queue(QueueType* q) {
	q->front = q->rear = 0;
}

static int is_empty(QueueType* q) {
	return q->front == q->rear;
}

/* rear�� MAX�� �����ϸ� �������ɷ� ���� */
static int is_full(QueueType* q) {
	return q->rear == MAX;
}

static void enqueue(QueueType* q, int data) {
	if (is_full(q)) {
		fprintf(stderr, "Queue Full\n");
		exit(1);
	}

	printf("- ENQUEUE: %d\n", data);
	q->arr[q->rear++] = data;
}

static int dequeue(QueueType* q) {
	if (is_empty(q)) {
		fprintf(stderr, "Queue Empty\n");
		exit(1);
	}

	int data = q->arr[q->front++];
	printf("- DEQUEUE: %d\n", data);
	
	return data;
}

// ----------------------------------------------------------------------

typedef struct sType {
	int tos;
	int stk[MAX];
} StackType;

static void init_stack(StackType* s) {
	s->tos = -1;
}

static int is_fullStack(StackType* s) {
	return s->tos >= MAX;
}

static int is_emptyStack(StackType* s) {
	return s->tos < 0;
}

static void push(StackType* s, int data) {
	if (is_fullStack(s)) {
		fprintf(stderr, "Stack Full\n");
		exit(1);
	}

	printf("- PUSH: %d\n", data);
	s->stk[++s->tos] = data;
}

static int pop(StackType* s) {
	if (is_emptyStack(s)) {
		fprintf(stderr, "Stack Empty\n");
		exit(1);
	}

	int data = s->stk[s->tos--];
	printf("- POP: %d\n", data);

	return data;
}


// ----------------------------------------------------------------------

typedef struct gType {
	int n;
	int grp[MAX][MAX];
} GraphType;

static void init_graph(GraphType* g) {
	g->n = 0;

	for (int i = 0; i < MAX; i++)
		for (int j = 0; j < MAX; j++)
			g->grp[i][j] = 0;
}

static void insert_edge(GraphType* g, int start, int end) {
	if (start > g->n || end > g->n) {
		fprintf(stderr, "Graph: Vertex Number Error");
		exit(1);
	}

	g->grp[start][end] = 1;
	g->grp[end][start] = 1;
}

static void dfs(GraphType* g, int start) {
	StackType* s = (StackType*)malloc(sizeof(StackType));
	init_stack(s);

	int visited[MAX] = { 0, };
	int rst[MAX] = { 0, };
	int idx = 0;

	push(s, start);
	printf("- visited[%d]: visit\n", start);
	visited[start] = 1;

	while (!is_emptyStack(s)) {
		int now = pop(s);
		rst[idx++] = now;

		for (int i = g->n; i >= 0; i--) {
			if (g->grp[now][i] && !visited[i]) {
				push(s, i);
				visited[i] = 1;
				printf("- visited[%d]: visit\n", i);
			}
		}

		putchar('\n');
	}

	for (int i = 0; i < idx; i++)
		printf("%d => ", rst[i]);
	putchar('\n');
}

static void dfs_recursive(GraphType* g, int start, int visited[]) {
	visited[start] = 1;
	printf("%d => ", start);

	for (int i = 1; i <= g->n; i++)
		if (g->grp[start][i] && !visited[i]) 
			dfs_recursive(g, i, visited);
		
}

static int* bfs(GraphType* g, int start) {
	QueueType* q = (QueueType*)malloc(sizeof(QueueType));
	init_queue(q);

	int rst[MAX] = { 0, };
	int idx = 0;

	int visited[MAX] = { 0, };

	enqueue(q, start);
	printf("- visited[%d]: visit\n", start);
	visited[start] = 1;

	while (!is_empty(q)) {
		int now = dequeue(q);
		rst[idx++] = now;

		for (int i = 1; i <= g->n; i++) {
			if (g->grp[now][i] && !visited[i]) {
				enqueue(q, i);
				visited[i] = 1;

				printf("- visited[%d]: visit\n", i);
			}
		}

		putchar('\n');
	}

	free(q);

	for (int i = 0; i < idx; i++)
		printf("%d => ", rst[i]);
	putchar('\n');

	return rst;
}

static void print_graph(int arr[][MAX], int n) {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++)
			printf("%-5d", arr[i][j]);
		putchar('\n');
	}
}

void graph_assignment() {
	GraphType* g = (GraphType*)malloc(sizeof(GraphType));
	init_graph(g);

	g->n = 6;

	insert_edge(g, 1, 2);
	insert_edge(g, 1, 3);
	insert_edge(g, 1, 6);
	insert_edge(g, 2, 4);
	insert_edge(g, 3, 4);
	insert_edge(g, 3, 5);
	insert_edge(g, 4, 5);
	insert_edge(g, 5, 6);

	print_graph(g->grp, g->n);

	puts("\n");

	puts("[ ************ BFS ************ ]");
	bfs(g, 1);

	puts("\n");

	puts("[ ************ DFS ************ ]");
	dfs(g, 1);


	free(g);
}