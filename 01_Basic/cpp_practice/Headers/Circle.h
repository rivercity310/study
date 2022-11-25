#ifndef CIRCLE_H
#define CIRCLE_H

// 추상클래스 Circle
class Circle {
private:
	int radius;
public:
	Circle();
	~Circle();
	void setRadius(int radius);
	double getArea();
};

#endif