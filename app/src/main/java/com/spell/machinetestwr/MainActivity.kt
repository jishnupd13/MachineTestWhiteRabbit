package com.spell.machinetestwr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
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

        val (topBar, lazyColumn) = createRefs()

        TopAppBar(
            title = { Text(text = "UserList") },
            navigationIcon = null,
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        LazyColumn(modifier = Modifier
            .constrainAs(lazyColumn) {
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            items(list) { item ->

               UserItem(item = item)
            }
        }
    }
}

@Composable
fun UserItem(item: UserDetailsModel) {

    androidx.constraintlayout.compose.ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (name) = createRefs()
        Text(text = item.name ?: "", modifier = Modifier
            .constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
            .padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}