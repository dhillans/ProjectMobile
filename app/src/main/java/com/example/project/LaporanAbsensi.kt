package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class LaporanAbsensi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absensi)

        // Daily Report Bar Chart
        val dailyChart = findViewById<BarChart>(R.id.chart_daily)
        setupBarChart(dailyChart, getDailyData(), "Laporan Harian")

        // Monthly Report Bar Chart
        val monthlyChart = findViewById<BarChart>(R.id.chart_monthly)
        setupBarChart(monthlyChart, getMonthlyData(), "Laporan Bulanan")

        // Yearly Report Bar Chart
        val yearlyChart = findViewById<BarChart>(R.id.chart_yearly)
        setupBarChart(yearlyChart, getYearlyData(), "Laporan Tahunan")
    }

    private fun setupBarChart(chart: BarChart, dataEntries: List<BarEntry>, label: String) {
        val dataSet = BarDataSet(dataEntries, label)
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        val data = BarData(dataSet)

        chart.data = data
        chart.description.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.animateY(1000)
        chart.invalidate() // refresh
    }

    private fun getDailyData(): List<BarEntry> {
        return listOf(
            BarEntry(0f, 4f), // Senin
            BarEntry(1f, 5f), // Selasa
            BarEntry(2f, 6f), // Rabu
            BarEntry(3f, 7f), // Kamis
            BarEntry(4f, 8f), // Jumat
            BarEntry(5f, 3f), // Sabtu
            BarEntry(6f, 0f)  // Minggu
        )
    }

    private fun getMonthlyData(): List<BarEntry> {
        return listOf(
            BarEntry(0f, 20f), // Januari
            BarEntry(1f, 18f), // Februari
            BarEntry(2f, 22f), // Maret
            // Add more months...
        )
    }

    private fun getYearlyData(): List<BarEntry> {
        return listOf(
            BarEntry(0f, 200f), // 2023
            BarEntry(1f, 210f), // 2024
            BarEntry(2f, 190f)  // 2025
            // Add more years...
        )
    }
}