package com.livingtechusa.gotjokes.ui.build

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.R
import com.livingtechusa.gotjokes.ui.components.DisplayImgCard


@Composable
fun BuildScreen() {
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        if (image == null && yoMamma.joke == null) {
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Spacer(Modifier.height(16.dp))
                    if (image != null) {
                        DisplayImgCard(url = image!!)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = caption,
                        onValueChange = {
                            buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(it))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = AnnotatedString(
                            text = stringResource(R.string.convert_caption_to_yoda_speak)
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(BuildEvent.ConvertToYodaSpeak(caption))
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.bad_jokes),
                        fontWeight = FontWeight.Bold,
                    )
                    // YoMamma Joke
                    Spacer(modifier = Modifier.height(16.dp))
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = AnnotatedString(
                            yoMamma.joke ?: stringResource(R.string.yomamma_is_unavailable_now)
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(yoMamma.joke.toString()))
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    // Dad Joke
                    Spacer(modifier = Modifier.height(16.dp))
                    val dadjoke =
                        if (!dadJoke.attachments.isEmpty()) dadJoke.attachments.get(0).text else null
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = AnnotatedString(
                            dadjoke
                                ?: stringResource(R.string.don_t_tell_your_momma_but_dad_s_off_line_now)
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(
                                BuildEvent.UpdateCaption(
                                    dadJoke.attachments.get(
                                        0
                                    ).text
                                )
                            )
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    // Joke
                    Spacer(modifier = Modifier.height(16.dp))
                    val jokeApiJokeValue =
                        if (jokeApiJoke.joke.isEmpty().not()) jokeApiJoke.joke else null
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = AnnotatedString(
                            jokeApiJokeValue
                                ?: stringResource(R.string.nuttin_here_ta_laugh_about)
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(
                                BuildEvent.UpdateCaption(
                                    jokeApiJokeValue.toString()
                                )
                            )
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.good_advice),
                        fontWeight = FontWeight.Bold
                    )
                    // Advice
                    Spacer(modifier = Modifier.height(16.dp))
                    val adviceString: String =
                        if (advice.slip.advice.isEmpty()
                                .not()
                        ) advice.slip.advice else stringResource(R.string.no_advice_is_sometimes_the_best)
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = AnnotatedString(
                            adviceString
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(
                                BuildEvent.UpdateCaption(
                                    adviceString
                                )
                            )
                        },
                        style = MaterialTheme.typography.bodySmall
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
                        text = AnnotatedString(
                            randomFact.text ?: stringResource(R.string.nuttin_ta_see_here)
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(randomFact.text))
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    // Cat Fact
                    Spacer(modifier = Modifier.height(16.dp))
                    val cats = if (catFact.fact.isEmpty().not()) catFact.fact else null
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = AnnotatedString(
                            cats ?: stringResource(R.string.all_out_of_cat_facts)
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(catFact.fact))
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    // Dog Fact
                    Spacer(modifier = Modifier.height(16.dp))
                    val dogs = if (dogFact.facts.isEmpty().not()) dogFact.facts[0] else null
                    ClickableText(
                        modifier = Modifier.fillMaxWidth(),
                        text = AnnotatedString(
                            dogs ?: stringResource(R.string.all_out_of_dog_gone_info)
                        ),
                        onClick = {
                            buildViewModel.onTriggerEvent(BuildEvent.UpdateCaption(dogFact.facts[0]))
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

