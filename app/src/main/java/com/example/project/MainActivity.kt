package com.example.project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = listOf(
            Penilaian("1", "Fadhilla Khairunnisa"),
            Penilaian("2", "Fadhilla Khairunnisa"),
            Penilaian("3", "Fadhilla Khairunnisa"),
            Penilaian("4", "Fadhilla Khairunnisa"),
        )

        val adapter = PenilaianAdapter(data) { item ->
            Toast.makeText(this, "Lihat: ${item.nama}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }
}
