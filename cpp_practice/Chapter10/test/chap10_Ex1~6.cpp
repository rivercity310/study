#include <iostream>

template <class T> T biggest(T a[], int size) {
	T max = 0;
	for (int i = 0; i < size; i++)
		if (a[i] > max) max = a[i];

	return max;
}

void chap10_Ex1() {
	int x[] = { 1, 10, 100, 5, 4 };
	double d[] = { 1.1, 2.2, 3.3, 3.2, 1.9 };

	std::cout << biggest(x, 5) << std::endl;
	std::cout << biggest(d, 5) << std::endl;
}



template <typename T> bool equalArrays(T a[], T b[], int size) {
	for (int i = 0; i < size; i++) {
		if (a[i] != b[i]) return false;
	}

	return true;
}

void chap10_Ex2() {
	int a[] = { 1, 2, 3, 4, 5 };
	int* b = a;

	double c[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };
	double* d = c;

	std::cout << std::boolalpha << equalArrays(a, b, 5) << std::endl;
	std::cout << std::boolalpha << equalArrays(c, d, 5) << std::endl;
}



template <class T> void reverseArray(T x[], int size) {
	for (int i = 0; i < size / 2; i++) {
		T tmp = x[i];
		x[i] = x[size - i - 1];
		x[size - i - 1] = tmp;
	}
}

void chap10_Ex3() {
	int x[] = { 1, 2, 3, 4, 5 };
	reverseArray(x, 5);
	for (int i = 0; i < 5; i++) std::cout << x[i] << " ";
	std::cout << std::endl;

	double y[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };
	reverseArray(y, 5);
	for (int i = 0; i < 5; i++) std::cout << y[i] << " ";
	std::cout << std::endl;

	char c[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
	reverseArray(c, 7);
	for (int i = 0; i < 7; i++) std::cout << c[i] << " ";
	std::cout << std::endl;
}



template <class T> bool search(T val, T a[], int size) {
	for (int i = 0; i < size; i++)
		if (a[i] == val) return true;

	return false;
}

void chap10_Ex4() {
	int x[] = { 1, 10, 100, 5, 4 };
	if (search(100, x, 5)) std::cout << "100이 배열 x에 포함됨";
	else std::cout << "100은 배열 x에 포함되지 않음";
	std::cout << std::endl;
}


template <typename T1, typename T2> T1* concat(T1 a[], int sizea, T2 b[], int sizeb) {
	T1* newAry = new T1[sizea + sizeb];
	for (int i = 0; i < sizea; i++) newAry[i] = a[i];
	for (int i = 0; i < sizeb; i++) newAry[sizea + i] = b[i];

	return newAry;
}

void chap10_Ex5() {
	int a[] = { 1, 2, 3, 4, 5 };
	int b[] = { 5, 4, 3, 2, 1, 0 };

	int* ary = concat(a, 5, b, 6);
	for (int i = 0; i < 11; i++) std::cout << *(ary + i) << " ";
	std::cout << std::endl;
}


template <typename T> T* remove(T src[], int srcSize, T minus[], int minusSize, int& retSize) {
	T* ary = new T[srcSize + minusSize];
	int i;
	int k = 0;

	for (i = 0; i < srcSize; i++) {
		bool check = false;
		for (int j = 0; j < minusSize; j++) if (src[i] == minus[j]) check = true;
		if (!check) ary[k++] = src[i];
	}

	retSize = k;
	return ary;
}

void chap10_Ex6() {
	int x[] = { 1, 2, 3, 4, 5 };
	int y[] = { 2, 4, 5, 6, 7, 8, 9 };
	int retSize;

	int* removed = remove(x, 5, y, 7, retSize);
	for (int i = 0; i < retSize; i++) std::cout << removed[i] << " ";
	std::cout << std::endl << retSize << std::endl;
}