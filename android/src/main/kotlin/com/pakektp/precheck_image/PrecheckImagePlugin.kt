package com.pakektp.precheck_image

import android.app.Activity
import com.interbio.precheckimagequality.PrecheckImageQuality
import com.interbio.precheckimagequality.PrecheckImageQualityConfiguration
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class PrecheckImagePlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  private lateinit var channel : MethodChannel
  private var activity: Activity? = null
  private val token: String = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MTIxMjA3NzMsImV4cCI6MjU3NjEyMDc3MywiYXVkIjoiYWxsX2FwcHMiLCJpc3MiOiJ0cnVzdGxpbmsiLCJzdWIiOiJhbmRyb2lkLGlvcyJ9.RmX_AHUFEZB7W25wkgKur9ZfNlUSVLRjJCorRB4R12ag_CSTtwVLudRVexEUr5KuVAQFux7bxMlzbDS2cFN5-e1vPK71gnTmKTGIkMCmqhP1mjF7YqamA5RHG3mlbp3_RLvlm6eWrEoYWdEaPJZ_roirI0CiZMF7Tg0cxiNfGmIS4poO6veoLbdENTmITpalf9ybxUDcGvfdORhvyr3AtfETNJ6Huwb5ga2seY10vzAp3VYfIBZ3chKz7ExJXxvkkepPae-RHbyRPscGt7wia2HgsITaGpwFPH_Ls1TSdsyodicWTVWL5YoUmjPy_n_h21wsBTn-t_CVAgtbX9iQig"

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivity() {
    activity = null
  }

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "precheck_image")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "launchCamera") {
      launchCamera(result)
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun launchCamera(result: Result) {
    val configuration = PrecheckImageQualityConfiguration().apply {
      license = token
    }
    activity?.let {
      PrecheckImageQuality.initialize(it, configuration)
      PrecheckImageQuality.startPrecheck(it, onSuccess = { image: String? ->
        result.success(image)
      }, onError = { errorCode: Int ->
        result.error("500", errorCode.toString(), "Service Error")
      })
    } ?: run {
      result.error("500", "Activity is null", "Service Error")
    }
  }
}
