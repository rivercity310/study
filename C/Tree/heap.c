#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 51

typedef struct {
	int heap[MAX];
	int size;
} HeapType;

static HeapType* alloc_node() {
	return (HeapType*)malloc(sizeof(HeapType));
}

static void init_heap(HeapType* h) {
	h->size = 0;
}

static void insert_heap(HeapType* h, int data) {
	int i = ++(h->size);

	while ((i != 1) && data > h->heap[i / 2]) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}

	h->heap[i] = data;
}

static int delete_heap(HeapType* h) {
	int root = h->heap[1];
	int last = h->heap[(h->size)--];

	int parent = 1;
	int child = 2;

	while (child <= h->size) {
		if ((child < h->size) && (h->heap[child] < h->heap[child + 1])) {
			child++;
		}

		if (last >= h->heap[child])
			break;

		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}

	h->heap[parent] = last;
	return root;
}

static int peek_heap(HeapType* h) {
	return h->heap[1];
}

void array_heap() {
	HeapType* heap = alloc_node();
	init_heap(heap);

	for (int i = 1; i <= 10; i++) 
		insert_heap(heap, i);
	
	for (int i = 0; i < 10; i++) {
		int hp = delete_heap(heap);
		printf("%d ", hp);
	}

	free(heap);
}

void sorting_with_heap() {
	int arr[MAX] = {0,};
	HeapType* heap = alloc_node();
	init_heap(heap);

	srand(time(NULL));
	
	for (int i = 0; i < MAX - 1; i++) {
		int x = rand() % 100;
		insert_heap(heap, x);

		printf("%-5d", x);
	}

	puts("\n\n");
	printf("Heap Peek: %d", peek_heap(heap));
	puts("\n\n");

	for (int i = MAX - 1; i >= 0; i--) 
		arr[i] = delete_heap(heap);

	for (int i = 0; i < MAX - 1; i++)
		printf("%-5d", arr[i]);
			
	free(heap);

	puts("\n\n");
}