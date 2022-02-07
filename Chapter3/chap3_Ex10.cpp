#include <iostream>
#include "Calculator.h"

using namespace std;

int Add::calculate() {
	return a + b;
}

int Sub::calculate() {
	return a - b;
}

int Mul::calculate() {
	return a * b;
}

int Div::calculate() {
	newCalc(a, b);
	return 0;
}

double Div::newCalc(int a, int b) {
	return a / (double)b;
}



/*
int main() {
	while (true) {
		cout << "두 정수와 연산자를 입력하세요: ";
		int a, b;
		char oper;
		cin >> a >> b >> oper;

		switch (oper) {
		case '+':
		{
			Add adder;
			adder.setValue(a, b);
			cout << a << " + " << b << " = " << adder.calculate() << endl;
			break;
		}
		case '-':
		{
			Sub sub;
			sub.setValue(a, b);
			cout << a << " - " << b << " = " << sub.calculate() << endl;
			break;
		}
		case '*':
		{
			Mul mul;
			mul.setValue(a, b);
			cout << a << " * " << b << " = " << mul.calculate() << endl;
			break;
		}
		
		default:
			cout << "잘못된 연산자 입력입니다..." << endl;
			break;
		}
	}
}
*/