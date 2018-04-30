package com.digitalpersona.onetouch.verification;

public abstract interface DPFPVerificationFactory
{
  public abstract DPFPVerification createVerification();
  
  public abstract DPFPVerification createVerification(int paramInt);
}
