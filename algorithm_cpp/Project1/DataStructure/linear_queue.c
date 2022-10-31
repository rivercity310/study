#include <stdio.h>
#include <stdlib.h>

#define MAX_QUEUE_SIZE 1001

typedef struct {
	int front;
	int rear;
	int data[MAX_QUEUE_SIZE];
} QueueType;

/* 오류 함수 */
void error(const char* msg) {
	fprintf(stderr, "%s\n", msg);
	exit(1);
}

/* 초기화 함수 */
void queue_init(QueueType* q) {
	q->rear = -1;
	q->front = -1;
	
	printf("init 함수 내 구조체변수 q의 주소 = %p\n", &q);
	printf("q가 가지고 있는 주소값 = %p\n", q);
	printf("q->front 주소 = %p\n", &(q->front));
	printf("q->rear 주소 = %p\n", &(q->rear));
	printf("q->data 주소 = %p\n", q->data);
}

/* 프린트 함수 */
void queue_print(QueueType* q) {
	for (int i = 0; i < MAX_QUEUE_SIZE; i++) {
		if (i <= q->front || i > q->rear)
			printf("   | ");
		else
			printf("%d | ", q->data[i]);
	}

	putchar('\n');
}

/* 조건 검사 */
int queue_is_full(QueueType* q) {
	return q->rear == MAX_QUEUE_SIZE - 1;
}

int queue_is_empty(QueueType* q) {
	return q->front == q->rear;
}

/* 인큐 */
void queue_Enqueue(QueueType* q, int data) {
	if (queue_is_full(q)) {
		error("Queue Full!");
		return;
	}

	q->data[++(q->rear)] = data;
}

/* 디큐 */
int queue_Dequeue(QueueType* q) {
	if (queue_is_empty(q)) {
		error("Queue Empty!");
		return -1;
	}

	return q->data[++(q->front)];
}


void linear_queue() {
	QueueType q;

	queue_init(&q);

	int x;
	while (1) {
		printf("Enqueue: ");
		scanf_s("%d", &x);
		if (x == -1) break;
		queue_Enqueue(&q, x);
	}

	queue_print(&q);

	while (!queue_is_empty(&q)) {
		x = queue_Dequeue(&q);
		printf("Dequeue: %d\n", x);
	}

	return;
}
