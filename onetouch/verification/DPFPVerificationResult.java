package com.digitalpersona.onetouch.verification;

public abstract interface DPFPVerificationResult
{
  public abstract int getFalseAcceptRate();
  
  public abstract boolean isVerified();
}
