package com.digitalpersona.onetouch.jni;

public class DeviceEvent
{
  public static final int COMPLETED = 0;
  public static final int ERROR = 1;
  public static final int DISCONNECT = 2;
  public static final int RECONNECT = 3;
  public static final int SAMPLE_QUALITY = 4;
  public static final int FINGER_TOUCHED = 5;
  public static final int FINGER_GONE = 6;
  public static final int IMAGE_READY = 7;
  public static final int STOPPED = 10;
  public int nEvent;
  public int errorCode;
  public String serialNumber;
  public byte[] data;
  
  public DeviceEvent() {}
}
