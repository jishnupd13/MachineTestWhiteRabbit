package com.spell.machinetestwr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spell.machinetestwr.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }

        observeUserDetails()
        viewModel.fetchUserDetails()
    }


    private fun observeUserDetails() {
        viewModel.userDetailsLiveData.observe(this) {
            if (it != null) {
                Log.e("userDetails","${it.size}")
                viewModel.insertDetails(it)
                viewModel.getAllData()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}