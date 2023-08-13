package com.umc.yourweather.util

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.umc.yourweather.R
import com.umc.yourweather.util.CalendarUtils.Companion.dpToPx

class SignUtils {
    companion object {
        const val KAKAOTAG = "카카오소셜로그인"
        const val NAVERTAG = "네이버소셜로그인"
        const val ALERT_TEXT_SIGN_IN = "이메일 또는 비밀번호를 다시 확인해주세요"
        const val ALERT_TEXT_FIND_PW = "이메일을 다시 확인해주세요"
        const val ALERT_TEXT_FIND_PW_EMAIL = "인증코드가 일치하지 않습니다."

        fun isValidPassword(password: String): Boolean {
            val passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$"
            return password.matches(passwordPattern.toRegex())
        }

//        fun changeVisible(visibility: Int): Int {
//            return if (visibility == View.VISIBLE) {
//                View.INVISIBLE
//            } else {
//                View.VISIBLE
//            }
//        }

        fun customSingInPopupWindow(context: Context, text: String, parentView: View, button: AppCompatButton) {
            val popupView =
                LayoutInflater.from(context).inflate(R.layout.toast_signin, null)

            var textViewMessage = popupView.findViewById<TextView>(R.id.tv_signin_toast)
            textViewMessage.text = text

            val width = parentView.width - dpToPx(context, 60)
            val height = dpToPx(context, 46)

            var popupWindow = PopupWindow(popupView, width, height, true)

            popupWindow.isOutsideTouchable = true

            popupWindow.showAsDropDown(button, (button.width - width) / 2, dpToPx(context, 19)) // 버튼 아래로 19dp 떨어진 위치에 표시
            val durationInMillis = 3000 // 팝업 윈도우가 보여지는 시간 (3초)

            // Handler를 사용하여 일정 시간 후에 팝업 윈도우를 닫는 작업 예약
            val handler = Handler()
            handler.postDelayed({
                popupWindow.dismiss() // 지정된 시간 후에 팝업 윈도우를 닫음
            }, durationInMillis.toLong())
        }
    }
}
