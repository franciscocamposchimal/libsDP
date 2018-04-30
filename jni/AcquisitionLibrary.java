package com.digitalpersona.onetouch.jni;





public class AcquisitionLibrary
{
  private static final String LIB_NAME = "otdpfpjni";
  



  private static AcquisitionLibrary ourInstance;
  



  private static boolean isInitialized = false;
  private static boolean isLoaded = false;
  
  static {
    try {
      getInstance();
    } catch (JniException localJniException) {
      ourInstance = null;
    }
  }
  



  public static AcquisitionLibrary getInstance()
    throws JniException
  {
    if (ourInstance == null)
      try { if (!isLoaded) {
          System.loadLibrary("otdpfpjni");
          isLoaded = true;
        }
        ourInstance = new AcquisitionLibrary();
        init();
      } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
        throw new JniException(-1);
      } catch (JniException localJniException) {
        ourInstance = null;
        throw localJniException;
      } catch (Exception localException) {
        ourInstance = null;
        throw new JniException(-33);
      }
    return ourInstance;
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
  
  private AcquisitionLibrary() {}
  
  private static native void init()
    throws JniException;
  
  private static native void terminate()
    throws JniException;
  
  public native ProductVersion getVersion()
    throws JniException;
  
  public native String[] enumerateDevices()
    throws JniException;
  
  public native ReaderInfo getDeviceInfo(String paramString)
    throws JniException;
}
