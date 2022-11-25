#include <iostream>
#include <queue>
using namespace std;

// 이진 트리의 기본 구조 -> 각각의 노드는 다른 두개의 하위 노드에 대한 링크를 가진다.


class org_tree {
private:
	struct node {
		string position;
		node* first;
		node* second;
	};

	node* root;

public:
	org_tree(const string& pos) {
		this->root = new node{ pos, NULL, NULL };
	}

	node* getRoot() {
		return this->root;
	}

	node* find(node* root, const string& pos) {
		if (root == NULL) return NULL;
		if (root->position == pos) return root;

		auto firstFound = find(root->first, pos);
		if (firstFound != NULL) return firstFound;

		return find(root->second, pos);
	}

	bool addSubordinate(const string& manager, const string& subordinate) {
		auto managerNode = find(root, manager);

		if (!managerNode) {
			cout << manager << "을(를) 찾을 수 없습니다." << endl;
			return false;
		}

		if (managerNode->first && managerNode->second) {
			cout << manager << " 아래에 " << subordinate << "을(를) 추가할 수 없습니다." << endl;
			return false;
		}

		if (!managerNode->first) managerNode->first = new node{ subordinate, NULL, NULL };
		else managerNode->second = new node{ subordinate, NULL, NULL };

		cout << manager << " 아래에 " << subordinate << "을(를) 추가했습니다." << endl;
		return true;
	}

	void preOrder(node* start) {
		if (!start) return;

		cout << start->position << ", ";

		preOrder(start->first);
		preOrder(start->second);
	}

	void inOrder(node* start) {
		if (!start) return;

		inOrder(start->first);
		cout << start->position << ", ";
		inOrder(start->second);
	}

	void postOrder(node* start) {
		if (!start) return;

		postOrder(start->first);
		postOrder(start->second);
		cout << start->position << ", ";
	}

	void levelOrder(node* start) {
		if (!start) return;

		queue<node*> q;
		q.push(start);

		while (!q.empty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				auto current = q.front();
				q.pop();

				cout << current->position << ", ";

				if (current->first) q.push(current->first);
				if (current->second) q.push(current->second);
			}
		}
	}
};

void chap2_tree_test() {
	auto root = new org_tree("CEO");

	root->addSubordinate("CEO", "부사장");
	root->addSubordinate("부사장", "IT부장");
	root->addSubordinate("부사장", "마케팅부장");
	root->addSubordinate("IT부장", "보안팀장");
	root->addSubordinate("IT부장", "앱개발팀장");
	root->addSubordinate("마케팅부장", "물류팀장");
	root->addSubordinate("마케팅부장", "홍보팀장");
	root->addSubordinate("부사장", "재무부장");
	cout << "\n" << endl;

	root->preOrder(root->getRoot());
	cout << "\n" << endl;

	root->inOrder(root->getRoot());
	cout << "\n" << endl;

	root->postOrder(root->getRoot());
	cout << "\n" << endl;

	root->levelOrder(root->getRoot());
	cout << "\n" << endl;

	delete root;
}

/*
struct org_tree {
	node* root;
	
	// 루트 노드를 생성하는 함수
	static org_tree create_org_structure(const string& pos) {
		org_tree tree;
		tree.root = new node{ pos, NULL, NULL };

		return tree;
	}

	// 특정 직책 이름(position)에 해당하는 노드를 찾아서 반환하는 함수
	static node* find(node* root, const string& value) {
		if (root == NULL) return NULL;

		if (root->position == value) return root;

		auto firstFound = org_tree::find(root->first, value);
		if (firstFound != NULL) return firstFound;

		return org_tree::find(root->second, value);
	}

	// 새로운 원소(부하 직원)을 추가하는 삽입 함수
	bool addSubordinate(const string& manager, const string& subordinate) {
		auto managerNode = org_tree::find(root, manager);

		if (!managerNode) {
			cout << manager << "을(를) 찾을 수 없습니다: " << endl;
			return false;
		}

		if (managerNode->first && managerNode->second) {
			cout << manager << " 아래에 " << subordinate << "을(를) 추가할 수 없습니다." << endl;
			return false;
		}

		if (!managerNode->first) managerNode->first = new node{ subordinate, NULL, NULL };
		else managerNode->second = new node{ subordinate, NULL, NULL };

		cout << manager << " 아래에 " << subordinate << "을(를) 추가했습니다." << endl;
		return true;
	}

	static void preOrder(node* start) {
		if (!start) return;

		cout << start->position << ", ";

		preOrder(start->first);
		preOrder(start->second);
	}

	static void inOrder(node* start) {
		if (!start) return;

		inOrder(start->first);
		cout << start->position << ", ";
		inOrder(start->second);
	}

	static void postOrder(node* start) {
		if (!start) return;

		postOrder(start->first);
		postOrder(start->second);
		cout << start->position << ", ";
	}

	// 레벨 순서 순회 방법은 현재 노드에 직접 연결되지 않은 노드로 이동
	// 이러한 경우 재귀를 사용하지 않고 구현하는 것이 더 쉽다
	static void levelOrder(node* start) {
		if (!start) return;

		queue<node*> q;
		q.push(start);

		while (!q.empty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				auto current = q.front();
				q.pop();

				cout << current->position << ", ";

				if (current->first) q.push(current->first);
				if (current->second) q.push(current->second);
			}
		}
	}

};

void chap2_tree() {
	auto tree = org_tree::create_org_structure("CEO");

	tree.addSubordinate("CEO", "부사장");
	tree.addSubordinate("부사장", "IT부장");
	tree.addSubordinate("부사장", "마케팅부장");
	tree.addSubordinate("IT부장", "보안팀장");
	tree.addSubordinate("IT부장", "앱개발팀장");
	tree.addSubordinate("마케팅부장", "물류팀장");
	tree.addSubordinate("마케팅부장", "홍보팀장");
	tree.addSubordinate("부사장", "재무부장");
	cout << "\n" << endl;

	cout << "[ Pre-order Traversal ]" << "\n";
	org_tree::preOrder(tree.root);
	cout << "\n" << endl;

	cout << "[ In-order Traversal ]" << "\n";
	org_tree::inOrder(tree.root);
	cout << "\n" << endl;

	cout << "[ Post-order Traversal ]" << "\n";
	org_tree::postOrder(tree.root);
	cout << "\n" << endl;

	cout << "[ Level-order Traversal ]" << "\n";
	org_tree::levelOrder(tree.root);
	cout << "\n" << endl;
}
*/