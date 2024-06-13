package com.pab2024klsa.informasiakademik.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pab2024klsa.informasiakademik.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.profileShare.setOnClickListener {
            shareText()
        }
        return view
    }

    private fun shareText() {
        // The text to share
        val textToShare = "NAMA\nNIM\nPRODI\nFAKULTAS"

        // Create the intent
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textToShare)
        }

        // Start the sharing activity
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
