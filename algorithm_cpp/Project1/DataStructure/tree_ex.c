#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct tree {
	struct tree* left;
	char* data;
	struct tree* right;
} TreeNode;

static int inorder_count;

static int eval(TreeNode* root) {
	if (root == NULL) 
		return 0;

	if (root->left == NULL && root->right == NULL)
		return atoi(root->data);
	
	else {
		int op1 = eval(root->left);
		int op2 = eval(root->right);

		printf("op1: %d, op2: %d\n", op1, op2);

		if (!(op1 == 0 || op2 == 0)) {
			if (root->data == "+")
				return op1 + op2;
			else if (root->data == "-")
				return op1 - op2;
			else if (root->data == "/")
				return op1 / op2;
			else if (root->data == "*")
				return op1 * op2;
		}
	}
}

static void inorder(TreeNode* node) {
	inorder_count++;
	if (node) {
		printf("node->left(%p)로 이동\n", node->left);
		inorder(node->left);
		printf("root의 주소 = %p, 값 = %s\n", node, node->data);
		printf("node->right(%p)로 이동\n", node->right);
		inorder(node->right);
		printf("\n");
	}
}

/*
[ ((10 + 20) * (40 - 30)) / ((60 + 70) * (80 - 40)) = 5500 ]
				

			                +
				  *					   *
			  +        -		  +			-
		  10	20	40   30    60   70   80   40
	      
*/


void tree_Ex() {
	// 좌측 서브트리
	TreeNode n8 = { NULL, "10", NULL};
	TreeNode n9 = { NULL, "20", NULL};
	TreeNode n10 = { NULL, "40", NULL};
	TreeNode n11 = { NULL, "30", NULL};
	TreeNode n4 = { &n8, "+", &n9};
	TreeNode n5 = { &n10, "-", &n11};
	TreeNode n2 = { &n4, "*", &n5};
	
	// 우측 서브트리
	TreeNode n12 = { NULL, "60", NULL };
	TreeNode n13 = { NULL, "70", NULL};
	TreeNode n14 = { NULL, "80", NULL};
	TreeNode n15 = { NULL, "40", NULL};
	TreeNode n6 = { &n12, "+", &n13 };
	TreeNode n7 = { &n14, "-", &n15};
	TreeNode n3 = { &n6, "*", &n7 };

	TreeNode root = { &n2, "+", &n3 };
	TreeNode* p = &root;

	printf("root 노드의 주소: %p\n", &root);
	printf("root->left 노드의 주소: %p\n", root.left);
	printf("root->right 노드의 주소: %p\n", root.right);
	inorder(p);
	putchar('\n');
	printf("inorder 함수 호출 횟수: %d\n", inorder_count);
	printf("Ans: %d\n", eval(p));

	printf("%d", (unsigned int)sizeof(TreeNode));
}