package org.example.counter

class RaceConditionDemo
fun main() {
    val counter = Counter()

    val t1 = Thread(counter, "Thread-1")
    val t2 = Thread(counter, "Thread-2")
    val t3 = Thread(counter, "Thread-3")

    t1.start()
    t2.start()
    t3.start()
}
