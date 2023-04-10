#include <stdio.h>
#include <stdlib.h>
#define MAX 32001

typedef struct {
    int front, rear;
    int q[MAX];
} QUEUE;

static void init_queue(QUEUE* q) {
    q->front = q->rear = 0;
}

static int is_full(QUEUE* queue) {
    return queue->rear == MAX - 1;
}

static int is_empty(QUEUE* queue) {
    return queue->front == queue->rear;
}

static void enqueue(QUEUE* queue, int x) {
    if (!is_full(queue))
        queue->q[queue->rear++] = x;
}

static int dequeue(QUEUE* queue) {
    if (is_empty(queue))
        exit(1);

    return queue->q[queue->front++];
}

static int grp[MAX][MAX];
static int indeg[MAX];

static void solved(int n) {
    QUEUE* queue = (QUEUE*) malloc(sizeof(QUEUE));
    init_queue(queue);

    int rst[MAX] = { 0, };
    int idx = 0;

    for (int i = 1; i <= n; i++)
        if (!indeg[i])
            enqueue(queue, i);

    while (!is_empty(queue)) {
        int now = dequeue(queue);
        rst[idx++] = now;

        for (int i = 1; i <= n; i++) {
            int next = grp[now][i];
            if (next) indeg[next] -= 1;
            if (!indeg[next]) enqueue(queue, next);
        }
    }

    for (int i = 0; i < idx; i++) printf("%d ", rst[i]);
    free(queue);
}

void boj_2252() {
    int n, m;
    scanf("%d %d", &n, &m);

    for (int i = 0; i < m; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        grp[a][b] = 1;
        indeg[b]++;
    }

    solved(n);
}