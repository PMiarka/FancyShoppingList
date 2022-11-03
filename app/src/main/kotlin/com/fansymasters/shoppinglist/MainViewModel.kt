package com.fansymasters.shoppinglist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    navigationReader: NavigationReader
) : ViewModel(),
    NavigationReader by navigationReader