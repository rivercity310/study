//
// Created by seungsu on 6/11/23.
//

#define Boolean int
#define True 1
#define False 0
#define SIZE 10

typedef int element;

typedef struct {
    int front;
    int rear;
    element* data;
} Queue;

void queue_init(Queue* q);
void queue_print(Queue* q);
Boolean queue_is_full(Queue* q);
Boolean queue_is_empty(Queue* q);
void enqueue(Queue* q, int x);
element dequeue(Queue* q);
element queue_peek(Queue* q);
int queue_size(Queue* q);
void queue_error(char* message);
void terminate_queue(Queue* q);