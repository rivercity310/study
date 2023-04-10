#include <iostream>
#include <string>
using namespace std;

void combine(string text1, string text2, string& text3) {
	text3 = text1 + " " + text2;
}

void Ex3() {
	string text1("I love you"), text2("very much");
	string text3;
	combine(text1, text2, text3);       // text1과 " ", text2를 덧붙여 text3 만들기
	cout << text3 << endl;
}