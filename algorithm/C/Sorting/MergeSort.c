//
// Created by seungsu on 11/28/22.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 20

static void print_arr(int* arr, int n) {
    for (int i = 0; i < n; i++)
        printf("%-5d", arr[i]);
    putchar('\n');
}

static void merging(int* arr, int start, int mid, int end) {
    int* tmp = (int*)malloc(sizeof(int) * MAX);

    int i = start;
    int j = mid + 1;
    int t = start;

    while (i <= mid && j <= end) {
        if (arr[i] <= arr[j]) tmp[t++] = arr[i++];
        else tmp[t++] = arr[j++];
    }

    while (i <= mid) tmp[t++] = arr[i++];

    while (j <= end) tmp[t++] = arr[j++];

    for (int a = start; a <= end; a++)
        arr[a] = tmp[a];
}

static void merge_sort(int* arr, int start, int end) {
    if (start < end) {
        int mid = (start + end) / 2;
        merge_sort(arr, start, mid);
        merge_sort(arr, mid + 1, end);
        merging(arr, start, mid, end);
    }
}

void merge_sort_test() {
    srand(time(NULL));
    int* arr = (int*)malloc(sizeof(int) * MAX);

    for (int i = 0; i < MAX; i++)
        arr[i] = rand() % 100;

    puts("[ 생성된 배열 ]");
    print_arr(arr, MAX - 1);

    putchar('\n');

    merge_sort(arr, 0, MAX - 1);

    puts("[ 정렬된 배열 ]");
    print_arr(arr, MAX - 1);

    free(arr);
}