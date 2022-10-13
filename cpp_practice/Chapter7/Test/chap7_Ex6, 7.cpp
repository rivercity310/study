#include <iostream>

class Matrix {
private:
	int ary[4];
public:
	Matrix(int a1 = 0, int a2 = 0, int a3 = 0, int a4 = 0);
	void show();

	Matrix operator+(Matrix op);
	Matrix& operator+=(Matrix op);
	bool operator==(Matrix op);
	void operator>>(int* op);
	void operator<<(int* op);
};

Matrix::Matrix(int a1, int a2, int a3, int a4) {
	ary[0] = a1;
	ary[1] = a2;
	ary[2] = a3;
	ary[3] = a4;
}

Matrix Matrix::operator+(Matrix op) {
	Matrix temp;
	for (int i = 0; i < 4; i++)
		temp.ary[i] = this->ary[i] + op.ary[i];

	return temp;
}

Matrix& Matrix::operator+=(Matrix op) {
	for (int i = 0; i < 4; i++) 
		this->ary[i] += op.ary[i];

	return *this;
}

bool Matrix::operator==(Matrix op) {
	bool check = true;

	for (int i = 0; i < 4; i++) 
		if (ary[i] != op.ary[i]) check = false;

	return check;
}

void Matrix::operator>>(int* op) {
	for (int i = 0; i < 4; i++)
		*(op + i) = this->ary[i];
}

void Matrix::operator<<(int* op) {
	for (int i = 0; i < 4; i++)
		this->ary[i] = op[i];
}

void Matrix::show() {
	std::cout << "Matrix = " << "{ ";
	for (int i = 0; i < 4; i++) {
		std::cout << ary[i] << " ";
	}
	std::cout << "}" << std::endl;
}

void chap7_Ex6() {
	Matrix a(1, 2, 3, 4), b(2, 3, 4, 5), c;
	c = a + b;
	a += b;

	a.show();
	b.show();
	c.show();
	
	if (a == c)
		std::cout << "a and c are the same matrix" << std::endl;
}

void chap7_Ex7() {
	Matrix a(4, 3, 2, 1), b;
	int x[4], y[4] = { 1, 2, 3, 4 };
	a >> x;      // a의 각 원소를 배열 x에 복사, x[]는 {4, 3, 2, 1}
	b << y;      // 배열 y의 원소 값을 b의 각 원소에 설정

	for (int i = 0; i < 4; i++) std::cout << x[i] << " ";
	std::cout << std::endl;

	b.show();
}