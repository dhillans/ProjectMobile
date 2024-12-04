package com.example.project

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class InputPenilaian : AppCompatActivity() {

    private lateinit var spinnerDivision: Spinner
    private lateinit var editTextDate: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var editTextEvaluation: EditText
    private lateinit var buttonCreate: Button

    // List lokal untuk menyimpan data sementara
    private val assessmentList = mutableListOf<Map<String, Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_penilaian)

        // Inisialisasi Views
        spinnerDivision = findViewById(R.id.spinnerDivision)
        editTextDate = findViewById(R.id.editTextDate)
        ratingBar = findViewById(R.id.ratingBar)
        editTextEvaluation = findViewById(R.id.editTextEvaluation)
        buttonCreate = findViewById(R.id.buttonCreate)

        // Setup Spinner Data
        val divisions = listOf("Divisi 1", "Divisi 2", "Divisi 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, divisions)
        spinnerDivision.adapter = adapter

        // Setup Date Picker
        val calendar = Calendar.getInstance()
        editTextDate.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)
                    val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    editTextDate.setText(format.format(selectedDate.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Simpan Data ke List Lokal
        buttonCreate.setOnClickListener {
            val division = spinnerDivision.selectedItem.toString()
            val date = editTextDate.text.toString()
            val rating = ratingBar.rating
            val evaluation = editTextEvaluation.text.toString()

            if (division.isNotEmpty() && date.isNotEmpty() && evaluation.isNotEmpty()) {
                val data = mapOf(
                    "division" to division,
                    "date" to date,
                    "rating" to rating,
                    "evaluation" to evaluation
                )

                // Tambahkan data ke List
                assessmentList.add(data)

                // Log data untuk verifikasi
                Log.d("InputPenilaianActivity", "Data Disimpan: $data")

                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        spinnerDivision.setSelection(0)
        editTextDate.text.clear()
        ratingBar.rating = 0f
        editTextEvaluation.text.clear()
    }
}
