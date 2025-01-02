package com.example.project

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class InputPenilaian : AppCompatActivity() {

    private lateinit var spinnerDivision: Spinner
    private lateinit var editTextDate: EditText
    private lateinit var editTextRatingAbsensi: EditText
    private lateinit var editTextRatingProfessional: EditText
    private lateinit var editTextRatingService: EditText
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
        editTextRatingAbsensi = findViewById(R.id.editTextRatingAbsensi)
        editTextRatingProfessional = findViewById(R.id.editTextRatingProfessional)
        editTextRatingService = findViewById(R.id.editTextRatingService)
        editTextEvaluation = findViewById(R.id.editTextEvaluation)
        buttonCreate = findViewById(R.id.buttonCreate)

        // Setup Spinner Data
        val divisions = listOf("Divisi 1", "Divisi 2", "Divisi 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, divisions)
        spinnerDivision.adapter = adapter

        // Setup Date Picker untuk input tanggal
        val calendar = Calendar.getInstance()

        // Klik pada EditText untuk memunculkan dialog kalender
        editTextDate.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    // Saat tanggal dipilih, format dan set di EditText
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)

                    // Format tanggal menjadi "dd-MM-yyyy"
                    val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    editTextDate.setText(format.format(selectedDate.time))
                },
                // Tanggal default adalah tanggal sekarang
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Save Data and Pass to List Activity
        buttonCreate.setOnClickListener {
            val division = spinnerDivision.selectedItem.toString()
            val date = editTextDate.text.toString()
            val ratingAbsensi = editTextRatingAbsensi.text.toString().toFloatOrNull()
            val ratingProfessional = editTextRatingProfessional.text.toString().toFloatOrNull()
            val ratingService = editTextRatingService.text.toString().toFloatOrNull()
            val evaluation = editTextEvaluation.text.toString()

            if (division.isNotEmpty() && date.isNotEmpty() && ratingAbsensi != null &&
                ratingProfessional != null && ratingService != null && evaluation.isNotEmpty()) {

                val data = mapOf(
                    "division" to division,
                    "date" to date,
                    "ratingAbsensi" to ratingAbsensi,
                    "ratingProfessional" to ratingProfessional,
                    "ratingService" to ratingService,
                    "evaluation" to evaluation
                )

                // Add to local list
                assessmentList.add(data)

                // Log data for verification
                Log.d("InputPenilaianActivity", "Data Saved: $data")

                // Pass data to EvaluationListActivity
                val intent = Intent(this, PenilaianList::class.java)
                intent.putExtra("division", division)
                intent.putExtra("date", date)
                intent.putExtra("ratingAbsensi", ratingAbsensi)
                intent.putExtra("ratingProfessional", ratingProfessional)
                intent.putExtra("ratingService", ratingService)
                intent.putExtra("evaluation", evaluation)
                startActivity(intent)

                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Harap isi semua field dengan benar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        spinnerDivision.setSelection(0)
        editTextDate.text.clear()
        editTextRatingAbsensi.text.clear()
        editTextRatingProfessional.text.clear()
        editTextRatingService.text.clear()
        editTextEvaluation.text.clear()
    }
}