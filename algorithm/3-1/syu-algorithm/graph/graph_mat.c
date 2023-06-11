//
// Created by seungsu on 6/12/23.
//

#include "graph_mat.h"
#include "../stack/stack.h"
#include "../queue/queue.h"
#include <stdio.h>
#include <stdlib.h>

void mat_init(GraphMat* g) {
    g->n = 0;

    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            g->grp[i][j] = 0;
}

void mat_insert_vertex(GraphMat* g, element v) {
    if (g->n + 1 > SIZE) {
        mat_error("overflow");
    }

    g->n++;
}

void mat_insert_edge(GraphMat* g, element start, element end) {
    if (start >= g->n || end >= g->n) {
        mat_error("vertex error");
    }

    g->grp[start][end] = 1;
    g->grp[end][start] = 1;
}

void mat_print(GraphMat* g) {
    for (int i = 0; i < g->n; i++) {
        for (int j = 0; j < g->n; j++) {
            printf("%2d", g->grp[i][j]);
        }
        putchar('\n');
    }
}

void mat_error(char* message) {
    fprintf(stderr, "%s\n", message);
    exit(0);
}

void mat_bfs(GraphMat* g, element start) {
    Queue* q = (Queue*) malloc(sizeof(Queue));
    Boolean visited[g->n];

    for (int i = 0; i < g->n; i++)
        visited[i] = False;

    queue_init(q);

    visited[start] = True;
    enqueue(q, start);

    while (!queue_is_empty(q)) {
        int now = dequeue(q);
        printf("%d -> ", now);

        for (int i = 0; i < g->n; i++) {
            if (g->grp[now][i] == 1) {
                if (visited[i] == False) {
                    enqueue(q, i);
                    visited[i] = True;
                }
            }
        }
    }
}

void mat_dfs_with_stk(GraphMat* g, element start) {
    Stack* stk = (Stack*) malloc(sizeof(Stack));
    stack_init(stk);

    Boolean visited[g->n];

    for (int i = 0; i < g->n; i++)
        visited[i] = False;

    push(stk, start);
    visited[start] = True;

    while (!stack_is_empty(stk)) {
        int now = pop(stk);
        printf("%d -> ", now);

        for (int i = 0; i < g->n; i++) {
            if (g->grp[now][i] == 1 && visited[i] == False) {
                visited[i] = True;
                push(stk, i);
            }
        }
    }
}

void mat_dfs(GraphMat* g, Boolean visited[], element node) {
    visited[node] = True;
    printf("%d -> ", node);

    for (int i = 0; i < g->n; i++) {
        if (visited[i] == False && g->grp[node][i] == 1) {
            mat_dfs(g, visited, i);
        }
    }
}