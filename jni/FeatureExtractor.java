package com.digitalpersona.onetouch.jni;




public class FeatureExtractor
{
  private static final FeatureExtractionLibrary theLibrary = ;
  


  public static final int FT_PRE_REG_FTR = 0;
  


  public static final int FT_VER_FTR = 2;
  

  private Object handle;
  


  public FeatureExtractor()
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
  
  public native FeatureExtractionResult extractFeatures(byte[] paramArrayOfByte, int paramInt)
    throws JniException;
  
  private native void createContext()
    throws JniException;
  
  private native void closeContext()
    throws JniException;
}
