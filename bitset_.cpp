#include <iostream>

typedef unsigned long BitSet;

#define BitSetNull (BitSet)0
#define BitSetBits 32
#define SetOf(no) ((BitSet)1 << no)

using namespace std;

int IsMember(BitSet s, int n) {
	return s & SetOf(n);
}

void Add(BitSet* s, int n) {
	*s |= SetOf(n);
}

void Remove(BitSet* s, int n) {
	*s &= ~SetOf(n);
}

int Size(BitSet s) {
	int n = 0;
	for (; s != BitSetNull; n++)
		s &= s - 1;

	return n;
}

void Print(BitSet s) {
	printf("{ ");
	for (int i = 0; i < BitSetBits; i++)
		if (IsMember(s, i))
			printf("%d ", i);
	printf("}\n");
}

void bitset_test() {
	BitSet s1 = BitSetNull;
	BitSet s2 = BitSetNull;

	for (int i = 1; i <= 5; i++) 
		Add(&s1, i);

	for (int i = 3; i <= 8; i++)
		Add(&s2, i);

	cout << "s1 : ";
	Print(s1);

	cout << "s2 : ";
	Print(s2);

	Remove(&s1, 5);
	cout << "s1 : ";
	Print(s1);

	cout << "s1 - s2 : ";
	Print(s1 & ~s2);

	cout << "intersection(s1, s2) : ";
	Print(s1 & s2);

	cout << "Union(s1, s2) : ";
	Print(s1 | s2);

	cout << "symmetric difference(s1, s2) : ";
	Print((s1 | s2) - (s1 & s2));
}