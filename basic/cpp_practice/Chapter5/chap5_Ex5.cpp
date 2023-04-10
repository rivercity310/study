#include <iostream>
using namespace std;

class Ex5_Circle {
	int radius;
public:
	Ex5_Circle(int r) { 
		radius = r; 
	}
	int getRadius() { return radius; }
	void setRadius(int r) { radius = r; }
	void show() { cout << "반지름이 " << radius << "인 원" << endl; }
};

void increaseBy(Ex5_Circle& a, Ex5_Circle& b) {
	int r = a.getRadius() + b.getRadius();
	a.setRadius(r);
}

void Ex5() {
	Ex5_Circle x(10), y(5);
	increaseBy(x, y);
	x.show();
}