package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13.databinding.ActivityMain443Binding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity443 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain443Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
//            var sum = 0L
//            var time = measureTimeMillis {
//                for(i in 1..2_000_000_000){
//                    sum += i
//                }
//            }
//            Log.d("kkang","time : $time")
//            binding.resultView.text = "sum : $sum"

//            val handler=object: Handler(){
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    binding.resultView.text = "sum : ${msg.arg1}"
//                }
//            }
//
//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..2_000_000_000) {
//                        sum += i
//                    }
//                    val message = Message()
//                    message.arg1=sum.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("kkang", "time : $time")
//            }


            val channel = Channel<Int>()

            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..2_000_000_000) {
                        sum += i
                    }
                }
                Log.d("kkang", "time : $time")
                channel.send(sum.toInt())
            }

            // 사용자 이벤트, 처리 작업속도가 빠른 수행을 시킨다
            // 메인 ,UI
            val mainScope= GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultView.text = "sum : $it"
                }
            }


        }
    }
}
    /*

    ANR 코루틴
        액티비티 -> 이벤트에 빠르게 반응해야함
        응답하는 희망 속도 200ms,300ms 느려도 500ms ~ 최대 1000ms
        액티비티 사용자 이벤트의 5초 이내에 반응하지 않으면 ANR발생
        Activity Not Respose : ANR
        액티비티에서 사용자 이벤트를 처리 하지 못하는 이유
        실행한 시스템에서 발생한 수행흐름에서 이벤트를 처리를 x
        예) 오래 걸리는 작업을 메인 스레드 즉 UI스래드를 사용자가 직접 처리 x

        문제. 계산 20억정도 요하는 작업
        화면에 에딧텍스트 뷰를 클릭해도 무반응

        참고. 만약 시간이 오래 걸리는 작업을 서비스 컴포넌트로 처리?
        가능함. 하지만 서비스의 역할은 작업을 진행하는 부분.
        액티비티 작업은 화면을 출력하는 부분.
        좋은 방법 x

        화면의 출력이 오래걸려도 액티비티가 담당해야함

        코루틴.
        코루틴은 corutine
        루틴이 작업을 처리하는 것 의미. 함께 작업 처리한다.
        비동기 처리 방식과 같다.

        스레드 deprecated.
        방식만 보고 넘어감.

        코루틴 코드.

        작업 구조:

        크게 두분류.
        1. 백 backgroundScope
        2. 메인 mainScope

        Dispatchers : 각 스코프에서 구동한 코루틴이 어디에서 동작하는지를 나타내기 위해서 사용.

        Dispatchers.Main : 메인 스레드에서 동작. UI변경가능.
        사용자 이벤트 처리 빨리 끝나는 작업을 맡김.
        -> 화면을 그리는 작업
        오래 걸린 작업의 결과값을 뷰에 붙여준다.

        Dispatchers.Default  :오래걸리는 작업.

        channel : 큐 알고리즘과 비슷
        퍼스트인 퍼스트아웃.

        채널의 send함수로 데이터 전달 -> 데이터를 담는코루틴에서
        recive나 consumeEach함수 등으로 받는다
    */

