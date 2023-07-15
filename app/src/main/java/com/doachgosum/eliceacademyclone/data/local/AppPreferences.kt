package com.doachgosum.eliceacademyclone.data.local
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * TODO(Migrate to Datastore)
 * **/
@SuppressLint("ApplySharedPref")
class AppPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        APP_PREFERENCE_NAME, Context.MODE_PRIVATE
    )

    companion object {
        private const val APP_PREFERENCE_NAME = "SharedPreferences"

        private const val KEY_MY_COURSE_IDS = "key_my_course_ids"
    }

    fun getMyCourseIds(): Set<Int> {
        return prefs.getStringSet(KEY_MY_COURSE_IDS, emptySet())
            ?.map { it.toInt() }
            ?.toSet()
            ?: emptySet()
    }

    fun setMyCourseIds(courseIds: Set<Int>) {
        prefs.edit {
            putStringSet(
                KEY_MY_COURSE_IDS,
                courseIds
                    .map { it.toString() }
                    .toSet()
            )
            commit()
        }
    }

}