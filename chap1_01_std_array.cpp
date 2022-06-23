#include <iostream>
#include <array>

// 깊은 복사를 하지 않기 위해 참조연산자 & 사용
template <size_t N> 
void prt_array(const std::array<int, N>& arr) {
	// 범위 기반 for문
	// 내부적으로 iterator 사용 => begin() 위치부터 ++해서 end() 위치에 도달하면 종료
	for (auto element : arr)
		std::cout << element << " ";
	std::cout << std::endl;

	// 위 코드는 다음과 동일하다
	for (auto it = arr.begin(); it != arr.end(); it++)
		std::cout << *it << " ";
	std::cout << std::endl;
}

void chap1_array() {
	// 01. 크기가 10인 int 타입 배열 선언
	std::array<int, 10> arr1;       
	arr1[0] = 1;
	std::cout << "arr1의 첫번째 원소: " << arr1[0] << std::endl;


	// 02. 초기화
	std::array<int, 4> arr2 = { 1, 2, 3, 4 };
	std::cout << "arr2의 모든 원소: ";
	for (int i = 0; i < arr2.size(); i++) std::cout << arr2[i] << " ";
	for (auto element : arr2) std::cout << element << " ";
	for (auto it = arr2.begin(); it != arr2.end(); it++) std::cout << *it << " ";
	std::cout << std::endl;


	// 03. 예외 처리 코드
	// at() 함수는 인자로 전달된 index 값이 유효하지 않으면 std::out_of_range 예외를 발생시킨다.
	// arr1.at(50)
	std::array<int, 4> arr3 = { 1, 2, 3, 4 };
	try {
		std::cout << arr3.at(3) << std::endl;
		std::cout << arr3.at(4) << std::endl;
	}
	catch (const std::out_of_range& ex) {
		std::cerr << ex.what() << std::endl;       // std::cerr : 에러 출력 관련 -> C의 stdout과 stderr이 있듯이
	}


	// 04. 순서대로 출력 -> 다양한 크기의 int형 배열을 출력하기 위해 template으로 함수 작성
	std::array<int, 6> arr4 = { 5, 6, 7, 8, 9, 0 };
	prt_array(arr4);



	// 05. 유용한 메소드
	// front() : 배열 첫 원소에 대한 참조 반환
	// back() : 배열 마지막 원소에 대한 참조 반환
	// data() : 배열 객체 내부에서 실제 데이터 메모리 버퍼를 가리키는 포인터 반환

	// 5 0 6 출력
	std::cout << arr4.front() << "\n";
	std::cout << arr4.back() << "\n";
	std::cout << *(arr4.data() + 1) << std::endl;
}