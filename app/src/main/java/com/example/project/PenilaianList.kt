package com.example.project
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class PenilaianList : AppCompatActivity()  {
    private lateinit var evaluationListView: ListView
    private lateinit var yearSpinner: Spinner
    private lateinit var evaluationAdapter: ArrayAdapter<String>
    private var evaluations = mutableListOf<String>()
    private var filteredEvaluations = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penilaian_list)

        // Initialize views
        evaluationListView = findViewById(R.id.evaluationListView)
        yearSpinner = findViewById(R.id.spinnerYear)

        // Example data for evaluations
        evaluations.add("Laporan 1 - 2023")
        evaluations.add("Laporan 2 - 2023")
        evaluations.add("Laporan 3 - 2024")
        evaluations.add("Laporan 4 - 2024")

        // Setup year spinner
        val years = arrayOf("2023", "2024")
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearAdapter

        // Filter evaluations based on year selection
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterEvaluations(years[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Setup ListView adapter for evaluations
        evaluationAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredEvaluations)
        evaluationListView.adapter = evaluationAdapter

        // CRUD Operations - long click to delete or short click to edit
        evaluationListView.setOnItemClickListener { _, _, position, _ ->
            // Implement edit functionality here
            Toast.makeText(this, "Edit: ${filteredEvaluations[position]}", Toast.LENGTH_SHORT).show()
        }

        evaluationListView.setOnItemLongClickListener { _, _, position, _ ->
            // Implement delete functionality here
            filteredEvaluations.removeAt(position)
            evaluationAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            true
        }
    }

    // Filter the evaluations based on the selected year
    private fun filterEvaluations(year: String) {
        filteredEvaluations.clear()
        for (evaluation in evaluations) {
            if (evaluation.contains(year)) {
                filteredEvaluations.add(evaluation)
            }
        }
        evaluationAdapter.notifyDataSetChanged()
    }

}