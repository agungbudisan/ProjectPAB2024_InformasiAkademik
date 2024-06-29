package com.pab2024klsa.informasiakademik.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.pab2024klsa.informasiakademik.R
import com.google.firebase.database.*

class DetailPembayaranActivity : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pembayaran)

        tableLayout = findViewById(R.id.tableLayout)
        database = FirebaseDatabase.getInstance().reference.child("detailPembayaran")

        loadTableData()
    }

    private fun loadTableData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("DetailPembayaran", "Data received from Firebase")

                for (data in snapshot.children) {
                    val col0 = data.child("col_0").getValue(String::class.java) ?: ""
                    val col1 = data.child("col_1").getValue(String::class.java) ?: ""
                    val col2 = data.child("col_2").getValue(String::class.java) ?: ""
                    val col3 = data.child("col_3").getValue(String::class.java) ?: ""
                    val col4 = data.child("col_4").getValue(String::class.java) ?: ""

                    // Membuat baris baru
                    val tableRow = TableRow(this@DetailPembayaranActivity)
                    tableRow.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                    )

                    // Menambahkan data ke baris
                    tableRow.addView(createTextView(col0))
                    tableRow.addView(createTextView(col1))
                    tableRow.addView(createTextView(col2))
                    tableRow.addView(createTextView(col3))
                    tableRow.addView(createTextView(col4))

                    // Menambahkan baris ke tabel
                    tableLayout.addView(tableRow)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DetailPembayaran", "Failed to read data", error.toException())
            }
        })
    }

    private fun createTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.setPadding(8, 8, 8, 8)
        return textView
    }
}
