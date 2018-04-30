package com.digitalpersona.onetouch.capture.event;

import java.util.EventObject;






























public class DPFPSensorEvent
  extends EventObject
{
  private static final long serialVersionUID = 8453369533390455940L;
  private int sensorStatus;
  public static final int FINGER_TOUCH = 5;
  public static final int FINGER_GONE = 6;
  public static final int IMAGE_READY = 7;
  
  public DPFPSensorEvent(String paramString, int paramInt)
  {
    super(paramString);
    if ((paramInt != 5) && (paramInt != 6) && (paramInt != 7))
      throw new IllegalArgumentException("sensorStatus");
    sensorStatus = paramInt;
  }
  







  public int getSensorStatus()
  {
    return sensorStatus;
  }
}
