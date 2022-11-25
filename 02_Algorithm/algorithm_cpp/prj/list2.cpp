#include <iostream>
#include <cstring>

constexpr int MAX = 30;
using namespace std;

typedef struct node {
	char name[MAX];
	struct node* next;
} Node;


class Str_LK {
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

	Node* get_head() {
		return head;
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

Node* concat_list(Str_LK* slk1, Str_LK* slk2) {
	if (!slk1->get_head() || !slk2->get_head()) 
		return slk2->get_head();
	else {
		Node* p = slk1->get_head();
		while (p->next)
			p = p->next;

		p->next = slk2->get_head();
		return slk1->get_head();
	}
}

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

/* 두 연결리스트 합치기 */
void list2_test_01() {
	Str_LK slk_lst[2];
	int sizes[2];

	for (int i = 0; i < 2; i++) {
		Str_LK slk;
		
		cout << i + 1 << "번째 연결리스트 작성" << "\n";
		
		cout << "개수: ";
		int n;
		cin >> n;
		sizes[i] = n;

		while (n--) {
			char name[MAX];
			cin >> name;

			slk.insert(name);
		}

		slk_lst[i] = slk;
	}

	Node* rst = concat_list(&slk_lst[0], &slk_lst[1]);
	for (; rst != NULL; rst = rst->next)
		cout << rst->name << " => ";
	cout << endl;
}