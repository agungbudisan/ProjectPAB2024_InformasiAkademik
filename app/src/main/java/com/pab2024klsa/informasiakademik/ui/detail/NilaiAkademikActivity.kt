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

class NilaiAkademikActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nilai_akademik)

        val barChart = findViewById<BarChart>(R.id.barChart)
        val graphIPK = findViewById<BarChart>(R.id.graphIPK)

        // Dummy data
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 60f))
        entries.add(BarEntry(2f, 40f))
        entries.add(BarEntry(3f, 50f))
        entries.add(BarEntry(4f, 70f))

        val barDataSet = BarDataSet(entries, "Nilai")
        val barData = BarData(barDataSet)
        barChart.data = barData
        graphIPK.data = barData

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("1-2", "3-4", "5-6", "7-8", "9-10"))

        val leftAxis = barChart.axisLeft
        leftAxis.setDrawGridLines(false)

        val rightAxis = barChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.isEnabled = false

        barChart.description.isEnabled = false
        barChart.animateY(1000)

        val xAxis2 = graphIPK.xAxis
        xAxis2.position = XAxis.XAxisPosition.BOTTOM
        xAxis2.setDrawGridLines(false)
        xAxis2.valueFormatter = IndexAxisValueFormatter(arrayOf("1-2", "3-4", "5-6", "7-8", "9-10"))

        val leftAxis2 = graphIPK.axisLeft
        leftAxis2.setDrawGridLines(false)

        val rightAxis2 = graphIPK.axisRight
        rightAxis2.setDrawGridLines(false)
        rightAxis2.isEnabled = false

        graphIPK.description.isEnabled = false
        graphIPK.animateY(1000)
    }
}