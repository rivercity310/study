#include <iostream>
#include <string>
#include <cstring>

using namespace std;

/*
날짜를 다루는 Date 클래스 작성
*/

class Date {
private:
	int year, month, day;
public:
	Date(int y, int m, int d);
	Date(string f);
	void show();

	int getYear() { return this->year; }
	int getMonth() { return this->month; }
	int getDay() { return this->day; }
};

Date::Date(int y, int m, int d) {
	this->year = y;
	this->month = m;
	this->day = d;
}

// 1945/8/15 같은 형태로 입력
Date::Date(string f) {
	/*
	string 클래스에는 토크나이징 기능의 함수가 없음..
	cstring 헤더에 있는 strtok_s을 사용하여 문자열을 분리하도록 하자
	char** context에 자르고 남은 문자열을 저장한다.

	[ 변환 과정 ] 
	1. string을 char[]로 변환: strcpy(char* destination, char* source)
	2. char[]을 strtok으로 잘라냄: strtok_s(char* str, const char* delimeters, char** context)
	3. 잘라낸 char[]을 다시 string으로 변환
	4. string 배열에 적재
	*/

	char str_buf[100];
	strcpy_s(str_buf, f.c_str());
	
	char* context = NULL;
	char* tok = strtok_s(str_buf, "/", &context);

	string str_arr[100];
	int str_cur = 0;
	while (tok != NULL) {
		str_arr[str_cur++] = string(tok);
		tok = strtok_s(NULL, "/", &context);
	}

	cout << str_arr[0] << endl;
	cout << str_arr[1] << endl;
	cout << str_arr[2] << endl;

	year = atoi(str_arr[0].c_str());
	month = atoi(str_arr[1].c_str());
	day = atoi(str_arr[2].c_str());
}

void Date::show() {
	cout << year << "년 " << month << "월 " << day << "일" << endl;
}

/*
int main() {
	Date birth(1997, 01, 26);
	Date independencyDay("1945/08/15");
	independencyDay.show();
	birth.show();
}
*/

