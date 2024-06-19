package com.pab2024klsa.informasiakademik.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pab2024klsa.informasiakademik.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class PembayaranDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_detail)

        val barChart = findViewById<BarChart>(R.id.barChart)

        // Dummy data
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        entries.add(BarEntry(3f, 50f))
        entries.add(BarEntry(4f, 70f))
        entries.add(BarEntry(5f, 60f))

        val barDataSet = BarDataSet(entries, "Data Set")
        val barData = BarData(barDataSet)
        barChart.data = barData

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun"))

        val leftAxis = barChart.axisLeft
        leftAxis.setDrawGridLines(false)

        val rightAxis = barChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.isEnabled = false

        barChart.description.isEnabled = false
        barChart.animateY(1000)
    }
}