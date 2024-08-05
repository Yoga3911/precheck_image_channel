import 'platform_interface.dart';

class PrecheckImage {
  Future<String?> launchCamera() {
    return PrecheckImagePlatform.instance.launchCamera();
  }
}
