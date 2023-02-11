package com.livingtechusa.gotjokes.ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.R
import com.livingtechusa.gotjokes.data.database.entity.JokeEntity
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildViewModel

const val CONFIRM_DELETE_LIST = "ConfirmDeleteListDialog"

@Composable
fun ConfirmDeleteListDialog(
    joke: JokeEntity
) {
    val back = LocalOnBackPressedDispatcherOwner.current
    val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)

    Column {
        val openDialog = remember { mutableStateOf(true) }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = {
                    Text(text = stringResource(R.string.confirm_to_delete))
                },
                text = {
                    Text(
                        text = stringResource((
                            R.string.do_you_want_to_delete_this_meme),
                            joke.caption
                        )
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            buildViewModel.onTriggerEvent(BuildEvent.Delete(joke))
                            openDialog.value = false
                           // back?.onBackPressedDispatcher?.onBackPressed()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.delete),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                           // back?.onBackPressedDispatcher?.onBackPressed()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    }
}