package com.example.project

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry


class LaporanKinerjaUser : AppCompatActivity() {
    var lineChartIndividu: LineChart? = null
    var lineChartDivisi: LineChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.laporan_kinerjauser)

        // Grafik Kinerja Individu
        lineChartIndividu = findViewById(R.id.lineChartIndividu)
        setDataForChart(lineChartIndividu, getDummyData(), "#F8B400")

        // Grafik Kinerja Divisi
        lineChartDivisi = findViewById(R.id.lineChartDivisi)
        setDataForChart(lineChartDivisi, getDummyData(), "#00B4D8")
    }

    private fun setDataForChart(
        chart: LineChart?,
        dataValues: List<Entry>,
        color: String
    ) {
        val lineDataSet = LineDataSet(dataValues, "Kinerja")
        lineDataSet.color = Color.parseColor(color)
        lineDataSet.setCircleColor(Color.parseColor(color))
        lineDataSet.setDrawValues(false) // Disable values to remove numbers

        val lineData = LineData(lineDataSet)
        chart?.data = lineData

        // Styling Chart
        chart?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        chart?.description?.isEnabled = false
        chart?.invalidate() // Refresh chart
    }

    // Function to create dummy data for testing (You can replace this with actual data later)
    private fun getDummyData(): List<Entry> {
        val data = ArrayList<Entry>()
        data.add(Entry(0f, 1f))
        data.add(Entry(1f, 2f))
        data.add(Entry(2f, 1.5f))
        data.add(Entry(3f, 3f))
        data.add(Entry(4f, 2.5f))
        data.add(Entry(5f, 4f))
        return data
    }
}
