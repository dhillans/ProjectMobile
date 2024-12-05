package com.example.project

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LaporanPenilaian : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.laporan_penilaian)

        val btnInputPen: Button = findViewById(R.id.btnInputPen)

        btnInputPen.setOnClickListener {
            Toast.makeText(this, "Input Penilaian clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
