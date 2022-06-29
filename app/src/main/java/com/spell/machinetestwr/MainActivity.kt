package com.spell.machinetestwr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spell.machinetestwr.remotemodels.UserDetailsModel
import com.spell.machinetestwr.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

   // private val userList = arrayListOf<UserDetailsModel>()

    private val userList = mutableStateListOf<UserDetailsModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayout(list = userList)
        }

        observeUserDetails()
        observeCount()
        observeLocalUserDetails()
        viewModel.getAllDataCount()
    }

    private fun observeLocalUserDetails() {
        viewModel.userDetailsLocalLiveData.observe(this) {
            it?.let { it1 ->
                userList.addAll(it1)
            }
        }
    }

    private fun observeCount() {
        viewModel.userDetailsLiveDataCount.observe(this) {
            if ((it ?: 0) > 0) {
                viewModel.getAllData()
            } else {
                viewModel.fetchUserDetails()
            }
        }
    }


    private fun observeUserDetails() {
        viewModel.userDetailsLiveData.observe(this) {
            if (it != null) {
                Log.e("userDetails", "${it.size}")
                viewModel.insertDetails(it)
            }
        }
    }
}

@Composable
fun ConstraintLayout(list: SnapshotStateList<UserDetailsModel>) {

   androidx.constraintlayout.compose.ConstraintLayout(modifier = Modifier.fillMaxSize()) {

       val (topBar,lazyColumn) = createRefs()
       TopAppBar(
           modifier = Modifier
               .constrainAs(topBar) {
                   top.linkTo(parent.top)
                   start.linkTo(parent.start)
                   end.linkTo(parent.end)
               }

       ) {

       }

       LazyColumn(modifier = Modifier
           .constrainAs(lazyColumn) {
               top.linkTo(topBar.bottom)
               start.linkTo(parent.start)
               end.linkTo(parent.end)
           }) {
           items(list){ item->

               Text(text = "${item.name}")
           }
       }
   }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}