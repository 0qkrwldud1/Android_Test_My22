package com.example.ch18_network_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ch18_network_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    //lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    var mode = "volley"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //volleyFragment= VolleyFragment()
        retrofitFragment= RetrofitFragment()

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.activity_content, volleyFragment)
//            .commit()
//        supportActionBar?.title="Volley Test"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, retrofitFragment)
                .commit()
            mode="retrofit"
            supportActionBar?.title="Retrofit Test"

        return super.onOptionsItemSelected(item)
    }
}