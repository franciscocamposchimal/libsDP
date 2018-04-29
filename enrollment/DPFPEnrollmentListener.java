package com.digitalpersona.onetouch.ui.swing;

import java.util.EventListener;

public abstract interface DPFPEnrollmentListener
  extends EventListener
{
  public abstract void fingerDeleted(DPFPEnrollmentEvent paramDPFPEnrollmentEvent)
    throws DPFPEnrollmentVetoException;
  
  public abstract void fingerEnrolled(DPFPEnrollmentEvent paramDPFPEnrollmentEvent)
    throws DPFPEnrollmentVetoException;
}
