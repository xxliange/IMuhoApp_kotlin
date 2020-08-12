package com.muhoapp.model

import com.muhoapp.model.domin.home.*
import com.muhoapp.model.domin.teach.NewTeachData
import com.muhoapp.model.domin.teach.SubjectTeachData
import com.muhoapp.model.domin.video.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

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

    @GET("webHomeGraudeBySetNumber")
    fun getTeachVideoListData(@Query("sid") sid: Int): Observable<BaseResponse<List<TeachVideoListData>>>

    @GET("webHomeSubjectByRandom")
    fun getTeachVideoMoreRandom(): Observable<BaseResponse<List<TeachVideoMoreRandom>>>

    @FormUrlEncoded
    @POST("veiwVideo")
    fun getVideoView(
        @Field("cid") cid: Int,
        @Field("type") type: Int
    ): Observable<BaseResponse<String>>

    @GET("getTeachingUserInfo")
    fun getVideoCollectInfo(@Query("cid") cid: Int): Observable<BaseResponse<VideoCollectInfo>>

    @FormUrlEncoded
    @POST("mediaToCollect")
    fun getVideoUserCollect(
        @Field("cid") cid: Int,
        @Field("type") type: Int,
        @Field("c_type") c_type: Int
    ): Observable<BaseResponse<String>>

    @GET("hotDetail")
    fun getUpdateVideoInfo(@Query("cid") cid: Int): Observable<BaseResponse<HomeUpdateVideoData>>

    @GET("webHomeVideoByRandom")
    fun getUpdateMoreVideo(): Observable<BaseResponse<List<HomeUpdateVideoMoreData>>>

    @GET("getTeachingAlbumDetail")
    fun getPayAlbumListData(@Query("sid") sid: Int): Observable<BaseResponse<List<PayAlbumVideoData>>>

    @GET("getTeachingAlbumIntro")
    fun getPayAlbumInfo(@Query("id") id: Int): Observable<BaseResponse<PayAlbumInfoData>>

    @GET("getTeachingAlbumUserInfo")
    fun getPayAlbumCollect(@Query("cid") cid: Int): Observable<BaseResponse<PayAlbumVideoCollectData>>

    @GET("paidSubjectByRandom")
    fun getPayAlbumMoreData(): Observable<BaseResponse<List<PayAlbumInfoMoreData>>>
}