package com.example.test10

import android.app.Notification
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.test10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {
                Log.d("kkang", "callback, granted..")
            } else {
                Log.d("kkang", "callback, denied..")
            }
        }
//        val status= ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
        val status= ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE")
        if(status == PackageManager.PERMISSION_GRANTED){
            Log.d("kkang", "granted..폰")
        }else {
            requestPermissionLauncher.launch("android.permission.READ_PHONE_STATE")
        }
    }
}