#include <iostream>
#include <cstring>

constexpr int MAX = 30;
using namespace std;

class Str_LK {
	typedef struct node {
		char name[MAX];
		struct node* next;
	} Node;

	void insert_head(char* name) {
		Node* p = new Node;

		strcpy_s(p->name, name);
		p->next = head;
		head = p;
	}

	Node* head;

public:
	Str_LK() { this->head = NULL; }
	
	~Str_LK() {
		Node* p = head;
		while (p) {
			head = p->next;
			delete p;
			p = head;
		}
	}

	void insert(char* name) {
		if (!head) {
			insert_head(name);
		}
		else {
			Node* p = head;
			while (p->next) p = p->next;

			p->next = new Node;
			p = p->next;

			strcpy_s(p->name, name);
			p->next = NULL;
		}
	}

	bool search(char* name) {
		for (Node* p = head; p != NULL; p = p->next)
			if (strcmp(p->name, name) == 0)
				return true;

		return false;
	}

	void print_all() {
		for (Node* p = head; p != NULL; p = p->next)
			cout << p->name << " => ";
		cout << endl;
	}
};

void list2_test() {
	Str_LK slk;

	int n;
	cin >> n;

	while (n--) {
		char name[MAX];
		cin >> name;

		slk.insert(name);
	}

	cout << "\n\n" << "[입력이 완료되었습니다]" << "\n\n";

	slk.print_all();

	cout << "검색? ";
	char name[MAX];
	cin >> name;

	if (slk.search(name)) cout << name << " 존재!";
	else cout << "존재하지 않는 이름!";
	cout << endl;
}