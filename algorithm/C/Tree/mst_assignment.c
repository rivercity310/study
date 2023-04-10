//
// Created by seungsu on 11/28/22.
//

#include <stdio.h>
#include <stdlib.h>

#define MAX 10

static int parent[MAX];

typedef struct node {
    int src;
    int dst;
    int weight;
} Node;

static char hashing(int n) {
    return n - 1 + 'A';
}

static void print_parents() {
    for (int i = 1; i <= MAX; i++)
        printf("%-3c", hashing(parent[i]));
    putchar('\n');
}

static int compare(const void* n1, const void* n2) {
    Node* t1 = (Node*)n1;
    Node* t2 = (Node*)n2;

    return t1->weight - t2->weight;
}

static int find_parent(int x) {
    if (x != parent[x])
        return parent[x] = find_parent(parent[x]);
    return x;
}

static void union_parent(int a, int b) {
    int ra = find_parent(a);
    int rb = find_parent(b);

    if (ra < rb) parent[rb] = ra;
    else parent[ra] = rb;
}

static void kruskal(Node* grp) {
    for (int i = 0; i <= MAX; i++)
        parent[i] = i;

    for (int i = 0; i < MAX; i++) {
        Node tmp = grp[i];

        if (find_parent(tmp.src) != find_parent(tmp.dst)) {
            union_parent(tmp.src, tmp.dst);
            printf("(%c, %c, %d)를 MST에 추가\t", hashing(tmp.src), hashing(tmp.dst), tmp.weight);
            print_parents();
        }
    }
}

void mst_assignment() {
    Node grp[] = {
            { 1, 2, 10 }, { 1, 6, 4 }, { 1, 7, 7 },
            { 2, 3, 12 }, { 3, 4, 15 }, { 3, 6, 8 },
            { 4, 6, 9 }, { 4, 5, 3 }, { 5, 7, 11 },
            { 6, 7, 5 }
    };

    qsort((Node*)grp, MAX, sizeof(Node), compare);

    puts("[ 배열 정렬 완료 ]");
    for (int i = 0; i < MAX; i++) {
        Node* tmp = grp + i;
        printf("(%c, %c, %d)", hashing(tmp->src), hashing(tmp->dst), tmp->weight);
        if (i != MAX - 1) printf(" -> ");
    }
    puts("\n");

    kruskal(grp);
}