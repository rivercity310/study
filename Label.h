#pragma once
// 정점의 경계로부터 거리 정보를 저장하기 위한 구조체
template <typename T>
struct Label {
	unsigned ID;
	T distance;

	// Label 객체 비교는 거리 값을 이용
	inline bool operator>(const Label<T>& l) const {
		return this->distance > l.distance;
	}
};
