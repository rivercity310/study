#include <iostream>
using namespace std;

/*
1. 두 개의 Circle 객체를 교환하는 swap() 함수를 참조에 의한 호출이 되도록 작성
*/

class Ex1_Circle {
private:
	int radius;
public:
	Ex1_Circle() : Ex1_Circle(1) {}
	Ex1_Circle(int r) { this->radius = r; }
	int getRadius() {
		return this->radius;
	}
};

void swap(Ex1_Circle& a, Ex1_Circle& b) {
	Ex1_Circle temp = a;
	a = b;
	b = temp;
}

void Ex1() {
	Ex1_Circle first(5);
	Ex1_Circle second(10);
	cout << "first의 반지름: " << first.getRadius() << endl;
	cout << "second의 반지름: " << second.getRadius() << endl;

	swap(first, second);
	cout << "first의 반지름: " << first.getRadius() << endl;
	cout << "second의 반지름: " << second.getRadius() << endl;
}