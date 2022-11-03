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

/*
[ 삽입 연산 ] : upheap 연산
현재 요소 개수가 size인 힙 h에 data 삽입 
*/
static void insert_heap(HeapType* h, int data) {
	int i = ++(h->size);

	// 트리를 거슬러 올라가면서 부모 노드와 비교
	while ((i != 1) && data > h->heap[i / 2]) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}

	h->heap[i] = data;
}

/*
[ 삭제 연산 ] : downheap 연산
- 1. 루트 노드 삭제
- 2. 마지막 노드를 루트 노드로 이동
- 3. 루트에서부터 단말까지 경로에 있는 노드들을 힙 성질을 만족할 때까지 교환
*/
static int delete_heap(HeapType* h) {
	int root = h->heap[1];				// 루트 노드
	int last = h->heap[(h->size)--];	// 마지막 노드

	int parent = 1;
	int child = 2;

	while (child <= h->size) {
		// 현재 노드의 자식노드 중 더 큰 자식노드를 찾는다
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

/* 힙정렬: O(nlogn) 정렬 알고리즘 */
void sorting_with_heap() {
	int arr[MAX] = {0,};
	HeapType* heap = alloc_node();
	init_heap(heap);

	srand(time(NULL));
	
	puts("[ 힙에 추가합니다 ]");
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
	

	puts("[ 정렬 완료 ]");
	puts("배열을 출력합니다...");

	for (int i = 0; i < MAX - 1; i++)
		printf("%-5d", arr[i]);
			
	free(heap);

	puts("\n\n");
}