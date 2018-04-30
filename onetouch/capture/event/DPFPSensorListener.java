package com.digitalpersona.onetouch.capture.event;

import java.util.EventListener;

public abstract interface DPFPSensorListener
  extends EventListener
{
  public abstract void fingerTouched(DPFPSensorEvent paramDPFPSensorEvent);
  
  public abstract void fingerGone(DPFPSensorEvent paramDPFPSensorEvent);
  
  public abstract void imageAcquired(DPFPSensorEvent paramDPFPSensorEvent);
}
