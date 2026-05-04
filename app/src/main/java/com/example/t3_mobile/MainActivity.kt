package com.example.t3_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Variabel diganti semua
        val editTeksNama = findViewById<EditText>(R.id.form_nama)
        val grupJenisKelamin = findViewById<RadioGroup>(R.id.opsi_gender)
        val centangBaca = findViewById<CheckBox>(R.id.pilih_baca)
        val centangCoding = findViewById<CheckBox>(R.id.pilih_coding)
        val centangGaming = findViewById<CheckBox>(R.id.pilih_gaming)
        val tombolSubmit = findViewById<Button>(R.id.tombol_kirim)
        val outputData = findViewById<TextView>(R.id.teks_output)

        centangBaca.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Hobi membaca terpilih", Toast.LENGTH_SHORT).show()
            }
        }

        centangCoding.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Hobi coding terpilih", Toast.LENGTH_SHORT).show()
            }
        }

        centangGaming.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Hobi gaming terpilih", Toast.LENGTH_SHORT).show()
            }
        }

        tombolSubmit.setOnClickListener {
            val isiNama = editTeksNama.text.toString().trim()

            if (isiNama.isEmpty()) {
                editTeksNama.error = "Input tidak boleh kosong"
                editTeksNama.requestFocus()
                return@setOnClickListener
            }

            val idTerpilih = grupJenisKelamin.checkedRadioButtonId
            val teksKelamin = if (idTerpilih != -1) {
                val radioTerpilih = findViewById<RadioButton>(idTerpilih)
                radioTerpilih.text.toString()
            } else {
                "Belum dipilih"
            }

            val kumpulanHobi = mutableListOf<String>()
            if (centangBaca.isChecked) kumpulanHobi.add("Membaca")
            if (centangCoding.isChecked) kumpulanHobi.add("Coding")
            if (centangGaming.isChecked) kumpulanHobi.add("Gaming")

            val ringkasan = """
                Nama Lengkap: $isiNama
                Jenis Kelamin: $teksKelamin
                Hobi Anda: ${kumpulanHobi.joinToString(", ")}
            """.trimIndent()

            outputData.text = ringkasan
            outputData.setBackgroundColor(android.graphics.Color.parseColor("#DEE5FF"))
            outputData.setTypeface(null, android.graphics.Typeface.ITALIC)
            outputData.setTextColor(android.graphics.Color.parseColor("#333333"))
        }
    }
}