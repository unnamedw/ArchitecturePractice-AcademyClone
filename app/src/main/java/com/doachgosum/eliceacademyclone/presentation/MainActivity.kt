package com.doachgosum.eliceacademyclone.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.doachgosum.eliceacademyclone.R
import com.doachgosum.eliceacademyclone.data.remote.request_param.FilterConditionRequestParam
import com.doachgosum.eliceacademyclone.presentation.util.getAppContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) {
            getAppContainer().courseApi.also {
                it.getAllCourse(
                    offset = 0,
                    count = 10,
                    filterIsRecommended = true,
                    filterIsFree = false,
                    paramJson = FilterConditionRequestParam(
                        courseIds = listOf(1, 2, 3)
                    )
                )

                it.getCourseDetail(courseId = 18817)
            }

            getAppContainer().lectureApi.also {
                it.getLectureList(
                    offset = 0,
                    count = 10,
                    courseId = 18817
                )
            }
        }


    }
}