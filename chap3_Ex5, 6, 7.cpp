#include <iostream>

// C/C++ 난수에 대한 자세한 설명: https://modoocode.com/304
// 아래 두 라이브러리는 C 스타일 난수 발생을 위해 사용한다.
#include <cstdlib>
#include <ctime>

// C++의 난수 발생을 위한 라이브러리
#include <random>

using namespace std;

/*
랜덤 수를 발생시키는 Random 클래스를 만들고, 랜덤한 정수를 10개 출력
*/

class Random {
private:
	int randomInteger;
public:
	int next();
	int nextInRange(int left, int right);

	int getRandomInteger() { return this->randomInteger; }
};

// 0부터 RAND_MAX(32767) 사이의 랜덤한 정수를 리턴
int Random::next() {
	// C 스타일의 난수 발생 (갖다 버리자)
	srand((unsigned)::time(0));
	this->randomInteger = rand();
	

	return this->randomInteger;
}

// left <= x <= right를 만족하는 x 리턴
int Random::nextInRange(int left, int right) {
	// C++의 random 라이브러리를 통한 난수 발생
	
	random_device rd;      // 시드값을 얻기 위한 random_device 생성
	mt19937 gen(rd());     // random_device를 통해 난수 생성 엔진을 초기화한다

	// left부터 right까지 균등하게 나타나는 난수를 생성하기 위해 균등 분포 정의
	uniform_int_distribution<int> dis(left, right);   
	
	// 난수 획득
	this->randomInteger = dis(gen);
	
	return this->randomInteger;
}

/*
int main() {
	Random r;
	cout << "0부터 " << RAND_MAX << "까지의 랜덤한 정수 10개 출력" << endl;
	for (int i = 0; i < 10; i++) cout << r.next() << " ";
	cout << endl;

	cout << "0부터 100까지의 랜덤한 정수 10개 출력 " << endl;
	for (int i = 0; i < 10; i++) cout << r.nextInRange(0, 100) << " ";
	cout << endl;

	cout << "0부터 100까지 짝수 난수 10개 출력" << endl;
	int count = 0;
	while (count < 10) {
		int k = r.nextInRange(0, 100);
		if (k % 2 == 0) {
			cout << k << " ";
			count++;
		}
		else continue;
	}
	cout << endl;

	cout << "0부터 100까지 홀수 난수 10개 출력" << endl;
	int count2 = 0;
	while (count2 < 10) {
		int k = r.nextInRange(0, 100);
		if (k % 2 != 0) {
			cout << k << " ";
			count2++;
		}
		else continue;
	}
	cout << endl;
}
*/