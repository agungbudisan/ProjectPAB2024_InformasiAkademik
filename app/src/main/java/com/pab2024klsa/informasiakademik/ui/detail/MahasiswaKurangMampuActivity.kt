package com.pab2024klsa.informasiakademik.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.TableRow.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.database.*
import com.pab2024klsa.informasiakademik.R

class MahasiswaKurangMampuActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var tableLayout: TableLayout
    private lateinit var spinnerOptions: Spinner
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa_kurang_mampu)

        barChart = findViewById(R.id.barChart)
        tableLayout = findViewById(R.id.tableLayout)
        spinnerOptions = findViewById(R.id.spinnerOptions)

        database = FirebaseDatabase.getInstance().reference

        setupChart()
        setDropdown()
        setupTable()
    }

    private fun setDropdown() {
        val options = arrayOf("Grafik Mahasiswa Kurang Mampu", "Daftar Mahasiswa Kurang Mampu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOptions.adapter = adapter

        spinnerOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        barChart.visibility = View.VISIBLE
                        tableLayout.visibility = View.GONE
                    }
                    1 -> {
                        barChart.visibility = View.GONE
                        tableLayout.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setupChart() {
        // Mengambil data dari Firebase untuk Mahasiswa Kurang Mampu
        database.child("MahasiswaKurangMampu").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("MahasiswaKurangMampu", "Data received from Firebase")

                val entries = ArrayList<BarEntry>()
                val labels = ArrayList<String>()

                var index = 0f
                for (data in snapshot.children) {
                    val value = data.child("col_4").getValue(String::class.java)?.toFloat() ?: 0f
                    entries.add(BarEntry(index, value))
                    labels.add(data.child("col_1").getValue(String::class.java) ?: "Unknown")
                    Log.d("MahasiswaKurangMampu", "Key: ${data.key}, Value: $value")
                    index += 1f
                }

                if (entries.isEmpty()) {
                    Log.w("MahasiswaKurangMampu", "No data entries found")
                    return
                }

                val barDataSet = BarDataSet(entries, "Tahun")
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
                Log.d("MahasiswaKurangMampu", "Chart data set successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MahasiswaKurangMampu", "Failed to read data", error.toException())
            }
        })
    }

    private fun setupTable() {
        // Mengambil data dari Firebase untuk Mahasiswa Kurang Mampu
        database.child("MahasiswaKurangMampu").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("MahasiswaKurangMampu", "Data received from Firebase")

                tableLayout.removeAllViews()

                // Adding header row
                val headerRow = TableRow(this@MahasiswaKurangMampuActivity)
                headerRow.addView(createTextView("#", true))
                headerRow.addView(createTextView("Angkatan", true))
                headerRow.addView(createTextView("Jenjang", true))
                headerRow.addView(createTextView("Gol UKT", true))
                headerRow.addView(createTextView("Jumlah Mahasiswa", true))
                tableLayout.addView(headerRow)

                var rowIndex = 0
                for (data in snapshot.children) {
                    val row = TableRow(this@MahasiswaKurangMampuActivity)

                    val numberTextView = createTextView((rowIndex + 1).toString())
                    row.addView(numberTextView)

                    val yearTextView = createTextView(data.child("col_1").getValue(String::class.java) ?: "Unknown")
                    row.addView(yearTextView)

                    val degreeTextView = createTextView(data.child("col_2").getValue(String::class.java) ?: "Unknown")
                    row.addView(degreeTextView)

                    val categoryTextView = createTextView(data.child("col_3").getValue(String::class.java) ?: "Unknown")
                    row.addView(categoryTextView)

                    val countTextView = createTextView(data.child("col_4").getValue(String::class.java) ?: "Unknown")
                    row.addView(countTextView)

                    // Set LayoutParams
                    val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                    params.setMargins(8, 8, 8, 8)
                    numberTextView.layoutParams = params
                    yearTextView.layoutParams = params
                    degreeTextView.layoutParams = params
                    categoryTextView.layoutParams = params
                    countTextView.layoutParams = params

                    tableLayout.addView(row, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                    rowIndex++
                }

                Log.d("MahasiswaKurangMampu", "Table data set successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MahasiswaKurangMampu", "Failed to read data", error.toException())
            }
        })
    }

    private fun createTextView(text: String, isHeader: Boolean = false): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.setPadding(8, 8, 8, 8)
        if (isHeader) {
            textView.setTypeface(null, Typeface.BOLD)
        }
//    textView.setBackgroundResource(R.drawable.cell_shape) // custom drawable for cell background
        return textView
    }
}