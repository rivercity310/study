//
// Created by seungsu on 6/11/23.
//

#include "bintree.h"
#include <stdio.h>

static BinTree* root;

void bt1() {
    BinTree *b, *c, *d, *e, *f;

    d = bt_create_node(NULL, NULL, 'D');
    e = bt_create_node(NULL, NULL, 'E');
    f = bt_create_node(NULL, NULL, 'F');
    b = bt_create_node(d, e, 'B');
    c = bt_create_node(f, NULL, 'C');
    root = bt_create_node(b, c, 'A');

    puts("[ 트리 전위순회 ]");
    bt_preorder(root);
    putchar('\n');

    puts("[ 트리 중위순회 ]");
    bt_inorder(root);
    putchar('\n');

    puts("[ 트리 후위순회 ] ");
    bt_postorder(root);
    putchar('\n');

    puts("[ 트리 후위순회로 메모리 해제 ]");
    bt_terminate(root);
}