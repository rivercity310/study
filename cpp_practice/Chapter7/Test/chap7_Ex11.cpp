#include <iostream>

class Stack {
	int* data;
	int size;
public:
	Stack();
	~Stack();
	Stack& operator<<(int x);
	bool operator!();
	void operator>>(int& x);
};

Stack::Stack() {
	data = new int[100];
	size = 0;
}

Stack::~Stack() {
	std::cout << "동적 메모리 해제" << std::endl;
	delete[] data;
}

Stack& Stack::operator<<(int x) {
	*(data + size) = x;
	size++;

	return *this;
}

bool Stack::operator!() {
	if (size == 0) return true;
	else return false;
}

void Stack::operator>>(int& x) {
	x = 0;
	size--;
	x = *(data + size);
}

void chap7_Ex11() {
	Stack stack;
	stack << 3 << 5 << 10;     // push

	while (true) {
		if (!stack) break;
		int x;
		stack >> x;    // pop
		std::cout << x << " ";
	}

	std::cout << std::endl;
}