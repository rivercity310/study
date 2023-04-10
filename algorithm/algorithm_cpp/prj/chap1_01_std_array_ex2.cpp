#include <array>
#include <iostream>
#include <type_traits>

/*
[ Ex2. 빠르고 범용적인 데이터 저장 컨테이너 만들기 ]
다양한 타입의 데이터 여러 개를 인자로 받아 공통 타입으로 변환하는 함수
*/

// 함수 반환형을 후행 리턴 타입으로 지정
template <typename ...Args> 
auto build_array(Args&&... args)->std::array<typename std::common_type<Args...>::type, sizeof...(args)> {
	using commonType = typename std::common_type<Args...>::type;
	return { std::forward<commonType>((Args&&)args)... };
}

void std_array_ex2() {
	auto data = build_array(1, 0u, 'a', 3.2f, false);

	for (auto i : data) std::cout << i << " ";
	std::cout << std::endl;
}