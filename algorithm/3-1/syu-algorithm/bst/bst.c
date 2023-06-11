//
// Created by seungsu on 6/12/23.
//

#include "bst.h"
#include <stdio.h>
#include <stdlib.h>

// 오른쪽 자식 트리 중에서 가장 작은 값을 가지는 노드를 찾는다
static Node* min_value_node(Node* node) {
    Node* tmp = node;
    while (tmp->left != NULL) tmp = tmp->left;
    return tmp;
}

Node* bst_create_node(element key) {
    Node* node = (Node*) malloc(sizeof(Node));
    node->left = NULL;
    node->right = NULL;
    node->data = key;

    return node;
}

Node* bst_search(Node* node, element key) {
    if (node == NULL) return NULL;

    if (node->data == key) return node;
    else if (node->data > key) return bst_search(node->left, key);
    else return bst_search(node->right, key);
}

Node* bst_search_iter(Node* node, element key) {
    while (node != NULL) {
        if (key == node->data) return node;
        else if (key < node->data) node = node->left;
        else node = node->right;
    }

    return NULL;
}

Node* bst_insert(Node* node, element key) {
    if (node == NULL) return bst_create_node(key);

    if (key < node->data) {
        node->left = bst_insert(node->left, key);
    }

    else if (key > node->data) {
        node->right = bst_insert(node->right, key);
    }

    return node;
}

Node* bst_delete(Node* root, element key) {
    if (root == NULL) return root;

    if (key < root->data) root->left = bst_delete(root->left, key);
    else if (key > root->data) root->right = bst_delete(root->right, key);
    else {
        // 삭제하려는 노드가 단말 노드인 경우 & 삭제하려는 노드가 자식을 1개만 가진 경우
        if (root->left == NULL) {
            Node* tmp = root->right;
            free(root);
            return tmp;
        }
        else if (root->right == NULL) {
            Node* tmp = root->left;
            free(root);
            return tmp;
        }

        // 삭제하려는 노드가 두 자식을 모두 가진 경우
        else {
            Node* tmp = min_value_node(root->right);
            root->data = tmp->data;
            root->right = bst_delete(root->right, tmp->data);
        }
    }

    return root;
}
