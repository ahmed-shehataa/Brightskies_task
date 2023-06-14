package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.animation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.common.domain.validation.InputWrapper
import com.ashehata.brightskies_task.common.domain.validation.ValidationType


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InputText(
    hint: String,
    inputWrapper: InputWrapper,
    keyboardType: KeyboardType = KeyboardType.Text,
) {

    val focusManager = LocalFocusManager.current

    val text by remember {
        inputWrapper.text
    }

    val isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val visualTransformation by remember {
        derivedStateOf {
            if (inputWrapper.validationType == ValidationType.Password && !isPasswordVisible)
                PasswordVisualTransformation()
            else VisualTransformation.None
        }
    }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(50.dp)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    1.dp, Color.Gray, MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            TextField(
                label = { Text(hint, style = MaterialTheme.typography.button) },
                value = text,
                onValueChange = {
                    inputWrapper.onValueChange(it)
                },
                textStyle = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1F),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    },
                    onDone = {
                        focusManager.clearFocus()
                    }

                ),
                visualTransformation = visualTransformation,
            )
        }

        AnimatedVisibility(
            visible = inputWrapper.isValid.value.not() && inputWrapper.text.value.isNotEmpty(),
            enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
            exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(id = inputWrapper.validationMessageResId),
                color = Color.Red,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

    }

}