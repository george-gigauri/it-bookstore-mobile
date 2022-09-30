package ge.herpi.itbookstore.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import ge.herpi.itbookstore.databinding.ItemBookBinding
import ge.herpi.itbookstore.main.domain.model.Book

class BookAdapter(
    private val onClick: (Book) -> Unit
) : ListAdapter<Book, BookAdapter.VH>(DIFF_UTIL) {

    inner class VH(private val binding: ItemBookBinding) : ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.ivBookCover.load(book.image)
            binding.tvTitle.text = book.title
            binding.tvSubtitle.text = book.subtitle
            binding.tvPrice.text = book.price

            binding.root.setOnClickListener { onClick(book) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}