#pragma once
class Sample {
	int* p;
	int size;
public:
	Sample(int n) {
		size = n;
		p = new int[n];
	}
	~Sample() {}

	void read();       // 동적 할당받은 정수 배열 p에 사용자로부터 정수를 입력받음
	void write();      // 정수 배열을 화면에 출력
	int big();         // 정수 배열에서 가장 큰 수 리턴
};
