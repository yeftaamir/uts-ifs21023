package com.ifs21023.dinopedia

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ifs21023.dinopedia.databinding.ActivityDetailBinding

class DetilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetaildBinding
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
        binding.ivDetailIcon.setImageResource(dino.icon)
        binding.tvDetailName.text = dino.name
        binding.tvDetailDescription.text = dino.description
        binding.tvDetailPeriod.text = dino.period
        binding.tvDetailCharacter.text = dino.character
        binding.tvDetailHabit.text = dino.habit
        binding.tvDetailPerilaku.text = dino.perilaku
        binding.tvDetailKlasifikasi.text = dino.klasifikasi
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}