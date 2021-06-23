package com.sumeet.keyboard

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.sumeet.keyboard.databinding.FragmentKeyboardBinding
import java.text.DecimalFormat

class FragmentKeyboard : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentKeyboardBinding;
    private var formatter = DecimalFormat("#,###,##0.00");
    private var number = "";
    private val MAX_DIGITS = 10;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentKeyboardBinding.inflate(inflater, container, false);
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.keyboardView.keyboardView.forEach {
            it.setOnClickListener(this);
        }

        updatePrice()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.keyboardView.button0 -> setText("0")
            binding.keyboardView.button1 -> setText("1")
            binding.keyboardView.button2 -> setText("2")
            binding.keyboardView.button3 -> setText("3")
            binding.keyboardView.button4 -> setText("4")
            binding.keyboardView.button5 -> setText("5")
            binding.keyboardView.button6 -> setText("6")
            binding.keyboardView.button7 -> setText("7")
            binding.keyboardView.button8 -> setText("8")
            binding.keyboardView.button9 -> setText("9")
            binding.keyboardView.buttonDot -> setText(".")
            binding.keyboardView.buttonRemove -> removeDigit()

        }
    }

    private fun setText(digit: String?) {
        log("setText digit $digit : number $number")
        if (number.length >= MAX_DIGITS)
            return
        if (number.contains(".")) {
            if (digit == ".")
                return
            val index = number.indexOf(".")
            //log("setText index $index")
            if (index > 0) {
                val decimalPart = number.substring(index, number.length)
                if (decimalPart.length > 2)
                    return
            }
        }
        if (digit == "0" && number.isEmpty())
            return

        number += digit;
        updatePrice()
    }

    private fun removeDigit() {
        log("removeDigit digit $number")
        if (number.isNotEmpty())
            number = number.substring(0, number.length - 1)
        updatePrice()
    }

    private fun updatePrice() {
        log("updatePrice digit $number")
        if (number.isEmpty()) {
            binding.tvAmount.text = formatter.format(0.0)
            binding.tvAmount.setTextColor(Color.LTGRAY)
            binding.tvCurrency.setTextColor(Color.LTGRAY)
        } else {

            val spannable = SpannableString(formatter.format(number.toDoubleOrNull() ?: 0.0))
            if (!number.contains(".")) {
                var index = spannable.indexOf(".")
                if (index > 0)
                    spannable.setSpan(
                        ForegroundColorSpan(Color.LTGRAY),
                        index,
                        spannable.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
            }

            binding.tvAmount.text = spannable
            //binding.tvAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.key_selected))
            //binding.tvCurrency.setTextColor(ContextCompat.getColor(requireContext(), R.color.key_selected))
            binding.tvAmount.setTextColor(Color.BLACK)
            binding.tvCurrency.setTextColor(Color.BLACK)
        }
    }

    fun log(msg: String) {
        Log.e("FragmentKeyboard", msg)
    }
}