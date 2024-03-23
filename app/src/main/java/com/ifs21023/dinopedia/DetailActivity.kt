package com.ifs21023.dinopedia

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.ifs21023.dinopedia.DinoMainActivity.Companion.EXTRA_FAMILI
import com.ifs21023.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var famili: Famili? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan data Family dari intent
        famili = intent.getParcelableExtra(EXTRA_FAMILI)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famili != null) {
            // Mengatur judul ActionBar
            supportActionBar?.title = "Jenis Family ${famili!!.name}"
            // Memuat data family ke tampilan
            loadData(famili!!)
        } else {
            // Jika tidak ada data family, tutup activity
            finish()
        }

        // Mendapatkan referensi button dari layout
        val btnKembali: Button = findViewById(R.id.btnKembali)
        // Menambahkan listener untuk button
        btnKembali.setOnClickListener {
            // Kembali ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        // Mendapatkan referensi button dino dari layout
        val btnDino: Button = findViewById(R.id.btnSelengkapnya)
        // Menambahkan listener untuk button dino
        btnDino.setOnClickListener {
            // Kembali ke MainActivityDino
            val intent = Intent(this, DinoMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        // Menambahkan listener untuk button Dino untuk menampilkan MainActivityDino dengan data Family
        binding.btnSelengkapnya.setOnClickListener {
            val intentWithData = Intent(this@DetailActivity, DinoMainActivity::class.java)
            intentWithData.putExtra(DinoMainActivity.EXTRA_FAMILI, famili!!)
            startActivity(intentWithData)
        }
    }
    private fun loadData(dino: Famili) {
        binding.ivGambar.setImageResource(dino.icon)
        binding.tvNamaDino.text = dino.name
        binding.tvDescription.text = dino.description
        binding.tvPeriodeHidup.text = dino.period
        binding.tvDescKarakter.text = dino.character
        binding.tvDetailHabitat.text = dino.habit
        binding.tvDetailPerilaku.text = dino.perilaku
        binding.tvDetailKlasifikasi.text = dino.klasifikasi
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }
}