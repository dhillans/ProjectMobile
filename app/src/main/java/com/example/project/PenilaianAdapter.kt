package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PenilaianAdapter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penilaian) // Correct layout that contains penilaianContainer

        // Sample list of Penilaian objects
        val penilaianList = listOf(
            Penilaian("Dokter A"),
            Penilaian("Dokter B"),
            Penilaian("Dokter C")
        )

        // Get the LinearLayout container to add buttons dynamically
        val container: LinearLayout = findViewById(R.id.penilaianContainer)

        // Iterate over the penilaianList and create a new LinearLayout for each Penilaian item
        for ((index, penilaian) in penilaianList.withIndex()) {
            // Inflate the item layout (itempenilaian.xml) for each item
            val itemLayout = layoutInflater.inflate(R.layout.activity_penilaian, container, false)

            // Set the text and the button action for each Penilaian
            val noTextView: TextView = itemLayout.findViewById(R.id.textNo)
            val namaTextView: TextView = itemLayout.findViewById(R.id.textNama)
            val lihatButton: Button = itemLayout.findViewById(R.id.buttonLihat)

            noTextView.text = (index + 1).toString() // Set the index as number
            namaTextView.text = penilaian.nama // Set the penilaian's name

            // Set onClickListener for the button
            lihatButton.setOnClickListener {
                // Navigate to EvaluationPenilaian activity
                intent.putExtra("NAMA_DOKTER", penilaian.nama)
                intent.putExtra("EVALUASI", "Evaluasi untuk ${penilaian.nama}")
                intent.putExtra("SARAN", "Saran perbaikan untuk ${penilaian.nama}")
                startActivity(intent)
            }

            // Add the itemLayout (new LinearLayout) to the container
            container.addView(itemLayout)
        }
    }
}
