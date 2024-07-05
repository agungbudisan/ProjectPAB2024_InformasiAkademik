package com.pab2024klsa.informasiakademik.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.pab2024klsa.informasiakademik.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.database.*

class NilaiAkademikActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var graphIPK: BarChart
    private lateinit var databaseNilai: DatabaseReference
    private lateinit var databasePersentase: DatabaseReference
    private lateinit var spinnerSemester: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nilai_akademik)

        barChart = findViewById(R.id.barChart)
        graphIPK = findViewById(R.id.graphIPK)
        spinnerSemester = findViewById(R.id.spinnerSemester)
        databaseNilai = FirebaseDatabase.getInstance().reference.child("nilaiAkademik")
        databasePersentase = FirebaseDatabase.getInstance().reference.child("nilaiAkademikPersentase")
        setupSpinner()
    }

    private fun setupSpinner() {
        val semesterArray = resources.getStringArray(R.array.semester)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, semesterArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSemester.adapter = adapter

        spinnerSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedSemester = parent.getItemAtPosition(position) as String
                Log.d("NilaiAkademik", "Selected Semester Range: $selectedSemester")
                loadChartData(selectedSemester)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing here
            }
        }
    }

    private fun loadChartData(semester: String) {
        databaseNilai.child(semester).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("NilaiAkademik", "Data received from Firebase for semester in: $semester")

                val entries = ArrayList<BarEntry>()
                val labels = ArrayList<String>()

                var index = 0f
                for (data in snapshot.children) {
                    val value = data.getValue(Long::class.java) ?: 0L
                    entries.add(BarEntry(index, value.toFloat()))
                    labels.add(data.key ?: "Unknown")
                    Log.d("NilaiAkademik", "Key: ${data.key}, Value: $value")
                    index += 1f
                }

                if (entries.isEmpty()) {
                    Log.w("NilaiAkademik", "No data entries found for semester in: $semester")
                    barChart.clear()
                    barChart.invalidate()
                    return
                }

                val barDataSet = BarDataSet(entries, "IPK")
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
                Log.d("NilaiAkademik", "Chart data set successfully for semester in: $semester")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("NilaiAkademik", "Failed to read data", error.toException())
            }
        })

        databasePersentase.child(semester).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("PersentaseNilaiAkademik", "Data received from Firebase for semester in: $semester")

                val entries = ArrayList<BarEntry>()
                val labels = ArrayList<String>()

                var index = 0f
                for (data in snapshot.children) {
                    val value = data.getValue(Long::class.java) ?: 0L
                    entries.add(BarEntry(index, value.toFloat()))
                    labels.add(data.key ?: "Unknown")
                    Log.d("PersentaseNilaiAkademik", "Key: ${data.key}, Value: $value")
                    index += 1f
                }

                if (entries.isEmpty()) {
                    Log.w("PersentaseNilaiAkademik", "No data entries found for semester in: $semester")
                    graphIPK.clear()
                    graphIPK.invalidate()
                    return
                }

                val ipkDataSet = BarDataSet(entries, "% dari total IPK")
                val ipkData = BarData(ipkDataSet)
                graphIPK.data = ipkData

                val xAxis2 = graphIPK.xAxis
                xAxis2.position = XAxis.XAxisPosition.BOTTOM
                xAxis2.setDrawGridLines(false)
                xAxis2.valueFormatter = IndexAxisValueFormatter(labels)

                val leftAxis2 = graphIPK.axisLeft
                leftAxis2.setDrawGridLines(false)

                val rightAxis2 = graphIPK.axisRight
                rightAxis2.setDrawGridLines(false)
                rightAxis2.isEnabled = false

                graphIPK.description.isEnabled = false
                graphIPK.animateY(1000)
                graphIPK.invalidate() // refresh chart
                Log.d("PersentaseNilaiAkademik", "Chart data set successfully for semester in: $semester")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("PersentaseNilaiAkademik", "Failed to read data", error.toException())
            }
        })
    }
}