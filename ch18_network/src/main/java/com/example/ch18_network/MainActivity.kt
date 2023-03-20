package com.example.ch18_network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ch18_network.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    var mode = "volley"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyFragment= VolleyFragment()
        retrofitFragment= RetrofitFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, volleyFragment)
            .commit()
        supportActionBar?.title="Volley Test"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_volley && mode !== "volley"){
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, volleyFragment)
                .commit()
            mode="volley"
            supportActionBar?.title="Volley Test"
        }else if(item.itemId === R.id.menu_retrofit && mode !== "retrofit"){
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, retrofitFragment)
                .commit()
            mode="retrofit"
            supportActionBar?.title="Retrofit Test"
        }
        return super.onOptionsItemSelected(item)
    }
}


/*
실습
        1. 임의의 무료 api서버에서 한페이지 정도의 데이터를 받아와서 리사이클러뷰에 출력하기.
        2. 새로운 모듈
        3. 메인, 리사이클러뷰
        4. 네트워크 인터페이스, 모델객체2개
        5. 리사이클러뷰 작업
        6. 공공데이터 등
 */