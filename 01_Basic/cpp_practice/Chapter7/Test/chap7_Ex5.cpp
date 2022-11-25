#include <iostream>
using std::string;

class Color {
	int r, g, b;
public:
	Color(int r = 0, int g = 0, int b = 0);
	Color operator+(Color op);
	bool operator==(Color op);
};

Color::Color(int r, int g, int b) {
	this->r = r;
	this->g = g;
	this->b = b;
}

Color Color::operator+(Color op) {
	Color temp;
	temp.r = this->r + op.r;
	temp.g = this->g + op.g;
	temp.b = this->b + op.b;

	return temp;
}

bool Color::operator==(Color op) {
	if (this->r == op.r && this->g == op.g && this->b == op.b) return true;
	else return false;
}

void chap7_Ex5() {
	Color red(255, 0, 0), blue(0, 0, 255), c;
	c = red + blue;

	Color fuchsia(255, 0, 255);
	if (c == fuchsia) std::cout << "º¸¶ó»ö" << std::endl;
}