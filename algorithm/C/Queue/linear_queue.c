#include <stdio.h>
#include <stdlib.h>

#define MAX_QUEUE_SIZE 1001

typedef struct {
	int front;
	int rear;
	int data[MAX_QUEUE_SIZE];
} QueueType;

extern void error(const char* msg) {
	fprintf(stderr, "%s\n", msg);
	exit(1);
}

static void queue_init(QueueType* q) {
	q->rear = -1;
	q->front = -1;
}

static void queue_print(QueueType* q) {
	for (int i = 0; i < MAX_QUEUE_SIZE; i++) {
		if (i <= q->front || i > q->rear)
			printf("   | ");
		else
			printf("%d | ", q->data[i]);
	}

	putchar('\n');
}

static int queue_is_full(QueueType* q) {
	return q->rear == MAX_QUEUE_SIZE - 1;
}

static int queue_is_empty(QueueType* q) {
	return q->front == q->rear;
}

static void queue_Enqueue(QueueType* q, int data) {
	if (queue_is_full(q)) {
		error("Stack Full!");
		return;
	}

	q->data[++(q->rear)] = data;
}

static int queue_Dequeue(QueueType* q) {
	if (queue_is_empty(q)) {
		error("Stack Empty!");
		return -1;
	}

	return q->data[++(q->front)];
}


void linear_queue() {
	QueueType q;

	queue_init(&q);

	int x;
	while (1) {
		printf("StackPush: ");
		scanf("%d", &x);
		if (x == -1) break;
		queue_Enqueue(&q, x);
	}

	queue_print(&q);

	while (!queue_is_empty(&q)) {
		x = queue_Dequeue(&q);
		printf("StackPop: %d\n", x);
	}
}
