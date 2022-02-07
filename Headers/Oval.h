#ifndef OVAL_H
#define OVAL_H

/*
[ 주어진 사각형에 내접하는 타원을 추상화한 클래스 Oval은 다음과 같다 ]
- 정수값의 사각형 너비와 높이를 가지는 width, height
- 너비와 높이 값을 매개 변수로 받는 생성자
- 너비와 높이를 1로 초기화하는 매개 변수 없는 생성자
- width와 height을 출력하는 소멸자
- 타원의 너비를 리턴하는 getWidth() 함수 멤버
- 타원의 높이를 리턴하는 getHeight() 함수 멤버
- 타원의 너비와 높이를 변경하는 set(int w, int h) 함수 멤버
- 타원의 너비와 높이를 화면에 출력하는 show() 함수 멤버
*/

class Oval {
private:
	int width, height;
public:
	Oval();
	Oval(int w, int h);
	~Oval();
	int getWidth();
	int getHeight();
	void set(int w, int h);
	void show();
};


#endif