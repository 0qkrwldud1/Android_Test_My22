package com.example.ch17_database_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch17_database_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var datas: MutableList<String>? = null
    var datas2: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            it.data!!.getStringExtra("name")?.let {
                // name의 키에 해당하는 값 -> it
                datas?.add(it)
                //
                adapter.notifyDataSetChanged()
            }
            it.data!!.getStringExtra("age")?.let {
                datas2?.add(it)
                adapter.notifyDataSetChanged()
            }
        }

        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        // 배열 초기화
        datas= mutableListOf<String>()
        datas2= mutableListOf<String>()

        //add......................

        // 디비 셀렉트
        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from User", null)
        cursor.run {
            while(moveToNext()){
                // 첫 컬럼 이름 값을 읽어서 배열에 담기
                datas?.add(cursor.getString(1))
                // 첫 컬럼 나이 값을 읽어서 배열에 담기
                datas2?.add(cursor.getString(2))
            }
        }
        db.close()

        // 리사이클러 뷰
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas,datas2)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId===R.id.menu_main_setting){
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }
}