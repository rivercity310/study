//
// Created by seungsu on 6/12/23.
//

#include <stdio.h>
#include <stdlib.h>
#include "graph_list.h"

void graph2() {
    GraphType* g = (GraphType*) malloc(sizeof(GraphType));
    grp_init(g);

    for (int i = 0; i < 4; i++)
        grp_insert_vertex(g);

    grp_insert_edge(g, 0, 1); grp_insert_edge(g, 1, 0);
    grp_insert_edge(g, 0, 2); grp_insert_edge(g, 2, 0);
    grp_insert_edge(g, 0, 3); grp_insert_edge(g, 3, 0);
    grp_insert_edge(g, 1, 2); grp_insert_edge(g, 2, 1);
    grp_insert_edge(g, 2, 3); grp_insert_edge(g, 3, 2);

    grp_print(g);
    grp_dfs_with_stk(g, 0);
    putchar('\n');

    Boolean visited[g->n];
    for (int i = 0; i < g->n; i++) visited[i] = False;

    grp_dfs(g, visited, 0);
    putchar('\n');

    grp_bfs(g, 0);
    putchar('\n');

    free(g);
}