package com.example.project

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PenilaianAdapter(
    private val penilaianList: List<Penilaian>,
    private val onItemClick: (Penilaian) -> Unit
) : RecyclerView.Adapter<PenilaianAdapter.PenilaianViewHolder>() {

    class PenilaianViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noTextView: TextView = view.findViewById(R.id.textNo)
        val namaTextView: TextView = view.findViewById(R.id.textNama)
        val lihatButton: Button = view.findViewById(R.id.buttonLihat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenilaianViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itempenilaian, parent, false)
        return PenilaianViewHolder(view)
    }

    override fun onBindViewHolder(holder: PenilaianViewHolder, position: Int) {
        val penilaian = penilaianList[position]
        holder.noTextView.text = (position + 1).toString()
        holder.namaTextView.text = penilaian.nama
        holder.lihatButton.setOnClickListener {
            // Mengirimkan data ke EvaluationActivity saat tombol "Lihat" diklik
            val context = holder.itemView.context
            val intent = Intent(context,EvaluationActivity::class.java)
            intent.putExtra("NAMA_DOKTER", penilaian.nama)
            intent.putExtra("EVALUASI", "Evaluasi untuk ${penilaian.nama}")
            intent.putExtra("SARAN", "Saran perbaikan untuk ${penilaian.nama}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return penilaianList.size
    }
}

