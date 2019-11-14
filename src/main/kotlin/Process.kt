class Process(private val name : String,private val burstTime: Int,private val enterTime: Int) {

    private fun getName() : String{
        return name;
    }

    private fun getBurstTime() : Int {
        return burstTime
    }

    private fun getEnterTime() : Int{
        return enterTime
    }

}