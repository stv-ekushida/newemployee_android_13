# newemployee_android_13
サービスと通知

## サービス
バックグランドで処理を実行したい場合に利用する

### ①サービスを継承したクラスを作成する
```
class SoundManageService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {    
        //バックグランドで実行する処理を記述する
        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
```




| 属性値 | 説明 |
|---|----|
|START_NOT_STICKY| サービスが強制終了しても自動で再起動しない |
|START_STICKY| サービスが強制終了した場合自動で再起動するが、インテントはNullで実行される |
|START_REDELIVER_INTENT| サービスが強制終了した場合自動で再起動するが、インテントは最後に渡されたインテントが渡される |


### ②マニフェストにサービスを登録する
[New] -> [Service] -> [Service]を選択し、Enableをtrue、Exportedをfalseにしてクラスを生成する。

```
        <service
            android:name=".SoundManageService"
            android:enabled="true"
            android:exported="false"></service>
```

| 属性値 | 説明 |
|---|----|
|android:name| 登録するサービス名 |
|android:enabled| 登録したサービスを利用するか否か？ |
|android:exprted| 登録するサービスを外部から利用可とするか？ |

### ③サービスの起動

```
        val intent = Intent(application, SoundManageService::class.java)
        startService(intent)
```        

### ④サービスの起動

```
        val intent = Intent(applicationContext, SoundManageService::class.java)
        stopService(intent)
```

## 通知

### ①通知チャネルを作成する

```
       //①通知チャネルの作成、Managerに登録
        val id = "soundmanagerservice_notification_channel"
        val name = getString(R.string.notification_channel_name)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
```

### ②通知を表示する

```
        //②通知を表示する　(Builder + Manager)
        val builder = NotificationCompat.Builder(applicationContext,
            "soundmanagerservice_notification_channel")
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
        builder.setContentText(getString(R.string.msg_notification_text_finish))

        val notification = builder.build()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, notification)
```            

## 通知からActivityを起動する場合
### PendingIntentを利用する

```
        val builder = NotificationCompat.Builder(applicationContext,
            "soundmanagerservice_notification_channel")
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
        builder.setContentTitle(getString(R.string.msg_notification_title_start))

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("fromNotification", true)

        val stopServiceIntent = PendingIntent.getActivity(applicationContext,
            0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setContentIntent(stopServiceIntent)
        builder.setAutoCancel(true)

        val notification = builder.build()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)     
```        

### Activity起動時の処理

```
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
```    





