package it.mem.marvel.data.model.entity

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("code") val code: Int?, //200
    @SerializedName("etag") val etag: String?, //8564460db2969053daea6042fb7b535a7f12351f
    @SerializedName("data") val data: Date<T>
)

