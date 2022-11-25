#include <iostream>
#include "Rect.h"

using namespace std;

/*
int main() {
	// Rect* dRectArray = new Rect[5];
	Rect* dRectArray = new Rect[5] {Rect(1, 1), Rect(2, 2), Rect(3, 3), Rect(4, 4), Rect(5, 5)};
	
	
	for (int i = 0; i < 5; i++) {
		cout << "width, height: ";
		int w, h;
		cin >> w >> h;
		// 다음 두가지 방법으로 배열 원소 객체에 접근 가능
		(dRectArray + i)->setWidth(w);
		dRectArray[i].setHeight(h);
	}
	
	for (int i = 0; i < 5; i++) 
		cout << (dRectArray + i)->getArea() << " ";
	cout << endl;

	delete[] dRectArray;
}
*/