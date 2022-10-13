#include <iostream>

class Circle {
	int radius;
public:
	Circle(int radius = 0) { this->radius = radius; }
	void show() { std::cout << "radius = " << radius << std::endl; }

	Circle operator++();        // 전위++
	Circle operator++(int x);   // 후위++

	friend Circle operator+(int op, Circle a);
};

Circle Circle::operator++() {
	this->radius++;

	return *this;
}

Circle Circle::operator++(int x) {
	Circle temp = *this;
	this->radius++;

	return temp;
}

Circle operator+(int op, Circle a) {
	Circle temp;
	temp.radius = op + a.radius;

	return temp;
}

void chap7_Ex8() {
	Circle a(5), b(4);
	++a;
	b = a++; 

	a.show();
	b.show();
}


void chap7_Ex9() {
	Circle a(5);
	Circle b = 1 + a;

	a.show();
	b.show();
}