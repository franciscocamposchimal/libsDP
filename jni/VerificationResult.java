package com.digitalpersona.onetouch.jni;



public class VerificationResult
{
  public double FARAchieved;
  
  public boolean decision;
  
  public int nScore;
  

  public VerificationResult(double paramDouble, boolean paramBoolean, int paramInt)
  {
    FARAchieved = paramDouble;
    decision = paramBoolean;
    nScore = paramInt;
  }
  
  public VerificationResult() {}
}
