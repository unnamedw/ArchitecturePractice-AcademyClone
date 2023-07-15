package com.doachgosum.eliceacademyclone.data.remote.request_param

import com.google.gson.annotations.SerializedName

data class FilterConditionRequestParam(
    @SerializedName("course_ids") val courseIds: List<Int>
)
