package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EvaluationActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        // Mendapatkan data dari Intent
        val namaDokter = intent.getStringExtra("NAMA_DOKTER")
        val evaluasi = intent.getStringExtra("EVALUASI")
        val saran = intent.getStringExtra("SARAN")

        // Mengisi data ke dalam TextView
        val namaDokterTextView = findViewById<TextView>(R.id.profileName)
        val evaluasiTextView = findViewById<TextView>(R.id.evaluationText)
        val saranTextView = findViewById<TextView>(R.id.suggestionText)

        namaDokterTextView.text = namaDokter
        evaluasiTextView.text = evaluasi
        saranTextView.text = saran
    }
    fun panggil(view: View?){
        val intent = Intent(applicationContext,Menu::class.java)
        startActivity(intent)
    }
//    fun panggil(view: View?){
//        val intent = Intent(applicationContext,Menu::class.java)
//        startActivity(intent)
//    }
}