#include <stdio.h>
#include <stdlib.h>

typedef struct node {
	struct node* left;
	int data;
	struct node* right;
} Node;

/* 새 노드를 before 노드 오른쪽에 삽입 */
void dinsert(Node* before, int data) {
	Node* p = (Node*)malloc(sizeof(Node));
	p->data = data;

	p->left = before;
	p->right = before->right;

	before->right->left = p;
	before->right = p;
}

void ddelete(Node* head, Node* removed) {
	if (removed == head) return;

	removed->left->right = removed->right;
	removed->right->left = removed->left;
	free(removed);
}

void double_linkedlist_test() {
	Node* head = (Node*)malloc(sizeof(Node));
		
	int n;
	scanf_s("%d", &n);

	for (int i = 0; i < n; i++) {
		int x;
		scanf_s("%d", &x);
		if (i == 0) {
			Node* tmp = (Node*)malloc(sizeof(Node));
			tmp->data = x;
			tmp->left = head;
			tmp->right = NULL;

			head->left = NULL;
			head->right = tmp;
		}
		else {
			Node* tmp = head->right;
			while (tmp->right) tmp = tmp->right;
			dinsert(tmp, x);
		}
	}

	for (Node* p = head->right; p != NULL; p = p->right)
		printf("%d ", p->data);
}