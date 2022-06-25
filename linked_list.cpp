#include <iostream>
using namespace std;

class linked_list {
private:
	struct node {
		int data;
		node* next;
	};

	node* head = nullptr;
	node* tail = nullptr;

public:
	linked_list(int val) {
		if (head == NULL) {
			head = new node{ val, NULL };
			tail = head;
		}
	}

	~linked_list() {
		while (head != NULL) {
			cout << head->data << " 삭제" << endl;
			node* tmp = head->next;
			delete head;
			head = tmp;
		}

		tail = head;
	}

	void add_data(int val) {
		if (tail != NULL) {
			tail->next = new node{ val, NULL };
			tail = tail->next;
			tail->data = val;
		}
	}

	void del_data(int val) {
		node* p = head;
		
		// 첫번째 노드라면
		if (p->data == val) {
			node* tmp = head->next;
			delete head;
			
			head = tmp;
		}		
		else {
			// 이전 노드 찾기
			node* prv = head;
			while (prv->next->data != val)
				prv = prv->next;
			
			node* tmp = prv->next->next;
			if (!tmp) cout << "NULL" << endl;
			else cout << tmp->data << " 선택" << endl;

			if (!tmp) {
				delete prv->next;
				prv->next = tmp;

				tail = tmp;
			}
			// 마지막 노드를 제거하는 경우
			else {
				cout << "Last Node Del" << endl;
				delete tail;
				tail = prv;
			}
		}
	}

	node* find_data(int val) {
		node* p = head;
		while (p != NULL) {
			if (p->data == val) return p;
			p = p->next;
		}

		return NULL;
	}

	void prt_data() {
		node* p = head;
		while (p != NULL) {
			cout << p->data << " ";
			p = p->next;
		}
		cout << endl;
	}
};

void ll_ex() {
	int n;
	cout << "초깃값: ";
	cin >> n;
	
	auto p = new linked_list(n);

	while (true) {
		int sel;
		cout << "(1)Input   (2)del    (3)종료 >> ";
		cin >> sel;

		int n;
		if (sel == 1) {
			cout << "Input: ";
			cin >> n;

			p->add_data(n);
		}
		else if (sel == 2) {
			cout << "Delete: ";
			cin >> n;
			if (p->find_data(n)) {
				p->del_data(n);
				cout << n << " 삭제!" << endl;
			}
			else cout << n << " 찾을 수 없음!" << endl;
		}
		else if (sel == 3) break;
		else cout << "\a유효하지 않은 입력값!" << endl;
		
		cout << "\n\n연결 리스트 출력" << endl;
		p->prt_data();
		cout << "\n\n" << endl;
	}
}