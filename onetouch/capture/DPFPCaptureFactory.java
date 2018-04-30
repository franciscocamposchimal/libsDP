package com.digitalpersona.onetouch.capture;

public abstract interface DPFPCaptureFactory
{
  public abstract DPFPCapture createCapture();
  
  public abstract DPFPCapture createCapture(String paramString);
  
  public abstract DPFPCapture createCapture(DPFPCapturePriority paramDPFPCapturePriority);
  
  public abstract DPFPCapture createCapture(String paramString, DPFPCapturePriority paramDPFPCapturePriority);
}
