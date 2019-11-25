package main.kotlin

fun main() {
    val processes = mutableListOf(Process("p1", 3, 1), Process("p3", 5, 1))
    while (processes.size > 0)
        for (process in processes.toTypedArray()) {
            process.increaseReamingBurstTime(2)
            if(process.isDone())
                processes.remove(process)
        }
    println(processes)
}