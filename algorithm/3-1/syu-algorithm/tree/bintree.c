//
// Created by seungsu on 6/11/23.
//

#include "bintree.h"
#include <stdio.h>
#include <stdlib.h>

// 특정 노드를 초기화하는 함수
BinTree* bt_create_node(BinTree* leftChild, BinTree* rightChild, element data) {
    BinTree* node = (BinTree*) malloc(sizeof(BinTree));
    node->data = data;
    node->left = leftChild;
    node->right = rightChild;

    return node;
}

BinTree* create_sample_tree() {
    BinTree *root, *b, *c, *d, *e, *f;

    d = bt_create_node(NULL, NULL, 'D');
    e = bt_create_node(NULL, NULL, 'E');
    f = bt_create_node(NULL, NULL, 'F');
    b = bt_create_node(d, e, 'B');
    c = bt_create_node(f, NULL, 'C');
    root = bt_create_node(b, c, 'A');

    return root;
}

void bt_preorder(BinTree* root) {
    if (root) {
        printf("%d ", root->data);
        bt_preorder(root->left);
        bt_preorder(root->right);
    }
}

void bt_inorder(BinTree* root) {
    if (root) {
        bt_inorder(root->left);
        printf("%d ", root->data);
        bt_inorder(root->right);
    }
}

void bt_postorder(BinTree* root) {
    if (root) {
        bt_postorder(root->left);
        bt_postorder(root->right);
        printf("%d ", root->data);
    }
}

void bt_terminate(BinTree* root) {
    if (root) {
        bt_terminate(root->left);
        bt_terminate(root->right);
        printf("%d 노드 삭제 -> ", root->data);
        free(root);
    }
}