#include <iostream>

using namespace std;

void swapWithCallByValue(int a, int b) {
	int temp;

	temp = a;
	a = b;
	b = temp;
}

void swapWithCallByAddress(int *a, int *b) {
	int temp;

	temp = *a;
	*a = *b;
	*b = temp;
}

void swapWithCallByReference(int& a, int& b) {
	int temp;

	temp = a;
	a = b;
	b = temp;
}

void callBy() {
	int m = 2, n = 9;
	swapWithCallByValue(m, n);
	cout << m << ", " << n << endl;        // 값이 복사되어 전달되므로 스왑되지 않음

	swapWithCallByAddress(&m, &n);
	cout << m << ", " << n << endl;        // 실주소를 전달하였으므로 값이 스왑됨

	swapWithCallByReference(m, n);         // 참조에 의한 호출
	cout << m << ", " << n << endl;
}




/*
// 값에 의한 호출, 소멸자만 실행됨
void increase1(Circle c) {
	int r = c.getRadius();
	c.setRadius(r + 1);         // 원본 객체의 내용은 변하지 않음
}

// 참조에 의한 호출, 생성자 소멸자 실행X
void increase2(Circle& c) {
	int r = c.getRadius();
	c.setRadius(r + 1);         // c가 참조하는 원본 객체의 반지름 1 증가
}
*/


/*
char c = 'a';

char get() {
	return c;
}

char& find() {        // char 타입의 참조 리턴
	return c;         // 변수 c에 대한 참조 리턴
}

void referenceReturn() {
	find() = 'b';       // c = 'b'가 됨
}
*/



char& find(char s[], int idx) {
	return s[idx];
}

void referenceReturn() {
	char name[] = "Seungsu";
	cout << name << endl;

	find(name, 0) = 'K';
	cout << name << endl;

	char& ref = find(name, 2);   // ref는 name[2]에 대한 참조
	ref = 't';
	cout << name << endl;
}

/*
[ 출력 ]
Seungsu
Keungsu
Ketngsu
*/