#include <iostream>

class SortedArray {
	int size;   // 현재 배열 크기
	int* p;     // 정수 배열에 대한 포인터
	void sort(); // 정수 배열 오름차순 정렬
public:
	SortedArray(); 
	SortedArray(SortedArray& src);
	SortedArray(int p[], int size);
	~SortedArray();

	SortedArray operator+(SortedArray& op2);
	SortedArray& operator=(const SortedArray& op2);
	void show();
};

SortedArray::SortedArray() {
	p = NULL;
	size = 0;
}

SortedArray::SortedArray(SortedArray& src) {
	this->size = src.size;
	this->p = new int[src.size];
	for (int i = 0; i < src.size; i++)
		this->p[i] = src.p[i];
}

SortedArray::SortedArray(int p[], int size) {
	this->size = size;
	this->p = new int[size];
	for (int i = 0; i < size; i++) *(this->p + i) = p[i];
}

SortedArray::~SortedArray() {
	std::cout << "동적 메모리 해제" << std::endl;
	if (p) delete[] p;
}

SortedArray SortedArray::operator+(SortedArray& op2) {
	SortedArray temp;
	temp.p = new int[this->size + op2.size];
	temp.size = this->size + op2.size;
	
	for (int i = 0; i < this->size; i++) temp.p[i] = this->p[i];
	for (int i = 0; i < op2.size; i++) temp.p[this->size + i] = op2.p[i];

	return temp;
}

SortedArray& SortedArray::operator=(const SortedArray& op2) {
	delete[] this->p;
	this->p = new int[op2.size];
	this->size = op2.size;

	for (int i = 0; i < op2.size; i++)
		this->p[i] = op2.p[i];

	return *this;
}

void SortedArray::sort() {
	for (int i = 0; i < size - 1; i++) 
		for (int j = 1; j < size - i; j++) 
			if (p[j - 1] > p[j]) {
				int temp = p[j - 1];
				p[j - 1] = p[j];
				p[j] = temp;
			}
}

void SortedArray::show() {
	sort();

	std::cout << " 배열 출력 : ";
	for (int i = 0; i < size; i++) {
		std::cout << p[i] << " ";
	}
	std::cout << std::endl;
}

void chap7_Ex12() {
	int n[] = { 2, 20, 6 };
	int m[] = { 10, 7, 8, 30 };
	SortedArray a(n, 3), b(m, 4), c;

	c = a + b;

	a.show();
	b.show();
	c.show();
}