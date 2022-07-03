package com.spell.machinetestwr.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.spell.machinetestwr.R
import com.spell.machinetestwr.remotemodels.UserDetailsModel
import com.spell.machinetestwr.ui.profiledetails.ProfileDetailsActivity
import com.spell.machinetestwr.ui.theme.BlackTheme
import com.spell.machinetestwr.ui.theme.fonts
import com.spell.machinetestwr.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private val userList = mutableStateListOf<UserDetailsModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackTheme {
                ConstraintLayout(list = userList)
            }
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
            title = {
                Text(
                    text = "Profiles",
                    fontFamily = fonts,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp
                )
            },
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

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = topBar.bottom,
                    bottom = parent.bottom,
                    verticalBias = 0F
                )

            }) {
            items(list) { item ->

                UserItem(item = item)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserItem(item: UserDetailsModel) {
    val context = LocalContext.current
    androidx.constraintlayout.compose.ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp)
            .clickable {

                try {
                    val intent = Intent(context, ProfileDetailsActivity::class.java)
                    intent.putExtra("userDetails", item)
                    context.startActivity(intent)
                    (context as MainActivity).overridePendingTransition(0, 0)
                } catch (e: Exception) {

                }


            }
    ) {
        val (image, textUserName, textEmail, textPhone, viewDivider) = createRefs()

        Image(
            painter = rememberImagePainter(data = item.profileImage ?: "", builder = {
                MyCustomPlaceHolder(R.drawable.placeholder)
                MyCustomPlaceHolder(R.drawable.placeholder)
            }),
            contentDescription = "profile image",
            modifier = Modifier
                .padding(start = 9.dp, top = 14.dp)
                .constrainAs(image) {
                    //  top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    linkTo(top = parent.top, bottom = parent.bottom, bias = 0F)
                }
                .size(90.dp)
                .clip(CircleShape)
        )

        Text(text = item.name ?: "", modifier = Modifier.constrainAs(textUserName) {
            height = Dimension.wrapContent
            width = Dimension.wrapContent
            top.linkTo(parent.top, margin = 14.dp)
            linkTo(
                start = image.end,
                end = parent.end,
                bias = 0F,
                startMargin = 10.dp,
                endMargin = 14.dp
            )
        }, fontFamily = fonts, fontWeight = FontWeight.Normal, fontSize = 14.sp)

        Text(text = item.email ?: "", modifier = Modifier.constrainAs(textEmail) {
            height = Dimension.wrapContent
            width = Dimension.fillToConstraints
            top.linkTo(textUserName.bottom, margin = 5.dp)
            linkTo(
                start = image.end,
                end = parent.end,
                bias = 0F,
                startMargin = 10.dp,
                endMargin = 14.dp
            )
        }, fontFamily = fonts, fontWeight = FontWeight.Light, fontSize = 14.sp)

        Text(text = item.company?.name ?: "", modifier = Modifier.constrainAs(textPhone) {
            height = Dimension.wrapContent
            width = Dimension.fillToConstraints
            top.linkTo(textEmail.bottom, margin = 5.dp)
            linkTo(
                start = image.end,
                end = parent.end,
                bias = 0F,
                startMargin = 10.dp,
                endMargin = 14.dp
            )
        }, fontFamily = fonts, fontWeight = FontWeight.Light, fontSize = 14.sp)

        Divider(
            color = Color.LightGray,
            thickness = 0.5.dp,
            modifier = Modifier.constrainAs(viewDivider) {
                top.linkTo(image.bottom, margin = 14.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end, margin = 14.dp)
                bottom.linkTo(parent.bottom)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}


@Composable
fun MyCustomPlaceHolder(@DrawableRes resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = "place holder",
        modifier = Modifier
            .size(90.dp)
            .padding(start = 9.dp, top = 14.dp)
            .clip(
                CircleShape
            ), contentScale = ContentScale.FillBounds
    )
}



