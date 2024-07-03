package com.pab2024klsa.informasiakademik.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.pab2024klsa.informasiakademik.R

class DetailPembayaranActivity : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private lateinit var tableLayoutFakultas: TableLayout
    private lateinit var spinnerFakultas: Spinner
    private lateinit var database: DatabaseReference
    private lateinit var fakultasDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pembayaran)

        tableLayout = findViewById(R.id.tableLayout)
        tableLayoutFakultas = findViewById(R.id.tableLayoutFakultas)
        spinnerFakultas = findViewById(R.id.spinnerFakultas)

        database = FirebaseDatabase.getInstance().reference.child("detailPembayaranAll")
        fakultasDatabase = FirebaseDatabase.getInstance().reference.child("detailPembayaranFakultas")

        loadTableData()
        loadFakultasData()
    }

    private fun loadTableData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("DetailPembayaran", "Data received from Firebase")

                // Add header row with styling
                val headerRow = TableRow(this@DetailPembayaranActivity)
                headerRow.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )

                headerRow.addView(createStyledTextView(getString(R.string.no), true))
                headerRow.addView(createStyledTextView(getString(R.string.profile_faculty), true))
                headerRow.addView(createStyledTextView(getString(R.string.tahun), true))
                headerRow.addView(createStyledTextView(getString(R.string.semester), true))
                headerRow.addView(createStyledTextView(getString(R.string.nominal_bayar), true))

                tableLayout.addView(headerRow)

                // Add data rows
                for (data in snapshot.children) {
                    val col0 = data.child("col_0").getValue(String::class.java) ?: ""
                    val col1 = data.child("col_1").getValue(String::class.java) ?: ""
                    val col2 = data.child("col_2").getValue(String::class.java) ?: ""
                    val col3 = data.child("col_3").getValue(String::class.java) ?: ""
                    val col4 = data.child("col_4").getValue(String::class.java) ?: ""

                    val tableRow = TableRow(this@DetailPembayaranActivity)
                    tableRow.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )

                    tableRow.addView(createStyledTextView(col0, false))
                    tableRow.addView(createStyledTextView(col1, false))
                    tableRow.addView(createStyledTextView(col2, false))
                    tableRow.addView(createStyledTextView(col3, false))
                    tableRow.addView(createStyledTextView(col4, false))

                    tableLayout.addView(tableRow)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DetailPembayaran", "Failed to read data", error.toException())
            }
        })
    }

    private fun loadFakultasData() {
        fakultasDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("DetailPembayaran", "Fakultas data received from Firebase")

                val fakultasList = mutableListOf<String>()
                for (fakultas in snapshot.children) {
                    fakultasList.add(fakultas.key ?: "")
                }

                val adapter = ArrayAdapter(
                    this@DetailPembayaranActivity,
                    android.R.layout.simple_spinner_item,
                    fakultasList
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerFakultas.adapter = adapter

                spinnerFakultas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        val selectedFakultas = parent.getItemAtPosition(position) as String
                        loadTableFakultasData(selectedFakultas)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DetailPembayaran", "Failed to read fakultas data", error.toException())
            }
        })
    }

    private fun loadTableFakultasData(fakultas: String) {
        fakultasDatabase.child(fakultas).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tableLayoutFakultas.removeAllViews()

                // Add header row with styling
                val headerRow = TableRow(this@DetailPembayaranActivity)
                headerRow.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )

                headerRow.addView(createStyledTextView(getString(R.string.no), true))
                headerRow.addView(createStyledTextView(getString(R.string.profile_prodi), true))
                headerRow.addView(createStyledTextView(getString(R.string.tahun), true))
                headerRow.addView(createStyledTextView(getString(R.string.semester), true))
                headerRow.addView(createStyledTextView(getString(R.string.nominal_bayar), true))

                tableLayoutFakultas.addView(headerRow)

                // Add data rows
                for (data in snapshot.children) {
                    val col0 = data.child("col_0").getValue(String::class.java) ?: ""
                    val col1 = data.child("col_1").getValue(String::class.java) ?: ""
                    val col2 = data.child("col_2").getValue(String::class.java) ?: ""
                    val col3 = data.child("col_3").getValue(String::class.java) ?: ""
                    val col4 = data.child("col_4").getValue(String::class.java) ?: ""

                    val tableRow = TableRow(this@DetailPembayaranActivity)
                    tableRow.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )

                    tableRow.addView(createStyledTextView(col0, false))
                    tableRow.addView(createStyledTextView(col1, false))
                    tableRow.addView(createStyledTextView(col2, false))
                    tableRow.addView(createStyledTextView(col3, false))
                    tableRow.addView(createStyledTextView(col4, false))

                    tableLayoutFakultas.addView(tableRow)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DetailPembayaran", "Failed to read fakultas data", error.toException())
            }
        })
    }

    private fun createStyledTextView(text: String, isHeader: Boolean): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.setPadding(8, 8, 8, 8)
        if (isHeader) {
            textView.setTextColor(resources.getColor(R.color.dark_blue, null))
            textView.setTypeface(null, Typeface.BOLD)
        } else {
            textView.setTextColor(resources.getColor(R.color.black, null))
        }
        return textView
    }
}
