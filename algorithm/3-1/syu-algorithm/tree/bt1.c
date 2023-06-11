//
// Created by seungsu on 6/11/23.
//

#include "bintree.h"
#include <stdio.h>

void bt1() {
    BinTree* root = create_sample_tree();

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