#include <stdio.h>
#include <stdlib.h>

#define MAX 1001

typedef struct {
	int front;
	int rear;
	int arr[MAX];
} QueueType;

static void queue_init(QueueType* q) {
	q->front = q->rear = -1;
}

static void queue_push(QueueType* q, int data) {
	q->arr[++q->rear] = data;
}

static int queue_pop(QueueType* q) {
	return q->arr[++q->front];
}

static int queue_empty(QueueType* q) {
	return q->front == q->rear;
}

int grp[MAX][MAX];
int visited[MAX];

static void dfs(int start, int n) {
	visited[start] = 1;
	printf("%d ", start);

	for (int i = 1; i <= n; i++)
		if (grp[start][i] && !visited[i])
			dfs(i, n);
}

static void bfs(int start, int n) {
	QueueType* q = (QueueType*)malloc(sizeof(QueueType));
	queue_init(q);
	queue_push(q, start);
	visited[start] = 1;

	while (!queue_empty(q)) {
		int now = queue_pop(q);
		printf("%d ", now);

		for (int i = 1; i <= n; i++) {
			if (grp[now][i] && !visited[i]) {
				queue_push(q, i);
				visited[i] = 1;
			}
		}
	}

	free(q);
}

void graph_sch() {
	int n, m, v;
	scanf("%d %d %d", &n, &m, &v);

	for (int i = 0; i < m; i++) {
		int a, b;
		scanf("%d %d", &a, &b);

		grp[a][b] = 1;
		grp[b][a] = 1;
	}

	dfs(v, n);

	for (int i = 1; i <= n; i++)
		visited[i] = 0;
	putchar('\n');

	bfs(v, n);
}