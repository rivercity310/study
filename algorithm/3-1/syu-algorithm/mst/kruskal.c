//
// Created by seungsu on 6/12/23.
//

#include <stdio.h>
#include <stdlib.h>
#include "disjoint_set/dset.h"

typedef struct {
    int src;
    int dst;
    int weight;
} Edge;

static int compare(const void* a, const void* b) {
    Edge* e1 = (Edge*) a;
    Edge* e2 = (Edge*) b;
    return e1->weight - e2->weight;
}

void kruskal() {
    Edge grp[] = {
            { 1, 7, 12 }, { 1, 4, 23 }, { 1, 2, 26 },
            { 2, 3, 36 }, { 2, 4, 21 }, { 2, 5, 45 }, { 3, 5, 29 },
            { 3, 6, 37 }, { 3, 7, 55 }, { 4, 7, 20 }, { 5, 6, 30 }
    };

    qsort(grp, SIZE, sizeof(Edge), compare);

    for (int i = 0; i < SIZE; i++) {
        printf("%d %d %d\n", grp[i].src, grp[i].dst, grp[i].weight);
    }

    DisjointSet* dset = (DisjointSet*) malloc(sizeof(DisjointSet));
    dset_init(dset);
    int sum = 0;

    for (int i = 0; i < SIZE; i++) {
        if (is_cycle(dset, grp[i].src, grp[i].dst) == False) {
            sum += grp[i].weight;
            dset_union_parent(dset, grp[i].src, grp[i].dst);
            printf("%d <-> %d\n", grp[i].src, grp[i].dst);
        }
    }

    printf("sum = %d\n", sum);
}