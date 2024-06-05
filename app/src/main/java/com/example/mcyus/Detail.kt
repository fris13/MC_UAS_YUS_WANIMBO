package com.example.mcyus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mcyus.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)


        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intess = intent
        var deskT = intess.getStringExtra("DESKT")
        var namaT = intess.getStringExtra("NAMAT")
        var imgT = intess.getStringExtra("IMGT")

        binding.imageDetail.loadImage(imgT)
        binding.NamaDetail.text =namaT
        binding.deskripsiDetail.text =deskT
    }

    }
