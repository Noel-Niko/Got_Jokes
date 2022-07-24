package com.livingtechusa.gotjokes.ui.build

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.ui.components.MemeImgCard


@Composable
fun BuildScreenLandscape() {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        BuildScreen()
    } else {
        val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
        val caption by buildViewModel.caption.collectAsState()
        val image by buildViewModel.imageUrl.collectAsState()
        val yoMamma by buildViewModel.yoMamma.collectAsState()
        val randomFact by buildViewModel.randomFact.collectAsState()
        val dadJoke by buildViewModel.dadJoke.collectAsState()
        val advice by buildViewModel.advice.collectAsState()
        val jokeApiJoke by buildViewModel.jokeApiJoke.collectAsState()
        val catFact by buildViewModel.catFact.collectAsState()
        val dogFact by buildViewModel.dogFact.collectAsState()

        val scrollState = rememberScrollState()

        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            if (image == null && yoMamma.joke == null) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                item {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            if (image != null) {
                                MemeImgCard(url = image!!)
                            }
                        }
                        Column(
                            modifier = Modifier.verticalScroll(scrollState)
                        ){
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = caption,
                                onValueChange = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(it))
                                },
                                label = { Text("Caption: What's your best idea?") }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            ClickableText(
                                modifier = Modifier
                                    .wrapContentWidth(),
                                text = AnnotatedString("Convert Caption to Yoda Speak"),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.ConvertToYodaSpeak(caption))
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Bad jokes...",
                                fontWeight = FontWeight.Bold
                            )
                            // YoMamma Joke
                            Spacer(modifier = Modifier.width(16.dp))
                            ClickableText(
                                modifier = Modifier.wrapContentWidth(),
                                text = AnnotatedString(yoMamma.joke ?: "YoMamma is unavaliable now."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(yoMamma.joke.toString()))
                                }
                            )
                            // Dad Joke
                            Spacer(modifier = Modifier.width(16.dp))
                            val dadjoke = if (!dadJoke.attachments.isEmpty()) dadJoke.attachments.get(0).text else null
                            ClickableText(
                                modifier = Modifier.wrapContentWidth(),
                                text = AnnotatedString(dadjoke ?: "Don't tell your momma, but Dad's off line now.."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(dadJoke.attachments.get(0).text))
                                }
                            )
                            // Joke
                            Spacer(modifier = Modifier.width(16.dp))
                            val jokeApiJokeValue = if (jokeApiJoke.joke.isEmpty().not()) jokeApiJoke.joke else null
                            ClickableText(
                                modifier = Modifier.wrapContentWidth(),
                                text = AnnotatedString(jokeApiJokeValue ?: "Nuttin here ta laugh about!'"),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(jokeApiJokeValue.toString()))
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Good advice...",
                                fontWeight = FontWeight.Bold
                            )
                            // Advice
                            Spacer(modifier = Modifier.width(16.dp))
                            val adviceString: String? = if (advice.slip.advice.isEmpty().not()) advice.slip.advice else null
                            ClickableText(
                                modifier = Modifier.wrapContentWidth(),
                                text = AnnotatedString(adviceString ?: "No advice is sometimes the best."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(adviceString ?: "No advice is sometimes the best."))
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Did you know...?",
                                fontWeight = FontWeight.Bold
                            )
                            // Random fact
                            Spacer(modifier = Modifier.width(16.dp))
                            ClickableText(
                                modifier = Modifier.wrapContentWidth(),
                                text = AnnotatedString(randomFact.text ?: "Nuttin ta see here."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(randomFact.text))
                                }
                            )
                            // Cat Fact
                            Spacer(modifier = Modifier.width(16.dp))
                            val cats = if (catFact.fact.isEmpty().not()) catFact.fact else null
                            ClickableText(
                                modifier = Modifier.wrapContentWidth(),
                                text = AnnotatedString(cats ?: "All out of cat facts."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(catFact.fact))
                                }
                            )
                            // Dog Fact
                            Spacer(modifier = Modifier.width(16.dp))
                            val dogs = if (dogFact.facts.isEmpty().not()) dogFact.facts[0] else null
                            ClickableText(
                                modifier = Modifier.wrapContentWidth(),
                                text = AnnotatedString(dogs ?: "All out of dog gone info."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(dogFact.facts[0]))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
