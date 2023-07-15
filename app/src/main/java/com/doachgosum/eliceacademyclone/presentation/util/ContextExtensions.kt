package com.doachgosum.eliceacademyclone.presentation.util

import android.app.Activity
import com.doachgosum.eliceacademyclone.MyApplication
import com.doachgosum.eliceacademyclone.di.AppContainer

fun Activity.getAppContainer(): AppContainer = (application as MyApplication).appContainer