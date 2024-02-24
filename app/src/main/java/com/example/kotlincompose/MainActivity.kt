package com.example.kotlincompose

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.example.kotlincompose.R
import com.example.kotlincompose.databinding.ActivityMainBinding
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Color.setOnClickListener {
            val cuerpo = binding.cuerpo
            val colores = arrayOf(R.color.purple_500, R.color.black, R.color.white,
                R.color.red, R.color.purple_700, R.color.green, R.color.yellow,
                R.color.orange, R.color.teal_200, R.color.pink, R.color.blue, R.color.grey)
            val random = Random.nextInt(0, 12)
            val color = colores.get(random)
            cuerpo.setBackgroundColor(color) }

        binding.Next.setOnClickListener {
            val intent =  Intent(this, MainActivity2::class.java).apply{
                val texto = binding.Texto.text.toString()
                putExtra("texto",texto)
            }
            startActivity(intent) }
    }
}