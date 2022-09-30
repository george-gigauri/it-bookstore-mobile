package ge.herpi.itbookstore.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    val binding: VB
        get() = _binding!!

    abstract fun getViewBinding(): VB

    @Suppress("UNCHECKED_CAST")
    private var _binding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        setup(savedInstanceState)
    }

    abstract fun setup(savedState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}