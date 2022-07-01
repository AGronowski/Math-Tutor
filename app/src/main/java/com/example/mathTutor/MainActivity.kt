package com.example.mathTutor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var int1 = 2
        var int2 = 5
        var equation = "  $int1 + $int2"
        var addition = true
        resultText.text = equation + " =  "

        var int1_2 = 3
        var int2_2 = 6
        var equation2 = "  $int1_2 + $int2_2"
        var addition2 = true
        resultTextTwo.text = equation2 + " =  "

        var int1_3 = 2
        var int2_3 = 2
        var equation3 = "  $int1_3 + $int2_3"
        var addition3 = true
        resultTextThree.text = equation3 + " =  "

        StrokeManager.download()
        StrokeManagerTwo.download()
        StrokeManagerThree.download()


        recognizeButton.setOnClickListener {

            val s  = StrokeManager.recognize(this)
            val d = StrokeManagerTwo.recognize(this)
            val f = StrokeManagerThree.recognize(this)

            val timer = object: CountDownTimer(100, 100) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    // Text 1
                    val recognized = StrokeManager.getText()


                    if (addition) {
                        if (int1 + int2 == recognized.toInt()) {
                            resultText.setTextColor(getResources().getColor(R.color.green))
                            resultText.text = equation + " = " + recognized
                        } else {
                            resultText.setTextColor(getResources().getColor(R.color.red))
                            resultText.text = equation + " ≠ " + recognized
                        }
                    }else{
                        if (int1 - int2 == recognized.toInt()) {
                            resultText.setTextColor(getResources().getColor(R.color.green))
                            resultText.text = equation + " = " + recognized
                        } else {
                            resultText.setTextColor(getResources().getColor(R.color.red))
                            resultText.text = equation + " ≠ " + recognized
                        }
                    }

                    // Text 2
                    val recognized2 = StrokeManagerTwo.getText()

                    if (addition2) {
                        if (int1_2 + int2_2 == recognized2.toInt()) {
                            resultTextTwo.setTextColor(getResources().getColor(R.color.green))
                            resultTextTwo.text = equation2 + " = " + recognized2
                        } else {
                            resultTextTwo.setTextColor(getResources().getColor(R.color.red))
                            resultTextTwo.text = equation2 + " ≠ " + recognized2
                        }
                    }else{
                        if (int1_2 - int2_2 == recognized2.toInt()) {
                            resultTextTwo.setTextColor(getResources().getColor(R.color.green))
                            resultTextTwo.text = equation2 + " = " + recognized2
                        } else {
                            resultTextTwo.setTextColor(getResources().getColor(R.color.red))
                            resultTextTwo.text = equation2 + " ≠ " + recognized2
                        }
                    }

                    // Text 3
                    val recognized3 = StrokeManagerThree.getText()


                    if (addition3) {
                        if (int1_3 + int2_3 == recognized3.toInt()) {
                            resultTextThree.setTextColor(getResources().getColor(R.color.green))
                            resultTextThree.text = equation3 + " = " + recognized3
                        } else {
                            resultTextThree.setTextColor(getResources().getColor(R.color.red))
                            resultTextThree.text = equation3 + " ≠ " + recognized3
                        }
                    }else{
                        if (int1_3 - int2_3 == recognized3.toInt()) {
                            resultTextThree.setTextColor(getResources().getColor(R.color.green))
                            resultTextThree.text = equation3 + " = " + recognized3
                        } else {
                            resultTextThree.setTextColor(getResources().getColor(R.color.red))
                            resultTextThree.text = equation3 + " ≠ " + recognized3
                        }
                    }


                    val timer2 = object: CountDownTimer(4000, 4000){
                        override fun onTick(millisUntilFinished: Long) {}

                        override fun onFinish() {
                            // Result 1
                            int1 = (1..20).random()
                            int2 = (1..20).random()


                            if (int1 > int2 ){
                                addition = false
                                equation = "  $int1 - $int2"
                            }else{
                                addition = true
                                equation = "  $int1 + $int2"
                            }
                            resultText.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused))
                            resultText.text = equation + " = "

                            // Result 2
                            int1_2 = (1..20).random()
                            int2_2 = (1..20).random()


                            if (int1_2 > int2_2 ){
                                addition2 = false
                                equation2 = "  $int1_2 - $int2_2"
                            }else{
                                addition2 = true
                                equation2 = "  $int1_2 + $int2_2"
                            }

                            resultTextTwo.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused))
                            resultTextTwo.text = equation2 + " = "

                            // Result 3
                            int1_3 = (1..20).random()
                            int2_3 = (1..20).random()


                            if (int1_3 > int2_3 ){
                                addition3 = false
                                equation3 = "  $int1_3 - $int2_3"
                            }else{
                                addition3 = true
                                equation3 = "  $int1_3 + $int2_3"
                            }

                            resultTextThree.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused))
                            resultTextThree.text = equation3 + " = "
                        }
                    }
                    timer2.start()

                }
            }
            timer.start()

            drawingViewXML.clear()
            drawingViewXMLTwo.clear()
            drawingViewXMLThree.clear()
            StrokeManager.clear()
            StrokeManagerTwo.clear()
            StrokeManagerThree.clear()
        }

    }
}