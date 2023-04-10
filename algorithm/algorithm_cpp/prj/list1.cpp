#include <iostream>
#include <vector>
#include <iomanip>

/*
[ 단순 연결 리스트 구현 ]
*/

using namespace std;

typedef struct node {
	int data;
	struct node* next;
} Node;

/* 연결 리스트를 관리하는 구조체 */
typedef struct {
	Node* head;			/* 머리 노드를 가리키는 포인터 */
	Node* current;		/* 현재 선택한 노드를 가리키는 포인터 */
} List;

static Node* AllocNode(void) {
	return new Node;
}

static void SetNode(Node* n, int x, Node* next) {
	n->data = x;
	n->next = next;
}

void Initialize(List* list) {
	list->head = NULL;
	list->current = NULL;
}

Node* Search(List* list, int x) {
	Node* p = list->head;
	while (p) {
		if (p->data == x) {
			list->current = p;
			return p;
		}

		p = p->next;
	}

	return NULL;
}

/* 머리에 노드 삽입 */
void InsertFront(List* list, int x) {
	Node* p = list->head;
	list->head = list->current = AllocNode();
	SetNode(list->head, x, p);
}

/* 꼬리에 노드 삽입 */
void InsertRear(List* list, int x) {
	if (!list->head)
		InsertFront(list, x);
	else {
		Node* p = list->head;
		
		/* p가 꼬리 노드를 가리키도록 설정 */
		while (p->next)
			p = p->next;

		p->next = list->current = AllocNode();
		SetNode(p->next, x, NULL);
	}
}

/* 머리 노드 삭제 */
void RemoveFront(List* list) {
	if (list->head) {
		Node* p = list->head->next;
		delete list->head;
		list->head = list->current = p;
	}
}

/* 꼬리 노드 삭제 */
void RemoveRear(List* list) {
	if (list->head) {
		if (list->head->next == NULL)
			RemoveFront(list);
		else {
			Node* p = list->head;
			Node* pre = NULL;

			while (p->next) {
				pre = p;
				p = p->next;
			}

			pre->next = NULL;
			delete p;
			list->current = pre;
		}
	}
}

/* 선택한 노드 삭제 */
void RemoveCurrent(List* list) {
	if (list->head) {
		if (list->current == list->head)
			RemoveFront(list);
		else {
			Node* p = list->head;
			
			while (p->next != list->current)
				p = p->next;

			p->next = list->current->next;
			delete list->current;
			list->current = p;
		}
	}
}

/* 모든 노드 삭제 */
void Clear(List* list) {
	while (list->head)
		RemoveFront(list);
	list->current = NULL;
}

/* 모든 노드 출력 */
void PrintNodes(const List* list) {
	if (list->head == NULL)
		puts("노드가 없습니다!");
	else {
		Node* p = list->head;

		while (p) {
			cout << p->data << " ";
			p = p->next;
		}
		cout << endl;
	}
}

int Select_Menu() {
	vector<string> msg = {
		"머리에 노드 삽입", "꼬리에 노드 삽입", "머리 노드 삭제",
		"꼬리 노드 삭제", "선택 노드 삭제", "노드 검색", "모든 노드 출력",
		"모든 노드 삭제 & 종료"
	};

	int ch = NULL;
	do {
		for (int i = 0; i < msg.size(); i++) {
			cout << "(" << i + 1 << ")";
			cout << setw(30) << left << msg[i];
			if (i % 3 == 2)
				putchar('\n');
		}
		cout << endl;

		cin >> ch;

	} while (ch < 0 || ch > msg.size());

	return ch;
}

void list1_test() {
	List list;
	Initialize(&list);

	do {
		int x;
		switch (Select_Menu()) {
		/* 머리에 삽입 */
		case 1:
			cout << "삽입할 데이터: ";
			cin >> x;
			InsertFront(&list, x);
			break;

		/* 꼬리에 삽입 */
		case 2:
			cout << "삽입할 데이터: ";
			cin >> x;
			InsertRear(&list, x);
			break;

		/* 머리 삭제 */
		case 3:
			RemoveFront(&list);
			break;

		/* 꼬리 삭제 */
		case 4:
			RemoveRear(&list);
			break;

		/* 선택 노드 삭제 */
		case 5:
			RemoveCurrent(&list);
			break;

		/* 노드 검색 */
		case 6: {
			cout << "검색할 데이터: ";
			cin >> x;

			Node* p = Search(&list, x);
			if (p) cout << p->data << " 존재\n";
			else cout << "존재하지 않는 데이터\n";
			break;
		}

		/* 모든 노드 출력 */
		case 7:
			PrintNodes(&list);
			break;

		/* 모든 노드 삭제 & 종료 */
		case 8:
			Clear(&list);
			return;
		}
	} while (true);
}