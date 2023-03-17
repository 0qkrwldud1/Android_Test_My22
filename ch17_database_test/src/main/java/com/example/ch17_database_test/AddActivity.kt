package com.example.ch17_database_test

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ch17_database_test.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){

            R.id.menu_add_save ->{
                //add........................
                val inputData = binding.addEditView.text.toString()
                val inputData2 = binding.addEditView2.text.toString()

                val db = DBHelper(this).writableDatabase

                db.execSQL(
                    "insert into User (name, age) values (?, ?)",
                    arrayOf<String>(inputData ,inputData2)
                )
                db.close()

                val intent = intent
                intent.putExtra("name", inputData)
                intent.putExtra("age", inputData2)
                setResult(Activity.RESULT_OK, intent)
                finish()
                true
            }   else -> true
        }
}