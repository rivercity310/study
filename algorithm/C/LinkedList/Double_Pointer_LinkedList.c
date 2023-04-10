#include <stdio.h>
#include <stdlib.h>

typedef struct node {
	int data;
	struct node* next;
} Node;

static void dp_add(Node** pp_head, Node** pp_tail, int x) {
	if (*pp_head) {
		(*pp_tail)->next = (Node*)malloc(sizeof(Node));
		*pp_tail = (*pp_tail)->next;
	}
	else {
		*pp_head = (Node*)malloc(sizeof(Node));
		*pp_tail = *pp_head;
	}

	if (*pp_tail) {
		(*pp_tail)->data = x;
		(*pp_tail)->next = NULL;
	}
}

static void dp_terminate(Node* p) {
	Node* tmp = p;
	
	while (tmp) {
		p = p->next;
		free(tmp);
		tmp = p;
	}
}

void DoublePointer_LinkedList() {
	Node* p_head = NULL;
	Node* p_tail = NULL;

	int tmp;
	while (1) {
		printf("Data: ");
		scanf("%d", &tmp);
		if (tmp == 9999) break;

		dp_add(&p_head, &p_tail, tmp);
	}

	int sum = 0;
	
	for (Node* p = p_head; p; p = p->next) {
		if (p != p_head) printf(" + ");
		printf("%d", p->data);
		sum += p->data;
	}
	printf(" = %d\n", sum);

	dp_terminate(p_head);
}