#include <iostream>

using namespace std;

typedef struct node {
	int data;
	struct node* next;
} Node;

void add(Node** pp_head, Node** pp_tail, int data) {
	if (*pp_head) {
		(*pp_tail)->next = (Node*)malloc(sizeof(Node));
		*pp_tail = (*pp_tail)->next;
	}
	else {
		*pp_head = (Node*)malloc(sizeof(Node));
		*pp_tail = *pp_head;
	}

	if (*pp_tail) {
		(*pp_tail)->data = data;
		(*pp_tail)->next = NULL;
	}
}

void dp_linkedlist_test() {
	Node* p_head = NULL;
	Node* p_tail = NULL;

	int tmp;
	while (true) {
		cout << "Data: ";
		cin >> tmp;

		if (tmp == 9999) break;
		add(&p_head, &p_tail, tmp);
	}

	int sum = 0;
	Node* p = p_head;
	while (p != NULL) {
		if (p != p_head) cout << " + ";
		cout << p->data;
		sum += p->data;
		p = p->next;
	}
	cout << " = " << sum << "\n";

	while (p_head != NULL) {
		p = p_head;
		p_head = p_head->next;
		cout << p->data << " 메모리 해제\n";
		free(p);
	}
	p_tail = p_head;
}