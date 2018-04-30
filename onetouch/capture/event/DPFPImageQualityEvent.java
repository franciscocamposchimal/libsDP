package com.digitalpersona.onetouch.capture.event;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import java.util.EventObject;













public class DPFPImageQualityEvent
  extends EventObject
{
  private static final long serialVersionUID = 7299985320705269216L;
  private final DPFPCaptureFeedback feedback;
  
  public DPFPImageQualityEvent(String paramString, DPFPCaptureFeedback paramDPFPCaptureFeedback)
  {
    super(paramString);
    feedback = paramDPFPCaptureFeedback;
  }
  




  public DPFPCaptureFeedback getFeedback()
  {
    return feedback;
  }
}
