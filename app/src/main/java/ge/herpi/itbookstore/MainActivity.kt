package ge.herpi.itbookstore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.herpi.itbookstore.common.base.BaseActivity
import ge.herpi.itbookstore.databinding.ActivityMainBinding
import ge.herpi.itbookstore.main.presentation.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            this.setKeepOnScreenCondition { viewModel.isLoading.value == true }
        }
        super.onCreate(savedInstanceState)
    }

    override fun setup(savedState: Bundle?) {
        binding.bottomNavView.setupWithNavController(findNavController(R.id.navFragment))
    }
}