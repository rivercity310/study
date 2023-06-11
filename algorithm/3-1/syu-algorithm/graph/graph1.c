//
// Created by seungsu on 6/12/23.
//

// 인접행렬 방식 그래프

#include <stdio.h>
#include <stdlib.h>
#include "graph_mat.h"

void graph1() {
    GraphMat* g = (GraphMat*) malloc(sizeof(GraphMat));
    mat_init(g);

    for (int i = 0; i < 4; i++)
        mat_insert_vertex(g, i);

    mat_insert_edge(g, 0, 1);
    mat_insert_edge(g, 0, 2);
    mat_insert_edge(g, 0, 3);
    mat_insert_edge(g, 1, 2);
    mat_insert_edge(g, 2, 3);

    mat_print(g);
    mat_bfs(g, 0);
    putchar('\n');

    mat_dfs_with_stk(g, 0);
    putchar('\n');

    Boolean visited[g->n];

    for (int i = 0; i < g->n; i++) visited[i] = False;
    mat_dfs(g, &visited, 0);

    free(g);
}