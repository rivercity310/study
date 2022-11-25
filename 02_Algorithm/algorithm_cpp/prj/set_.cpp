#include <iostream>

using namespace std;

class IntSet {
private:
	int max;
	int num;
	int* set;

public:
	IntSet(int max) {
		this->num = 0;
		this->max = max;
		this->set = new int[max];
	}

	~IntSet() { delete[] set; }

	int isMember(int x) {
		for (int i = 0; i < this->num; i++)
			if (this->set[i] == x)
				return i;

		return -1;
	}

	void Add(int n) {
		if (isMember(n) == -1 && this->num < this->max)
			this->set[this->num++] = n;
	}

	void Remove(int n) {
		int idx;
		if ((idx = isMember(n)) != -1)
			this->set[idx] = this->set[--this->num];
	}

	bool isEqual(const IntSet* s) {
		if (this->num != s->num)
			return false;

		for (int i = 0; i < this->num; i++) {
			for (int j = 0; j < s->num; j++) {
				if (this->set[i] == s->set[j])
					break;

				if (j == s->num)
					return false;
			}
		}

		return true;
	}

	int getSize() {
		return this->num;
	}

	/* 집합 s2를 this->Set에 대입 */
	void Assign(const IntSet* s2) {
		int n = this->max < s2->num ? this->max : s2->num;
		for (int i = 0; i < n; i++)
			this->set[i] = s2->set[i];

		this->num = n;
	}

	/* 집합 this->set과 s의 합집합을 rst에 대입 */
	void Union(const IntSet* s, IntSet* rst) {
		for (int i = 0; i < this->num; i++)
			rst->Add(this->set[i]);

		for (int i = 0; i < s->num; i++)
			rst->Add(s->set[i]);
	}

	void Intersection(const IntSet* s, IntSet* rst) {
		for (int i = 0; i < this->num; i++) {
			for (int j = 0; j < s->num; j++)
				if (this->set[i] == s->set[j])
					rst->Add(this->set[i]);
		}
	}

	void Difference(const IntSet* s, IntSet* rst) {
		int i, j;
		for (i = 0; i < this->num; i++) {
			for (j = 0; j < s->num; j++)
				if (this->set[i] == s->set[j])
					break;

			if (j == s->num)
				rst->Add(s->set[i]);
		}
	}

	void Print() {
		printf("{ ");
		for (int i = 0; i < this->num; i++)
			printf("%d ", this->set[i]);
		printf("}\n");
	}
};

void set_ex() {
	IntSet s1(5), s2(5);

	for (int i = 0; i < s1.getSize(); i++) {
		s1.Add(i);
		s2.Add(i);
	}

	std::cout << std::boolalpha << s1.isEqual(&s2) << "\n";

	cout << "set s1 is : ";
	s1.Print();

	cout << "set s2 is : ";
	s2.Print();

	IntSet s3(5);
	s1.Intersection(&s2, &s3);

	s3.Print();
}