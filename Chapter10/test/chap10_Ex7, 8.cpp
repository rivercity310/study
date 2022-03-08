#include <iostream>

class Comparable {
public:
	virtual bool operator > (Comparable& op2) = 0;
	virtual bool operator < (Comparable& op2) = 0;
	virtual bool operator == (Comparable& op2) = 0;
 };

class Circle : public Comparable {
	int radius;
public:
	Circle(int radius = 1) { this->radius = radius; }
	int getRadius() { return this->radius; }
	bool operator > (Comparable& op2);
	bool operator < (Comparable& op2);
	bool operator == (Comparable& op2);
};

bool Circle::operator > (Comparable& op2) {
	Circle* c = (Circle*)&op2;
	if (this->radius > c->getRadius()) return true;
	else return false;
}

bool Circle::operator < (Comparable& op2) {
	Circle* c = (Circle*)&op2;
	if (this->radius < c->getRadius()) return true;
	else return false;
}

bool Circle::operator == (Comparable& op2) {
	Circle* c = (Circle*)&op2;
	if (this->radius == c->getRadius()) return true;
	else return false;
}

template <class T> T bigger(T a, T b) {
	if (a > b) return a;
	else return b;
}

void chap10_Ex8() {
	int a = 10, b = 50, c;
	c = bigger(a, b);
	std::cout << c << std::endl;

	Circle waffle(10), pizza(20), y;
	y = bigger(waffle, pizza);
	std::cout << y.getRadius() << std::endl;
}