package com.digitalpersona.onetouch.jni;





public class JniException
  extends Exception
{
  private int errorCode;
  


  private int extendedErrorCode;
  



  public JniException(int paramInt)
  {
    errorCode = paramInt;
  }
  






  public JniException(int paramInt1, int paramInt2)
  {
    errorCode = paramInt1;
    extendedErrorCode = paramInt2;
  }
  
  public int getErrorCode() {
    return errorCode;
  }
  
  public int getExtendedErrorCode() {
    return extendedErrorCode;
  }
}
