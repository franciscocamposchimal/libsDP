package com.digitalpersona.onetouch.capture.event;

import java.util.EventListener;

public abstract interface DPFPImageQualityListener
  extends EventListener
{
  public abstract void onImageQuality(DPFPImageQualityEvent paramDPFPImageQualityEvent);
}
