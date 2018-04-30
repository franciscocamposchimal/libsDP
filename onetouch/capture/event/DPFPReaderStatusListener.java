package com.digitalpersona.onetouch.capture.event;

import java.util.EventListener;

public abstract interface DPFPReaderStatusListener
  extends EventListener
{
  public abstract void readerConnected(DPFPReaderStatusEvent paramDPFPReaderStatusEvent);
  
  public abstract void readerDisconnected(DPFPReaderStatusEvent paramDPFPReaderStatusEvent);
}
