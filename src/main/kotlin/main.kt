package main.kotlin

import java.lang.NumberFormatException


fun main() {
    println("enter process properties : ")
    val list = ArrayList<Process>()
    var quantum = 0
    run {
        var quantumFlag = true
        do {
            try {
                println("enter quantum")
                quantum = readLine().toString().toInt()
            } catch (ex: NumberFormatException) {
                quantumFlag = true
                println("quantum must be a int")
            }
        } while (!quantumFlag)
    }
    var contextChangeTime: Int
    run {
        var contextChangeTimeFlag = true
        do {
            try {
                println("enter context change time")
                contextChangeTime = readLine().toString().toInt()
            } catch (ex: NumberFormatException) {
                contextChangeTimeFlag = true
                println(" context change time must be a int")
            }
        } while (!contextChangeTimeFlag)
    }
    list.run {
        while (true) {
            println("enter exit to finish or enter process name")
            val processName = readLine().toString()
            if (processName == "exit")
                break

            var processBurstTime = 0
            var burstTimeFlag = true;
            do {
                try {
                    println("enter process burstTime")
                    processBurstTime = readLine().toString().toInt()
                } catch (ex: NumberFormatException) {
                    println("process burst time must be a int number")
                    burstTimeFlag = false
                }
            } while (!burstTimeFlag)

            var enterTimeFlag = true
            var processEnterTime = 0
            do {
                try {
                    println("enter process enterTime")
                    processEnterTime = readLine().toString().toInt()
                } catch (ex: NumberFormatException) {
                    println("process enter time must be a int number")
                    enterTimeFlag = false
                }
            } while (!enterTimeFlag)
            add(Process(processName, processBurstTime, processEnterTime))
        }
    }

    list.forEach { process: Process ->
        println(process)
    }

    list.sort();

    val tempList: MutableList<Process> = list.toMutableList()

    var time = list[0].getEnterTime()

    val runIterate = ArrayList<String>();


    while (tempList.size > 0)
        for ((index, process) in tempList.toTypedArray().withIndex()) {
            runIterate.add(process.getName())
            if (process.getReamingBurstTime() > quantum) {
                process.increaseReamingBurstTime(quantum)
                time += quantum

            } else {
                process.increaseReamingBurstTime(process.getReamingBurstTime())
                time += process.getReamingBurstTime()
                tempList.removeAt(index)
                continue
            }
            val nextIndex = (index + 1) % tempList.size
            val nextProcessStartRemainingTime = time - tempList[nextIndex].getEnterTime()
            if (nextProcessStartRemainingTime > 0) {
                if (nextProcessStartRemainingTime > process.getReamingBurstTime()) {
                    process.increaseReamingBurstTime(process.getReamingBurstTime())
                    time = tempList[nextIndex].getEnterTime()
                } else {
                    process.increaseReamingBurstTime(nextProcessStartRemainingTime)
                    time += nextProcessStartRemainingTime
                }
            }
        }

    println(runIterate)

}

