
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 21

typedef struct {
	int data;
	char str[MAX];
} Test;


int main() {
	Test* a = (Test*)malloc(sizeof(Test));

	if (a) {
		a->data = 100;
		strcpy_s(a->str, "just testing");

		printf("备炼眉 函荐狼 林家 : %p\n", &a);
		printf("Data 林家 : %p, 蔼 : %d\n", &a->data, a->data);
		printf("String 林家 : %p, 蔼 : %s\n", a->str, a->str);

		free(a);
	}
}
