package ge.herpi.itbookstore.saves.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.herpi.itbookstore.R
import ge.herpi.itbookstore.common.base.BaseFragment
import ge.herpi.itbookstore.databinding.FragmentSavedBooksBinding
import ge.herpi.itbookstore.main.domain.model.Book
import ge.herpi.itbookstore.main.presentation.BookAdapter

@AndroidEntryPoint
class SavedBooksFragment : BaseFragment<FragmentSavedBooksBinding>() {

    private val viewModel: SavedBooksViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter(::onBookClicked)

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSavedBooksBinding = FragmentSavedBooksBinding.inflate(inflater)

    override fun setup(view: View, savedInstanceState: Bundle?) {
        binding.rvSavedBooks.adapter = adapter
        binding.btnClear.setOnClickListener { viewModel.clear() }
        binding.EmptyWishlist.setOnClickListener { findNavController().navigate(R.id.navHome) }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.btnClear.isInvisible = it.isEmpty()
            binding.EmptyWishlist.isVisible = it.isEmpty()
            binding.Wishlist.isVisible = it.isNotEmpty()
            adapter.submitList(it.map { i -> i.toBook() })
        }
    }

    private fun onBookClicked(book: Book) {
        val action =
            SavedBooksFragmentDirections.actionSavedBooksFragmentToBookDetailsFragment(book.isbn13)
        findNavController().navigate(action)
    }
}