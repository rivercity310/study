#include <iostream>
#include <iomanip>

using std::string;

class Shape {
public:
	virtual string getName() = 0;
	virtual double getArea() = 0;
};

class Oval : public Shape {
private:
	string name;
	int minorAxis, mainAxis;
public:
	Oval(string name, int mainAxis, int minorAxis) {
		this->name = name;
		this->mainAxis = mainAxis;
		this->minorAxis = minorAxis;
	}
	string getName() { return this->name; }
	double getArea() {
		double area = std::atan(1) * 4 * minorAxis * mainAxis;
		return area;
	}
};

class Rect : public Shape {
private:
	string name;
	int width, height;
public:
	Rect(string name, int width, int height) {
		this->name = name;
		this->width = width;
		this->height = height;
	}
	string getName() { return this->name; }
	double getArea() {
		return (double)(this->width * this->height);
	}
};

class Triangular : public Shape {
private:
	string name;
	int base, height;
public:
	Triangular(string name, int base, int height) {
		this->name = name;
		this->base = base;
		this->height = height;
	}
	string getName() { return this->name; }
	double getArea() { return (double)(this->base * this->height / 2); }
};

void chap9_Ex7() {
	Shape* p[3];
	p[0] = new Oval("ºó´ë¶±", 10, 20);
	p[1] = new Rect("Âû¶±", 30, 40);
	p[2] = new Triangular("Åä½ºÆ®", 30, 40);

	for (int i = 0; i < 3; i++)
		std::cout << p[i]->getName() << "ÀÇ ³ÐÀÌ´Â " << std::fixed << std::setprecision(2) << p[i]->getArea() << std::endl;

	for (int i = 0; i < 3; i++) delete p[i];
}