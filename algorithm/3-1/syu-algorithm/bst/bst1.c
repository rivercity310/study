//
// Created by seungsu on 6/12/23.
//

#include "bst.h"
#include <stdio.h>
#include <stdlib.h>

void bst1() {
    Node* root = NULL;
    root = bst_insert(root, 5);
    root = bst_insert(root, 4);
    root = bst_insert(root, 3);
    root = bst_insert(root, 2);
    root = bst_insert(root, 1);
    root = bst_insert(root, 10);
    root = bst_insert(root, 9);

    puts("[ 전위 순회 결과 ]");
    bt_preorder(root);
    putchar('\n');

    puts("[ 중위 순회 결과 ]");
    bt_inorder(root);
    putchar('\n');

    puts("[ 후위 순회 결과 ]");
    bt_postorder(root);
    putchar('\n');

    while (1) {
        int x;
        printf("삭제하려는 값: ");
        scanf("%d", &x);
        
        if (x == 9999) break;

        Node* find = bst_search_iter(root, x);
        if (find == NULL) {
            printf("찾지 못함\n");
            exit(0);
        }
        else {
            bst_delete(root, find->data);
            printf("%d 찾고 삭제함\n", x);
        }

        puts("[ 전위 순회 결과 ]");
        bt_preorder(root);
        putchar('\n');

        puts("[ 중위 순회 결과 ]");
        bt_inorder(root);
        putchar('\n');

        puts("[ 후위 순회 결과 ]");
        bt_postorder(root);
        putchar('\n');
    }
    
    puts("[ 메모리 해제 ]");
    bt_terminate(root);
}