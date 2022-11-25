#include <iostream>

class AbstractStack {
public:
	virtual bool push(int n) = 0;
	virtual bool pop(int& n) = 0;
	virtual int getSize() = 0;
};

class IntStack : public AbstractStack {
private:
	int capacity;
	int size;
	int* mem;
public:
	IntStack(int capacity = 100);
	~IntStack();
	bool push(int n);      // 스택에 n을 푸시한다. 스택이 풀이면 false 리턴
	bool pop(int& n);      // 스택에서 팝한 정수를 n에 저장하고, 스택이 empty이면 false 리턴
	int getSize() { return this->size; }   // 현재 스택에 저장된 정수의 개수 리턴
};

IntStack::IntStack(int capacity) {
	this->size = 0;
	this->capacity = capacity;
	this->mem = new int[capacity];
}

IntStack::~IntStack() { 
	delete[] mem;
}

bool IntStack::push(int n) {
	if (size <= capacity) {
		*(mem + size) = n;
		size++;
		return true;
	}
	else {
		std::cout << "Stack Full!" << std::endl;
		return false;
	}
}

bool IntStack::pop(int& n) {
	if (size <= 0) {
		std::cout << "Stack Empty!!" << std::endl;
		return false;
	}
	else {
		size--;
		n = *(mem + size);
		return true;
	}
}

void chap9_Ex6() {
	IntStack intStack;

	std::cout << "10개의 정수를 입력하세요: ";
	for (int i = 0; i < 10; i++) {
		int input; std::cin >> input;
		intStack.push(input);
	}

	int n;
	while (true) {
		if (!intStack.pop(n)) break;
		std::cout << n << " ";
	}
}