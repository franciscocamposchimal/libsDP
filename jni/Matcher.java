package com.digitalpersona.onetouch.jni;




public class Matcher
{
  private static final MatchingLibrary theLibrary = ;
  


  public static final int FT_DEFAULT_REG = 0;
  


  public static final double HIGH_SEC_FAR = 1.0E-4D;
  


  public static final double MED_SEC_FAR = 0.001D;
  


  public static final double LOW_SEC_FAR = 0.01D;
  


  private Object handle;
  


  public Matcher()
    throws JniException
  {
    createContext();
  }
  






  protected void finalize()
    throws Throwable
  {
    closeContext();
    super.finalize();
  }
  
  public static native int getFeaturesRequired()
    throws JniException;
  
  public native void setFARRequested(double paramDouble)
    throws JniException;
  
  public native double getFARRequested()
    throws JniException;
  
  public native byte[] generateRegFeatures(byte[][] paramArrayOfByte, int paramInt)
    throws JniException;
  
  public native VerificationResult verify(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws JniException;
  
  private native void createContext()
    throws JniException;
  
  private native void closeContext()
    throws JniException;
}
