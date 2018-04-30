package com.digitalpersona.onetouch;




public class DPFPError
{
  private final int errorCode;
  

  private final int extendedErrorCode;
  

  private final String errorText;
  

  private final Exception exception;
  


  protected DPFPError(int paramInt1, int paramInt2, String paramString, Exception paramException)
  {
    errorCode = paramInt1;
    extendedErrorCode = paramInt2;
    errorText = paramString;
    exception = paramException;
  }
  




  public DPFPError(int paramInt)
  {
    this(paramInt, 0, null, null);
  }
  





  public DPFPError(int paramInt, String paramString)
  {
    this(paramInt, 0, paramString, null);
  }
  





  public DPFPError(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, null, null);
  }
  






  public DPFPError(int paramInt1, int paramInt2, String paramString)
  {
    this(paramInt1, paramInt2, paramString, null);
  }
  





  public DPFPError(Exception paramException, String paramString)
  {
    this(0, 0, paramString, paramException);
  }
  




  public DPFPError(Exception paramException)
  {
    this(paramException, paramException.toString());
  }
  




  public int getErrorCode()
  {
    return errorCode;
  }
  




  public String getErrorText()
  {
    return errorText;
  }
  




  public int getExtendedErrorCode()
  {
    return extendedErrorCode;
  }
  




  public Exception getException()
  {
    return exception;
  }
}
