package com.msa.todo.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.msa.todo.R

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit,
) {
    if (openDialog) {

        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Normal
                )

            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        closeDialog()
                    })
                {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { closeDialog() })
                {
                    Text(text = stringResource(id = R.string.no))
                }
            },
            onDismissRequest = { closeDialog() }
        )


    }
}


@Composable
@Preview
private fun PreviewDialog(){
    DisplayAlertDialog(
        title = "",
        message= "",
        openDialog= true,
        closeDialog={},
        onYesClicked= {},
    )
}