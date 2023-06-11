//
// Created by seungsu on 6/12/23.
//

#include <stdio.h>
#include <stdlib.h>
#include "graph_list.h"
#include "../stack/stack.h"
#include "../queue/queue.h"

void grp_init(GraphType* g) {
    g->n = 0;

    for (int i = 0; i < SIZE; i++)
        g->grp[i] = NULL;
}

void grp_insert_vertex(GraphType* g) {
    if (g->n + 1 > SIZE) {
        grp_error("overflow");
    }

    g->n++;
}

void grp_insert_edge(GraphType* g, int u, int v) {
    if (u >= g->n || v >= g->n) {
        grp_error("vertex index error");
    }

    GraphNode* node = (GraphNode*) malloc(sizeof(GraphNode));
    node->vertex = v;
    node->link = g->grp[u];
    g->grp[u] = node;
}

void grp_error(char* message) {
    fprintf(stderr, "%s\n", message);
    exit(0);
}

void grp_print(GraphType* g) {
    for (int i = 0; i < g->n; i++) {
        GraphNode* tmp = g->grp[i];
        printf("정점 %d의 인접 리스트", i);

        while (tmp != NULL) {
            printf(" -> %d", tmp->vertex);
            tmp = tmp->link;
        }

        putchar('\n');
    }
}

void grp_dfs(GraphType* g, Boolean visited[], int start) {
    visited[start] = True;
    printf("%d -> ", start);

     for (GraphNode* node = g->grp[start]; node != NULL; node = node->link) {
        if (visited[node->vertex] == False) {
            grp_dfs(g, visited, node->vertex);
        }
    }
}

void grp_dfs_with_stk(GraphType* g, int start) {
    Stack* stk = (Stack*) malloc(sizeof(Stack));
    stack_init(stk);

    Boolean visited[g->n];
    for (int i = 0; i < g->n; i++) visited[i] = False;

    visited[start] = True;
    push(stk, start);
    
    while (!stack_is_empty(stk)) {
        int now = pop(stk);
        printf("%d -> ", now);

        GraphNode* tmp = g->grp[now];
        while (tmp != NULL) {
            if (visited[tmp->vertex] == False) {
                visited[tmp->vertex] = True;
                push(stk, tmp->vertex);
            }

            tmp = tmp->link;
        }
    }
}

void grp_bfs(GraphType* g, int start) {
    Queue* q = (Queue*) malloc(sizeof(Queue));
    queue_init(q);
    Boolean visited[g->n];
    for (int i = 0; i < g->n; i++) visited[i] = False;

    enqueue(q, start);
    visited[start] = True;

    while (!queue_is_empty(q)) {
        int now = dequeue(q);
        printf("%d -> ", now);

        for (GraphNode* next = g->grp[now]; next != NULL; next = next->link) {
            if (visited[next->vertex] == False) {
                enqueue(q, next->vertex);
                visited[next->vertex] = True;
            }
        }
    }
}
