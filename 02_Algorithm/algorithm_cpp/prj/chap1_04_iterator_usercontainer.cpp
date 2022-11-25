#include <iostream>

using namespace std;

struct singly_ll_node {
	int data;
	singly_ll_node* next;
};

class singly_ll {
private:
	using node = singly_ll_node;
	using node_ptr = node*;
	node_ptr head;

public:
	void push_front(int val) {
		auto new_node = new node{ val, NULL };
		if (head != NULL)
			new_node->next = head;

		head = new_node;
	}

	void pop_front() {
		auto first = head;
		if (head) {
			head = head->next;
			delete first;
		}
	}

	// singly_ll 클래스의 기본 반복자 구현 (생성자, 접근자)
	struct singly_ll_iterator {
	private:
		node_ptr ptr;
	public:
		singly_ll_iterator(node_ptr p) : ptr(p) {}
		int& operator*() { return ptr->data; }
		node_ptr get() { return ptr; }

		// 선행 증가와 후행 증가를 위한 ++ 연산자 함수 구현
		singly_ll_iterator& operator++() {    // 선행 증가
			ptr = ptr->next;
			return *this;
		}

		singly_ll_iterator operator++(int) {  // 후행 증가
			singly_ll_iterator result = *this;
			++(*this);
			return result;
		}

		// 두 반복자가 같은지 판단하는 관계 연산자 함수를 friend로 정의
		friend bool operator==(const singly_ll_iterator& left, const singly_ll_iterator& right) {
			return left.ptr == right.ptr;
		}

		friend bool operator!=(const singly_ll_iterator& left, const singly_ll_iterator& right) {
			return left.ptr != right.ptr;
		}
	};

	// singly_ll 클래스의 반복자에 대한 함수 추가
	singly_ll_iterator begin() { return singly_ll_iterator(head); }
	singly_ll_iterator end() { return singly_ll_iterator(NULL); }
	singly_ll_iterator begin() const { return singly_ll_iterator(head); }
	singly_ll_iterator end() const { return singly_ll_iterator(NULL); }


	// 기본 생성자, 깊은 복사를 위한 복사 생성자, 초기화 리스트를 사용하는 생성자 추가
	singly_ll() = default;    // default: 컴파일러가 만든 기본 생성자를 사용하겠다는 뜻 (얕은 복사)
	
	singly_ll(const singly_ll& other) : head(NULL) {
		if (other.head) {
			head = new node{ 0, NULL };
			auto cur = head;
			auto it = other.begin();

			while (true) {
				cur->data = *it;

				auto tmp = it;
				++tmp;

				if (tmp == other.end()) break;

				cur->next = new node{ 0, NULL };
				cur = cur->next;
				it = tmp;
			}
		}
	}

	singly_ll(const std::initializer_list<int>& ilist) : head(NULL) {
		for (auto it = std::rbegin(ilist); it != std::rend(ilist); it++)
			push_front(*it);
	}
};

void std_iterator_ex1() {
	singly_ll sll = { 1, 2, 3 };
	sll.push_front(0);

	cout << "첫번째 리스트: ";
	for (auto i : sll) cout << i << " ";
	cout << endl;

	auto sll2 = sll;
	sll2.push_front(-1);
	cout << "첫번째 리스트를 복사한 후, 맨 앞에 -1을 추가: ";
	for (auto i : sll2) cout << i << " ";
	cout << endl;

	cout << "깊은 복사 후 첫번째 리스트: ";
	for (auto i : sll) cout << i << " ";
	cout << endl;
}