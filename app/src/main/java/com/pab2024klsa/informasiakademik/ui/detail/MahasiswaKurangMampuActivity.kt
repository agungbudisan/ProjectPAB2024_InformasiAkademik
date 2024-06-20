package com.pab2024klsa.informasiakademik.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pab2024klsa.informasiakademik.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class MahasiswaKurangMampuActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa_kurang_mampu)

        barChart = findViewById(R.id.barChart)
        val buttonData1: Button = findViewById(R.id.buttonData1)
        val buttonData2: Button = findViewById(R.id.buttonData2)

        setupChart()

        buttonData1.setOnClickListener {
            setData1()
        }

        buttonData2.setOnClickListener {
            setData2()
        }

        setDefaultData()
    }

    private fun setupChart() {
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

    private fun setData1() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 80f))
        entries.add(BarEntry(2f, 60f))
        entries.add(BarEntry(3f, 50f))
        entries.add(BarEntry(4f, 70f))
        entries.add(BarEntry(5f, 60f))

        val barDataSet = BarDataSet(entries, "Data Set 1")
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.invalidate()
    }

    private fun setData2() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 20f))
        entries.add(BarEntry(1f, 90f))
        entries.add(BarEntry(2f, 40f))
        entries.add(BarEntry(3f, 70f))
        entries.add(BarEntry(4f, 60f))
        entries.add(BarEntry(5f, 80f))

        val barDataSet = BarDataSet(entries, "Data Set 2")
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.invalidate()
    }

    private fun setDefaultData() {
        val data1 = listOf(30f, 80f, 60f, 50f, 70f, 60f)
        val data2 = listOf(20f, 90f, 40f, 70f, 60f, 80f)

        val totalEntries = ArrayList<BarEntry>()
        for (i in data1.indices) {
            totalEntries.add(BarEntry(i.toFloat(), data1[i] + data2[i]))
        }

        val barDataSet = BarDataSet(totalEntries, "Total Data Set")
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.invalidate()
    }
}