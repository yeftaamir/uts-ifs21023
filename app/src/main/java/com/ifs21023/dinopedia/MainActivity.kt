package com.ifs21023.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.ifs21023.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataDinosaurus = ArrayList<Dinosaurus>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinosaurus.setHasFixedSize(false)
        dataDinosaurus.addAll(getListDinosaurus())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDinosaurus(): ArrayList<Dinosaurus> {
        val dataName =
            resources.getStringArray(R.array.nama_famili)
        val dataIcon =
            resources.obtainTypedArray(R.array.gambar_dino)
        val dataDescription =
            resources.getStringArray(R.array.deskripsi_dino)
        val dataPeriod =
            resources.getStringArray(R.array.periode_hidup)
        val dataCharater =
            resources.getStringArray(R.array.karakter_fisik)
        val dataHabit =
            resources.getStringArray(R.array.habitat_lingkungan_hidup)
        val dataPerilaku =
            resources.getStringArray(R.array.perilaku_dino)
        val dataKlasifikasi =
            resources.getStringArray(R.array.klasifikasi_dino)

        val listDinosaurus = ArrayList<Dinosaurus>()
        for (i in dataName.indices) {
            val dino = Dinosaurus(
                dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],
                dataPeriod[i], dataCharater[i], dataHabit[i], dataPerilaku[i], dataKlasifikasi[i]
            )
            listDinosaurus.add(dino)
        }
        return listDinosaurus
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
        ) {
            binding.rvDinosaurus.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinosaurus.layoutManager =
                LinearLayoutManager(this)
        }
        val listFilmAdapter = ListDinoAdapter(dataDinosaurus)
        binding.rvDinosaurus.adapter = listFilmAdapter
        listFilmAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinosaurus) {
                showSelectedFilm(data)
            }
        })
    }

    private fun showSelectedFilm(dino: Dinosaurus) {
        val intentWithData = Intent(
            this@MainActivity,
            DetailActivity::class.java
        )
        intentWithData.putExtra(DetailActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

    lateinit var topAppBar: MaterialToolbar

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_menu -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
}