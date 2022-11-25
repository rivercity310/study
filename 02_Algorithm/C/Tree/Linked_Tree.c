#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define max(a,b)  (((a) > (b)) ? (a) : (b))

typedef struct tn {
	struct tn* left;
	int data;
	struct tn* right;
} TreeNode;

extern void traversal(TreeNode* root, int mode);

static TreeNode* initNode(int data, TreeNode* leftChild, TreeNode* rightChild) {
	TreeNode* node = (TreeNode*)malloc(sizeof(TreeNode));

	node->data = data;
	node->left = leftChild;
	node->right = rightChild;

	return node;
}

extern TreeNode* makeTree() {
	TreeNode* n7 = initNode(7, NULL, NULL);
	TreeNode* n6 = initNode(6, NULL, NULL);
	TreeNode* n5 = initNode(5, NULL, NULL);
	TreeNode* n4 = initNode(4, NULL, NULL);
	TreeNode* n3 = initNode(3, n6, n7);
	TreeNode* n2 = initNode(2, n4, n5);
	TreeNode* n1 = initNode(1, n2, n3);

	return n1;
}

static int node_count(TreeNode* root) {
	int count = 0;
	if (root) 
		count = 1 + node_count(root->left) + node_count(root->right);
	
	return count;
}

static int leaf_node_count(TreeNode* node) {
	int count = 0;

	if (node) {
		if (node->left == NULL && node->right == NULL)
			return 1;
		else {
			count = leaf_node_count(node->left) + leaf_node_count(node->right);
		}
	}

	return count;
}

static int get_height(TreeNode* node) {
	int height = 0;

	if (node) 
		height = 1 + max(get_height(node->left), get_height(node->right));

	return height;
}

void Linked_Tree() {
	TreeNode* root = makeTree();

	printf("%d\n", node_count(root));
	printf("%d\n", leaf_node_count(root));
	printf("%d\n", get_height(root));

	for (int i = 1; i <= 4; i++)
		traversal(root, i);

	traversal(root, 0);
}