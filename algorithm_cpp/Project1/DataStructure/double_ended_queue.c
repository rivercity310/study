#include <stdio.h>
#include <stdlib.h>

#define MAX_QUEUE_SIZE 20

typedef struct {
	int front;
	int rear;
	int data[MAX_QUEUE_SIZE];
} Deque;

extern void error(const char* msg);

static void init_deque(Deque* dq) {
	dq->front = dq->rear = 0;
}

static int dq_is_empty(Deque* dq) {
	return dq->front == dq->rear;
}

static int dq_is_full(Deque* dq) {
	return (dq->rear + 1) % MAX_QUEUE_SIZE == dq->front;
}

static void dq_print(Deque* dq) {
	printf("(front = %d, rear = %d)\t", dq->front, dq->rear);
	
	if (!dq_is_empty(dq)) {
		int i = dq->front;
		do {
			i = (i + 1) % MAX_QUEUE_SIZE;
			printf("%d | ", dq->data[i]);
			if (i == dq->rear) break;
		} while (i != dq->front);
	}

	putchar('\n');
}

static void dq_add_front(Deque* dq, int x) {
	if (dq_is_full(dq))
		error("Deque is Full!");
	
	dq->data[dq->front] = x;
	dq->front = (dq->front - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
}

static void dq_add_rear(Deque* dq, int x) {
	if (dq_is_full(dq))
		error("Deque is Full!");

	dq->rear = (dq->rear + 1) % MAX_QUEUE_SIZE;
	dq->data[dq->rear] = x;
}

static int dq_get_front(Deque* dq) {
	if (dq_is_empty(dq))
		error("Deque is Empty!");

	return dq->data[(dq->front + 1) % MAX_QUEUE_SIZE];
}

static int dq_delete_front(Deque* dq) {
	if (dq_is_empty(dq))
		error("Deque is Empty!");

	dq->front = (dq->front + 1) % MAX_QUEUE_SIZE;
	return dq->data[dq->front];
}

static int dq_delete_rear(Deque* dq) {
	int prev = dq->rear;

	if (dq_is_empty(dq))
		error("Deque is Empty!");

	dq->rear = (dq->rear - 1 + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
	return dq->data[prev];
}

static int dq_get_rear(Deque* dq) {
	if (dq_is_empty(dq))
		error("Deque is Empty!");

	return dq->data[dq->rear];
}

void double_ended_queue() {
	Deque* dq = (Deque*)malloc(sizeof(Deque));
	init_deque(dq);

	for (int i = 0; i < 10; i++) {
		if (i < 5) dq_add_front(dq, i);
		else dq_add_rear(dq, i);
		dq_print(dq);
	}

	printf("Deque Get Front: %d\n", dq_get_front(dq));
	printf("Deque Get Rear: %d\n", dq_get_rear(dq));

	for (int i = 0; i < 6; i++) {
		if (i < 3) dq_delete_front(dq);
		else dq_delete_rear(dq);
		dq_print(dq);
	}

	printf("Deque Get Front: %d\n", dq_get_front(dq));
	printf("Deque Get Rear: %d\n", dq_get_rear(dq));

	free(dq);
}