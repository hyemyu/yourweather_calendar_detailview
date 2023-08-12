package com.umc.yourweather.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.yourweather.R
import com.umc.yourweather.data.enums.PlatformType
import com.umc.yourweather.data.remote.response.BaseResponse
import com.umc.yourweather.data.remote.response.UserResponse
import com.umc.yourweather.data.service.UserService
import com.umc.yourweather.databinding.FragmentMyPageBinding
import com.umc.yourweather.di.RetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {
    lateinit var binding: FragmentMyPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.flMyPageL1.setOnClickListener {
            val mIntent = Intent(activity, MyPageMyInfo::class.java)
            startActivity(mIntent)
        }
//        binding.tvMyPageAlarm.setOnClickListener {
//            val mIntent = Intent(activity, MyPageAlarm::class.java)
//            startActivity(mIntent)
//        }
        userInfoAPI()
    }

    private fun userInfoAPI() {
        val service = RetrofitImpl.authenticatedRetrofit.create(UserService::class.java)
        val call = service.getMyPage()

        call.enqueue(object : Callback<BaseResponse<UserResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<UserResponse>>,
                response: Response<BaseResponse<UserResponse>>,
            ) {
                if (response.isSuccessful) {
                    val baseResponse = response.body()
                    if (baseResponse != null) {
                        val userResponse = baseResponse.result
                        // 받아온 userResponse 객체를 활용하여 뷰에 데이터를 설정하고 아이콘을 표시
                        if (userResponse != null) {
                            binding.tvMyPageUsername.text = userResponse.nickname
                            binding.tvMyPageEmail.text = userResponse.email
                            val platformType = PlatformType.valueOf(userResponse.platform)
                            // 플랫폼 종류에 따라 이미지 설정
                            val platformImageResId = when (platformType) {
                                PlatformType.GOOGLE -> R.drawable.ic_signin_google
                                PlatformType.KAKAO -> R.drawable.ic_signin_kakao
                                PlatformType.NAVER -> R.drawable.ic_signin_naver
                                // 필요한 경우 다른 플랫폼도 추가 가능
                                else -> R.drawable.img_yourweatherlogo
                            }
                            binding.ivMyPagePlatform.setImageResource(platformImageResId)
                        }
                    } else {
                        Log.e("Error (null)", "Response body 비었음")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("마이페이지 API Error", "Response Code: ${response.code()}, Error Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<BaseResponse<UserResponse>>, t: Throwable) {
                Log.e("마이페이지API Failure", "Error: ${t.message}", t)
            }
        })
    }
}
