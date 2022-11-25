#include <iostream>

template <class T> void print(T ary[], int n) {
	for (int i = 0; i < n; i++)
		std::cout << ary[i] << "\t";
	std::cout << std::endl;
}

// 템플릿 함수와 동일한 이름의 함수 중복
void print(char ary[], int n) {
	for (int i = 0; i < n; i++)
		std::cout << (int)ary[i] << '\t';
	std::cout << std::endl;
}

void chap10_print() {
	int x[] = { 1, 2, 3, 4, 5 };
	double d[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };
	print(x, 5);
	print(d, 5);

	char c[5] = { 1, 2, 3, 4, 5 };
	print(c, 5);
}