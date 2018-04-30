package com.digitalpersona.onetouch.capture.event;

import com.digitalpersona.onetouch.DPFPSample;
import java.util.EventObject;













public class DPFPDataEvent
  extends EventObject
{
  private static final long serialVersionUID = 6480620350988030513L;
  private final DPFPSample sample;
  
  public DPFPDataEvent(String paramString, DPFPSample paramDPFPSample)
  {
    super(paramString);
    sample = paramDPFPSample;
  }
  




  public DPFPSample getSample()
  {
    return sample;
  }
}
