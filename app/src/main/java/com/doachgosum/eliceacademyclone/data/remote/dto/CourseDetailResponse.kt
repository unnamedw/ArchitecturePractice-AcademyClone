package com.doachgosum.eliceacademyclone.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.doachgosum.eliceacademyclone.domain.model.CourseModel

@Keep
data class CourseDetailResponse(
    @SerializedName("course")
    val course: CourseApiModel,
    @SerializedName("course_role")
    val courseRole: Int,
    @SerializedName("course_sections")
    val courseSections: List<Any>,
    @SerializedName("has_past_course_role")
    val hasPastCourseRole: Boolean,
    @SerializedName("_result")
    val result: ResultApiModel
) {
    @Keep
    data class CourseApiModel(
        @SerializedName("agreement_info")
        val agreementInfo: AgreementInfoApiModel,
        @SerializedName("aibot_info")
        val aibotInfo: AibotInfoApiModel,
        @SerializedName("attend_info")
        val attendInfo: AttendInfoApiModel,
        @SerializedName("begin_datetime")
        val beginDatetime: Long,
        @SerializedName("class_times")
        val classTimes: List<Any>,
        @SerializedName("class_type")
        val classType: Int,
        @SerializedName("code")
        val code: String,
        @SerializedName("complete_datetime")
        val completeDatetime: Any?,
        @SerializedName("completion_info")
        val completionInfo: CompletionInfoApiModel,
        @SerializedName("course_agreed_datetime")
        val courseAgreedDatetime: Any?,
        @SerializedName("course_info_created_datetime")
        val courseInfoCreatedDatetime: Long,
        @SerializedName("course_info_id")
        val courseInfoId: Int,
        @SerializedName("course_info_user")
        val courseInfoUser: CourseInfoUserApiModel,
        @SerializedName("course_type")
        val courseType: Int,
        @SerializedName("created_datetime")
        val createdDatetime: Long,
        @SerializedName("credit")
        val credit: Any?,
        @SerializedName("description")
        val description: String,
        @SerializedName("discount_begin_datetime")
        val discountBeginDatetime: Any?,
        @SerializedName("discount_end_datetime")
        val discountEndDatetime: Any?,
        @SerializedName("discount_rate")
        val discountRate: Any?,
        @SerializedName("discount_title")
        val discountTitle: Any?,
        @SerializedName("discounted_price")
        val discountedPrice: String,
        @SerializedName("discounted_price_usd")
        val discountedPriceUsd: String,
        @SerializedName("end_datetime")
        val endDatetime: Any?,
        @SerializedName("enroll_begin_datetime")
        val enrollBeginDatetime: Long,
        @SerializedName("enroll_end_datetime")
        val enrollEndDatetime: Long,
        @SerializedName("enroll_limit_number")
        val enrollLimitNumber: Any?,
        @SerializedName("enroll_type")
        val enrollType: Int,
        @SerializedName("enrolled_role_period")
        val enrolledRolePeriod: Int,
        @SerializedName("enrolled_student_count")
        val enrolledStudentCount: Int,
        @SerializedName("enrolled_user_count")
        val enrolledUserCount: Int,
        @SerializedName("ern")
        val ern: String,
        @SerializedName("exercise_page_count")
        val exercisePageCount: Int,
        @SerializedName("faq")
        val faq: List<Any>,
        @SerializedName("head_tas")
        val headTas: List<Any>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image_file_url")
        val imageFileUrl: String,
        @SerializedName("info_summary_visibility_dict")
        val infoSummaryVisibilityDict: InfoSummaryVisibilityDictApiModel,
        @SerializedName("instructors")
        val instructors: List<Any>,
        @SerializedName("is_chat_room_disabled")
        val isChatRoomDisabled: Boolean,
        @SerializedName("is_course_library")
        val isCourseLibrary: Any?,
        @SerializedName("is_datetime_enrollable")
        val isDatetimeEnrollable: Boolean,
        @SerializedName("is_discounted")
        val isDiscounted: Boolean,
        @SerializedName("is_enroll_guest_enabled")
        val isEnrollGuestEnabled: Boolean,
        @SerializedName("is_enroll_noti_enabled")
        val isEnrollNotiEnabled: Boolean,
        @SerializedName("is_exercise_deprecated")
        val isExerciseDeprecated: Boolean,
        @SerializedName("is_free")
        val isFree: Boolean,
        @SerializedName("is_legacy_test")
        val isLegacyTest: Boolean,
        @SerializedName("is_library_material_instance_exist")
        val isLibraryMaterialInstanceExist: Boolean,
        @SerializedName("is_library_material_instance_sync_enabled")
        val isLibraryMaterialInstanceSyncEnabled: Boolean,
        @SerializedName("is_lms_course")
        val isLmsCourse: Boolean,
        @SerializedName("is_material_library")
        val isMaterialLibrary: Any?,
        @SerializedName("is_origin_library_sync_enabled")
        val isOriginLibrarySyncEnabled: Boolean,
        @SerializedName("is_post_student_email_enabled")
        val isPostStudentEmailEnabled: Boolean,
        @SerializedName("is_post_student_info_visible")
        val isPostStudentInfoVisible: Boolean,
        @SerializedName("is_post_tutor_email_enabled")
        val isPostTutorEmailEnabled: Boolean,
        @SerializedName("is_recommended")
        val isRecommended: Boolean,
        @SerializedName("last_attend_updated_date")
        val lastAttendUpdatedDate: Any?,
        @SerializedName("last_library_version_status")
        val lastLibraryVersionStatus: Any?,
        @SerializedName("last_review_status")
        val lastReviewStatus: Int,
        @SerializedName("leaderboard_ranking_type")
        val leaderboardRankingType: Any?,
        @SerializedName("lectures")
        val lectures: List<Any>,
        @SerializedName("library_access_type")
        val libraryAccessType: Any?,
        @SerializedName("library_status")
        val libraryStatus: Any?,
        @SerializedName("library_type")
        val libraryType: Int,
        @SerializedName("logo_file_url")
        val logoFileUrl: String,
        @SerializedName("modified_datetime")
        val modifiedDatetime: Long,
        @SerializedName("normal_lecture_count")
        val normalLectureCount: Int,
        @SerializedName("normal_lecture_page_count")
        val normalLecturePageCount: Int,
        @SerializedName("objective")
        val objective: List<String>,
        @SerializedName("organization_id")
        val organizationId: Int,
        @SerializedName("origin_course")
        val originCourse: OriginCourseApiModel,
        @SerializedName("origin_library_synced_datetime")
        val originLibrarySyncedDatetime: Long,
        @SerializedName("origin_library_version_name")
        val originLibraryVersionName: Int,
        @SerializedName("period")
        val period: Int,
        @SerializedName("preference")
        val preference: PreferenceApiModel,
        @SerializedName("price")
        val price: String,
        @SerializedName("price_usd")
        val priceUsd: String,
        @SerializedName("product_uid")
        val productUid: String,
        @SerializedName("promote_video_url")
        val promoteVideoUrl: Any?,
        @SerializedName("release_datetime")
        val releaseDatetime: Long,
        @SerializedName("short_description")
        val shortDescription: String,
        @SerializedName("status")
        val status: Int,
        @SerializedName("subscription_level")
        val subscriptionLevel: Any?,
        @SerializedName("syllabus_url")
        val syllabusUrl: Any?,
        @SerializedName("taglist")
        val taglist: List<String>,
        @SerializedName("tags")
        val tags: List<Any>,
        @SerializedName("target_audience")
        val targetAudience: List<TargetAudienceApiModel>,
        @SerializedName("tas")
        val tas: List<Any>,
        @SerializedName("test_lecture")
        val testLecture: Any?,
        @SerializedName("test_lecture_count")
        val testLectureCount: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("total_video_duration")
        val totalVideoDuration: Int,
        @SerializedName("tracks")
        val tracks: List<TrackApiModel>,
        @SerializedName("version")
        val version: Int
    ) {
        @Keep
        data class AgreementInfoApiModel(
            @SerializedName("description")
            val description: String,
            @SerializedName("is_enabled")
            val isEnabled: Boolean,
            @SerializedName("title")
            val title: String
        )

        @Keep
        data class AibotInfoApiModel(
            @SerializedName("is_enabled")
            val isEnabled: Boolean
        )

        @Keep
        data class AttendInfoApiModel(
            @SerializedName("active_begin_date")
            val activeBeginDate: String,
            @SerializedName("active_end_date")
            val activeEndDate: String,
            @SerializedName("check_in_begin_time")
            val checkInBeginTime: String,
            @SerializedName("check_in_end_time")
            val checkInEndTime: String,
            @SerializedName("check_out_begin_time")
            val checkOutBeginTime: String,
            @SerializedName("check_out_end_time")
            val checkOutEndTime: String,
            @SerializedName("is_enabled")
            val isEnabled: Boolean,
            @SerializedName("required_stay_seconds")
            val requiredStaySeconds: Int
        )

        @Keep
        data class CompletionInfoApiModel(
            @SerializedName("condition")
            val condition: ConditionApiModel,
            @SerializedName("unit")
            val unit: UnitApiModel
        ) {
            @Keep
            data class ConditionApiModel(
                @SerializedName("is_enabled")
                val isEnabled: Boolean,
                @SerializedName("progress")
                val progress: Int,
                @SerializedName("score")
                val score: Int
            )

            @Keep
            data class UnitApiModel(
                @SerializedName("is_enabled")
                val isEnabled: Boolean,
                @SerializedName("value")
                val value: Int
            )
        }

        @Keep
        data class CourseInfoUserApiModel(
            @SerializedName("firstname")
            val firstname: String,
            @SerializedName("fullname")
            val fullname: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("lastname")
            val lastname: String,
            @SerializedName("profile_url")
            val profileUrl: String
        )

        @Keep
        data class InfoSummaryVisibilityDictApiModel(
            @SerializedName("class_times")
            val classTimes: Boolean,
            @SerializedName("class_type")
            val classType: Boolean,
            @SerializedName("completion_condition")
            val completionCondition: Boolean,
            @SerializedName("completion_unit")
            val completionUnit: Boolean,
            @SerializedName("enrolled_student_count")
            val enrolledStudentCount: Boolean,
            @SerializedName("exercise_page_count")
            val exercisePageCount: Boolean,
            @SerializedName("lecture_page_access_period")
            val lecturePageAccessPeriod: Boolean,
            @SerializedName("level")
            val level: Boolean,
            @SerializedName("period")
            val period: Boolean,
            @SerializedName("programming_language")
            val programmingLanguage: Boolean,
            @SerializedName("total_video_duration")
            val totalVideoDuration: Boolean
        )

        @Keep
        data class OriginCourseApiModel(
            @SerializedName("id")
            val id: Int,
            @SerializedName("organization")
            val organization: OrganizationApiModel,
            @SerializedName("title")
            val title: String
        ) {
            @Keep
            data class OrganizationApiModel(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("name_short")
                val nameShort: String
            )
        }

        @Keep
        data class PreferenceApiModel(
            @SerializedName("live_streaming")
            val liveStreaming: Boolean,
            @SerializedName("tab_menus_visibility")
            val tabMenusVisibility: TabMenusVisibilityApiModel
        ) {
            @Keep
            data class TabMenusVisibilityApiModel(
                @SerializedName("boards")
                val boards: Boolean,
                @SerializedName("configs")
                val configs: Boolean,
                @SerializedName("dashboard")
                val dashboard: Boolean,
                @SerializedName("leaderboard")
                val leaderboard: Boolean,
                @SerializedName("lectureroom")
                val lectureroom: Boolean,
                @SerializedName("lectures")
                val lectures: Boolean,
                @SerializedName("libraries")
                val libraries: Boolean,
                @SerializedName("manage")
                val manage: Boolean,
                @SerializedName("members")
                val members: Boolean,
                @SerializedName("section_schedule")
                val sectionSchedule: Boolean,
                @SerializedName("sections")
                val sections: Boolean,
                @SerializedName("tutoring")
                val tutoring: Boolean
            )
        }

        @Keep
        data class TargetAudienceApiModel(
            @SerializedName("description")
            val description: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("title")
            val title: String
        )

        @Keep
        data class TrackApiModel(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        )
    }

    @Keep
    data class ResultApiModel(
        @SerializedName("reason")
        val reason: Any?,
        @SerializedName("status")
        val status: String
    )
}

fun CourseDetailResponse.CourseApiModel.toDomainModel(): CourseModel {
    return CourseModel(
        id = id,
        title = title,
        imgUrl = imageFileUrl,
        shortDescription = shortDescription,
        description = description,
        tags = taglist
    )
}