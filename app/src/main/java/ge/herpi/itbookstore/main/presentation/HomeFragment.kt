package ge.herpi.itbookstore.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.herpi.itbookstore.R
import ge.herpi.itbookstore.common.base.BaseFragment
import ge.herpi.itbookstore.databinding.FragmentHomeBinding
import ge.herpi.itbookstore.main.domain.model.Book

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: MainViewModel by activityViewModels()
    private val adapter: BookAdapter = BookAdapter(::onBookClicked)

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun setup(view: View, savedInstanceState: Bundle?) {
        binding.rvNewBooks.adapter = adapter
        binding.btnSearch.setOnClickListener { findNavController().navigate(R.id.navSearch) }
    }

    private fun onBookClicked(book: Book) {
        val dir = HomeFragmentDirections.actionHomeFragmentToBookDetailsFragment(book.isbn13)
        findNavController().navigate(dir)
    }

    override fun onStart() {
        super.onStart()

        viewModel.errorMessage.observe(this) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.books.observe(this) {
            adapter.submitList(it)
        }
    }
}