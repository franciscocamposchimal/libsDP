package com.digitalpersona.onetouch.capture.event;

import java.util.EventListener;

public abstract interface DPFPDataListener
  extends EventListener
{
  public abstract void dataAcquired(DPFPDataEvent paramDPFPDataEvent);
}
