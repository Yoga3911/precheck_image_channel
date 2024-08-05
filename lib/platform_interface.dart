import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'method_channel.dart';

abstract class PrecheckImagePlatform extends PlatformInterface {
  /// Constructs a PrecheckImagePlatform.
  PrecheckImagePlatform() : super(token: _token);

  static final Object _token = Object();

  static PrecheckImagePlatform _instance = MethodChannelPrecheckImage();

  /// The default instance of [PrecheckImagePlatform] to use.
  ///
  /// Defaults to [MethodChannelHello].
  static PrecheckImagePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [PrecheckImagePlatform] when
  /// they register themselves.
  static set instance(PrecheckImagePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> launchCamera() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
