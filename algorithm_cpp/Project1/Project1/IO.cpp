#include <iostream>
#include <cstring>
#include <iomanip>

using namespace std;

// 출력 예제 ostream 클래스
void ostream_ex() {
	cout.put('A');
	cout.put(97);     // a

	cout.put('C').put('+').put('+');       // 참조를 리턴하므로 메서드 체이닝 가능

	char str[] = "I Love C++ Programming";
	cout.write(str, 6);                     // str에서 n개 출력 -> I Love 출력

	cout.flush();                           // 버퍼에 있는 문자 강제 출력
}


// 입력 예제 istream 클래스
void istream_ex() {
	// 문자 읽기

	// 01. int get()
	int ch;
	while ((ch = cin.get()) != EOF) {		// get(): 입력 스트림에서 문자를 읽어 리턴
		cout.put(ch);
		if (ch == '\n') break;
	}

	// 02. istream& get(char& ch)
	char c;
	while (true) {
		cin.get(c);   // 키를 c에 읽어옴
		if (cin.eof()) break;

		cout.put(c);
		if (c == '\n') break;
	}




	// 문자열 읽기
	
	// 01. istream& get(char* s, int n)
	// 입력 스트림에서 n - 1개의 문자를 읽어 s에 저장 마지막에 '\0' 삽입
	char cmd[80];
	while (true) {
		cin.get(cmd, 80);
		if (strcmp(cmd, "exit") == 0) break;
		
		cin.ignore(1);         // 버퍼에 남아있는 '\n' 제거 (무한루프 방지)
	}
	cout << cmd << endl;


	// 02. 한줄 읽기 
	// istream& get(char* s, int n, char delim='\n')
	// istream& getline(char* s, int n, char delim='\n')   -> delim에 지정된 구분 문자를 스트림에서 제거

	char line[80];
	cin.getline(line, 80);   // getline 함수는 '\n'을 스트림 버퍼에서 제거해주기 때문에 ignore() 안해도 됨
	cout << line << endl;
}




/*
[ C++의 포맷 입출력 조작 방식은 다음 세가지가 있다. ]
- 포맷 플래그
- 포맷 함수
- 조작자
*/

// ios -> 입출력 최상위 객체
// 포멧플래그 -> ios 객체 내에 상수로 정의됨
void formatflag_ex() {
	cout << 30 << "\n";			// 10진수 출력
	
	cout.unsetf(ios::dec);		// 10진수 해제
	cout.setf(ios::hex);		// 16진수 설정
	cout << 30 << "\n";

	cout.setf(ios::showbase);	// 16진수에 0x접두어 붙이도록 설정
	cout << 30 << "\n";

	cout.setf(ios::uppercase);	// 16진수의 A~F를 대문자로 출력
	cout << 30 << "\n";

	// 비트 OR 연산으로 동시에 2개 지정 가능
	cout.setf(ios::dec | ios::showpoint);   // 10진수 표현과 동시에 실수에 소숫점 이하 나머지 0으로 출력
	cout << 23.5 << "\n";

	cout.setf(ios::scientific);		// 실수를 과학산술용 표현으로 출력
	cout << 23.5 << "\n";

	cout.setf(ios::showpos);		// 양수인 경우 + 부호도 함께 출력
	cout << 23.5 << endl;
}

void format_function_ex() {
	cout.width(10);				// 다음 1회 출력을 너비 10칸으로 지정 ( << 연산자에만 유효 )
	cout << "Hello" << "\n";

	cout.fill('-');				// 빈칸을 '-' 문자로 채움
	cout.width(10);
	cout << "Hello" << "\n";		

	cout.precision(5);			// 출력되는 숫자의 자릿수를 5로 지정 (반올림)
	cout << 11. / 3. << "\n";

}

// 조작자를 이용한 입출력 포맷 지정 방법
void manipulator_ex() {
	/*
	[ 조작자 : 용도 ]

	01. 매개변수가 없는 조작자
	- endl : 스트림 버퍼를 모두 출력(flush)하고 다음 줄로 넘김
	- oct : 정수 필드를 8진수 기반으로 출력
	- dec : 정수 필드를 10진수 기반으로 출력
	- hex : 정수 필드를 16진수 기반으로 출력
	- left : 왼쪽 맞춤으로 출력
	- right : 오른쪽 맞춤으로 출력
	- fixed : 실수 필드를 고정 소수점 방식으로 출력
	- scientific : 실수 필드를 과학 산술용 방식으로 출력
	- flush : 스트림 버퍼 강제 출력
	- showbase : 16진수의 경우 0x, 8진수의 경우 0을 앞에 붙여 출력
	- noshowbase : showbase 지정 취소
	- showpoint : 실수값에 대해, 정수 부분과 소수점 이하의 끝자리 이후 남은 공간 0으로 채워서 출력
	- noshowpoint : showpoint 지정 취소
	- showpos : 양수인 경우 + 부호를 붙여 출력
	- skipws : 입력 스트림에서 공백 문자를 읽지 않고 건너 뜀
	- noskipws : skipws 취소
	- boolalpha : 불린 값을 문자열 "true", "false"로 출력

	02. 매개변수를 가지는 조작자 -> iomanip 헤더파일에 정의
	- setw(n) : 출력 너비를 n으로 설정
	- setfill(c) : 빈공간 c로 채워서 출력
	- resetioflags(flags) : flags에 지정된 플래그들 해제
	- setbase(base) : base를 출력할 수의 진수로 지정
	- setioflags(flags) : flags를 스트림 입출력 플래그로 설정
	- setprecision(n) : 출력되는 수의 유효 숫자 자리수를 n개로 설정
	- 
	*/


	cout << hex << showbase << 30 << "\n";
	cout << dec << showpos << 100 << "\n";
	cout << true << " " << false << "\n";
	cout << boolalpha << true << " " << false << endl;

	cout << endl;

	cout << showbase;
	cout << setw(8) << "Number";
	cout << setw(10) << "Octal";
	cout << setw(10) << "Hexa" << endl;

	for (int i = 0; i < 50; i += 5) {
		cout << setw(8) << setfill('.') << dec << i;
		cout << setw(10) << setfill(' ') << oct << i;
		cout << setw(10) << setfill(' ') << hex << i << endl;
	}
}