#include <iostream>

class MyVector6 {
	int* mem;
	int size;
public:
	MyVector6(int n = 100, int val = 99);
	~MyVector6() { delete[] mem; }
	int getSize() { return this->size; }
	int getValue(int i) { return *(this->mem + i); }
};

MyVector6::MyVector6(int n, int val) {
	mem = new int[n];
	size = n;

	for (int i = 0; i < size; i++) *(mem + i) = val;
}

void chap6_Ex4() {
	MyVector6 mv1, mv2(50, 1);
	for (int i = 0; i < mv1.getSize(); i++) {
		std::cout << mv1.getValue(i) << " ";
	}
	
	std::cout << std::endl;
	for (int i = 0; i < mv2.getSize(); i++) {
		std::cout << mv2.getValue(i) << " ";
	}
}