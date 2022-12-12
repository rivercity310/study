#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct tr {
	int data;
	struct tr* left;
	struct tr* right;
} TreeNode;

static TreeNode* search(TreeNode* node, int key) {
	if (!node) return NULL;
	if (node->data == key) return node;
	else if (node->data > key) return search(node->left, key);
	return search(node->right, key);
}

static TreeNode* search2(TreeNode* node, int key) {
	while (node) {
		if (node->data == key) return node;
		else if (node->data > key) node = node->left;
		else node = node->right;
	}

	return NULL;
}

static TreeNode* new_node(int data) {
	TreeNode* tmp = (TreeNode*)malloc(sizeof(TreeNode));
	tmp->data = data;
	tmp->left = tmp->right = NULL;
	return tmp;
}

static TreeNode* insert_node(TreeNode* node, int key) {
	if (node == NULL) return new_node(key);
	if (key < node->data) node->left = insert_node(node->left, key);
	else if (key > node->data) node->right = insert_node(node->right, key);
	return node;
}

static TreeNode* min_value_node(TreeNode* node) {
    TreeNode* curr = node;
    while (curr->left) curr = curr->left;
    return curr;
}

static TreeNode* delete_node(TreeNode* root, int key) {
	if (root == NULL) return root;
	if (key < root->data) root->left = delete_node(root->left, key);
	else if (key > root->data) root->right = delete_node(root->right, key);
	else {
		if (root->left == NULL) {	// (1) or (2)
			TreeNode* temp = root->right;
			free(root);
			return temp;
		}
		else if (root->right == NULL) {	// (1) or (2)
			TreeNode* temp = root->left;
			free(root);
			return temp;
		}

		TreeNode* temp = min_value_node(root->right); 		// (3)
		root->data = temp->data;
		root->right = delete_node(root->right, temp->data);
	}
	return root;
}

static void inorder(TreeNode* root) {
	if (root) {
		inorder(root->left);
		printf("%d ", root->data);
		inorder(root->right);
	}
}

void binary_search_tree() {
	TreeNode* root = NULL;
	TreeNode* tmp = NULL;

	int n;
	scanf("%d", &n);

	srand(time(NULL));

	for (int i = 0; i < n; i++)
		root = insert_node(root, rand() % 100);

	puts("[ BST Inorder ]");
	inorder(root);
	puts("\n");

	for (int i = 0; i < 3; i++) {
		printf("del: ");
		scanf("%d", &n);
		delete_node(root, n);
	
		puts("[ BST Inorder ]");
		inorder(root);
		puts("\n");
	}
}