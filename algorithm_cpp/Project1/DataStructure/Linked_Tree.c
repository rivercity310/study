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

static TreeNode* make_tree() {
	TreeNode* root = NULL;

	for (int i = 0; i < 3; i++) {
		TreeNode tr = { i + 3, NULL, NULL };

		if (!root) root = &tr;
		else if (!root->left) root->left = &tr;
		else if (!root->right) root->right = &tr;
	}

	return root;
}

void Linked_Tree() {
	TreeNode* root = make_tree();
	printf("root->data: %d\n", root->data);
	
	for (int i = 1; i <= 3; i++) {
		traversal(root, i);
		puts("\n");
	}
}