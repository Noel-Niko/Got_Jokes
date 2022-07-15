package com.livingtechusa.gotjokes.ui.build

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.livingtechusa.gotjokes.R
import com.livingtechusa.gotjokes.data.api.model.DadJokes
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.ui.theme.JokesTheme


@Composable
fun BuildScreen() {
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val caption by buildViewModel.caption.collectAsState()
    val image by buildViewModel.imageUrl.collectAsState()
    val yoMamma by buildViewModel.yoMamma.collectAsState()
    val randomFact by buildViewModel.randomFact.collectAsState()
    val dadJoke by buildViewModel.dadJoke.collectAsState()
    val advice by buildViewModel.advice.collectAsState()
    val chuckNorrisJoke by buildViewModel.chuckNorrisJoke.collectAsState()
    val catFact by buildViewModel.catFact.collectAsState()
    val dogFact by buildViewModel.dogFact.collectAsState()

    val scaffoldState = rememberScaffoldState()

    JokesTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(25.dp),
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End),
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.wrapContentWidth(align = Alignment.Start),
                            onClick = {
                                buildViewModel.onTriggerEvent(BuildEvent.GetNewImgFlipImage)
                            }) {
                            Icon(Icons.Filled.Refresh, "refresh")
                        }
                        IconButton(
                            modifier = Modifier.wrapContentWidth(align = Alignment.End),
                            onClick = {
                                buildViewModel.onTriggerEvent(BuildEvent.Save(image.toString(), caption))
                            }) {
                            Icon(Icons.Default.Build, "save") //painter = painterResource(id = R.drawable.ic_save_24px),
                        }
                    },
                    title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(align = Alignment.CenterHorizontally),
                            text = "Got Jokes?"
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 10.dp

                )
            }
        ) { padding ->
            // BuildLayout(buildViewModel,Modifier.padding(padding), image, yoMamma)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                //TODO: reduce to one randomly selected picture
                // add placeholders for the text
                //animate the progress icon to be 3 dots moving
                if (image == null && yoMamma.joke == null) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = ((LocalConfiguration.current.screenHeightDp) / 3).dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                } else {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            Spacer(Modifier.height(16.dp))
                            if (image != null) {
                                MemeImgCard(url = image!!)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = caption,
                                onValueChange = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(it))
                                },
                                label = { Text("Caption: What's your best idea?") }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = AnnotatedString("Convert Caption to Yoda Speak"),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.ConvertToYodaSpeak(caption))
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Bad jokes...",
                                fontWeight = FontWeight.Bold
                            )
                            // YoMamma Joke
                            Spacer(modifier = Modifier.height(16.dp))
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = AnnotatedString(yoMamma.joke ?: "YoMamma is unavaliable now."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(yoMamma.joke.toString()))
                                }
                            )
                            // Dad Joke
                            Spacer(modifier = Modifier.height(16.dp))
                            val dadjoke = if(!dadJoke.attachments.isEmpty() ) dadJoke.attachments.get(0).text else null
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = AnnotatedString( dadjoke ?: "Don't tell your momma, but Dad's off line now.."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(dadJoke.attachments.get(0).text))
                                }
                            )
                            // Chuck Norris Joke
                            Spacer(modifier = Modifier.height(16.dp))
                            val chuckNorris = if( chuckNorrisJoke.value.isEmpty().not()) chuckNorrisJoke.value else null
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = AnnotatedString(chuckNorris ?: "Nuttin here 'bout Chuck'."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(chuckNorrisJoke.value))
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Good advice...",
                                fontWeight = FontWeight.Bold
                            )
                            // Advice
                            Spacer(modifier = Modifier.height(16.dp))
                            val adviceString: String? = if(advice.slip.advice.isEmpty().not()) advice.slip.advice else null
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = AnnotatedString(adviceString ?: "No advice is sometimes the best."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(adviceString ?: "No advice is sometimes the best."))
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Did you know...?",
                                fontWeight = FontWeight.Bold
                            )
                            // Random fact
                            Spacer(modifier = Modifier.height(16.dp))
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = AnnotatedString(randomFact.text ?: "Nuttin ta see here."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(randomFact.text))
                                }
                            )
                            // Cat Fact
                            Spacer(modifier = Modifier.height(16.dp))
                            val cats = if(catFact.fact.isEmpty().not()) catFact.fact else null
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = AnnotatedString(cats ?: "All out of cat facts."),
                                onClick = {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(catFact.fact))
                                }
                            )
                            // Dog Fact
                            Spacer(modifier = Modifier.height(16.dp))
                            val dogs = if(dogFact.facts.isEmpty().not()) dogFact.facts[0] else null
                            ClickableText(
                                modifier = Modifier.fillMaxWidth(),
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


@Composable
fun MemeImgCard(url: String) {
    val imagePainter = rememberImagePainter(
        data = url,
        builder = {
            allowHardware(false)
        }
    )
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(16.dp)) {
        Box {
            Image(
                painter = imagePainter,
                contentDescription = "Random Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )
        }
    }

}