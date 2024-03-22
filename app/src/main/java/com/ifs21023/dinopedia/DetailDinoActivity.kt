package com.ifs21023.dinopedia

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ifs21023.dinopedia.databinding.ActivityDetailBinding

class DetailDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var dino: Dinosaurus? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.ivGambar.setImageResource(dino.gambar1)
        binding.ivGambar.setImageResource(dino.gambar2)
        binding.ivGambar.setImageResource(dino.gambar3)
        binding.ivGambar.setImageResource(dino.gambar4)
        binding.ivGambar.setImageResource(dino.gambar5)
        binding.tvNamaDino.text = dino.namadino1
        binding.tvNamaDino.text = dino.namadino2
        binding.tvNamaDino.text = dino.namadino3
        binding.tvNamaDino.text = dino.namadino4
        binding.tvNamaDino.text = dino.namadino5
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