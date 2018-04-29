package com.digitalpersona.onetouch.ui.swing;

import java.util.EventListener;

abstract interface CaptureListener
  extends EventListener
{
  public abstract void fingerCaptured(CaptureEvent paramCaptureEvent);
  
  public abstract void fingerCancelled(CaptureEvent paramCaptureEvent);
  
  public abstract void captureStopped();
}
