package com.digitalpersona.onetouch.capture.event;

import com.digitalpersona.onetouch.DPFPError;
import java.util.EventObject;











public class DPFPErrorEvent
  extends EventObject
{
  private static final long serialVersionUID = 820816207862442050L;
  private final DPFPError error;
  
  public DPFPErrorEvent(String paramString, DPFPError paramDPFPError)
  {
    super(paramString);
    error = paramDPFPError;
  }
  




  public DPFPError getError()
  {
    return error;
  }
}
