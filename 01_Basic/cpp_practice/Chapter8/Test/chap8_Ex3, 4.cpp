#include <iostream>
using std::string;

class Point {
	int x, y;
protected:
	void move(int x, int y) { this->x =x; this->y = y; }
	int getX() { return x; }
	int getY() { return y; }
public:
	Point(int x = 0, int y = 0) { this->x = x; this->y = y; }
	void reset() {
		this->x = this->y = 0;
	}
	virtual void show() = 0;
};

class ColorPoint : public Point {
	string color;
public:
	ColorPoint(int x = 0, int y = 0, string color = "Black") : Point(x, y) {
		this->color = color;
	}
	void setPoint(int x, int y) {
		move(x, y);
	}
	void setColor(string color) { this->color = color; }
	void show();
};

void ColorPoint::show() {
	std::cout << color << "색의 (" << getX() << ", " << getY() << ")에 위치한 점" << std::endl;
}

void chap8_Ex3() {
	ColorPoint cp(5, 5, "Blue");
	cp.setPoint(10, 20);
	cp.setColor("Red");
	cp.show();

	ColorPoint* pt = &cp;
	pt->setColor("Yellow");
	pt->setPoint(12, 21);
	pt->show();

	pt->reset();
	pt->show();
}