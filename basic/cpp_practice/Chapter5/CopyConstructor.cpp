#include <iostream>
using namespace std;

class Circle {
private:
	int radius;
public:
	Circle() : Circle(1) {}
	Circle(int r) { this->radius = r; }
	Circle(const Circle& c) {          // 복사 생성자
		this->radius = c.radius;
		cout << "복사 생성자 실행 radius = " << radius << endl;
	}
	double getArea() { return 3.14 * pow(radius, 2); }
};

void copyConstructor() {
	Circle src(30);     // src 객체의 보통 생성자 호출
	Circle dest(src);   // dest 객체의 복사 생성자 호출

	cout << "원본의 면적 = " << src.getArea() << endl;
	cout << "사본의 면적 = " << dest.getArea() << endl;
}