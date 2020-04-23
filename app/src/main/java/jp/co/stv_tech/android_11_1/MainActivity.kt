package jp.co.stv_tech.android_11_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //通知から起動したときの処理
        val fromNotification = intent.getBooleanExtra("fromNotification", false)

        if(fromNotification) {
            val btPlay = findViewById<Button>(R.id.btPlay)
            val btStop = findViewById<Button>(R.id.btStop)
            btPlay.isEnabled = false
            btStop.isEnabled = true
        }
    }

    //再生ボタン押下時
    fun onPlayButtonClick(view: View) {

        val intent = Intent(application, SoundManageService::class.java)
        startService(intent)

        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)

        btPlay.isEnabled = false
        btStop.isEnabled = true
    }

    //停止ボタン押下時
    fun onStopButtonClick(view: View) {
        val intent = Intent(applicationContext, SoundManageService::class.java)
        stopService(intent)

        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)

        btPlay.isEnabled = true
        btStop.isEnabled = false
    }
}
