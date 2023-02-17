package com.example.edgedetectionandroidapp

import android.content.Intent
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity2 : FlutterActivity()  {
    private val platform = "com.example.metafy_edge_detection_module"

    private lateinit var channel: MethodChannel

    private var token = mapOf(
        "token" to "1234"
    )
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d("FlutterActivity", "configureFlutterEngine---->>>>>")
        channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, platform)

        channel.setMethodCallHandler { call, result ->
            if (call.method == "sendSettings") {
                result.success(token)
            }

            if (call.method == "getImageUrl") {
                var arguments = call.arguments() as Map<String, String>?
                val url = arguments!!["url"]
                Log.d("FlutterActivity", "native side url ---->>>>> $url")
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java,
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        }
    }
}