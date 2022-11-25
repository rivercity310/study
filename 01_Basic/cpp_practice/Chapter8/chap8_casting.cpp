#include <iostream>
using std::string;

class Point {
	int x, y;
public:
	void set(int x, int y) { this->x = x; this->y = y; }
	void showPoint() {
		std::cout << "(" << x << ", " << y << ")" << std::endl;
	}
};

class ColorPoint : public Point {
	string color;
public:
	void setColor(string color) { this->color = color; }
	void showColorPoint();
};

void ColorPoint::showColorPoint() {
	std::cout << color << " : ";
	showPoint();
}

void chap8_casting() {
	ColorPoint cp;
	ColorPoint* pDer = &cp;
	Point* pBase = pDer;    // 업캐스팅

	pDer->set(3, 4);
	pDer->setColor("red");
	pDer->showColorPoint();

	pBase->showPoint();

	ColorPoint* ppDer = (ColorPoint*)pBase;   // 다운캐스팅
	ppDer->set(5, 6);
	ppDer->setColor("blue");
	ppDer->showColorPoint();
}