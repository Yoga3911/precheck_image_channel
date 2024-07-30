import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:precheck_image/platform_interface.dart';


/// An implementation of [PrecheckImagePlatform] that uses method channels.
class MethodChannelPrecheckImage extends PrecheckImagePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('precheck_image');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
