#pragma once
#include <string>

using namespace std;

class namedCircle {
	int radius;
	string name;
public:
	void setCircle(string name, int radius);
	double getArea();
	string getName();
};

class CircleManager {
	namedCircle* circlePtr;
	int size;
public:
	CircleManager(int size);
	~CircleManager();
	void initCircle();       // Circle 객체 배열 초기화
	void searchByName();     // 사용자로부터 원의 이름을 입력받아 면적 출력
	void searchByArea();     // 사용자로부터 면적을 입력받아 면적보다 큰 원 출력
};