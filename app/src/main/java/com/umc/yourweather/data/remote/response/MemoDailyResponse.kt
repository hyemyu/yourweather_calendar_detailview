package com.umc.yourweather.data.remote.response

import com.google.gson.annotations.SerializedName
import com.umc.yourweather.data.enums.Status

data class MemoDailyResponse(
    @SerializedName("daily_memolist")
    val memoList: List<MemoItemResponse>,
    @SerializedName("daily_memocontentlist")
    val memoContentList: List<MemoContentResponse>,
){
    data class MemoItemResponse(
        @SerializedName("memoId")
        val memoId: Int,
        @SerializedName("creationDatetime")
        val dateTime: String,
        @SerializedName("status")
        val status: com.umc.yourweather.data.enums.Status,
        @SerializedName("temperature")
        val temperature: Int,
    )


    data class MemoContentResponse(
        @SerializedName("memoId")
        val memoId: Int,
        @SerializedName("creationDatetime")
        val dateTime: String,
        @SerializedName("content")
        val status: String,
    )
}
