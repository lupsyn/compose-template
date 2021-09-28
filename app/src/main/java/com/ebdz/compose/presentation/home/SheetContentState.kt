package com.ebdz.compose.presentation.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

internal sealed class SheetContentState {

    @Parcelize
    object Empty : SheetContentState(), Parcelable

}
