#include <iostream>
#include "Rect.h"

using namespace std;

Rect::Rect() : Rect(1, 1) {}
Rect::Rect(int w, int h) : width(w), height(h) {}
Rect::~Rect() { cout << "Rect 객체 소멸..." << endl; }

void Rect::setWidth(int w) { width = w; }
void Rect::setHeight(int h) { height = h; }
int Rect::getArea() {
	return width * height;
}

/*
int main() {
	Rect rectArray[3];      // Rect 객체 배열 생성
	
	for (int i = 0; i < 3; i++) {
		cout << "width와 height 입력: ";
		int w, h;
		cin >> w >> h;
		rectArray[i].setWidth(w);
		rectArray[i].setHeight(h);
	}

	for (int i = 0; i < 3; i++)
		cout << "rectArray[" << i << "]의 면적은: " << rectArray[i].getArea() << endl;

	// 객체 포인터로 배열 접근
	Rect* rectPtr;
	rectPtr = rectArray;
	for (int i = 0; i < 3; i++) {
		cout << "rectArray[" << i << "]의 면적은: " << rectPtr->getArea() << endl;
		rectPtr++;
	}
}
*/