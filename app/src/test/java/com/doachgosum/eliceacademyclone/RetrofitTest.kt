package com.doachgosum.eliceacademyclone

import com.doachgosum.eliceacademyclone.data.remote.request_param.FilterConditionRequestParam
import com.doachgosum.eliceacademyclone.di.AppContainer
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RetrofitTest {

    private lateinit var container: AppContainer

    @Before
    fun setup() {
        container = AppContainer()
    }

    @Test
    fun test(): Unit = runBlocking {
        container.courseApi.getAllCourse(
            offset = 0,
            count = 10,
            filterIsRecommended = true,
            filterIsFree = false,
            paramJson = FilterConditionRequestParam(
                courseIds = listOf(1, 2, 3, 4)
            )
        ).also {
            println(it)
        }
    }

}