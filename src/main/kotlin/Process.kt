package main.kotlin

class Process(private val name: String, private val burstTime: Int, private val enterTime: Int) : Comparable<Process> {

    private var done = false
    private var reamingBurstTime = burstTime
    private var startTime = -1
    private var finishTime = -1

    override fun compareTo(other: Process): Int {
        return when {
            other.burstTime == this.burstTime -> when {
                other.enterTime == this.enterTime -> 0
                other.enterTime > this.enterTime -> -1
                else -> 1
            }
            other.enterTime == this.enterTime -> if (other.burstTime > this.burstTime) -1 else 1
            other.enterTime > this.enterTime -> -2
            else -> 2
        }
    }

    fun getName(): String {
        return name;
    }

    fun getBurstTime(): Int {
        return burstTime
    }

    fun getEnterTime(): Int {
        return enterTime
    }

    fun isDone(): Boolean {
        return this.done
    }

    private fun setDone() {
        this.done = true
    }

    fun getReamingBurstTime(): Int {
        return this.reamingBurstTime
    }

    fun getStartTime():Int{
        return this.startTime
    }

    fun setStartTime(value: Int){
        this.startTime = value
    }

    fun getFinishTime():Int{
        return this.finishTime
    }

    fun setFinishTime(value: Int){
        this.finishTime = value
    }

    fun getWaitTime():Int{
        return when{
            startTime == -1 || finishTime == -1 -> -1
            else -> {
                finishTime - enterTime - burstTime
            }
        }
    }

    fun increaseReamingBurstTime(value: Int) {
        this.reamingBurstTime -= value
        if (reamingBurstTime <= 0)
            setDone()
    }

    override fun toString(): String {
        return "name : $name | burtTime : $burstTime | enterTime : $enterTime | remainingBursTime : $reamingBurstTime"
    }

}