#include <iostream>
#include <string>
using std::string;

class Trace {
	static string log;
public:
	static void put(string tag, string info);
	static void print(string tag = "none");
};

string Trace::log = "";
void Trace::put(string tag, string info) {
	log.append(tag + "\t" + info + "\n");
}

void Trace::print(string tag) {
	string msg, newlog = ""; //= tag == "none" ? "모든 Trace 정보를 출력합니다." : tag + " 태그의 Trace 정보를 출력합니다.";
	
	if (tag == "none") {
		msg = "모든 Trace 정보를 출력합니다.";
		newlog = log;
	}
	else {
		msg = tag + " 태그의 Trace 정보를 출력합니다.";
		
		string delimiter = "\n";

		// 부호없는 정수 자료형 (unsigned int)
		size_t pos = 0;
		string token;
		while ((pos = log.find(delimiter)) != string::npos) {
			token = log.substr(0, pos);
			std::cout << token << std::endl;
			log.erase(0, pos + delimiter.length());
		}

		std::cout << log << std::endl;
	}
	
	std::cout << "------- " << msg << " -------" << std::endl;
	std::cout << newlog << std::endl;
}

void f() {
	int a, b, c;
	std::cout << "두 개의 정수를 입력하세요: ";
	std::cin >> a >> b;
	Trace::put("f()", "정수를 입력 받았음");    // 디버깅 정보 저장
	c = a + b;
	Trace::put("f()", "합 계산");
	std::cout << "합은 " << c << std::endl;
}

void chap6_Ex8() {
	Trace::put("main()", "프로그램을 시작합니다");
	f();
	Trace::put("main()", "종료");

	Trace::print("f()");  // "f()" 태그를 가진 디버깅 정보를 모두 출력한다.
	Trace::print();      // 모든 디버깅 정보를 출력한다.
}