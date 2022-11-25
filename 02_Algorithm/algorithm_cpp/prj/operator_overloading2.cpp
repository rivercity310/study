#include <iostream>
using namespace std;

class dot {
	int x;
	int y;
public:
	dot(int x = 0, int y = 0) : x(x), y(y) {};

	friend ostream& operator<<(ostream& os, const dot d) {
		os << "(" << d.x << ", " << d.y << ")";
		return os;
	}

	// 추출 연산자 함수에서 두번째 매개변수를 참조로 선언해야 한다.
	friend istream& operator>>(istream& is, dot& d) {
		cout << "x: ";
		is >> d.x;
		cout << "y: ";
		is >> d.y;

		return is;
	}
};

// 사용자 조작자 만들기 : 매개변수 os에 cout이 전달
ostream& fivestar(ostream& os) {
	return os << "*****";
}

ostream& rightarrow(ostream& os) {
	return os << "---->";
}

void operator_overloading2() {
	// 입력 연산자(<<) 중복 ostream& 반환
	dot d(4, 5);
	cout << d << endl;


	// 추출 연산자(>>) 중복 
	dot f;
	cin >> f;
	cout << f << endl;



	// 사용자 정의 조작자
	cout << fivestar << " C++ " << rightarrow << endl;
}