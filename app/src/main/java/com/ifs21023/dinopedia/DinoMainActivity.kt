package com.ifs21023.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21023.dinopedia.databinding.ActivityDinoMainBinding

class DinoMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = ActivityDinoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        binding.rvDino.setHasFixedSize(true)

        // Set up list of Dino and show them in RecyclerView
        val dataDino = getListDino()
        showRecyclerList(dataDino)

        // Set up click listener for "About" TextView
        val btnProfile = findViewById<TextView>(R.id.about_menu)
        btnProfile.setOnClickListener{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val famili = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_FAMILI,
                Famili::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }

        val dataNamaDino = resources.getStringArray(R.array.nama_dino)
        val dataIconDino = resources.obtainTypedArray(R.array.dino_gambar)
        val dataDeskripsiDino = resources.getStringArray(R.array.deskripsi_dino)
        val dataKelompokDino = resources.getStringArray(R.array.dino_kelompok)
        val dataHabitatDino = resources.getStringArray(R.array.habitat_dino)
        val dataMakananDino = resources.getStringArray(R.array.makanan_dino)
        val dataPanjangDino = resources.getStringArray(R.array.panjang_dino)
        val dataTinggiDino = resources.getStringArray(R.array.tinggi_dino)
        val dataBobotDino = resources.getStringArray(R.array.bobot_dino)
        val dataKelemahanDino = resources.getStringArray(R.array.kelemahan_dino)


        val startIndex = famili?.mulai
        val endIndex = famili?.selesai

        val dinoList = ArrayList<Dino>()
        for (i in startIndex!!..endIndex!!) {
            val dino = Dino(
                dataNamaDino[i],
                dataIconDino.getResourceId(i, -1),
                dataDeskripsiDino[i], dataKelompokDino[i], dataHabitatDino[i],
                dataMakananDino[i],
                dataPanjangDino[i],
                dataTinggiDino[i],
                dataBobotDino[i],
                dataKelemahanDino[i]

            )
            dinoList.add(dino)
        }
        // Release the TypedArray
        dataIconDino.recycle()
        return dinoList
    }

    private fun showRecyclerList(dataDino: ArrayList<Dino>) {
        val layoutManager = if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }

        binding.rvDino.layoutManager = layoutManager
        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@DinoMainActivity, DetailDinoActivity::class.java)
        intentWithData.putExtra(EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
        const val EXTRA_DINO = "extra_dino"
    }
}
