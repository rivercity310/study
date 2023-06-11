//
// Created by seungsu on 6/12/23.
//

#define SIZE 11
#define Boolean int
#define True 1
#define False 0

typedef struct {
    int parent[SIZE];
} DisjointSet;

void dset_init(DisjointSet* dset);
int dset_find_parent(DisjointSet* dset, int x);
void dset_union_parent(DisjointSet* dset, int a, int b);
Boolean is_cycle(DisjointSet* dset, int a, int b);