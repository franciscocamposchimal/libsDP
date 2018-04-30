package com.digitalpersona.onetouch.jni;




public class FeatureExtractionLibrary
{
  private static final String LIB_NAME = "otfxjni";
  


  static
  {
    try
    {
      System.loadLibrary("otfxjni");
    } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
      localUnsatisfiedLinkError.printStackTrace(); } }
  
  private static FeatureExtractionLibrary ourInstance = new FeatureExtractionLibrary();
  
  public static FeatureExtractionLibrary getInstance()
  {
    return ourInstance;
  }
  





  private FeatureExtractionLibrary()
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
