#include <iostream>

class BaseArray {
private:
	int capacity;    // 배열의 크기
	int* mem;        // 정수 배열을 만들기 위한 메모리의 포인터
protected:
	BaseArray(int capacity = 100) {         // 생성자가 protected임
		this->capacity = capacity;
		mem = new int[capacity];
	}
	~BaseArray() { delete[] mem; }
	void put(int idx, int val) { mem[idx] = val; }
	int get(int idx) { return mem[idx]; }
	int getCapacity() { return capacity; }
};

// BaseArray를 상속받아 큐처럼 작동하는 MyQueue 클래스 작성
class MyQueue : public BaseArray {
private:
	int len, ptr;
public:
	MyQueue(int capacity) : BaseArray(capacity) { this->len = this->ptr = 0; }
	int capacity() { return getCapacity(); }
	int length() { return this->len; }
	void enqueue(int n);
	int dequeue();
};

void MyQueue::enqueue(int n) {
	if (len < getCapacity()) {
		put(len, n);
		len++;
	}
	else std::cout << "용량이 꽉차서 저장 불가!" << std::endl;

	return;
}

int MyQueue::dequeue() {
	int data = get(ptr++);
	len--;

	return data;
}


class MyStack : public BaseArray {
	int len;
public:
	MyStack(int cap) : BaseArray(cap) { this->len = 0; }
	void push(int val);
	int pop();
	int capacity() { return getCapacity(); }
	int length() { return this->len; }
};

void MyStack::push(int val) {
	put(len++, val);
}

int MyStack::pop() {
	--len;
	int data = get(len);

	return data;
}

void chap8_Ex5() {
	MyQueue mq(100);
	int n;
	std::cout << "큐에 삽입할 10개의 정수: ";
	for (int i = 0; i < 10; i++) {
		std::cin >> n;
		mq.enqueue(n);
	}

	std::cout << "큐 용량: " << mq.capacity() << ", 큐 크기: " << mq.length() << std::endl;
	std::cout << "큐의 원소를 순서대로 제거하며 출력: ";
	while (mq.length() != 0) std::cout << mq.dequeue() << " ";
	std::cout << std::endl << "큐의 현재 크기: " << mq.length() << std::endl;
}


void chap8_Ex6() {
	MyStack mStack(100);
	int n;
	std::cout << "스택에 삽입할 5개의 정수: ";
	for (int i = 0; i < 5; i++) {
		std::cin >> n;
		mStack.push(n);
	}

	std::cout << "스택 용량: " << mStack.capacity() << ", 스택크기: " << mStack.length() << std::endl;
	std::cout << "스택의 모든 원소 팝 ";
	while (mStack.length() != 0) std::cout << mStack.pop() << " ";
	std::cout << std::endl << "스택의 현재 크기: " << mStack.length() << std::endl;
}

