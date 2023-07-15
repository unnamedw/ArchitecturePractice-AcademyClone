package com.doachgosum.eliceacademyclone.presentation.util

import android.app.Activity
import androidx.fragment.app.Fragment
import com.doachgosum.eliceacademyclone.MyApplication
import com.doachgosum.eliceacademyclone.di.AppContainer

fun Activity.getAppContainer(): AppContainer = (application as MyApplication).appContainer

fun Fragment.getAppContainer(): AppContainer = (requireActivity().application as MyApplication).appContainer