package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PenilaianAdapter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penilaian)

        // Sample data: Map of years to their respective Penilaian lists
        val penilaianData = mapOf(
            "2023" to listOf(Penilaian("Dokter A"), Penilaian("Dokter B")),
            "2024" to listOf(Penilaian("Dokter C"), Penilaian("Dokter D"))
        )

        // Get references to UI elements
        val container: LinearLayout = findViewById(R.id.penilaianContainer)
        val yearDropdown: Spinner = findViewById(R.id.yearDropdown)

        // Populate the dropdown with years
        val years = penilaianData.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearDropdown.adapter = adapter

        // Set listener for dropdown selection
        yearDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedYear = years[position]
                val penilaianList = penilaianData[selectedYear] ?: emptyList()

                // Clear the container before adding new items
                container.removeAllViews()

                // Add Penilaian items for the selected year
                for ((index, penilaian) in penilaianList.withIndex()) {
                    // Inflate the item layout (itempenilaian.xml) for each item
                    val itemLayout =
                        layoutInflater.inflate(R.layout.itempenilaian, container, false)

                    // Set the text and the button action for each Penilaian
                    val noTextView: TextView = itemLayout.findViewById(R.id.textNo)
                    val namaTextView: TextView = itemLayout.findViewById(R.id.textNama)
                    val lihatButton: Button = itemLayout.findViewById(R.id.buttonLihat)

                    noTextView.text = (index + 1).toString() // Set the index as number
                    namaTextView.text = penilaian.nama // Set the penilaian's name

                    // Set onClickListener for the button
                    lihatButton.setOnClickListener {
                        val intent = Intent(this@PenilaianAdapter, EvaluationPenilaian::class.java)
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
    }
}