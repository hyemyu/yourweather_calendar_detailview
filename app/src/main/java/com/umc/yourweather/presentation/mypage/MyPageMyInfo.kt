package com.umc.yourweather.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.umc.yourweather.R
import com.umc.yourweather.databinding.ActivityMyInfoBinding

class MyPageMyInfo : AppCompatActivity() {
    lateinit var binding: ActivityMyInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.flMyinfoBackbtn.setOnClickListener {
            finish()
        }

        binding.btnMyinfoPwChange.setOnClickListener {
            val mIntent = Intent(this, MyPagePwChange::class.java)
            startActivity(mIntent)
        }

        val nickname = intent.getStringExtra("nickname")
        val email = intent.getStringExtra("email")
        val platform = intent.getStringExtra("platform")

        binding.tvMyinfoNickname.text = nickname
        binding.tvMyinfoEmail.text = email

        binding.btnMyinfoNicknameChange.setOnClickListener {
            val mIntent = Intent(this, MyPageNicknameChange::class.java)
            mIntent.putExtra("nickname", nickname)

            startActivity(mIntent)
        }

        // 로그아웃
        binding.tvMyinfoLogout.setOnClickListener {
            // alertdialog_mypage_logout 프래그먼트로 이동
            val fragment = MyPageLogoutFragment()
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.total_View, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

//            // 기기에 저장된 비밀번호와 이메일 정보 삭제
//            MySharedPreferences.clearUser(this)
//            val tokenPrefs = TokenSharedPreferences(this)
//            tokenPrefs.clearTokens()
//
//            // 로그인 창으로 이동
//            val intent = Intent(this, SignIn::class.java)
//            startActivity(intent)
//            finish()
        }

        if (platform != "YOURWEATHER") {
            binding.tvMyinfoPw.text = "SNS 계정에서 변경하실 수 있습니다."
            binding.btnMyinfoPwChange.visibility = View.GONE
            binding.ivMyinfoSocialIc.visibility = View.VISIBLE
            if (platform == "GOOGLE") {
                binding.ivMyinfoSocialIc.setImageResource(R.drawable.ic_signin_naver)
            }
            if (platform == "KAKAO") {
                binding.ivMyinfoSocialIc.setImageResource(R.drawable.ic_signin_kakao)
            }
            if (platform == "NAVER") {
                binding.ivMyinfoSocialIc.setImageResource(R.drawable.ic_signin_naver)
            }
        }

        // 회원탈퇴
        binding.tvMyinfoBtnWithdraw.setOnClickListener {
            val mIntent = Intent(this, MyPageWithdraw2::class.java)
            startActivity(mIntent)
        }
    }
}
