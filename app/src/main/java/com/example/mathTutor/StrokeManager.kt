package com.example.mathTutor

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.common.MlKitException
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.vision.digitalink.*


object StrokeManager:AppCompatActivity() {
    private var inkBuilder = Ink.builder()
    private var strokeBuilder = Ink.Stroke.builder()
    private lateinit var model: DigitalInkRecognitionModel
    var mytext = "1"

    fun getText(): String{
        return "$mytext"
    }

    fun addNewTouchEvent(event: MotionEvent) {
        val action = event.actionMasked
        val x = event.x
        val y = event.y
        val t = System.currentTimeMillis()

        when (action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> strokeBuilder.addPoint(
                Ink.Point.create(
                    x,
                    y,
                    t
                )
            )
            MotionEvent.ACTION_UP -> {
                strokeBuilder.addPoint(Ink.Point.create(x, y, t))
                inkBuilder.addStroke(strokeBuilder.build())
                strokeBuilder = Ink.Stroke.builder()

            }
            else -> {
                // Action not relevant for ink construction
            }
        }
    }

    fun recognize(context: Context) {
        val recognizer: DigitalInkRecognizer =
            DigitalInkRecognition.getClient(
                DigitalInkRecognizerOptions.builder(model).build()
            )

        val ink = inkBuilder.build()
        // helps improve accuracy for recognizing numbers
//        var preContext : String = "0123";
//        val recognitionContext : RecognitionContext =
//            RecognitionContext.builder()
//                .setPreContext(preContext)
//                .build()

        recognizer.recognize(ink)
            .addOnSuccessListener { result: RecognitionResult ->

                mytext = result.candidates[0].text
                var i = 0
                //ensures prediction is an integer
                while (mytext.toIntOrNull() == null){
                    i++;
                    mytext = result.candidates[i].text
                    Log.e("Result", "prediction error: $mytext")
                }

            }
            .addOnFailureListener { e: Exception ->
                Log.e("StrokeManager", "Error during recognition: $e")
            }
    }

    fun clear() {
        inkBuilder = Ink.builder()
    }

    fun download() {
        var modelIdentifier: DigitalInkRecognitionModelIdentifier? = null
        try {
            modelIdentifier =
                DigitalInkRecognitionModelIdentifier.fromLanguageTag("en")
        } catch (e: MlKitException) {
            // language tag failed to parse, handle error.
        }

        model =
            DigitalInkRecognitionModel.builder(modelIdentifier!!).build()

        val remoteModelManager = RemoteModelManager.getInstance()
        remoteModelManager.download(model, DownloadConditions.Builder().build())
            .addOnSuccessListener {
                Log.i("StrokeManager", "Model downloaded")
            }
            .addOnFailureListener { e: Exception ->
                Log.e("StrokeManager", "Error while downloading a model: $e")
            }
    }
}