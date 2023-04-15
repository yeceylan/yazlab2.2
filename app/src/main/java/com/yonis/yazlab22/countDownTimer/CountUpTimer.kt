import android.os.CountDownTimer
import com.yonis.yazlab22.activity.GameActivity

abstract class CountUpTimer(countUpInterval: Long) {
    private val countDownTimer: CountDownTimer
    private var countDownCycle=0

    init {
        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, countUpInterval) {
            override fun onTick(millisUntilFinished: Long) {
               // addLetter()
                this@CountUpTimer.onTick(Long.MAX_VALUE* countDownCycle - millisUntilFinished)
            }

            override fun onFinish() {
                countDownCycle++
                this@CountUpTimer.start()

            }

        }
        countDownCycle = 1
    }

    fun start() {
        countDownTimer.start()

    }

    fun stop() {
        countDownTimer.cancel()
    }

    fun cancel() {
        stop()
        countDownCycle = 1
    }
//    fun addLetter(){
//        GameActivity.courseRVAdapter.notifyDataSetChanged()
//    }
    abstract fun onTick(millisElapsed: Long)
}
