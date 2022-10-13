#include <iostream>
using namespace std;

class Adder {
	int sum = 0;
public:
	static int add(int* a, int size = 5);
	static int add(int* a, int size, int* b);
};

int Adder::add(int* a, int size) {
	int sum = 0;
	for (int i = 0; i < size; i++) sum += *(a + i);

	return sum;
}

int Adder::add(int* a, int size, int* b) {
	int sum = 0;
	for (int i = 0; i < size; i++) sum += (*(a + i) + *(b + i));
	
	return sum;
}

void chap6_Ex1() {
	int a[] = { 1, 2, 3, 4, 5 };
	int b[] = { 6, 7, 8, 9, 10 };
	int c = Adder::add(a, 5);
	int d = Adder::add(a, 5, b);
	int e = Adder::add(b);

	cout << c << endl;
	cout << d << endl;
	cout << e << endl;
}