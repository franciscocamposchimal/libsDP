package com.digitalpersona.onetouch.capture.event;

import java.util.EventObject;



























public class DPFPReaderStatusEvent
  extends EventObject
{
  private static final long serialVersionUID = -9091864641778426131L;
  private int status;
  public static final int READER_DISCONNECTED = 2;
  public static final int READER_CONNECTED = 3;
  
  public DPFPReaderStatusEvent(String paramString, int paramInt)
  {
    super(paramString);
    if ((paramInt != 3) && (paramInt != 2))
      throw new IllegalArgumentException("readerStatus");
    status = paramInt;
  }
  






  public int getReaderStatus()
  {
    return status;
  }
}
