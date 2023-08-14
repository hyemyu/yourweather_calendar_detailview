
package com.umc.yourweather.presentation.weatherinput

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umc.yourweather.R
import com.umc.yourweather.databinding.FragmentInitialNoWeatherBinding
import com.umc.yourweather.di.UserSharedPreferences

class InitialNoWeatherFragment : Fragment() {
    private lateinit var binding: FragmentInitialNoWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInitialNoWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 첫회원이면 토스트메시지 띄움 NickName에서 번들 저장해서 넘겨줌
        if (arguments?.getBoolean("isSignUpUser") == true) {
            showInitialToast()
        }

        binding.btnInitialWeather.setOnClickListener {
            val newFragment = HomeWeatherInputFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_initial_l1, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        val userNickname = UserSharedPreferences.getUserNickname(requireContext())
        binding.tvInitialUsername.text = userNickname
    }

    private fun showInitialToast() {
        val toastView = layoutInflater.inflate(R.layout.toast_initial, binding.root, false)
        val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
        toast.view = toastView
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, resources.getDimensionPixelSize(R.dimen.initial_toast_margin_bottom))
        toast.show()
    }
}
