//
// Created by seungsu on 6/11/23.
//
#include "../tree/bintree.h"

typedef BinTree Node;

Node* bst_search(Node* n, element key);
Node* bst_search_iter(Node* n, element key);
Node* bst_insert(Node* node, element key);
Node* bst_delete(Node* root, element key);
Node* bst_create_node(element key);