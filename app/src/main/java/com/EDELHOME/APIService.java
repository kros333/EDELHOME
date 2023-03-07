package com.EDELHOME;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APIService
{
    @POST("addAppUser")
    Call<ResponseBody> addAppUser(@Body AppUser obj);
}
