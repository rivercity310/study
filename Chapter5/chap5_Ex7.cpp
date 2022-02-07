#include <iostream>
using namespace std;

class MyIntStack {
	int* p;      // 최대 10개의 정수 저장
	int tos;     // 스택의 꼭대기를 가리키는 인덱스
	int size;    // 스택의 최대 크기
public:
	MyIntStack();
	MyIntStack(int size);
	MyIntStack(const MyIntStack& s);   // 복사 생성자
	~MyIntStack();
	bool push(int n);      // 정수 n 푸쉬, 꽉 차있으면 false, 아니면 true
	bool pop(int& n);      // 팝하여 n에 저장, 비어있으면 false, 아니면 true
};

MyIntStack::MyIntStack() : MyIntStack(10) {}
MyIntStack::MyIntStack(int size) {
	this->size = size;
	p = new int[size];
	tos = 0;
}
MyIntStack::MyIntStack(const MyIntStack& s) {
	this->size = s.size;
	this->tos = s.tos;
	this->p = new int[size];
	
}

MyIntStack::~MyIntStack() {
	cout << "사이즈 " << size << " stack 소멸!" << endl;
}

bool MyIntStack::push(int n) {
	if (tos < 10) {
		*(p + tos) = n;
		tos++;
		return true;
	}

	return false;
}

bool MyIntStack::pop(int& n) {
	if (tos > 0) {
		tos--;
		n = *(p + tos);
		return true;
	}

	return false;
}


void Ex7() {
	MyIntStack a;
	for (int i = 0; i < 11; i++) {
		if (a.push(i)) cout << i << " ";
		else cout << endl << i + 1 << " 번째 stack full" << endl;
	}

	int n;
	for (int i = 0; i < 11; i++) {
		if (a.pop(n)) cout << n << " ";
		else cout << endl << i + 1 << " 번째 stack empty" << endl;
	}
}

void Ex8() {
	MyIntStack a(10);
	a.push(10);
	a.push(20);
	MyIntStack b = a;   // 복사 생성
	b.push(30);

	int n;
	a.pop(n);
	cout << "스택 a에서 팝한 값 " << n << endl;
	
	b.pop(n);
	cout << "스택 b에서 팝한 값 " << n << endl;
}