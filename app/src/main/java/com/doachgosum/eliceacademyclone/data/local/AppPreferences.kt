package com.doachgosum.eliceacademyclone.data.local
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.doachgosum.eliceacademyclone.constant.LogTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * TODO(Migrate to Datastore)
 * **/
@SuppressLint("ApplySharedPref")
class AppPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        APP_PREFERENCE_NAME, Context.MODE_PRIVATE
    )

    private val _courseIdFlow: MutableStateFlow<Set<Int>> = MutableStateFlow(getMyCourseIds())
    val courseIdFlow: Flow<Set<Int>> get() = _courseIdFlow

    companion object {
        private const val APP_PREFERENCE_NAME = "SharedPreferences"

        private const val KEY_MY_COURSE_IDS = "key_my_course_ids"
    }

    fun getMyCourseIds(): Set<Int> {
        return (prefs.getStringSet(KEY_MY_COURSE_IDS, emptySet())
            ?.map { it.toInt() }
            ?.toSet()
            ?: emptySet())
                .also {
                    Log.d(LogTag.TAG_DEBUG, it.toString())
                }
    }

    fun setMyCourseIds(courseIds: Set<Int>) {
        val newCourseIds = courseIds
            .map { it.toString() }
            .toSet()

        prefs.edit {
            putStringSet(KEY_MY_COURSE_IDS, newCourseIds)
            commit()
        }.also {
            _courseIdFlow.value = courseIds
        }
    }

}