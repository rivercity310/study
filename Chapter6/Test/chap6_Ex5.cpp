#include <iostream>

class ArrayUtil6 {
public:
	static void intToDouble(int* source, double* dest, int size);
	static void DoubleToInt(double* source, int* dest, int size);
};

void ArrayUtil6::intToDouble(int* source, double* dest, int size) {
	for (int i = 0; i < size; i++) dest[i] = (double)source[i];
}

void ArrayUtil6::DoubleToInt(double* source, int* dest, int size) {
	for (int i = 0; i < size; i++) dest[i] = (int)source[i];
}

void chap6_Ex5() {
	int x[] = { 1, 2, 3, 4, 5 };
	double y[5], z[] = { 1.2, 1.3, 2.5, 3.5, 3.1 };

	ArrayUtil6::intToDouble(x, y, 5);
	for (int i = 0; i < 5; i++) std::cout << y[i] << " ";
	std::cout << std::endl;

	ArrayUtil6::DoubleToInt(z, x, 5);
	for (int i = 0; i < 5; i++) std::cout << x[i] << " ";
	std::cout << std::endl;
}