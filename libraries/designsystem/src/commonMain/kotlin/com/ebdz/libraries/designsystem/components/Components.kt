package com.ebdz.libraries.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * [DefaultFullscreenContent] containing an icon and a text showing some full screen information.
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
 * [TitleWithString] is a title text style.
 */
@Composable
fun TitleWithString(header: String) {
    Text(
        text = header,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

/**
 * [ImageIconWithContentDescriptor] icon with content descriptor
 */
@Composable
fun ImageIconWithContentDescriptor(
    icon: ImageVector,
    iconContentDescription: String,
    iconColor: Color
) {
    Icon(
        imageVector = icon,
        contentDescription = iconContentDescription,
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
 * @param backContentDescription accessibility label for the back button
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(onUpPress: () -> Unit, backContentDescription: String) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onUpPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = backContentDescription
                )
            }
        }
    )
}

/**
 * [AddFloatingButton] Floating Action button do add new elements.
 *
 * @param contentDescription string to describe the add button
 * @param onClick function to be called on the click
 */
@Composable
fun AddFloatingButton(
    contentDescription: String,
    onClick: () -> Unit
) {
    FloatingActionButton(containerColor = MaterialTheme.colorScheme.primary, onClick = onClick) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = contentDescription
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
