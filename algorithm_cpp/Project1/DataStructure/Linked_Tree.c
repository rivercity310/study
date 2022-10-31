#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

/* 메모리 할당 정책에 따라 24바이트 할당 (8, 8, 8) */
typedef struct tn {
	struct tn* left;
	int data;
	struct tn* right;
} TreeNode;

extern void traversal(TreeNode* root, int mode);
extern void lt_terminate(TreeNode* root);

void Linked_Tree() {
	TreeNode* root = NULL;

	int n;
	scanf_s("%d", &n);

	for (int i = 1; i <= n; i++) {
		TreeNode* tmp = (TreeNode*)malloc(sizeof(TreeNode));
		tmp->data = i;
		tmp->left = tmp->right = NULL;

		if (!root) {
			root = tmp;
			continue;
		}

		TreeNode* p = root;

		if (!p->left) p->left = tmp;
		else if (!p->right) p->right = tmp;
		else {
			while (p->left) p = p->left;
			p->left = tmp;

		}
	}

	for (int i = 1; i <= 3; i++)
		traversal(root, i);

	lt_terminate(root);
}