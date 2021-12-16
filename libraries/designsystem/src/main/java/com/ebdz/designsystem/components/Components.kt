package com.ebdz.designsystem.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebdz.designsystem.R
import com.ebdz.designsystem.Theme

/**
 * [DefaultFullscreenContent] containing a icon and a text showing some full screen information.
 * Component usually used for error, info or empty list screens.
 *
 * @param imageIconWithContentDescriptor composable icon vector which should be displayed
 * @param title composable title component
 * @param modifier modifier to be set
 */
@Composable
fun DefaultFullscreenContent(
    imageIconWithContentDescriptor: @Composable (() -> Unit),
    title: @Composable (() -> Unit),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageIconWithContentDescriptor.invoke()
        Spacer(modifier = Modifier.height(24.dp))
        title.invoke()
    }
}

/**
 * [TitleWithString] it's a text with string :)
 */
@Composable
fun TitleWithString(@StringRes header: Int) {
    Text(
        text = stringResource(id = header),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSecondary,
    )
}

/**
 * [ImageIconWithContentDescriptor] icon with content descriptor
 */
@Composable
fun ImageIconWithContentDescriptor(
    icon: ImageVector,
    @StringRes iconContentDescription: Int,
    iconColor: Color,
) {
    Icon(
        imageVector = icon,
        contentDescription = stringResource(id = iconContentDescription),
        modifier = Modifier.size(64.dp),
        tint = iconColor
    )
}

/**
 * Basic [LoadingContent] screen to be used when the screen is loading, making the transition smoother.
 */
@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), content = {})
}

/**
 * [Toolbar] is a TopAppBar for screens that need a back button.
 *
 * @param onUpPress function to be called when the back/up button is clicked
 */
@Composable
fun Toolbar(onUpPress: () -> Unit) {
    TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
        IconButton(onClick = onUpPress, modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(id = R.string.back_arrow_content_description)
            )
        }
    }
}

/**
 * [AddFloatingButton] Floating Action button do add new elements.
 *
 * @param contentDescription string resource to describe the add button
 * @param onClick function to be called on the click
 */
@Composable
fun AddFloatingButton(
    @StringRes contentDescription: Int,
    onClick: () -> Unit
) {
    FloatingActionButton(backgroundColor = MaterialTheme.colors.primary, onClick = onClick) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = stringResource(id = contentDescription)
        )
    }
}

/**
 * [InputTextField] is TextField input for forms.
 *
 * @param label text field label
 * @param text text to be shown
 * @param onTextChange function to update text
 * @param modifier text field modifier
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputTextField(
    label: String,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        label = { Text(text = label) },
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        modifier = modifier
    )
}

@Suppress("UndocumentedPublicFunction")
@Preview(showBackground = false)
@Composable
fun FullContent() {
    Theme {
        DefaultFullscreenContent(
            imageIconWithContentDescriptor = {
                ImageIconWithContentDescriptor(
                    icon = Icons.Outlined.Error,
                    iconContentDescription = -1,
                    iconColor = MaterialTheme.colors.error
                )
            },
            title = {
                TitleWithString(R.string.error_description)
            }
        )
    }
}
