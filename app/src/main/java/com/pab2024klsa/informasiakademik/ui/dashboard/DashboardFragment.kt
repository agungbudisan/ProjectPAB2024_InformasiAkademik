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
import com.pab2024klsa.informasiakademik.ui.detail.PembayaranDetailActivity

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
//        view.findViewById<Button>(R.id.btn_nilai_akademik).setOnClickListener {
//            findNavController().navigate(R.id.action_dashboardFragment_to_nilaiAkademikFragment)
//        }
        view.findViewById<Button>(R.id.btn_nilai_akademik).setOnClickListener {
            val intent = Intent(activity, NilaiAkademikActivity::class.java)
            startActivity(intent)
        }
//
//        view.findViewById<Button>(R.id.btn_mahasiswa_aktif).setOnClickListener {
//            findNavController().navigate(R.id.action_dashboardFragment_to_mahasiswaAktifFragment)
//        }
//
//        view.findViewById<Button>(R.id.btn_mahasiswa_kurang_mampu).setOnClickListener {
//            findNavController().navigate(R.id.action_dashboardFragment_to_mahasiswaKurangMampuFragment)
//        }
        view.findViewById<Button>(R.id.btn_mahasiswa_kurang_mampu).setOnClickListener {
            val intent = Intent(activity, MahasiswaKurangMampuActivity::class.java)
            startActivity(intent)
        }
//
//        view.findViewById<Button>(R.id.btn_analisis_pembayaran).setOnClickListener {
//            findNavController().navigate(R.id.action_dashboardFragment_to_analisisPembayaranFragment)
//        }
        view.findViewById<Button>(R.id.btn_analisis_pembayaran).setOnClickListener {
            val intent = Intent(activity, PembayaranDetailActivity::class.java)
            startActivity(intent)
        }
//
//        view.findViewById<Button>(R.id.btn_ace).setOnClickListener {
//            findNavController().navigate(R.id.action_dashboardFragment_to_aceFragment)
//        }

        return view
    }
}
