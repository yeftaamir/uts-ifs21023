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
        val dataCharacter =
            resources.getStringArray(R.array.karakter_fisik)
        val dataHabit =
            resources.getStringArray(R.array.habitat_lingkungan_hidup)
        val dataPerilaku =
            resources.getStringArray(R.array.perilaku_dino)
        val dataKlasifikasi =
            resources.getStringArray(R.array.klasifikasi_dino)
        val dataGambarDino1 =
            resources.obtainTypedArray(R.array.tyrannosauridea_gambar)
        val dataGambarDino2 =
            resources.obtainTypedArray(R.array.sauropodomorpha_gambar)
        val dataGambarDino3 =
            resources.obtainTypedArray(R.array.stegosauridae_gambar)
        val dataGambarDino4 =
            resources.obtainTypedArray(R.array.hadrosauridae_gambar)
        val dataGambarDino5 =
            resources.obtainTypedArray(R.array.ceratopside_gambar)
        val dataNamaDino1 =
            resources.getStringArray(R.array.tyrannosauridea_nama)
        val dataNamaDino2 =
            resources.getStringArray(R.array.sauropodomorpha_nama)
        val dataNamaDino3 =
            resources.getStringArray(R.array.stegosauridae_nama)
        val dataNamaDino4 =
            resources.getStringArray(R.array.hadrosauridae_nama)
        val dataNamaDino5 =
            resources.getStringArray(R.array.ceratopside_nama)
        val dataDescDino1 =
            resources.getStringArray(R.array.tyrannosauridea_desc)
        val dataDescDino2 =
            resources.getStringArray(R.array.sauropodomorpha_desc)
        val dataDescDino3 =
            resources.getStringArray(R.array.stegosauridae_desc)
        val dataDescDino4 =
            resources.getStringArray(R.array.hadrosauridae_desc)
        val dataDescDino5 =
            resources.getStringArray(R.array.ceratopside_desc)

        val listDinosaurus = ArrayList<Dinosaurus>()
        for (i in dataName.indices) {
            val dino = Dinosaurus(
                dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],
                dataPeriod[i], dataCharacter[i], dataHabit[i], dataPerilaku[i], dataKlasifikasi[i], dataGambarDino1.getResourceId(i, -1),
                dataGambarDino2.getResourceId(i, -1), dataGambarDino3.getResourceId(i, -1), dataGambarDino4.getResourceId(i, -1),
                dataGambarDino5.getResourceId(i, 1), dataNamaDino1[i], dataNamaDino2[i], dataNamaDino3[i], dataNamaDino4[i],
                dataNamaDino5[i], dataDescDino1[i], dataDescDino2[i], dataDescDino3[i], dataDescDino4[i], dataDescDino5[i]
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