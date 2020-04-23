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
