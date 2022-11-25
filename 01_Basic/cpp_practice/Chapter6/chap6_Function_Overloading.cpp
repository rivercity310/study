#include <iostream>
using namespace std;

int big(int a, int b) {
	return a > b ? a : b;
}

int big(int* a, int size) {
	int max = 0;
	for (int i = 0; i < size; i++) 
		if (a[i] > max) max = a[i];
	
	return max;
}

void FO() {
	cout << "big(int 3, int 5): " << big(3, 5) << endl;
	
	int a[] = { 1, 2, 3, 4, 5 };
	cout << "big(int* a, int 5): " << big(a, 5) << endl;
}