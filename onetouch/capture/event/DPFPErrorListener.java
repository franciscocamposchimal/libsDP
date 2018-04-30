package com.digitalpersona.onetouch.capture.event;

import java.util.EventListener;

public abstract interface DPFPErrorListener
  extends EventListener
{
  public abstract void errorOccured(DPFPErrorEvent paramDPFPErrorEvent);
  
  public abstract void exceptionCaught(DPFPErrorEvent paramDPFPErrorEvent);
}
