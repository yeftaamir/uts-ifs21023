package com.ifs21023.dinopedia

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.ifs21023.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var dino: Dinosaurus? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSelengkapnya = findViewById<Button>(R.id.btnSelengkapnya)
        btnSelengkapnya.setOnClickListener {
            // Buat Intent untuk memulai DinoDetailActivity
            val intent = Intent(this, DetailDinoActivity::class.java)
            intent.putExtra(EXTRA_DINO, dino)
            // Mulai DinoDetailActivity
            startActivity(intent)
        }

        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO,
                Dinosaurus::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Film ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }


    }
    private fun loadData(dino: Dinosaurus) {
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
        const val EXTRA_DINO = "extra_dino"
    }
}