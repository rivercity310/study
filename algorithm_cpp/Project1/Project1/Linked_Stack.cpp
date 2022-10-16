#include <iostream>

using namespace std;

class Linked_Stack {
private:
	typedef struct node {
		int data;
		struct node* next;
	} Node;

	Node* tos;

public:
	Linked_Stack() { this->tos = NULL; }

	~Linked_Stack() {
		while (tos) {
			Node* tmp = tos->next;
			cout << tos->data << " 메모리 해제\n";
			delete tos;
			tos = tmp;
		}
	}

	bool isempty() {
		return this->tos == NULL;
	}

	void insert(int data) {
		Node* tmp = new Node;
		if (tmp) {
			tmp->data = data;
			tmp->next = tos;
			tos = tmp;
		}
	}

	int pop() {
		if (isempty()) {
			fprintf(stderr, "Stack Empty\n");
			exit(1);
		}
		else {
			Node* tmp = tos;
			int data = tmp->data;
			tos = tmp->next;
			delete tmp;

			return data;
		}
	}

	void print() {
		for (Node* p = tos; p != NULL; p = p->next)
			cout << p->data << " => ";
		cout << "NULL\n";
	}
};

void Linked_Stack_Test() {
	Linked_Stack stk;

	int n;
	cin >> n;

	for (int i = 0; i < n; i++)
		stk.insert(i);

	stk.print();
}
