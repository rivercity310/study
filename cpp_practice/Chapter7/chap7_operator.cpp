#include <iostream>
using namespace std;

class Power {
	int kick;
	int punch;
public:
	Power(int kick = 0, int punch = 0) {
		this->kick = kick;
		this->punch = punch;
	}
	void show();
	Power operator+(Power op2);
	bool operator==(Power& op2);
	Power& operator+=(Power op2);
};

void Power::show() {
	cout << "Kick: " << kick << ", " << "Punch: " << punch << endl;
}

Power Power::operator+(Power op2) {
	Power temp;
	temp.kick = this->kick + op2.kick;
	temp.punch = this->punch + op2.punch;

	return temp;
}

bool Power::operator==(Power& op2) {
	if (kick == op2.kick && punch == op2.punch) return true;
	else return false;
}

Power& Power::operator+=(Power op2) {
	kick += op2.kick;
	punch += op2.punch;

	return *this;
}

void Oper() {
	Power a(1, 2), b(2, 1), c(3, 3), d;
	d = a + b;
	a.show();
	b.show();
	d.show();
	c += a;
	c.show();

	if (d == c) cout << "두 파워가 같음" << endl;
	else cout << "두 파워가 다름" << endl;
}