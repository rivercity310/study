#include <iostream>

class Calculator {
public:
	virtual ~Calculator() { std::cout << "~Calculator 실행" << std::endl; }
	virtual int add(int a, int b) = 0;
	virtual int subtract(int a, int b) = 0;
	virtual double average(int a[], int size) = 0;
};

class GoodCalc : public Calculator {
public:
	virtual ~GoodCalc() { std::cout << "~GoodCalc 실행" << std::endl; }
	int add(int a, int b) override { return a + b; }
	int subtract(int a, int b) override { return a - b; }
	double average(int a[], int size) override {
		double sum = 0;
		for (int i = 0; i < size; i++)
			sum += a[i];

		return sum / size;
	}
};

void chap9_abstract() {
	int a[] = { 1, 2, 3, 4, 5 };
	Calculator* p = new GoodCalc();

	std::cout << p->add(2, 3) << std::endl;
	std::cout << p->subtract(5, 1) << std::endl;
	std::cout << p->average(a, 5) << std::endl;

	delete p;
}