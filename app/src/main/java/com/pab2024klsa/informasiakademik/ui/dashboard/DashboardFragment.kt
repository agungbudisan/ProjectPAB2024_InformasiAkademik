package com.pab2024klsa.informasiakademik.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pab2024klsa.informasiakademik.R
import com.pab2024klsa.informasiakademik.ui.detail.KeketatanDetailActivity
import com.pab2024klsa.informasiakademik.ui.detail.MahasiswaKurangMampuActivity
import com.pab2024klsa.informasiakademik.ui.detail.NilaiAkademikActivity
import com.pab2024klsa.informasiakademik.ui.detail.AnalisisPembayaranActivity
import com.pab2024klsa.informasiakademik.ui.detail.MahasiswaAktifActivity

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Setup button click listeners
        view.findViewById<Button>(R.id.btn_keketatan).setOnClickListener {
            val intent = Intent(activity, KeketatanDetailActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.btn_nilai_akademik).setOnClickListener {
            val intent = Intent(activity, NilaiAkademikActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.btn_mahasiswa_aktif).setOnClickListener {
            val intent = Intent(activity, MahasiswaAktifActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.btn_mahasiswa_kurang_mampu).setOnClickListener {
            val intent = Intent(activity, MahasiswaKurangMampuActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.btn_analisis_pembayaran).setOnClickListener {
            val intent = Intent(activity, AnalisisPembayaranActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
