package com.example.thinkzy.fragments

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.BinderThread
import com.example.thinkzy.R
import com.example.thinkzy.databinding.FragmentSpinBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class SpinFragment : Fragment() {

    private lateinit var spinSound: MediaPlayer
    private lateinit var winSound: MediaPlayer


    private lateinit var binding : FragmentSpinBinding
    private val spinnerOptions = arrayOf("$50", "$500", " ", "$50 ","Try Again","$10","Try Again","$10")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSpinBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun showBurstConfetti() {
        binding.konfettiView.start(
            Party(
                angle = 0,
                spread = 360,
                speed = 20f,
                maxSpeed = 30f,
                damping = 0.9f,
                size = listOf(Size.SMALL, Size.LARGE),
                timeToLive = 2000L,
                position = Position.Relative(0.5, 0.5),
                emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
            )
        )
    }


    private fun vibrateOnWin() {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(300)
        }
    }

    private fun showResult(itemTitle : String){
        Toast.makeText(requireContext(), itemTitle, Toast.LENGTH_SHORT).show()
        binding.btnSpin.isEnabled = true // Enable the button again

        if (itemTitle != "Try Again" && itemTitle != " ") {
            showBurstConfetti()
            vibrateOnWin()
            winSound.start()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dollar.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "WithdrawalFragment")
            bottomSheetDialog.enterTransition
        }
        binding.coin.setOnClickListener {
            val bottomSheetDialog : BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "WithdrawalFragment")
            bottomSheetDialog.enterTransition
        }

        spinSound = MediaPlayer.create(requireContext(), R.raw.wheel_sound)
        winSound = MediaPlayer.create(requireContext(), R.raw.win_sound)


        binding.btnSpin.setOnClickListener {
            // Prevent re-clicking while spinning
            binding.btnSpin.isEnabled = false

            val sectorCount = spinnerOptions.size
            val sectorAngle = 360f / sectorCount
            val randomIndex = Random.nextInt(sectorCount)

            // Calculate final rotation angle (e.g. 5 full rounds + sector landing)
            val fullRotations = 5
            val finalAngle = (fullRotations * 360) + (randomIndex * sectorAngle)
            // Optional: Reset wheel before spinning again
            binding.wheel.rotation = 0f

            // Start spin sound
            spinSound.start()

            // Animate the wheel
            binding.wheel.animate()
                .rotation(finalAngle)
                .setDuration(3000)
                .withEndAction {
                    // Stop and reset spin sound
                    spinSound.pause()
                    spinSound.seekTo(0)

                    winSound.start()

                    vibrateOnWin()

                    val result = spinnerOptions[randomIndex]
                    if (result != "Try Again" && result.trim().isNotEmpty()) {
                        showBurstConfetti()
                    }

                    showResult(result)

                    binding.btnSpin.isEnabled = true
                }
                .start()
        }
    }
}

// Note : The array elements should be in the same order as on wheel (in anti-clock wise)
//as the wheel spins clock wise .  !!tricky point