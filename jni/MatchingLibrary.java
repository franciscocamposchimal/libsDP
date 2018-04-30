package com.digitalpersona.onetouch.jni;




public class MatchingLibrary
{
  private static final String LIB_NAME = "otmcjni";
  


  static
  {
    try
    {
      System.loadLibrary("otmcjni");
    } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
      localUnsatisfiedLinkError.printStackTrace(); } }
  
  private static MatchingLibrary ourInstance = new MatchingLibrary();
  
  public static MatchingLibrary getInstance()
  {
    return ourInstance;
  }
  





  private MatchingLibrary()
  {
    try
    {
      init();
    }
    catch (Exception localException) {}
  }
  


  protected void finalize()
    throws Throwable
  {
    try
    {
      
    }
    catch (Exception localException) {}
    

    super.finalize();
  }
  
  public static native ProductVersion getVersion()
    throws JniException;
  
  private static native void init()
    throws JniException;
  
  private static native void terminate()
    throws JniException;
}
