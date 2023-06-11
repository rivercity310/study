//
// Created by seungsu on 6/12/23.
//

#define SIZE 10
#define Boolean int
#define True 1
#define False 0

typedef int element;

typedef struct {
    int n;
    element grp[SIZE][SIZE];
} GraphMat;

void mat_init(GraphMat* g);
void mat_insert_vertex(GraphMat* g, element v);
void mat_insert_edge(GraphMat* g, element start, element end);
void mat_print(GraphMat* g);
void mat_error(char* message);
void mat_bfs(GraphMat* g, element start);
void mat_dfs_with_stk(GraphMat* g, element start);
void mat_dfs(GraphMat* g, Boolean visited[], element start);