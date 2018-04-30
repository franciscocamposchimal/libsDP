package com.digitalpersona.onetouch.verification;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPTemplate;

public abstract interface DPFPVerification
{
  public static final int PROBABILITY_ONE = Integer.MAX_VALUE;
  public static final int HIGH_SECURITY_FAR = 2147;
  public static final int MEDIUM_SECURITY_FAR = 21474;
  public static final int LOW_SECURITY_FAR = 214748;
  
  public abstract int getFARRequested();
  
  public abstract void setFARRequested(int paramInt);
  
  public abstract DPFPVerificationResult verify(DPFPFeatureSet paramDPFPFeatureSet, DPFPTemplate paramDPFPTemplate);
}
