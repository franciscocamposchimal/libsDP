package com.digitalpersona.onetouch.verification._impl;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.jni.JniException;
import com.digitalpersona.onetouch.jni.Matcher;
import com.digitalpersona.onetouch.jni.VerificationResult;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationFactory;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;

public class DPFPVerificationFactoryImpl
  implements DPFPVerificationFactory
{
  public DPFPVerificationFactoryImpl() {}
  
  public DPFPVerification createVerification()
  {
    return new VerificationImpl();
  }
  
  public DPFPVerification createVerification(int paramInt) {
    return new VerificationImpl(paramInt);
  }
  

  private static class VerificationImpl
    implements DPFPVerification
  {
    private Matcher matcher;
    
    public VerificationImpl()
    {
      try
      {
        matcher = new Matcher();
      } catch (JniException localJniException) {
        throw new RuntimeException(localJniException);
      }
    }
    
    public VerificationImpl(int paramInt) {
      this();
      setFARRequested(paramInt);
    }
    
    public synchronized int getFARRequested() {
      try {
        return Float2FAR(matcher.getFARRequested());
      } catch (JniException localJniException) {}
      return 0;
    }
    
    public synchronized void setFARRequested(int paramInt)
    {
      try {
        matcher.setFARRequested(FAR2Float(paramInt));
      } catch (JniException localJniException) {
        localJniException.printStackTrace();
      }
    }
    
    public synchronized DPFPVerificationResult verify(DPFPFeatureSet paramDPFPFeatureSet, DPFPTemplate paramDPFPTemplate) {
      try {
        VerificationResult localVerificationResult = matcher.verify(paramDPFPTemplate.serialize(), paramDPFPFeatureSet.serialize());
        return new VerificationResultImpl(Float2FAR(FARAchieved), decision);
      } catch (JniException localJniException) {}
      return new VerificationResultImpl(0, false);
    }
    

    private int Float2FAR(double paramDouble) { return Math.max((int)(paramDouble * 2.147483647E9D), 0); }
    private double FAR2Float(int paramInt) { return paramInt / 2.147483647E9D; }
    

    private static class VerificationResultImpl
      implements DPFPVerificationResult
    {
      private final int FAR;
      
      private final boolean verified;
      
      public VerificationResultImpl(int paramInt, boolean paramBoolean)
      {
        FAR = paramInt;
        verified = paramBoolean;
      }
      
      public int getFalseAcceptRate() {
        return FAR;
      }
      
      public boolean isVerified() {
        return verified;
      }
    }
  }
}
