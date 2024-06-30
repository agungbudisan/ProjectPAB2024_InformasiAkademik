package com.pab2024klsa.informasiakademik.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.pab2024klsa.informasiakademik.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.database.*

class KeketatanDetailActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var barChart1: BarChart
    private lateinit var database: DatabaseReference
    private lateinit var buttonLihatDetail: Button
    private lateinit var spinnerFaculty: Spinner
    private val faculties = listOf("All", "Ilmu Budaya", "Ekonomi dan Bisnis", "Sosial dan Politik", "Hukum", "Keguruan dan Ilmu Pendidikan","Matematika dan Ipa", "Kedokteran", "Pertanian", "Teknik", "Pascasarjana", "Seni Rupa dan Desain", "Keolahragaan", "Vokasi", "Psikologi", "Teknologi Informasi dan Sains Data", "Peternakan" )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keketatan_detail)

        barChart = findViewById(R.id.barChart)
        barChart1 = findViewById(R.id.barChart1)
//        buttonLihatDetail = findViewById(R.id.buttonLihatDetail)
        spinnerFaculty = findViewById(R.id.spinnerFaculty)

        database = FirebaseDatabase.getInstance().reference.child("DetailKeketatan")

        setupSpinner()

//        buttonLihatDetail.setOnClickListener {
//            val intent = Intent(this, DetailPembayaranActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, faculties)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFaculty.adapter = adapter

        spinnerFaculty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        barChart.visibility = View.VISIBLE
                        barChart1.visibility = View.GONE
                        loadChartData(barChart, "All") // Load data for All faculties
                    }

                    1 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Ilmu Budaya") // Load data for Faculty A
                    }

                    2 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Ekonomi dan Bisnis") // Load data for Faculty B
                    }

                    3 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Sosial dan Politik") // Load data for Faculty B
                    }

                    4 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Hukum") // Load data for Faculty B
                    }

                    5 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Keguruan dan Ilmu Pendidikan") // Load data for Faculty B
                    }

                    6 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Matematika dan Ipa") // Load data for Faculty B
                    }
                    7 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Kedokteran") // Load data for Faculty B
                    }
                    8 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Pertanian") // Load data for Faculty B
                    }
                    9 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Teknik") // Load data for Faculty B
                    }
                    10 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Pascasarjana") // Load data for Faculty B
                    }
                    11 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Seni Rupa dan Desain") // Load data for Faculty B
                    }
                    12 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Keolahragaan") // Load data for Faculty B
                    }
                    13 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Vokasi") // Load data for Faculty B
                    }
                    14 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Psikologi") // Load data for Faculty B
                    }
                    15 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Teknologi Informasi dan Sains Data") // Load data for Faculty B
                    }
                    16 -> {
                        barChart.visibility = View.GONE
                        barChart1.visibility = View.VISIBLE
                        loadChartData(barChart1, "Peternakan") // Load data for Faculty B
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun loadChartData(barChart: BarChart, faculty: String) {
        val databaseRef = FirebaseDatabase.getInstance().reference

        val childRef = when (faculty) {
            "All" -> databaseRef.child("DetailKeketatan")
            "Ilmu Budaya" -> databaseRef.child("DetalFacultyA")
            "Ekonomi dan Bisnis" -> databaseRef.child("KeketatanFEB")
            "Sosial dan Politik" -> databaseRef.child("KeketatanFISIP")
            "Hukum" -> databaseRef.child("KeketatanFH")
            "Keguruan dan Ilmu Pendidikan" -> databaseRef.child("KeketatanFKIP")
            "Matematika dan Ipa" -> databaseRef.child("KeketatanFMIPA")
            "Kedokteran" -> databaseRef.child("KeketatanFK")
            "Pertanian" -> databaseRef.child("KeketatanFP")
            "Teknik" -> databaseRef.child("KeketatanFT")
            "Pascasarjana" -> databaseRef.child("KeketatanPasca")
            "Seni Rupa dan Desain" -> databaseRef.child("KeketatanFSRD")
            "Keolahragaan" -> databaseRef.child("KeketatanFKOR")
            "Vokasi" -> databaseRef.child("KeketatanSV")
            "Psikologi" -> databaseRef.child("KeketatanFAPSI")
            "Teknologi Informasi dan Sains Data" -> databaseRef.child("KeketatanFATISDA")
            "Peternakan" -> databaseRef.child("KeketatanFAPET")
            // Add more cases for other faculties if needed
            else -> databaseRef.child("DetailKeketatan") // Default to "All" if unexpected value
        }

        childRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("DetailKeketatan", "Data received from Firebase")

                val entriesAccepted = ArrayList<BarEntry>()
                val entriesRegistered = ArrayList<BarEntry>()
                val labels = ArrayList<String>()

                var index = 0f
                for (data in snapshot.children) {
                    val accepted = data.child("acc").getValue(Long::class.java) ?: 0L
                    val registered = data.child("reg").getValue(Long::class.java) ?: 0L

                    entriesAccepted.add(BarEntry(index, accepted.toFloat()))
                    entriesRegistered.add(BarEntry(index, registered.toFloat()))
                    labels.add(data.key ?: "Unknown")
                    Log.d("DetailKeketatan", "Year: ${data.key}, Accepted: $accepted, Registered: $registered")
                    index += 1f
                }

                if (entriesAccepted.isEmpty() || entriesRegistered.isEmpty()) {
                    Log.w("DetailKeketatan", "No data entries found")
                    return
                }

                val barDataSetAccepted = BarDataSet(entriesAccepted, "Accepted")
                barDataSetAccepted.color = resources.getColor(R.color.orange) // Set color for accepted bars

                val barDataSetRegistered = BarDataSet(entriesRegistered, "Registered")
                barDataSetRegistered.color = resources.getColor(R.color.blue) // Set color for registered bars

                val barData = BarData(barDataSetAccepted, barDataSetRegistered)
                barData.barWidth = 0.45f // Set bar width

                barChart.data = barData

                val groupSpace = 0.08f
                val barSpace = 0.03f
                barChart.barData.groupBars(0f, groupSpace, barSpace) // Group the bars

                val xAxis = barChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                xAxis.granularity = 1f
                xAxis.isGranularityEnabled = true

                val leftAxis = barChart.axisLeft
                leftAxis.setDrawGridLines(false)

                val rightAxis = barChart.axisRight
                rightAxis.setDrawGridLines(false)
                rightAxis.isEnabled = false

                barChart.description.isEnabled = false
                barChart.setFitBars(true)
                barChart.invalidate() // refresh chart
                Log.d("DetailKeketatan", "Chart data set successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DetailKeketatan", "Failed to read data", error.toException())
            }
        })
    }
}
