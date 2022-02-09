#include <iostream>
using namespace std;

class Power3 {
	int kick, punch;
public:
	Power3(int kick = 0, int punch = 0) {
		this->kick = kick;
		this->punch = punch;
	}
	void show() {
		cout << "Kick: " << kick << ", " << "Punch: " << punch << endl;
	}
	friend Power3 operator+(int op1, Power3 op2);
	friend Power3& operator++(Power3& op);       // 전위 ++ 연산자 함수
	friend Power3 operator++(Power3& op, int x); // 후위 ++ 연산자 함수
	Power3& operator << (int n);
};

Power3 operator+(int op1, Power3 op2) {
	Power3 temp;
	temp.kick = op1 + op2.kick;
	temp.punch = op1 + op2.punch;
	return temp;
}

Power3& operator++(Power3& op) {
	op.kick++;
	op.punch++;
	return op;
}

Power3 operator++(Power3& op, int x) {
	Power3 temp = op;       // 변경하기 전의 op 상태 저장
	op.kick++;
	op.punch++;
	return temp;
}

Power3& Power3::operator<<(int n) {
	kick += n;
	punch += n;
	return *this;
}

void oper3() {
	Power3 a(3, 5), b;
	b = ++a;
	a.show();
	b.show();

	b = a++;
	a.show();
	b.show();

	a << 3 << 5 << 6;
	a.show();
}