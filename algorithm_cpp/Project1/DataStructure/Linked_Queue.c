#include <stdio.h>
#include <stdlib.h>

/*
[ 연결리스트로 구현한 큐 ]
*/

typedef struct q_node {
	int data;
	struct q_node* next;
} QueueNode;

typedef struct {
	QueueNode* front;
	QueueNode* rear;
} LinkedQueueType;

static void lq_init(LinkedQueueType* q) {
	q->front = q->rear = NULL;
}

static int lq_isempty(LinkedQueueType* q) {
	return q->rear == NULL;
}

static void lq_enqueue(LinkedQueueType* q, int data) {
	QueueNode* tmp;
	if ((tmp = (QueueNode*)malloc(sizeof(QueueNode)))) {
		tmp->data = data;
		tmp->next = NULL;

		if (lq_isempty(q)) {
			q->front = tmp;
			q->rear = tmp;
		}
		else {
			q->rear->next = tmp;
			q->rear = tmp;
		}
	}
}

static int lq_dequeue(LinkedQueueType* q) {
	QueueNode* tmp = q->front;
	
	if (lq_isempty(q)) {
		fprintf(stderr, "Queue Empty!\n");
		exit(1);
	}

	int data = tmp->data;
	q->front = q->front->next;
	if (q->front == NULL)
		q->rear = NULL;

	free(tmp);
	return data;
}

static void lq_print(LinkedQueueType* q) {
	for (QueueNode* tmp = q->front; tmp; tmp = tmp->next)
		printf("%d->", tmp->data);
	printf("NULL\n");
}

static void lq_terminate(LinkedQueueType* q) {
	while (q->rear) {
		int x = lq_dequeue(q);
		printf("데이터 해제: %d\n", x);
	}
}

void Linked_Queue() {
	LinkedQueueType q;
	lq_init(&q);

	int n = 10;
	for (int i = 0; i < n; i++) {
		lq_enqueue(&q, i);
		lq_print(&q);
	}

	for (int i = 0; i < n / 2; i++) {
		int x = lq_dequeue(&q);
		printf("dequeue: %d\n", x);
		lq_print(&q);
	}

	lq_terminate(&q);
}