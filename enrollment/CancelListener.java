package com.digitalpersona.onetouch.ui.swing;

import java.util.EventListener;

abstract interface CancelListener
  extends EventListener
{
  public abstract void enrollmentCancelled(CancelEvent paramCancelEvent);
}
