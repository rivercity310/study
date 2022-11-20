#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 5

typedef struct node {
	int data;
	struct node* next;
} Node;

typedef struct {
	Node* head;
	Node* tail;
} LinkedList;

static void ll_init(LinkedList* lst) {
	lst->head = lst->tail = NULL;
}

static void ll_add_node(LinkedList* lst, int x) {
	Node* p;
	if ((p = (Node*)malloc(sizeof(Node)))) {
		p->data = x;
		p->next = NULL;
	}

	if (!lst->head) {
		lst->head = p;
		lst->tail = lst->head;
	}
	else {
		lst->tail->next = p;
		lst->tail = lst->tail->next;
	}
}

static void ll_delete(LinkedList* lst) {
	Node* pre = lst->head;

	while (pre->next->next)
		pre = pre->next;
	
	free(pre->next);
	lst->tail = pre;
	lst->tail->next = NULL;
}

static void ll_terminate(LinkedList* lst) {
	Node* p = lst->head;
	while (p) {
		lst->head = lst->head->next;
		free(p);
		p = lst->head;
	}
	lst->tail = lst->head;
}

static void ll_print(LinkedList* lst) {
	int sum = 0;
	for (Node* p = lst->head; p != NULL; p = p->next) {
		printf("%d", p->data);
		sum += p->data;
		if (p != lst->tail) printf(" + ");
	}
	printf(" = %d\n", sum);
}

void Linked_List() {
	LinkedList* lst = (LinkedList*)malloc(sizeof(LinkedList));
	ll_init(lst);

	for (int i = 0; i < MAX; i++) 
		ll_add_node(lst, rand() % 100);
	
	ll_print(lst);
	putchar('\n');

	for (int i = 0; i < 3; i++)
		ll_delete(lst);

	ll_print(lst);

	ll_terminate(lst);
	free(lst);
}