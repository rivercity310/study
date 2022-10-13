#include <iostream>
#include <forward_list>

template <typename T>
void prt_forward_list(const std::forward_list<T>& f_list) {
	for (auto element : f_list) std::cout << element << " ";
	std::cout << std::endl;
}

void chap1_forward_list() {
	std::forward_list<int> fwd_list = { 1, 2, 3 };
	fwd_list.push_front(0);       // 맨 앞에 0 추가

	auto it = fwd_list.begin();

	// 새로운 원소를 삽입하려면 바로 앞 원소의 next 포인터를 수정해야 하므로
	fwd_list.insert_after(it, 5);  // 맨 앞 바로 뒤에 5 추가
	fwd_list.insert_after(it, 6);  // 맨 앞 바로 뒤에 6 추가


	// 원소 삭제
	fwd_list.pop_front();     // 맨 앞 원소 삭제
	// fwd_list.erase_after(it);  // 맨 앞 다음 원소를 삭제
	// fwd_list.erase_after(it, fwd_list.end());  // 맨 앞 원소 다음부터 맨 마지막까지 삭제

	prt_forward_list(fwd_list);
}

void chap1_forward_list2() {
	std::forward_list<int> list1 = { 23, 0, 1, -3, 34, 32 };
	list1.sort();   // default: std::less<value_type>

	prt_forward_list(list1);

	list1.sort(std::greater<int>());
	prt_forward_list(list1);
}

void chap1_forward_list3() {
	std::forward_list<int> list1 = { 2, 53, 1, 0, 4, 10 };
	list1.reverse();
	prt_forward_list(list1);

	list1 = { 0, 1, 0, 1, -1, 10, 5, 5, 10, 0 };
	list1.sort(std::greater<int>());
	list1.unique();
	prt_forward_list(list1);

	// 리스트에서 특정 원소가 바로 앞 원소보다 2 이상 크지 않으면 삭제
	list1.sort(std::less<int>());
	list1.unique([](int a, int b) {return b - a < 2; });
	prt_forward_list(list1);
}