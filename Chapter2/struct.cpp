#include <iostream>

using namespace std;

struct stCircle {
	// 구조체의 디폴트 접근 지정은 public
	stCircle();
	stCircle(int r);
	~stCircle();
	double getArea();

private:
	int radius;
};

stCircle::stCircle() : stCircle(1) {}
stCircle::stCircle(int r) {
	this->radius = r;
}
stCircle::~stCircle() {
	cout << radius << " 원 소멸";
}

double stCircle::getArea() {
	return 3.14 * radius * radius;
}

class classCircle {
	// 클래스의 디폴트 접근 지정은 private
	int radius;
public:
	classCircle();
	classCircle(int r);
	double getArea();
}; 

/*
int main() {
	stCircle st1;
	cout << st1.getArea() << endl;
}
*/