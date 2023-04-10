#include <iostream>
#include <vector>

void chap10_iterator() {
	std::vector<int> v;
	v.push_back(1);
	v.push_back(2);
	v.push_back(3);
	v.push_back(4);

	std::vector<int>::iterator it;
	for (it = v.begin(); it != v.end(); it++) {
		int n = *it;
		n = n * 2;
		*it = n;
	}

	for (it = v.begin(); it != v.end(); it++)
		std::cout << *it << " ";
	std::cout << std::endl;
}