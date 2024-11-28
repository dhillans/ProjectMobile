package com.example.project

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class DashboardAdmin :  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboardadmin)

        findViewById<Button>(R.id.tvBackToProfiles).setOnClickListener {
            Toast.makeText(this, "Navigasi ke halaman sebelumnya", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnAddEvaluasi).setOnClickListener {
            Toast.makeText(this, "Tambah data evaluasi kinerja", Toast.LENGTH_SHORT).show()
        }
    }
}