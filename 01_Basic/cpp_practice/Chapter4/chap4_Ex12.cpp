#include <iostream>
#include <string>

#include "CircleManager.h"
using namespace std;

void namedCircle::setCircle(string name, int radius) {
	this->name = name;
	this->radius = radius;
}

double namedCircle::getArea() {
	return 3.14 * pow(radius, 2);
}

string namedCircle::getName() {
	return name;
}




CircleManager::CircleManager(int size) {
	this->size = size;
	circlePtr = new namedCircle[size];
}

CircleManager::~CircleManager() {
	cout << "CircleManager 소멸!!" << endl;
}

void CircleManager::initCircle() {
	for (int i = 0; i < size; i++) {
		string name;
		int radius;
		cout << "원 " << i + 1 << "의 이름과 반지름: ";
		cin >> name >> radius;
		(circlePtr + i)->setCircle(name, radius);
	}
}


void CircleManager::searchByName() {
	cout << "검색하고자 하는 원의 이름: ";
	string inputStr;
	cin >> inputStr;

	for (int i = 0; i < size; i++) {
		string circleName = (circlePtr + i)->getName();
		if (circleName == inputStr) {
			cout << inputStr << "의 면적은 " << (circlePtr + i)->getArea() << endl;
			return;
		}
	}

	cout << "등록되지 않은 원입니다." << endl;
	return;
}

void CircleManager::searchByArea() {
	cout << "최소 면적을 정수로 입력하세요: ";
	int minArea;
	cin >> minArea;

	cout << minArea << "보다 큰 원을 검색합니다." << endl;
	for (int i = 0; i < size; i++) {
		double area = (circlePtr + i)->getArea();
		if (minArea <= area) {
			string circleName = (circlePtr + i)->getName();
			cout << circleName << "의 면적은 " << area << ", ";
		}
	}
	cout << endl;
	return;
}

void Ex12() {
	cout << "원의 개수 >> ";
	int size;
	cin >> size;
	CircleManager CM(size);
	CM.initCircle();

	while (true) {
		CM.searchByName();
		CM.searchByArea();
	}
}