package org.example.counter

class Counter: Runnable {
    // thread -> 상태를 유지하도록 설계하면 안됨 syncronized로 동시접근 문제 해결
    private var count: Int = 0

    override fun run() {
        synchronized(this) {
            this.increment()
            println("value for thread after inc ${Thread.currentThread().name} : $count")

            this.decrement()
            println("value for thread after dec ${Thread.currentThread().name} : $count")
        }
    }

    private fun increment() = this.count++

    private fun decrement() = this.count--
}