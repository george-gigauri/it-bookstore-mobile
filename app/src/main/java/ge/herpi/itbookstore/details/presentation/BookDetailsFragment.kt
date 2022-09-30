package ge.herpi.itbookstore.details.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ge.herpi.itbookstore.R
import ge.herpi.itbookstore.common.base.BaseFragment
import ge.herpi.itbookstore.common.extension.color
import ge.herpi.itbookstore.common.extension.toast
import ge.herpi.itbookstore.databinding.FragmentBookDetailsBinding
import ge.herpi.itbookstore.details.domain.model.Pdf

@AndroidEntryPoint
class BookDetailsFragment : BaseFragment<FragmentBookDetailsBinding>() {

    private val args by navArgs<BookDetailsFragmentArgs>()
    private val viewModel: BookDetailsViewModel by activityViewModels()
    private val pdfAdapter: PdfUrlAdapter = PdfUrlAdapter(this::onPdfClicked)

    override fun getBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentBookDetailsBinding = FragmentBookDetailsBinding.inflate(inflater)

    override fun setup(view: View, savedInstanceState: Bundle?) {
        setOnClickListeners()
        binding.rvPdfUrls.adapter = pdfAdapter
    }

    private fun setOnClickListeners() {
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        binding.btnSave.setOnClickListener { onSaveClicked() }
    }

    override fun onStart() {
        super.onStart()

        viewModel.load(args.isbn13)
        viewModel.data.observe(this) {
            binding.ivBookCover.load(it.image)
            binding.tvYear.text = it.year
            binding.tvTotalPages.text = it.pages
            binding.tvRating.text = it.rating
            binding.tvBookTitle.text = it.title
            binding.tvBookTitleMain.text = it.title
            binding.tvBookAuthor.text = "By: ${it.authors}"
            binding.tvPrice.text = it.price
            binding.tvDescription.text = it.desc
            pdfAdapter.submitList(it.pdf)
        }

        viewModel.isSaved.observe(this) {
            binding.btnSave.setImageResource(
                if (it) R.drawable.ic_vuesax_bold_archive_tick
                else R.drawable.ic_vuesax_outline_archive_add
            )

            binding.btnSave.setColorFilter(
                if (it) color(R.color.red) else color(R.color.lighter_gray)
            )
        }

        viewModel.isLoading.observe(this) {
            binding.btnSave.isEnabled = !it
        }

        viewModel.errorMessage.observe(this) {
            if (!it.isNullOrEmpty()) {
                toast(it)
            }
        }
    }

    private fun onPdfClicked(pdf: Pdf) {
        Intent(Intent.ACTION_VIEW).apply {
            data = pdf.url.toUri()
        }.also {
            startActivity(it)
        }
    }

    private fun onSaveClicked() {
        val isSaved = viewModel.isSaved.value
        val book = viewModel.data.value
        if (isSaved == true) book?.let { viewModel.remove(it) } else book?.let { viewModel.save(it) }
    }
}