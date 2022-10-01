#include <iostream>

using namespace std;

typedef struct node {
	int data;
	struct node* next;
} Node;

void list1_test() {
	Node* root = new Node{ 1, NULL };
	Node* p = root;

	while (true) {
		int x;
		cin >> x;

		if (x == -1) break;
		p->next = new Node{ x, NULL };
		p = p->next;
	}

	p = root;
	while (p) {
		cout << p->data << " ";
		p = p->next;
	}
	cout << endl;

	p = root;
	while (p) {
		root = p->next;
		cout << p->data << "»èÁ¦\n";
		delete p;
		p = root;
	}
}