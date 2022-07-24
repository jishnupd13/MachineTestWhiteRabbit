package com.spell.machinetestwr.ui.profiledetails

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.spell.machinetestwr.Utils
import com.spell.machinetestwr.application.WRApplication
import com.spell.machinetestwr.remotemodels.UserDetailsModel
import com.spell.machinetestwr.toNormal
import com.spell.machinetestwr.ui.theme.BlackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileDetailsActivity : ComponentActivity() {

    private var userDetailsModel: UserDetailsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDetailsModel = intent.getParcelableExtra("userDetails")
        setContent {
            BlackTheme {

                    Log.e("provided strings 3002", "".toNormal())
                RootLayout()
            }
        }
    }

    @Preview(showSystemUi = true, device = Devices.PIXEL)
    @Composable
    fun RootLayout() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (layoutProfileHeader) = createRefs()

            ConstraintLayout(modifier = Modifier
                .constrainAs(layoutProfileHeader) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .background(color = Color(0xFF3A4045))) {

            }
        }
    }


}