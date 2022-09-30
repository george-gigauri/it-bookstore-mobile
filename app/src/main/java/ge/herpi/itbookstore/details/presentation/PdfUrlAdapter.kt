package ge.herpi.itbookstore.details.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ge.herpi.itbookstore.databinding.ItemPdfUrlBinding
import ge.herpi.itbookstore.details.domain.model.Pdf

class PdfUrlAdapter(
    private val onClick: (Pdf) -> Unit
) : ListAdapter<Pdf, PdfUrlAdapter.VH>(DIFF_UTIL) {

    inner class VH(private val binding: ItemPdfUrlBinding) : ViewHolder(binding.root) {
        fun bind(pdf: Pdf) {
            binding.tvTitle.text = pdf.name
            binding.root.setOnClickListener { onClick(pdf) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemPdfUrlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Pdf>() {
            override fun areItemsTheSame(oldItem: Pdf, newItem: Pdf): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Pdf, newItem: Pdf): Boolean {
                return oldItem == newItem
            }
        }
    }
}