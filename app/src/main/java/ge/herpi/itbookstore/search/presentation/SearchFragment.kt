package ge.herpi.itbookstore.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.herpi.itbookstore.common.base.BaseFragment
import ge.herpi.itbookstore.databinding.FragmentSearchBinding
import ge.herpi.itbookstore.main.domain.model.Book
import ge.herpi.itbookstore.search.data.paging.SearchPagingAdapter

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private val adapter: SearchPagingAdapter = SearchPagingAdapter(::onBookClicked)

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

    override fun setup(view: View, savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        binding.rvSearchResults.adapter = adapter
        binding.etSearch.doOnTextChanged { text, _, _, _ -> viewModel.searchWithQuery("$text") }
    }

    override fun onStart() {
        super.onStart()

        viewModel.searchData.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun onBookClicked(book: Book) {
        val action = SearchFragmentDirections.actionSearchFragmentToBookDetailsFragment(book.isbn13)
        findNavController().navigate(action)
    }
}