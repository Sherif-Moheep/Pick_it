package com.hitech.pickit.profile.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.profile.presentation.util.AppLanguage
import com.hitech.pickit.core.presentation.theme.PickItTheme

@Composable
fun LanguageDialog(
    openDialog: Boolean,
    currentAppLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val appLanguages = AppLanguage.entries.toTypedArray()

    if(openDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = { },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = stringResource(R.string.cancel)
                    )
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.language_icon),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = stringResource(R.string.choose_language)
                )
            },
            text = {
                Column(Modifier.selectableGroup()) {
                    appLanguages.forEach { language ->
                        LanguageRow(
                            appLanguage = language,
                            isSelected = (currentAppLanguage == language),
                            onSelected = {
                                onLanguageSelected(language)
                                onDismiss()
                            }
                        )
                    }
                }
            },
        )
    }
}

@Composable
private fun LanguageRow(
    appLanguage: AppLanguage,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = onSelected,
                role = Role.RadioButton
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Spacer(
            modifier = Modifier
                .width(16.dp)
        )
        Text(
            text = appLanguage.displayName,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
@Preview(name = "Language Dialog")
@Composable
fun LanguageDialogPreview() {
    PickItTheme {
        LanguageDialog(
            currentAppLanguage = AppLanguage.ENGLISH,
            onLanguageSelected = {},
            onDismiss = {},
            openDialog = true
        )
    }
}