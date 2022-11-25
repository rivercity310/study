#include <iostream>

template <class T> void myswap(T& a, T& b) {
	T Temp = a;
	a = b;
	b = Temp;
}

// src[]의 n개 원소를 dest[]에 복사하는 템플릿
template <typename T1, typename T2> void mcopy(T1 src[], T2 dest[], int n) {
	for (int i = 0; i < n; i++)
		dest[i] = (T2)src[i];
}

void chap10_template() {
	int a[5] = { 1, 2, 3, 4, 5 };
	double b[5];

	mcopy(a, b, 5);
	for (int i = 0; i < 5; i++) std::cout << b[i] << " ";
	std::cout << std::endl;

	char c[5] = { 'H', 'e', 'l', 'l', 'o' };
	char d[5];
	
	mcopy(c, d, 5);
	for (int i = 0; i < 5; i++) std::cout << d[i] << " ";
	std::cout << std::endl;
}