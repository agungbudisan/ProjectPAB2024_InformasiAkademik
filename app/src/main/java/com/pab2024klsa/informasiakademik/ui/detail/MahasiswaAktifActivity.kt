package com.pab2024klsa.informasiakademik.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
    private lateinit var spinnerFakultas: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa_aktif)

        barChartMhsAktif = findViewById(R.id.barChartMhsAktif)
        spinnerFakultas = findViewById(R.id.spinnerFakultasMhsAktif)
        database = FirebaseDatabase.getInstance().reference.child("MahasiswaAktif")

        setupSpinner()
    }

    private fun setupSpinner() {
        val fakultasArray = resources.getStringArray(R.array.fakultas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, fakultasArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFakultas.adapter = adapter

        spinnerFakultas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedFakultas = parent.getItemAtPosition(position) as String
                Log.d("MahasiswaAktif", "Selected Fakultas: $selectedFakultas")
                loadChartData(selectedFakultas)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing here
            }
        }
    }

    private fun loadChartData(fakultas: String) {
        database.child(fakultas).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("MahasiswaAktif", "Data received from Firebase for fakultas: $fakultas")

                val entries = ArrayList<BarEntry>()
                val labels = ArrayList<String>()

                var index = 0f
                for (data in snapshot.children) {
                    val value = data.getValue(Long::class.java) ?: 0L
                    entries.add(BarEntry(index, value.toFloat()))
                    labels.add(data.key ?: "Unknown")
                    Log.d("MahasiswaAktif", "Key: ${data.key}, Value: $value")
                    index += 1f
                }

                // Handle empty data
                if (entries.isEmpty()) {
                    Log.w("MahasiswaAktif", "No data entries found for fakultas: $fakultas")
                    barChartMhsAktif.clear()
                    barChartMhsAktif.invalidate()
                    return
                }

                // Set up the bar chart data
                val barDataSet = BarDataSet(entries, "Mahasiswa")
                val barData = BarData(barDataSet)
                barChartMhsAktif.data = barData

                val xAxis = barChartMhsAktif.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                xAxis.labelRotationAngle = -65f // Rotate the labels 65 degrees counter-clockwise
                xAxis.setLabelCount(labels.size / 2, true) // Show only half of the labels

                val leftAxis = barChartMhsAktif.axisLeft
                leftAxis.setDrawGridLines(false)

                val rightAxis = barChartMhsAktif.axisRight
                rightAxis.setDrawGridLines(false)
                rightAxis.isEnabled = false

                barChartMhsAktif.description.isEnabled = false
                barChartMhsAktif.animateY(1000)
                barChartMhsAktif.invalidate() // refresh chart
                Log.d("MahasiswaAktif", "Chart data set successfully for fakultas: $fakultas")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MahasiswaAktif", "Failed to read data", error.toException())
            }
        })
    }
}
