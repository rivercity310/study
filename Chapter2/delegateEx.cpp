#include <iostream>

using namespace std;

class Point {
private:
	// 필드에 직접 선언하여 초기화 가능
	// ex) int x = 0; int y = 1;
	int x, y;
public:
	Point();
	Point(int x, int y);
	~Point();
	void show();
};

Point::Point() : Point(0, 0) {}
Point::Point(int a, int b) : x(a), y(b) {}   // 이렇게 선언해도 됨

Point::~Point() {
	
	cout << "점 " << x << ", " << y << " 소멸" << endl;
}

void Point::show() {
	cout << "(" << x << ", " << y << ")" << endl;
}

/*
int main() {
	Point k1;
	k1.show();

	Point k2(5, 6);
	k2.show();
}
*/