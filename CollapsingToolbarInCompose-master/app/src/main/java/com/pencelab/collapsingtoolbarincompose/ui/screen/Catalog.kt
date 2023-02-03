package com.pencelab.collapsingtoolbarincompose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.pencelab.collapsingtoolbarincompose.R
import com.pencelab.collapsingtoolbarincompose.data.model.Animal
import com.pencelab.collapsingtoolbarincompose.ui.composables.CollapsingToolbar
import com.pencelab.collapsingtoolbarincompose.ui.composables.EagerCatalog
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.ToolbarState
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.scrollflags.EnterAlwaysCollapsedState
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.scrollflags.EnterAlwaysState
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.scrollflags.ExitUntilCollapsedState
import com.pencelab.collapsingtoolbarincompose.ui.management.states.toolbar.scrollflags.ScrollState

private val MinToolbarHeight = 96.dp
private val MaxToolbarHeight = 176.dp

@Composable
fun Catalog(
    animals: List<Animal>,
    columns: Int,
    onPrivacyTipButtonClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)
    val scrollState = rememberScrollState()

    toolbarState.scrollValue = scrollState.value

    Box(modifier = modifier) {
        EagerCatalog(
            animals = animals,
            columns = columns,
            modifier = Modifier.fillMaxSize(),
            scrollState = scrollState,
            contentPadding = PaddingValues(top = MaxToolbarHeight)
        )
        CollapsingToolbar(
            backgroundImageResId = R.drawable.toolbar_background,
            progress = toolbarState.progress,
            onPrivacyTipButtonClicked = onPrivacyTipButtonClicked,
            onSettingsButtonClicked = onSettingsButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalDensity.current) {toolbarState.height.toDp()})
                .graphicsLayer { translationY = toolbarState.offset }
        )
    }
}

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
/*    return rememberSaveable(saver = ScrollState.Saver) {
        ScrollState(toolbarHeightRange)
    }*/

/*    return rememberSaveable(saver = EnterAlwaysState.Saver) {
        EnterAlwaysState(toolbarHeightRange)
    }*/

/*    return rememberSaveable(saver = EnterAlwaysCollapsedState.Saver) {
        EnterAlwaysCollapsedState(toolbarHeightRange)
    }*/

    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(toolbarHeightRange)
    }
}
