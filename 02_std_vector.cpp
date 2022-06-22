#include <iostream>
#include <vector>

void chap1_vector() {
	// 크기가 0인 벡터 선언
	std::vector<int> vec;

	// 지정한 초깃값으로 이루어진 크기가 5인 벡터 선언
	std::vector<int> vec2 = { 1, 2, 3, 4, 5 };

	// 크기가 10인 벡터 선언
	std::vector<int> vec3(10);

	// 크기가 10이고, 모든 원소가 5로 초기화된 벡터 선언
	std::vector<int> vec4(10, 5);

	vec4.push_back(1);
	vec4.push_back(2);
	vec4.insert(vec4.begin(), 0);    // 맨 앞에 0 추가
	vec4.insert(find(vec4.begin(), vec4.end(), 1), 4);  // 1 앞에 4 추가

	// emplace: 새 원소 위치에 곧바로 객체 생성 -> insert나 push_back에 비해 성능 좋음
	vec4.emplace(vec4.begin(), 11);   // 맨 앞에 11 추가
	vec4.emplace_back(12);			  // 맨 뒤에 12 추가
	
	vec4.pop_back();				// 맨 뒤 원소 하나 제거
	vec4.erase(vec4.begin());		// 맨 처음 원소 제거
	vec4.erase(vec4.begin(), vec4.begin() + 4);  // 1번째 원소부터 4번쨰 앞 원소까지 제거
	
	for (auto element : vec4) std::cout << element << " ";
	std::cout << std::endl;

	vec4.clear();          // 모든 원소 제거
	vec4.reserve(100);     // 벡터에서 사용할 용량 지정
	vec4.shrink_to_fit();  // 여분의 메모리 공간을 해제 (용량 = 크기)
}