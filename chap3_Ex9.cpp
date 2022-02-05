#include <iostream>
#include "Oval.h"

using namespace std;

// Constructor
Oval::Oval() : Oval(1, 1) {}
Oval::Oval(int w, int h) {
	this->width = w;
	this->height = h;
}

// Destructor
Oval::~Oval() {
	cout << "------------ [ ¼Ò¸ê ] ---------------\n";
	cout << "Width: " << width << "\n";
	cout << "Height: " << height << "\n";
	cout << "---------------------------------------" << endl;
}

// getter/setter
int Oval::getWidth() {
	return this->width;
}

int Oval::getHeight() {
	return this->height;
}

// Methods
void Oval::show() {
	delete this;
}

void Oval::set(int w, int h) {
	width = w;
	height = h;
}



// main
int main() {
	Oval a, b(3, 4);
	a.set(10, 20);
	a.show();
	b.show();
}
