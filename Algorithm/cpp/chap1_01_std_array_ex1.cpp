#include <iostream>
#include <sstream>
#include <algorithm>

/*
[ Ex1 : 동적 크기 배열 구현하기 ]
학교에서 학생 정보를 관리하는 간단한 응용 프로그램
*/

template <typename T> class dynamic_array {
	T* data;
	size_t n;

public:
	// 생성자와 복사 생성자
	dynamic_array(int n) {
		this->n = n;
		data = new T[n];
	}

	dynamic_array(const dynamic_array<T>& other) {
		n = other.n;
		data = new T[n];

		for (int i = 0; i < n; i++) data[i] = other[i];
	}

	// 멤버에 직접 접근하기 위한 [] 연산자와 at 함수 작성
	T& operator[](int idx) {
		return data[idx];
	}

	const T& operator[](int idx) const {
		return data[idx];
	}

	T& at(int idx) {
		if (idx < n) return data[idx];
		throw "Index out of range";
	}


	// 배열의 크기를 반환하는 size와 소멸자 구현
	size_t size() const {
		return n;
	}

	~dynamic_array() {
		delete[] data;
	}


	// 이 배열의 원소를 순회할 때 사용할 반복자 관련 함수 정의
	T* begin() { return data; }
	const T* begin() const { return data; }
	T* end() { return data + n; }
	const T* end() const { return data + n; }


	// 두 배열을 하나로 합치는 연산을 수행하는 + 연산자 함수 정의
	friend dynamic_array<T> operator+(const dynamic_array<T>& arr1, dynamic_array<T>& arr2) {
		dynamic_array<T> result(arr1.size() + arr2.size());
		std::copy(arr1.begin(), arr1.end(), result.begin());
		std::copy(arr2.begin(), arr2.end(), result.begin() + arr1.size());

		return result;
	}


	// 배열에 저장된 모든 데이터를 문자열로 반환하는 함수
	std::string to_string(const std::string& sep = ", ") {
		if (n == 0) return "";

		std::ostringstream os;
		os << data[0];
		for (int i = 1; i < n; i++) os << sep << data[i];

		return os.str();
	}
};

// 학생 정보를 저장할 구조체 정의
struct student {
	// 학생의 이름과 학급 정보
	std::string name;
	int standard;
};

std::ostream& operator<<(std::ostream& os, const student& s) {
	return (os << "[" << s.name << ", " << s.standard << "]");
}

void std_array_ex1() {
	int n;
	std::cout << "1반 학생 수? ";
	std::cin >> n;

	dynamic_array<student> class1(n);
	for (int i = 0; i < n; i++) {
		std::string name;
		int standard;

		std::cout << i + 1 << "번째 학생의 이름과 나이를 입력: ";
		std::cin >> name >> standard;

		class1[i] = student{ name, standard };
	}

	// 깊은 복사
	auto class2 = class1;
	std::cout << "1반을 복사하여 2반 생성: " << class2.to_string() << std::endl;

	// 두 학급을 합쳐서 새로운 큰 학급 생성
	auto class3 = class1 + class2;
	std::cout << "1반과 2반을 합쳐 3반 생성: " << class3.to_string() << std::endl;
}