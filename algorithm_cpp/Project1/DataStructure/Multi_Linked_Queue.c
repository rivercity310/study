#include <stdio.h>
#include <stdlib.h>

#define MAX 3

typedef struct queueNode {
	int data;
	struct queueNode* next;
} QueueNode;

typedef struct {
	QueueNode* front;
} LinkedQueueType;

/* 
1. static 함수는 다른 파일에서 호출하지 못한다.
2. 다른 파일에서 동일한 함수명을 사용할 수 있다.
*/
static QueueNode* allocNode() {
	return (QueueNode*)malloc(sizeof(QueueNode));
}

static void init(LinkedQueueType q[], int size) {
	for (int i = 0; i < size; i++) {
		q[i].front = NULL;
	}
}

static int is_empty(LinkedQueueType q[], int i) {
	return q[i].front == NULL;
}

static void push(LinkedQueueType q[], int data, int i) {
	QueueNode* tmp = allocNode();
	tmp->data = data;
	tmp->next = NULL;

	// 해당 큐가 비어있으면
	if (is_empty(q, i)) {
		q[i].front = tmp;
		return;
	}

	// 끝 노드를 찾고 그 다음 노드에 푸쉬
	QueueNode* p = q[i].front;
	while (p->next) p = p->next;
	p->next = tmp;

}

static int pop(LinkedQueueType q[], int i) {
	QueueNode* tmp = q[i].front;
	int data = tmp->data;

	q[i].front = q[i].front->next;

	free(tmp);
	return data;
}

static void print_queue(LinkedQueueType q[], int i) {
	QueueNode* tmp = q[i].front;

	for (; tmp; tmp = tmp->next) 
		printf("%d => ", tmp->data);
	puts("NULL");
}

static void terminate(LinkedQueueType q[], int size) {
	for (int i = 0; i < size; i++) {
		QueueNode* tmp = q[i].front;
		QueueNode* p = tmp;

		printf("[ Queue(%d) 메모리 해제 작업 수행 ]\n", i);
		while (tmp) {
			tmp = p->next;
			printf("%d 해제\n", p->data);
			free(p);
			p = tmp;
		}

		puts("\n");
	}
}

void Multi_Linked_Queue_Test() {
	LinkedQueueType q[MAX];
	init(q, MAX);

	for (int i = 0; i < MAX; i++) {
		
		printf("[ Queue(%d) push 작업을 수행합니다. ]\n", i);

		push(q, 1, i);
		push(q, 2, i);
		push(q, 3, i);
		print_queue(q, i);

		puts("\n");
	}

	for (int i = 0; i < MAX; i++) {
		printf("q[%d]의 주소 = %d\n", i, &q[i]);
		printf("q[%d].front에 연결된 노드들의 주소\n", i);
		for (QueueNode* tmp = q[i].front; tmp; tmp = tmp->next)
			printf("tmp->data = %d, data의 주소 = %lu, tmp->next = %lu, tmp->next의 주소 = %lu\n", tmp->data, &(tmp->data), tmp->next, &tmp->next);

		puts("\n");
	}

	puts("\n");

	
	for (int i = 0; i < MAX; i++) {
		
		printf("[ Queue(%d) pop 작업을 수행합니다. ]\n", i);
		printf("q[%d] = %u, q[%d].front = %u\n", i, &q[i], i, q[i].front);

		putchar('\n');

		while (!is_empty(q, i)) {
			int a = pop(q, i);
			printf("poped: %d\n", a);
			print_queue(q, i);
		}

		puts("---------------------------------------------------------------------------------------\n");
	}
	
	terminate(q, MAX);
}
