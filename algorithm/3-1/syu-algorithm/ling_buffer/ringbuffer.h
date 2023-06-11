//
// Created by seungsu on 6/11/23.
//

// [ 원형큐 ]
// 아래 두 상황을 구분하기 위해 front 자리를 비워둠
// 공백상태 -> front == rear
// 포화상태 -> front % SIZE == (rear + 1) % SIZE

#define Boolean int
#define True 1
#define False 0
#define SIZE 10

typedef int element;

typedef struct {
    int front;
    int rear;
    element* data;
} RingBuffer;

void rb_init(RingBuffer* rb);
Boolean rb_is_empty(RingBuffer* rb);
Boolean rb_is_full(RingBuffer* rb);
int rb_size(RingBuffer* rb);
void rb_enqueue(RingBuffer* rb, int x);
element rb_dequeue(RingBuffer* rb);
element rb_peek(RingBuffer* rb);
void rb_error(char* message);
void rb_print_queue(RingBuffer* rb);
void rb_terminate(RingBuffer* rb);