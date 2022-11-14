#include <stdio.h>
#include <stdlib.h>

// bfs를 위한 간단한 큐 구현
#define MAX 50

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

/* rear가 MAX에 도달하면 가득찬걸로 간주 */
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

// dfs 구현을 위한 간단한 스택 구현
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

typedef struct linked {
	int data;
	struct linked* next;
} LinkedType;

typedef struct gType {
	int n;
	LinkedType* grp[MAX];
} GraphType;

static void init_graph(GraphType* g, int n) {
	g->n = n;
	for (int i = 1; i < MAX; i++)
		g->grp[i] = NULL;
}

static void insert_edge(GraphType* g, int start, int end) {
	LinkedType* tmp = (LinkedType*)malloc(sizeof(LinkedType));
	tmp->data = end;
	tmp->next = NULL;

	if (g->grp[start]) {
		LinkedType* p = g->grp[start];
		tmp->next = p;
	}

	g->grp[start] = tmp;
}

static void print_graph(GraphType* g) {
	for (int i = 1; i <= g->n; i++) {
		LinkedType* tmp = g->grp[i];

		printf("%d번 노드에 연결된 노드 : ", i);
		while (tmp) {
			printf("%d -> ", tmp->data);
			tmp = tmp->next;
		}
		putchar('\n');
	}
}

static void bfs(GraphType* g, int start) {
	QueueType* q = (QueueType*)malloc(sizeof(QueueType));
	init_queue(q);

	int visited[MAX] = { 0, };
	int rst[MAX] = { 0, };
	int idx = 0;

	enqueue(q, start);
	printf("- visited[%d]: 방문처리\n", start);
	visited[start] = 1;

	while (!is_empty(q)) {
		int now = dequeue(q);
		rst[idx++] = now;

		for (LinkedType* tmp = g->grp[now]; tmp; tmp = tmp->next) {
			if (!visited[tmp->data]) {
				enqueue(q, tmp->data);
				printf("- visited[%d]: 방문처리\n", tmp->data);
				visited[tmp->data] = 1;
			}
		}

		putchar('\n');
	}

	for (int i = 0; i < idx; i++)
		printf("%d => ", rst[i]);
}

static void dfs(GraphType* g, int start) {
	StackType* s = (StackType*)malloc(sizeof(StackType));
	init_stack(s);

	int visited[MAX] = { 0, };
	int rst[MAX] = { 0, };
	int idx = 0;

	push(s, start);
	printf("- visited[%d]: 방문처리\n", start);
	visited[start] = 1;

	while (!is_emptyStack(s)) {
		int now = pop(s);
		rst[idx++] = now;

		for (LinkedType* tmp = g->grp[now]; tmp; tmp = tmp->next) {
			if (!visited[tmp->data]) {
				push(s, tmp->data);
				printf("- visited[%d]: 방문처리\n", tmp->data);
				visited[tmp->data] = 1;
			}
		}

		putchar('\n');
	}

	for (int i = 0; i < idx; i++)
		printf("%d => ", rst[i]);
}

void graph_assignment2() {
	GraphType* g = (GraphType*)malloc(sizeof(GraphType));
	init_graph(g, 6);

	insert_edge(g, 1, 2); insert_edge(g, 2, 1);
	insert_edge(g, 1, 3); insert_edge(g, 3, 1);
	insert_edge(g, 1, 6); insert_edge(g, 6, 1);
	insert_edge(g, 2, 4); insert_edge(g, 4, 2);
	insert_edge(g, 3, 4); insert_edge(g, 4, 3);
	insert_edge(g, 3, 5); insert_edge(g, 5, 3);
	insert_edge(g, 4, 5); insert_edge(g, 5, 4);
	insert_edge(g, 5, 6); insert_edge(g, 6, 5);

	puts("[완성된 그래프]");
	print_graph(g);

	putchar('\n');

	puts("[ ************ BFS 수행 ************ ]");
	bfs(g, 1);

	puts("\n");

	puts("[ ************ DFS 수행 ************ ]");
	dfs(g, 1);
}