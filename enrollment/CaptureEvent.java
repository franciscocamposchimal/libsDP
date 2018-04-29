package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.util.EventObject;



class CaptureEvent
  extends EventObject
{
  private int ID;
  private DPFPFingerIndex finger;
  private DPFPTemplate template;
  public static final int CANCELLED = 0;
  public static final int CAPTURED = 1;
  
  CaptureEvent(Object paramObject, int paramInt, DPFPFingerIndex paramDPFPFingerIndex, DPFPTemplate paramDPFPTemplate)
  {
    super(paramObject);
    ID = paramInt;
    finger = paramDPFPFingerIndex;
    template = paramDPFPTemplate;
  }
  
  public int getID() {
    return ID;
  }
  
  public DPFPTemplate getTemplate() {
    return template;
  }
  
  public DPFPFingerIndex getFinger() {
    return finger;
  }
}
