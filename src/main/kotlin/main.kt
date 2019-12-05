package main.kotlin


fun main() {
    println("enter process properties : ")
    val list = ArrayList<Process>()
    list.add(Process("p1", 3, 1))
    list.add(Process("p2", 6, 4))
    list.add(Process("p3", 3, 3))
    list.add(Process("p4", 5, 6))
    list.add(Process("p5", 6, 6))
    var quantum = 3
/*    run {
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
    }*/
    var contextChangeTime = 1
    /*  run {
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
              var burstTimeFlag = true
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
  */
    list.sort()

    list.forEach { process: Process ->
        println(process)
    }


    val tempList: MutableList<Process> = list.toMutableList()

    var time = list[0].getEnterTime()

    val runIterate = ArrayList<String>()


    while (tempList.size > 0)
        for (process in tempList.toTypedArray()) {
            println("start *****  processing $process  | Time : $time")
            if(process.getStartTime() == -1)
                process.setStartTime(time)
            runIterate.add(process.getName())
            if (process.getReamingBurstTime() > quantum) {
                process.increaseReamingBurstTime(quantum)
                time += quantum
            } else {
                val reamingBurstTime = process.getReamingBurstTime()
                process.increaseReamingBurstTime(reamingBurstTime)
                time += reamingBurstTime
                process.setFinishTime(time)
                tempList.remove(process)
            }
            if(tempList.size == 0) {
                println("end *****  processing $process  | Time : $time")
                continue
            }
            val nextIndex = (tempList.indexOf(process) + 1) % tempList.size
            val nextProcessEnterRemainingTime = tempList[nextIndex].getEnterTime() - time
            if (nextProcessEnterRemainingTime > 0) {
                if (nextProcessEnterRemainingTime > process.getReamingBurstTime()) {
                    process.setFinishTime(time)
                    time = tempList[nextIndex].getEnterTime()
                    process.increaseReamingBurstTime(process.getReamingBurstTime())
                    tempList.remove(process)
                } else {
                    process.increaseReamingBurstTime(nextProcessEnterRemainingTime)
                    time += nextProcessEnterRemainingTime
                }
            }
            if (tempList[nextIndex] != process)
                time += contextChangeTime
            println("end *****  processing $process  | Time : $time")
        }

    println(runIterate)
    println(time)
    var totalWaitTime = 0
    list.forEach { process: Process ->
        println(process.toString() + " | finishTime :" + process.getFinishTime() + " | startTime :" + process.getStartTime() + " | waitTime : " + process.getWaitTime())
        totalWaitTime += process.getWaitTime()
    }
    println("average : " + totalWaitTime/list.size)

}

