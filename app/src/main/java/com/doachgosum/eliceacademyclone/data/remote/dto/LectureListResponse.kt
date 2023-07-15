package com.doachgosum.eliceacademyclone.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LectureListResponse(
    @SerializedName("lecture_count")
    val lectureCount: Int,
    @SerializedName("lectures")
    val lectures: List<LectureApiModel>,
    @SerializedName("_result")
    val result: ResultApiModel
) {
    @Keep
    data class LectureApiModel(
        @SerializedName("cheat_info")
        val cheatInfo: Any?,
        @SerializedName("close_schedule_datetime")
        val closeScheduleDatetime: Any?,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_opened")
        val isOpened: Boolean,
        @SerializedName("is_preview")
        val isPreview: Boolean,
        @SerializedName("is_test_accessible_after_completion")
        val isTestAccessibleAfterCompletion: Boolean?,
        @SerializedName("is_test_reset_enabled_by_student")
        val isTestResetEnabledByStudent: Any?,
        @SerializedName("is_test_score_opened")
        val isTestScoreOpened: Boolean?,
        @SerializedName("lecture_group_total_point")
        val lectureGroupTotalPoint: Any?,
        @SerializedName("lecture_type")
        val lectureType: Int,
        @SerializedName("open_schedule_datetime")
        val openScheduleDatetime: Any?,
        @SerializedName("order_no")
        val orderNo: Int,
        @SerializedName("teaching_datetime")
        val teachingDatetime: Any?,
        @SerializedName("test_begin_datetime")
        val testBeginDatetime: Long?,
        @SerializedName("test_description")
        val testDescription: String?,
        @SerializedName("test_duration")
        val testDuration: Any?,
        @SerializedName("test_end_datetime")
        val testEndDatetime: Any?,
        @SerializedName("test_qualification")
        val testQualification: Any?,
        @SerializedName("test_score_open_datetime")
        val testScoreOpenDatetime: Any?,
        @SerializedName("title")
        val title: String,
        @SerializedName("total_page_count")
        val totalPageCount: Int,
        @SerializedName("total_page_point")
        val totalPagePoint: Int
    )

    @Keep
    data class ResultApiModel(
        @SerializedName("reason")
        val reason: Any?,
        @SerializedName("status")
        val status: String
    )

}