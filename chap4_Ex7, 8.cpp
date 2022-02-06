#include <iostream>
#include "Circle.h"

using namespace std;

inline Circle::Circle() {
	cout << "원이 성공적으로 생성되었습니다." << endl;
}

inline Circle::~Circle() {
	cout << "반지름 " << radius << "인 원 소멸!" << endl;
}

inline void Circle::setRadius(int radius) {
	this->radius = radius;
}

inline double Circle::getArea() {
	return 3.14 * pow(radius, 2);
}

void Ex7() {
	cout << "원 몇개를 생성할까요? ";
	int length;
	cin >> length;

	Circle* circlePtr = new Circle[length];
	int areaCheck = 0;
	for (int i = 0; i < length; i++) {
		cout << "원 " << i + 1 << "의 반지름 >> ";
		int radius;
		cin >> radius;
		(circlePtr + i)->setRadius(radius);

		double circleArea = (circlePtr + i)->getArea();
		if (circleArea >= 100) areaCheck++;
	}

	cout << "면적이 100보다 큰 원은 " << areaCheck << "개 입니다." << endl;
	delete[] circlePtr;
}