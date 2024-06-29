package com.pab2024klsa.informasiakademik.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.pab2024klsa.informasiakademik.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.database.*

class AnalisisPembayaranActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var database: DatabaseReference
    private lateinit var buttonLihatDetail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analisis_pembayaran)

        barChart = findViewById(R.id.barChart)
        buttonLihatDetail = findViewById(R.id.buttonLihatDetail)

        database = FirebaseDatabase.getInstance().reference.child("analisisPembayaran")

        loadChartData()

        buttonLihatDetail.setOnClickListener {
            val intent = Intent(this, DetailPembayaranActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadChartData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("AnalisisPembayaran", "Data received from Firebase")

                val entries = ArrayList<BarEntry>()
                val labels = ArrayList<String>()

                var index = 0f
                for (data in snapshot.children) {
                    val value = data.getValue(Long::class.java) ?: 0L
                    entries.add(BarEntry(index, value.toFloat()))
                    labels.add(data.key ?: "Unknown")
                    Log.d("AnalisisPembayaran", "Key: ${data.key}, Value: $value")
                    index += 1f
                }

                if (entries.isEmpty()) {
                    Log.w("AnalisisPembayaran", "No data entries found")
                    return
                }

                val barDataSet = BarDataSet(entries, "Pembayaran")
                val barData = BarData(barDataSet)
                barChart.data = barData

                val xAxis = barChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)

                val leftAxis = barChart.axisLeft
                leftAxis.setDrawGridLines(false)

                val rightAxis = barChart.axisRight
                rightAxis.setDrawGridLines(false)
                rightAxis.isEnabled = false

                barChart.description.isEnabled = false
                barChart.animateY(1000)
                barChart.invalidate() // refresh chart
                Log.d("AnalisisPembayaran", "Chart data set successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("AnalisisPembayaran", "Failed to read data", error.toException())
            }
        })
    }
}
