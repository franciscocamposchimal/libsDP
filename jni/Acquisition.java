package com.digitalpersona.onetouch.jni;




public class Acquisition
{
  public static final int DP_PRIORITY_HIGH = 1;
  


  public static final int DP_PRIORITY_NORMAL = 2;
  


  public static final int DP_PRIORITY_LOW = 3;
  

  private static AcquisitionLibrary theLibrary;
  

  private DeviceEventHandler handler;
  

  private final String serialNumber;
  

  private final int priority;
  

  private boolean subscribed;
  

  private boolean eventsEnabled;
  

  private Object handle;
  


  public Acquisition(String paramString, int paramInt)
    throws JniException
  {
    if (theLibrary == null) {
      theLibrary = AcquisitionLibrary.getInstance();
    }
    serialNumber = paramString;
    priority = paramInt;
    subscribed = false;
    eventsEnabled = false;
  }
  








  public synchronized native void subscribe()
    throws JniException;
  







  public synchronized native void unsubscribe()
    throws JniException;
  







  public synchronized boolean isSubscribed()
  {
    return subscribed;
  }
  









  public synchronized void startEvents()
  {
    eventsEnabled = true;
  }
  







  public synchronized void stopEvents()
  {
    eventsEnabled = false;
  }
  






  public synchronized boolean getEventsEnabled()
  {
    return eventsEnabled;
  }
  



  public DeviceEventHandler getHandler()
  {
    return handler;
  }
  



  public void setHandler(DeviceEventHandler paramDeviceEventHandler)
  {
    handler = paramDeviceEventHandler;
  }
  

  static
  {
    try
    {
      theLibrary = AcquisitionLibrary.getInstance();
    } catch (Exception localException) {
      theLibrary = null;
    }
  }
}
