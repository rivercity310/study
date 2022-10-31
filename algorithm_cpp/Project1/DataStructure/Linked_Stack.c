#include <stdio.h>
#include <stdlib.h>

/*
[ 연결 리스트로 스택 구현하기 ]
*/

typedef struct stk_node {
	int data;
	struct stk_node* next;
} StackNode;

typedef struct {
	StackNode* top;
} LinkedStackType;

static void ls_init(LinkedStackType* s) {
	s->top = NULL;
}

static int ls_isempty(LinkedStackType* s) {
	return s->top == NULL;
}

static void ls_push(LinkedStackType* s, int data) {
	StackNode* tmp;
	if ((tmp = (StackNode*)malloc(sizeof(StackNode)))) {
		tmp->data = data;
		tmp->next = s->top;
		s->top = tmp;
	}
}

static void ls_print(LinkedStackType* s) {
	for (StackNode* p = s->top; p; p = p->next)
		printf("%d->", p->data);
	puts("NULL\n");
}

static int ls_pop(LinkedStackType* s) {
	if (ls_isempty(s)) {
		fprintf(stderr, "Stack Empty!\n");
		exit(1);
	}
	
	StackNode* tmp = s->top;
	int data = tmp->data;
	s->top = s->top->next;
	free(tmp);

	return data;
}

static void ls_terminate(LinkedStackType* s) {
	while (s->top) {
		int x = ls_pop(s);
		printf("메모리 해제: %d\n", x);
	};
}

void Linked_Stack() {
	LinkedStackType s;
	ls_init(&s);

	int n = 5;
	for (int i = 1; i <= n; i++) {
		ls_push(&s, i);
		ls_print(&s);
	}

	puts("\n");

	for (int i = 1; i < n; i++) {
		printf("pop: %d\n", ls_pop(&s));
		ls_print(&s);
	}

	ls_terminate(&s);
}