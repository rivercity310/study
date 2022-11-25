#include <stdio.h>
#include <stdlib.h>

#define MAX 20

typedef struct node {
	int data;
	struct node* next;
} Node;

static Node* rll_insert_first(Node* head, int x) {
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

		tmp->next = head;
		head = tmp;
		p->next = tmp;
	}

	return head;
}

static Node* rll_insert_last(Node* head, int x) {
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

		p->next = tmp;
		tmp->next = head;
	}

	return head;
}

static void rll_print(Node* head) {
	if (head == NULL) return;

	for (Node* p = head; p->next != head; p = p->next)
		printf("%d ", p->data);
	putchar('\n');
}

static void rll_terminate(Node* head) {
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