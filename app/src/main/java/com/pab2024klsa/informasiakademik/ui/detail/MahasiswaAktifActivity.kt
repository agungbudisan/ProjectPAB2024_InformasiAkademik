package com.pab2024klsa.informasiakademik.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.database.*
import com.pab2024klsa.informasiakademik.R

class MahasiswaAktifActivity : AppCompatActivity() {
    private lateinit var barChartMhsAktif: BarChart
    private lateinit var database: DatabaseReference
    private lateinit var btnLihatDetail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa_aktif)

        barChartMhsAktif = findViewById(R.id.barChartMhsAktif)

        database = FirebaseDatabase.getInstance().reference.child("MahasiswaAktif")

        loadChartData()

        btnLihatDetail.setOnClickListener {
            val intent = Intent(this, DetailMhsAktifActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadChartData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("MahasiswaAktif", "Data received from Firebase")

                val entries = ArrayList<BarEntry>()
                val labels = ArrayList<String>()

                var index = 0f
                for (data in snapshot.children) {
                    val nama = data.child("col_1").getValue(String::class.java) ?: "Unknown"
                    val jumlahString = data.child("col_2").getValue(String::class.java) ?: "0"
                    val jumlah = jumlahString.toLongOrNull() ?: 0L
                    entries.add(BarEntry(index, jumlah.toFloat()))
                    labels.add(nama)
                    Log.d("MahasiswaAktif", "Nama: $nama, Jumlah: $jumlah")
                    index += 1f
                }

                if (entries.isEmpty()) {
                    Log.w("MahasiswaAktif", "No data entries found")
                    return
                }

                val barDataSet = BarDataSet(entries, "MhsAktif")
                val barData = BarData(barDataSet)
                barChartMhsAktif.data = barData

                val xAxis = barChartMhsAktif.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)

                val leftAxis = barChartMhsAktif.axisLeft
                leftAxis.setDrawGridLines(false)

                val rightAxis = barChartMhsAktif.axisRight
                rightAxis.setDrawGridLines(false)
                rightAxis.isEnabled = false

                barChartMhsAktif.description.isEnabled = false
                barChartMhsAktif.animateY(1000)
                barChartMhsAktif.invalidate() // refresh chart
                Log.d("MahasiswaAktif", "Chart data set successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MahasiswaAktif", "Failed to read data", error.toException())
            }
        })
    }
}
