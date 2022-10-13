#include <iostream>
using namespace std;

class Power2 {
	int kick;
	int punch;
public:
	Power2(int kick = 0, int punch = 0) {
		this->kick = kick;
		this->punch = punch;
	}
	void show() {
		cout << "Kick: " << kick << ", " << "Punch: " << punch << endl;
	}
	Power2 operator++(int x);     // 전위 연산자와의 구분을 위해 의미없는 매개변수 x를 가지도록 선언해야 한다.
};

Power2 Power2::operator++(int x) {
	Power2 temp = *this;       // 증가 이전 객체 상태를 저장
	kick++;
	punch++;
	return temp; 
}

void oper2() {
	Power2 a(3, 5), b;
	a.show();
	b.show();
	b = a++;
	a.show();
	b.show();
}