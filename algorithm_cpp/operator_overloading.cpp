#include <iostream>
using namespace std;

class Power {
private:
	int kick;
	int punch;

public:
	Power(int kick = 0, int punch = 0) {
		this->kick = kick;
		this->punch = punch;
	}

	// 이항 연산자 중복 (멤버 함수로 작성)
	Power operator+(Power op2) {
		Power tmp;
		tmp.kick = this->kick + op2.kick;
		tmp.punch = this->punch + op2.punch;

		return tmp;
	}

	bool operator==(Power op2) {
		if (this->kick == op2.kick && this->punch == op2.punch) return true;
		else return false;
	}

	Power& operator+=(Power op2) {
		kick += op2.kick;
		punch += op2.punch;

		return *this;		// 객체 자신의 참조를 리턴
	}

	
	/*
	[ 외부 함수를 friend로 선언하는 경우 멤버 함수로 선언하는 경우와는 조금 다르다 (매개변수) ]
	friend Power operator+(Power op1, Power op2) {
		Power tmp;
		tmp.kick = op1.kick + op2.kick;
		tmp.punch = op1.punch + op2.punch;

		return tmp;
	}
	*/


	// 단항 연산자 중복 (전위, 후위에 따라 다름 주의), 전위는 매개변수 없음
	// 전위 연산자 중복
	Power& operator++() {
		kick++;
		punch++;

		return *this;
	}

	Power& operator--() {
		kick--;
		punch--;

		return *this;
	}

	/*
	friend Power& operator++(Power& op) {
		op.kick++;
		op.punch++;

		return op;
	}
	*/

	bool operator!() {
		if (kick == 0 && punch == 0) return true;
		else return false;
	}


	// 후위 연산자 중복 (매개변수 x에는 의미없는 값이 전달되므로 무시, 단지 구분을 위함)
	Power operator++(int x) {       
		Power tmp = *this;		// 현재 상태를 저장해놓고 증가시킴
		kick++;
		punch++;

		return tmp;
	}

	Power operator--(int x) {
		Power tmp = *this;
		kick--;
		punch--;

		return tmp;
	}

	/*
	friend Power& operator++(Power& op, int x) {
		Power tmp = op;
		op.kick++;
		op.punch++;

		return tmp;
	}
	*/


	friend Power& operator<<(Power& op, int n) {
		op.kick += n;
		op.punch += n;

		return op;
	}


	void to_string() {
		cout << "(" << kick << ", " << punch << ")" << endl;
	}
};

void operator_overloading() {
	Power a(1, 2), b(3, 4);
	Power c = a + b;
	c.to_string();

	Power d = c;
	if (d == c) cout << "같음" << endl;
	else cout << "다름" << endl;

	Power e = c += d;
	e.to_string();

	Power f = ++e;
	f.to_string();

	f++;
	f.to_string();

	f--;
	f.to_string();

	Power k;
	k << 1 << 2 << 3 << 4 << 5;
	k.to_string();
}