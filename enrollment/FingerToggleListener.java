package com.digitalpersona.onetouch.ui.swing;

import java.util.EventListener;

abstract interface FingerToggleListener
  extends EventListener
{
  public abstract void fingerAdded(FingerTogglelEvent paramFingerTogglelEvent);
  
  public abstract void fingerRemoved(FingerTogglelEvent paramFingerTogglelEvent);
}
