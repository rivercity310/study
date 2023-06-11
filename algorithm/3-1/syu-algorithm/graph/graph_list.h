//
// Created by seungsu on 6/12/23.
//

#define SIZE 10
#define Boolean int
#define True 1
#define False 0
#define NULL (void*)0

typedef int element;

typedef struct gNode {
    int vertex;
    struct gNode* link;
} GraphNode;

typedef struct {
    int n;
    GraphNode* grp[SIZE];
} GraphType;

void grp_init(GraphType* g);
void grp_insert_vertex(GraphType* g);
void grp_insert_edge(GraphType* g, int u, int v);
void grp_error(char* message);
void grp_print(GraphType* g);
void grp_dfs(GraphType* g, Boolean visited[], int start);
void grp_dfs_with_stk(GraphType* g, int start);
void grp_bfs(GraphType* g, int start);