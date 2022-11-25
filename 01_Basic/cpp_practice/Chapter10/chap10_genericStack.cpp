#include <iostream>

template <class T> class Stack {
	T* data;
	int tos, capacity;
public:
	Stack(int capacity = 100) { 
		this->tos = 0;
		this->capacity = capacity;
		data = new T();
	}

	~Stack() { delete data; }

	bool push(T d) {
		if (tos <= this->capacity) {
			*(data + tos) = d;
			tos++;
			return true;
		}
		else {
			std::cout << "Stack Full!" << std::endl;
			return false;
		}
	}

	bool pop(T& retData) {
		if (tos > 0) {
			tos--;
			retData = *(data + tos);
			return true;
		}
		else {
			std::cout << "Stack Empty!" << std::endl;
			return false;
		}
	}
};


void chap10_GenericStack() {

	/*
	Stack<int>* iPtr = new Stack<int>(10);
	for (int i = 0; i < 10; i++) {
		std::cout << i + 1 << ": ";
		int data; std::cin >> data;
		iPtr->push(data);
	}

	for (int i = 0; i < 10; i++) {
		int retData;
		std::cout << i + 1 << ": ";
		iPtr->pop(retData);
		std::cout << retData << std::endl;
	}
	*/

	Stack<double>* dPtr = new Stack<double>(5);
	for (int i = 0; i < 5; i++) {
		std::cout << i + 1 << ": ";
		double data; std::cin >> data;
		dPtr->push(data);
	}

	for (int i = 0; i < 5; i++) {
		double retData;
		std::cout << i + 1 << ": ";
		dPtr->pop(retData);
		std::cout << retData << std::endl;
	}
}



