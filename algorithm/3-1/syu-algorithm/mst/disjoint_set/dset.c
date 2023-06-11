//
// Created by seungsu on 6/12/23.
//

#include "dset.h"

void dset_init(DisjointSet* dset) {
    for (int i = 0; i < SIZE; i++)
        dset->parent[i] = i;
}

int dset_find_parent(DisjointSet* dset, int x) {
    if (dset->parent[x] != x) {
        return dset->parent[x] = dset_find_parent(dset, dset->parent[x]);
    }

    return x;
}

void dset_union_parent(DisjointSet* dset, int a, int b) {
    int ra = dset_find_parent(dset, a);
    int rb = dset_find_parent(dset, b);

    if (ra < rb) dset->parent[rb] = ra;
    else dset->parent[ra] = rb;
}

Boolean is_cycle(DisjointSet* dset, int a, int b) {
    int ra = dset_find_parent(dset, a);
    int rb = dset_find_parent(dset, b);

    if (ra == rb) return True;
    return False;
}