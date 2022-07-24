package com.livingtechusa.gotjokes.ui.display

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.livingtechusa.gotjokes.R
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildScreen
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.MemeImgCard
import com.livingtechusa.gotjokes.ui.theme.JokesTheme

@Composable
fun DisplayScreenLandscape() {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        DisplayScreen()
    } else {
        val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
        val caption by buildViewModel.caption.collectAsState()
        val image by buildViewModel.imageUrl.collectAsState()
        val scrollState = rememberScrollState()
        val maxHeight = configuration.screenHeightDp

        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            if (image == null) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                item {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            //  Spacer(Modifier.height(16.dp))
                            if (image != null) {
                                MemeImgCard(url = image!!)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .verticalScroll(scrollState)
                                .align(alignment = Alignment.CenterVertically)

                        ) {
                            Text(
                                modifier = Modifier
                                    .width(maxHeight.dp)
                                    .wrapContentWidth(),
                                text = caption
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.Save)
                                },
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                            ) {
                                Text(stringResource(R.string.save))
                            }
                        }
                    }
                }
            }
        }
    }
}