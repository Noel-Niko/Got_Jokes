//package com.livingtechusa.gotjokes.ui.build
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Refresh
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.State
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.platform.ComposeView
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.DefaultLifecycleObserver
//import androidx.lifecycle.LifecycleObserver
//import androidx.lifecycle.Observer
//import com.livingtechusa.gotjokes.BaseApplication
//import com.livingtechusa.gotjokes.domain.model.Joke
//import com.livingtechusa.gotjokes.domain.util.DEFAULT_IMAGE
//import com.livingtechusa.gotjokes.domain.util.LoadPicture
//import com.livingtechusa.gotjokes.ui.build.BuildEvent.*
//import com.livingtechusa.gotjokes.ui.components.BuildAJoke
//import com.livingtechusa.gotjokes.ui.components.BuildView
//import com.livingtechusa.gotjokes.ui.components.ScaffoldSample
//import com.livingtechusa.gotjokes.ui.components.ScaffoldWithTopBar
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.delay
//import javax.inject.Inject
//
//@AndroidEntryPoint
//class BuildFragment : Fragment() {
//
//    @Inject
//    lateinit var application: BaseApplication
//
//    companion object {
//        fun newInstance() = BuildFragment()
//    }
//
//    private val viewModel: BuildViewModel by viewModels()
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        return ComposeView(requireContext()).apply {
//            setContent {
//
//
//                val joke = viewModel.joke       //: Joke? by viewModel.joke.observeAsState()             //: State<Joke?> = viewModel.joke.observeAsState()
//                val loading = viewModel.loading  ///.observeAsState()
//                val scaffoldState = rememberScaffoldState()
//
//
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    scaffoldState = scaffoldState,
//                    topBar = {
//                        TopAppBar(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentWidth(Alignment.End),
//                            title = {
//                                Text(
//                                    modifier = Modifier.wrapContentWidth(align = Alignment.Start),
//                                    text = "Got Jokes?"
//                                )
//                            },
//                            navigationIcon = {
//                                IconButton(
//                                    modifier = Modifier.wrapContentWidth(align = Alignment.End),
//                                    onClick = {
//                                        viewModel.onTriggerEvent(GetNewImgFlipImage)
//                                    }) {
//                                    Icon(Icons.Filled.Refresh, "refresh")
//                                }
//                            },
//                            backgroundColor = MaterialTheme.colors.primary,
//                            contentColor = Color.White,
//                            elevation = 10.dp
//
//                        )
//                    }
//                ) {
//                    joke?.let { it1 ->
//                        BuildAJoke(
//                            loading = loading ?: false,
//                            joke = it1
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//
//}