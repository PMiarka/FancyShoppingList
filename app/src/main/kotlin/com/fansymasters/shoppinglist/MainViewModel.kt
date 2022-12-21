package com.fansymasters.shoppinglist

import androidx.lifecycle.ViewModel
import com.fansymasters.shoppinglist.common.GeneralErrorHandler
import com.fansymasters.shoppinglist.common.ProgressHandler
import com.fansymasters.shoppinglist.presentation.UiEventStateReader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    navigationReader: NavigationReader,
    uiEventStateReader: UiEventStateReader,
    progressHandler: ProgressHandler,
    generalErrorHandler: GeneralErrorHandler
) : ViewModel(),
    NavigationReader by navigationReader,
    UiEventStateReader by uiEventStateReader,
    ProgressHandler by progressHandler,
    GeneralErrorHandler by generalErrorHandler