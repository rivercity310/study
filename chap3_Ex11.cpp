#include <iostream>
#include "Box.h"

using namespace std;

Box::Box(int w, int h) {
	setSize(w, h);
	fill = '*';
}

void Box::setFill(char f) {
	this->fill = f;
}

void Box::setSize(int w, int h) {
	width = w;
	height = h;
}

void Box::draw() {
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++)
			cout << fill;
		cout << endl;
	}
}

/*
int main() {
	Box b(10, 2);
	b.draw();
	cout << endl;

	b.setSize(4, 7);
	b.setFill('^');
	b.draw();
}
*/