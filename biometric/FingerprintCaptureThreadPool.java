package com.zkteco.biometric;

import java.util.Map;

public class FingerprintCaptureThreadPool
{
  public FingerprintCaptureThreadPool() {}
  
  private static Map<String, FingerprintCaptureThread> fingerprintCaptureThreadMap = new java.util.HashMap();
  
  public static void start(FingerprintSensor fingerprintSensor, int index) {
    String threadKey = buildKey(fingerprintSensor, index);
    if (fingerprintCaptureThreadMap.get(threadKey) == null) {
      FingerprintCaptureThread fingerprintCaptureThread = new FingerprintCaptureThread(fingerprintSensor, index);
      new Thread(fingerprintCaptureThread).start();
      fingerprintCaptureThreadMap.put(threadKey, fingerprintCaptureThread);
    } else {
      System.out.println(threadKey + " is already running, you don't need to start it once more");
    }
  }
  
  private static String buildKey(FingerprintSensor fingerprintSensor, int index) {
    return "zkfpsensor." + FingerprintSensor.class.getCanonicalName() + "." + index + "_" + fingerprintSensor.getDeviceTag();
  }
  
  public static void destroy() {
    java.util.Collection<FingerprintCaptureThread> threads = fingerprintCaptureThreadMap.values();
    java.util.Iterator<FingerprintCaptureThread> iterator = threads.iterator();
    while (iterator.hasNext()) {
      FingerprintCaptureThread thread = (FingerprintCaptureThread)iterator.next();
      thread.cancel();
    }
    fingerprintCaptureThreadMap.clear();
  }
  
  public static void cancel(FingerprintSensor fingerprintSensor, int index) {
    String threadKey = buildKey(fingerprintSensor, index);
    FingerprintCaptureThread fingerprintCaptureThread = (FingerprintCaptureThread)fingerprintCaptureThreadMap.get(threadKey);
    if (fingerprintCaptureThread != null) {
      fingerprintCaptureThread.cancel();
      fingerprintCaptureThreadMap.remove(threadKey);
    } else {
      System.out.println(threadKey + " is already cancelled or never running");
    }
  }
}
