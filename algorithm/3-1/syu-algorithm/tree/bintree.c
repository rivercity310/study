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

void bt_preorder(BinTree* root) {
    if (root) {
        printf("%c ", root->data);
        bt_preorder(root->left);
        bt_preorder(root->right);
    }
}

void bt_inorder(BinTree* root) {
    if (root) {
        bt_inorder(root->left);
        printf("%c ", root->data);
        bt_inorder(root->right);
    }
}

void bt_postorder(BinTree* root) {
    if (root) {
        bt_postorder(root->left);
        bt_postorder(root->right);
        printf("%c ", root->data);
    }
}

void bt_terminate(BinTree* root) {
    if (root) {
        bt_terminate(root->left);
        bt_terminate(root->right);
        printf("%c 노드 삭제 -> ", root->data);
        free(root);
    }
}