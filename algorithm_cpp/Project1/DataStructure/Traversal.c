#include <stdio.h>

typedef struct tn {
	struct tn* left;
	int data;
	struct tn* right;
} TreeNode;

void preorder(TreeNode* root) {
	if (root) {
		printf("%d ", root->data);
		preorder(root->left);
		preorder(root->right);
	}
}

void inorder(TreeNode* root) {
	if (root) {
		inorder(root->left);
		printf("%d ", root->data);
		inorder(root->right);
	}
}

void postorder(TreeNode* root) {
	if (root) {
		postorder(root->left);
		postorder(root->right);
		printf("%d ", root->data);
	}
}

void traversal(TreeNode* root, int mode) {
	switch (mode) {
	case 1:
	{
		preorder(root);
		break;
	}

	case 2:
	{
		inorder(root);
		break;
	}
	case 3:
	{
		postorder(root);
		break;
	}
	default:
		printf("일치하지 않는 번호\n");
		return;
	}
}