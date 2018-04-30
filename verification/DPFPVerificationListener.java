package com.digitalpersona.onetouch.ui.swing;

import java.util.EventListener;

public abstract interface DPFPVerificationListener
  extends EventListener
{
  public abstract void captureCompleted(DPFPVerificationEvent paramDPFPVerificationEvent)
    throws DPFPVerificationVetoException;
}
