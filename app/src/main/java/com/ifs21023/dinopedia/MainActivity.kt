package com.ifs21023.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.ifs21023.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFamily.setHasFixedSize(false)

        val dataFamili = getListFamili()
        showRecyclerList(dataFamili)

        val btnProfile = findViewById<TextView>(R.id.about_menu)
        btnProfile.setOnClickListener{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Recycle")
    private fun getListFamili(): ArrayList<Famili> {
        val dataName = resources.getStringArray(R.array.nama_famili)
        val dataIcon = resources.obtainTypedArray(R.array.gambar_famili)
        val dataDescription = resources.getStringArray(R.array.deskripsi_famili)
        val dataPeriod = resources.getStringArray(R.array.periode_hidup)
        val dataCharacter = resources.getStringArray(R.array.karakter_fisik)
        val dataHabit = resources.getStringArray(R.array.habitat_lingkungan_hidup)
        val dataPerilaku = resources.getStringArray(R.array.perilaku_dino)
        val dataKlasifikasi = resources.getStringArray(R.array.klasifikasi_dino)
        val mulai = resources.getStringArray(R.array.start)
        val selesai = resources.getStringArray(R.array.end)


        val listFamili= ArrayList<Famili>()
        for (i in dataName.indices) {
            val famili = Famili(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataPeriod[i],
                dataCharacter[i],
                dataHabit[i],
                dataPerilaku[i],
                dataKlasifikasi[i],
                mulai[i].toInt(),
                selesai[i].toInt(),
            )
            listFamili.add(famili)
        }
        return listFamili
    }

    private fun showRecyclerList(dataFamili: ArrayList<Famili>) {
        val layoutManager = if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }

        binding.rvFamily.layoutManager = layoutManager
        val listFamilyAdapter = ListFamiliAdapter(dataFamili)
        binding.rvFamily.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object : ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                showSelectedFamili(data)
            }
        })
    }

    private fun showSelectedFamili(famili: Famili) {
        val intentWithData = Intent(
            this@MainActivity,
            DetailActivity::class.java
        )
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }

    lateinit var topAppBar: MaterialToolbar
}
