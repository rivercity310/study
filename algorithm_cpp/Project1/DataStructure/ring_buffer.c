#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_QUEUE_SIZE 20

extern void error(const char* msg);

/*
[ 원형 큐 ]
- front: 첫번째 요소 하나 앞의 인덱스
- rear: 마지막 요소 인덱스
- 공백과 포화 상태를 구별하기 위해 하나의 공간은 비워둔다
	- 공백 (front = rear)
	- 포화 (front = (rear + 1) % MAX_QUEUE_SIZE)
*/

typedef struct {
	int front;
	int rear;
	int data[MAX_QUEUE_SIZE];
} RingBuffer;

/* 원형큐 초기화 함수 */
static void rb_init(RingBuffer* rbuf) {
	rbuf->front = rbuf->rear = 0;
}

/* 상태 조건 검사 함수 */
static int rb_is_empty(RingBuffer* rbuf) {
	return rbuf->front == rbuf->rear;
}

static int rb_is_full(RingBuffer* rbuf) {
	return rbuf->front == (rbuf->rear + 1) % MAX_QUEUE_SIZE;
}

/* 원형큐 출력 함수 */
static void rb_print(RingBuffer* rbuf) {
	printf("[front = %d, rear = %d]\n", rbuf->front, rbuf->rear);

	if (!rb_is_empty(rbuf)) {
		int i = rbuf->front;
		
		do {
			i = (i + 1) % MAX_QUEUE_SIZE;
			printf("%d | ", rbuf->data[i]);
			if (i == rbuf->rear)
				break;
		} while (i != rbuf->front);
	}

	putchar('\n');
}

/* 인큐 함수 */
static void rb_enqueue(RingBuffer* rbuf, int x) {
	if (rb_is_full(rbuf))
		error("RingBuffer is Full!");

	rbuf->rear = (rbuf->rear + 1) % MAX_QUEUE_SIZE;
	rbuf->data[rbuf->rear] = x;
}

/* 디큐 함수 */
static int rb_dequeue(RingBuffer* rbuf) {
	if (rb_is_empty(rbuf))
		error("RingBuffer is Empty!");

	rbuf->front = (rbuf->front + 1) % MAX_QUEUE_SIZE;
	return rbuf->data[rbuf->front];
}

void ring_buffer() {
	RingBuffer* rb = (RingBuffer*)malloc(sizeof(RingBuffer));
	rb_init(rb);

	srand(time(NULL));

	for (int i = 0; i < MAX_QUEUE_SIZE; i++) {
		if (rand() % 5 == 0) 
			rb_enqueue(rb, rand() % 100);

		rb_print(rb);

		if (rand() % 20 == 0) {
			int data = rb_dequeue(rb);
		}

		rb_print(rb);
	}

	free(rb);
}

