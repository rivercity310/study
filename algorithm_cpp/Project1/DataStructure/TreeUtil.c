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
		puts("[ Preorder Start ]");
		preorder(root);
		putchar('\n');
		break;
	}

	case 2:
	{
		puts("[ Inorder Start ]");
		inorder(root);
		putchar('\n');
		break;
	}
	case 3:
	{
		puts("[ Postorder Start ]");
		postorder(root);
		putchar('\n');
		break;
	}
	default:
		printf("일치하지 않는 번호\n");
		return;
	}
}

void lt_terminate(TreeNode* root) {
	if (root) {
		lt_terminate(root->left);
		lt_terminate(root->right);
		printf("%d 데이터 해제\n", root->data);
		free(root);
	}
}