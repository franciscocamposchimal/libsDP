package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import java.util.EventObject;



class FingerTogglelEvent
  extends EventObject
{
  private final int ID;
  private final DPFPFingerIndex finger;
  public static final int FINGER_REMOVED = 0;
  public static final int FINGER_ADDED = 1;
  
  FingerTogglelEvent(Object paramObject, int paramInt, DPFPFingerIndex paramDPFPFingerIndex)
  {
    super(paramObject);
    ID = paramInt;
    finger = paramDPFPFingerIndex;
  }
  
  public int getID() {
    return ID;
  }
  
  public DPFPFingerIndex getFinger() {
    return finger;
  }
}
