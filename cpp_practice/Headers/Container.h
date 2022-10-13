#pragma once

class Container {
private:
	int size;        // 현재 저장량, 최대 저장량은 10
public:
	Container();
	void fill();     // 10으로 채우기
	void consume(int n);  // 1만큼 소모
	int getSize();   // 현재 크기 리턴
};