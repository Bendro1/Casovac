package com.example.casovac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.casovac.databinding.ActivityMainBinding
import java.util.Locale
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var zaciatokVSekundach : Long = 10
    private var casovacBezi = false
    private var casovac : CountDownTimer?=null
    private var zvysneSekundy = zaciatokVSekundach

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.casTxt.text= java.lang.String.format(Locale.getDefault(), "%02d:%02d",(zaciatokVSekundach/60),(zaciatokVSekundach%60))
        binding.resetCasovacBtn.visibility= View.GONE

        binding.spustitCasovacBtn.setOnClickListener{
            if(!casovacBezi){
                casovacBezi=true
                spustitCasovac()
                binding.spustitCasovacBtn.text = "PAUSE"
            }
            else{
                casovacBezi=false
                casovac?.cancel()
                binding.spustitCasovacBtn.text= "START"
            }
        }
        binding.resetCasovacBtn.setOnClickListener {
            resetCasovac()
        }
    }
    private fun spustitCasovac(){
        binding.resetCasovacBtn.visibility = View.VISIBLE
        casovac = object : CountDownTimer(zvysneSekundy*1000, 1000){
            override fun onTick(milisUntilFinis: Long) {
                binding.casTxt.text = java.lang.String.format(Locale.getDefault(),"%02d:%02d",(milisUntilFinis/1000)/60,(milisUntilFinis/1000)%60)
                zvysneSekundy = milisUntilFinis/1000
            }

            override fun onFinish() {
                binding.casTxt.text = "00:00"
            }

        }
        casovac?.start()

    }
    private fun resetCasovac(){
        binding.resetCasovacBtn.visibility=View.GONE
        casovacBezi = false
        casovac?.cancel()
        zvysneSekundy = zaciatokVSekundach
        binding.casTxt.text = zvysneSekundy.toString()
        binding.spustitCasovacBtn.text = "START"
    }
}