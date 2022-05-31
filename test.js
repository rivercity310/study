const a = [1, 2, 3, 4, 5, 3];
for (let i = 0; i < a.length; i++) {
    process.stdout.write(a[i] + " ");
}
console.log();

function bubble_sort(a) {
    for (let i = 0; i < a.length; i++) {
        for (let j = a.length - 1; j > i; j--) {
            if (a[j - 1] > a[j]) {
                let temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }
}

const bubble_sort2 = a => {
    let k = 0;
    let n = a.length;

    while (k < n - 1) {
        let last = n - 1;
        for (let j = n - 1; j > k; j--) {
            if (a[j - 1] > a[j]) {
                let temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;

                last = j;
            }
        }

        k = last;
    }
}

bubble_sort2(a);
for (let i = 0; i < a.length; i++) {
    process.stdout.write(a[i] + " ");
}