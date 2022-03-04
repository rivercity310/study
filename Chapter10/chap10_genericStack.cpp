#include <iostream>

template <class T> class MyStack {
	int tos;
	T* data;        // T 타입의 배열, 스택에 최대 100개의 원소 저장
public:
	MyStack();
	~MyStack();
	void push(T element);
	bool pop(T& a);
};

template <class T> MyStack<T>::MyStack() {
	data = new T[100];
	this->tos = 0;
}

template <class T> MyStack<T>::~MyStack() {
	delete[] data;
}

template <class T> void MyStack<T>::push(T element) {
	if (tos < 100) data[this->tos++] = element;
	else std::cout << "Stack Full!" << std::endl;
}

template <class T> bool MyStack<T>::pop(T& a) {
	if (tos > 0) {
		a = data[--tos];
		return true;
	}
	else
		return false;
	
}

void chap10_genericStack() {
	MyStack<int> iStack;   // int 타입을 다루는 스택 객체 생성
	MyStack<double> dStack; 

	iStack.push(1);
	iStack.push(2);

	int a;
	while (iStack.pop(a)) std::cout << a << " ";
	std::cout << std::endl;

	dStack.push(1.2);
	dStack.push(1.3);
	dStack.push(1.4);

	double d;
	while (dStack.pop(d)) std::cout << d << " ";
	std::cout << std::endl;
}