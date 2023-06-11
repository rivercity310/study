//
// Created by seungsu on 6/11/23.
//

// 이진 트리 헤더파일

#define Boolean int
#define True 1
#define False 0

typedef char element;

typedef struct bin_tree {
    element data;
    struct bin_tree* left;
    struct bin_tree* right;
} BinTree;

BinTree* bt_create_node(BinTree* leftChild, BinTree* rightChild, element data);
void bt_terminate(BinTree* root);
void bt_preorder(BinTree* root);
void bt_inorder(BinTree* root);
void bt_postorder(BinTree* root);