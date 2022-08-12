package com.example.marvelapiapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapiapp.databinding.ActivitySplashBinding
import com.example.marvelapiapp.ui.MainActivity
import com.example.marvelapiapp.utils.goToActivityAnimation

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var  _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val background = Thread {
            Thread.sleep(2000)
            goToMain()
        }
        background.start()
    }


    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        goToActivityAnimation()
        finish()
    }
}