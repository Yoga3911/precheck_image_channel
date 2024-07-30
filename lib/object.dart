
import 'platform_interface.dart';

class PrecheckImage {
  Future<String?> getPlatformVersion() {
    return PrecheckImagePlatform.instance.getPlatformVersion();
  }
}
