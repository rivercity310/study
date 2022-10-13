#include <iostream>

using namespace std;

class Linked {
	typedef struct node {
		int data;
		struct node* next;
	} Node;

	Node* alloc_node() {
		return new Node;
	}

	void insert_first(int x) {
		Node* p = alloc_node();

		p->data = x;
		p->next = head;
		head = p;
	}

	Node* head;

public:
	Linked() {
		head = NULL;
	}

	~Linked() {
		Node* p = head;
		while (p) {
			head = p->next;
			delete p;
			p = head;
		}
	}

	void insert(int x) {
		if (!head)
			insert_first(x);
		else {
			Node* p = head;
			while (p->next)
				p = p->next;

			p->next = alloc_node();
			p = p->next;
			
			p->data = x;
			p->next = NULL;
		}
	}

	void delete_first() {
		if (head) {
			Node* tmp = head;
			head = head->next;
			delete tmp;
		}
	}

	void print_all() {
		Node* p = head;

		while (p) {
			cout << p->data << " ";
			p = p->next;
		}
	}

	void print_all2() {
		for (Node* p = head; p != NULL; p = p->next)
			cout << p->data << " ";
	}
};

void list0_test() {
	Linked lk;

	while (true) {
		int x;
		cin >> x;

		if (x == -1) break;
		else lk.insert(x);
	}

	cout << "\n\n\n";

	while (true) {
		int x;
		cin >> x;

		if (x == 1) lk.delete_first();
		else break;
	}

	lk.print_all2();
}