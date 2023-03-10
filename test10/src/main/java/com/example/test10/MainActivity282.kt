package com.example.test10

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.example.test10.databinding.ActivityMain282Binding

class MainActivity282 : AppCompatActivity() {

    fun ToastTest(msg:String) {
        Toast.makeText(this@MainActivity282, "토스트 테스트: ${msg}",Toast.LENGTH_LONG).show()
    }

    val eventHandler = object : DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {
            if (p1 == DialogInterface.BUTTON_POSITIVE) {
                Log.d("kkang", "positive button click")
                ToastTest("positive button click")
            } else if (p1 == DialogInterface.BUTTON_NEGATIVE) {
                Log.d("kkang", "negative button click")
                ToastTest("negative button click")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain282Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("test dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("OK", eventHandler)
                setNegativeButton("Cancel", eventHandler)
                show()
            }
        }
        binding.button2.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(
                    items,
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            Log.d(
                                "kkang",
                                "선택한 과일 : ${items[p1]}"
                            )
                            ToastTest("선택한 과일 : ${items[p1]}")
                            // 선택한 값에대한 데이터 전송
                            // 다른화면 뷰 으로 전환
                            // 인텐트, 프리퍼런스 임시메모리에 저장 가져오기, 파이어베이스로 사용
                        }
                    })
                setPositiveButton("닫기", null)
                show()
            }
        }
        // 체크박스 4가지, 선택시 콘솔/토스트 출력
        binding.button3.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(
                    items,
                    booleanArrayOf(true, false, true, false),
                    object : DialogInterface.OnMultiChoiceClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                            Log.d(
                                "kkang",
                                "${items[p1]} 이 ${if (p2) "선택되었습니다." else "선택 해제되었습니다."}"
                            )
                            ToastTest("${items[p1]}이 ${if (p2) "선택되었습니다." else "선택 해제되었습니다."}")
                        }
                    })
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.button4.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(
                    items,
                    1,
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            Log.d(
                                "kkang",
                                "${items[p1]} 이 선택되었습니다."
                            )
                        }
                    })
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.button5.setOnClickListener {
            val items = arrayOf<String>("공부", "운동", "놀기", "산책")
            AlertDialog.Builder(this).run {
                setTitle("주말 할 일 목록")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(
                    items,
                    booleanArrayOf(false, false, false, false),
                    object : DialogInterface.OnMultiChoiceClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                            Log.d(
                                "kkang",
                                "${items[p1]} 이 ${if (p2) "선택되었습니다." else "선택 해제되었습니다."}"
                            )
                            ToastTest("${items[p1]}이 ${if (p2) "선택되었습니다." else "선택 해제되었습니다."}")
                        }
                    })
                setPositiveButton("닫기", null)
                show()
            }
        }
    }
}