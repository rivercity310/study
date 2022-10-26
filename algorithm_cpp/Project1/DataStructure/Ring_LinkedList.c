#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 20

/*
[ 원형 연결리스트 ]
- 마지막 노드의 링크가 첫번째 노드를 가리키는 리스트
  => 한 노드에서 다른 모든 노드로 접근이 가능하다는 장점
*/

typedef struct node {
	int data;
	struct node* next;
} Node;

Node* rll_insert_first(Node* head, int x) {
	Node* tmp;
	if ((tmp = (Node*)malloc(sizeof(Node)))) {
		tmp->data = x;

		if (head == NULL) {
			head = tmp;
			tmp->next = head;
			return head;
		}
		
		/* p를 마지막 노드로 이동 */
		Node* p = head;
		do {
			p = p->next;
		} while (p->next != head);

		tmp->next = head;
		head = tmp;
		p->next = tmp;
	}

	return head;
}

Node* rll_insert_last(Node* head, int x) {
	Node* tmp;
	if ((tmp = (Node*)malloc(sizeof(Node)))) {
		tmp->data = x;

		if (head == NULL) {
			head = tmp;
			tmp->next = head;
			return head;
		}

		Node* p = head;
		do {
			p = p->next;
		} while (p->next != head);

		/* 
		마지막 노드가 생성된 tmp 노드를 가리키게 하고, 
		마지막 노드가 head 노드를 가리키게 한다.
		*/
		p->next = tmp;
		tmp->next = head;
	}

	return head;
}

void rll_print(Node* head) {
	if (head == NULL) return;

	for (Node* p = head; p->next != head; p = p->next)
		printf("%d ", p->data);
	putchar('\n');
}

void rll_terminate(Node* head) {
	Node* p = head;
	while (p->next != head) {
		Node* tmp = p->next;
		printf("Dealloc: %d\n", p->data);
		free(p);
		p = tmp;
	}
}

void Ring_LinkedList() {
	Node* head = NULL;

	for (int i = 0; i < MAX; i++) {
		if (i < 10) head = rll_insert_first(head, i);
		else head = rll_insert_last(head, i);
	}

	rll_print(head);
	rll_terminate(head);
}