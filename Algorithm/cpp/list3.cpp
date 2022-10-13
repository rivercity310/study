#include <iostream>

using namespace std;

/*
[ 연결리스트 응용 : 다항식 표현 ]
*/

typedef struct node {
	int coef;
	int expo;
	struct node* next;
} Node;

typedef struct {
	int size;
	Node* head;
	Node* tail;
} ListType;

void error(const char* msg) {
	fprintf(stderr, "%s\n", msg);
	exit(1);
}

ListType* create() {
	ListType* plist = new ListType;
	plist->size = 0;
	plist->head = plist->tail = NULL;

	return plist;
}

void insert_last(ListType* plist, int coef, int expo) {
	Node* tmp = new Node;
	if (!tmp) 
		error("메모리 할당 에러");

	tmp->coef = coef;
	tmp->expo = expo;
	tmp->next = NULL;

	if (!plist->tail)
		plist->head = plist->tail = tmp;
	else {
		plist->tail->next = tmp;
		plist->tail = plist->tail->next;
	}

	plist->size++;
}

void poly_add(ListType* plist1, ListType* plist2, ListType* plist3) {
	Node* a = plist1->head;
	Node* b = plist2->head;

	while (a && b) {
		if (a->expo == b->expo) {
			int sum = a->coef + b->coef;
			if (sum) insert_last(plist3, sum, a->expo);
			a = a->next;
			b = b->next;
		}
		else if (a->expo > b->expo) {
			insert_last(plist3, a->coef, a->expo);
			a = a->next;
		}
		else {
			insert_last(plist3, b->coef, b->expo);
			b = b->next;
		}
	}

	for (; a != NULL; a = a->next)
		insert_last(plist3, a->coef, a->expo);

	for (; b != NULL; b = b->next)
		insert_last(plist3, b->coef, b->expo);
}

void poly_print(ListType* plist) {
	Node* p = plist->head;

	printf("polynomial = ");
	for (; p; p = p->next) {
		printf("%dx^%d", p->coef, p->expo);
		if (p->next) printf(" + ");
	}
	putchar('\n');
}

void list3_test() {
	ListType* list1, * list2, * list3;

	list1 = create();
	list2 = create();
	list3 = create();

	/* list1 : 3x^12 + 2x^8 + 1 */
	insert_last(list1, 3, 12);
	insert_last(list1, 2, 8);
	insert_last(list1, 1, 0);

	/* list2 : 8x^12 - 3x^10 + 10x^6 */
	insert_last(list2, 8, 12);
	insert_last(list2, -3, 10);
	insert_last(list2, 10, 6);

	poly_print(list1);
	poly_print(list2);

	poly_add(list1, list2, list3);
	poly_print(list3);

	delete list1;
	delete list2;
	delete list3;
}