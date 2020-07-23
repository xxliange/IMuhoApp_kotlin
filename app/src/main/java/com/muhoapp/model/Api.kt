package com.muhoapp.model

import com.muhoapp.model.domin.home.*
import com.muhoapp.model.domin.teach.NewTeachData
import com.muhoapp.model.domin.teach.SubjectTeachData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    //    fun getBannerData(@Query("device_type") device_type:Int, @Query("business_type") business_type : Int) : Observable<BaseResponse<List<BannerData>>>
    @Headers("token:web", "user-id:0")
    @GET("getBanner")
    fun getBannerData(
        @Query("device_type") device_type: Int,
        @Query("business_type") business_type: Int
    ): Call<BannerData>

    @GET("getBanner")
    fun getBannerDataOb(
        @Query("device_type") device_type: Int,
        @Query("business_type") business_type: Int
    ): Observable<BaseResponse<List<BannerData>>>

    @GET("getVipPlayer")
    fun getStarData(): Observable<BaseResponse<List<StarData>>>

    @GET("getTeachingAlbum")
    fun getPayAlbumData(): Observable<BaseResponse<List<PayAlbumData>>>

    @GET("getHotKeyWords")
    fun getSkillSort(): Observable<BaseResponse<List<SkillSortData>>>

    @GET("searchContent")
    fun getSkillContent(
        @Query("keywords") keywords: String,
        @Query("l") l: Int,
        @Query("type") type: Int
    ): Observable<BaseResponse<List<SkillContentData>>>

    @GET("getPrivateWorkouts")
    fun getPrivateTeach(@Query("l") l: Int): Observable<BaseResponse<List<PrivateTeachData>>>

    @GET("hotlist")
    fun getUpDateContent(@Query("l") l: Int): Observable<BaseResponse<List<UpdateData>>>

    @GET("getNewTeach")
    fun getNewTeachData(@Query("l") l: Int): Observable<BaseResponse<List<SubjectTeachData>>>

    @GET("getSubjectByType")
    fun getSubjectTeachData(
        @Query("l") l: Int,
        @Query("type") type: Int
    ): Observable<BaseResponse<List<SubjectTeachData>>>
}