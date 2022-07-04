package com.livingtechusa.gotjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.livingtechusa.gotjokes.ui.build.BuildScreen
import com.livingtechusa.gotjokes.ui.theme.JokesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var app: BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BuildScreen()
                }
                //        setContentView(R.layout.activity_main)
                //        if (savedInstanceState == null) {
                //            supportFragmentManager.beginTransaction()
                //                .replace(R.id.container, BuildFragment.newInstance())
                //                .commitNow()
                //        }
            }
        }
    }
}