package com.digitalpersona.onetouch.capture._impl;

import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCapturePriority;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorListener;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorListener;
import com.digitalpersona.onetouch.jni.Acquisition;
import com.digitalpersona.onetouch.jni.DeviceEvent;
import com.digitalpersona.onetouch.jni.JniException;
import javax.swing.event.EventListenerList;

public class DPFPCaptureFactoryImpl implements com.digitalpersona.onetouch.capture.DPFPCaptureFactory
{
  public DPFPCaptureFactoryImpl() {}
  
  public DPFPCapture createCapture()
  {
    return new CaptureImpl();
  }
  
  public DPFPCapture createCapture(String paramString) {
    return new CaptureImpl(paramString);
  }
  
  public DPFPCapture createCapture(DPFPCapturePriority paramDPFPCapturePriority) {
    return new CaptureImpl(paramDPFPCapturePriority);
  }
  
  public DPFPCapture createCapture(String paramString, DPFPCapturePriority paramDPFPCapturePriority) {
    return new CaptureImpl(paramString, paramDPFPCapturePriority);
  }
  





  static class CaptureImpl
    implements DPFPCapture
  {
    private DPFPCapturePriority priority = DPFPCapturePriority.CAPTURE_PRIORITY_NORMAL;
    private String readerSerialNumber = null;
    private Acquisition acquisition = null;
    private final EventListenerList listenerList = new EventListenerList();
    
    public CaptureImpl() {}
    
    public CaptureImpl(String paramString)
    {
      this();
      setReaderSerialNumber(paramString);
    }
    
    public CaptureImpl(DPFPCapturePriority paramDPFPCapturePriority) {
      this();
      setPriority(paramDPFPCapturePriority);
    }
    
    public CaptureImpl(String paramString, DPFPCapturePriority paramDPFPCapturePriority) {
      this();
      setReaderSerialNumber(paramString);
      setPriority(paramDPFPCapturePriority);
    }
    
    public synchronized DPFPCapturePriority getPriority() {
      return priority;
    }
    
    public synchronized void setPriority(DPFPCapturePriority paramDPFPCapturePriority)
      throws IllegalStateException
    {
      assertStopped();
      priority = paramDPFPCapturePriority;
    }
    
    public synchronized String getReaderSerialNumber() {
      return readerSerialNumber;
    }
    
    public synchronized void setReaderSerialNumber(String paramString)
      throws IllegalStateException
    {
      assertStopped();
      readerSerialNumber = paramString;
    }
    
    public synchronized void startCapture()
      throws IllegalStateException
    {
      assertStopped();
      try {
        int i = priority == DPFPCapturePriority.CAPTURE_PRIORITY_LOW ? 3 : priority == DPFPCapturePriority.CAPTURE_PRIORITY_HIGH ? 1 : 2;
        


        acquisition = new Acquisition(readerSerialNumber, i);
        acquisition.setHandler(new com.digitalpersona.onetouch.jni.DeviceEventHandler()
        {
          private String lastSerial = "{00000000-0000-0000-0000-000000000000}";
          
          public void onEvent(DeviceEvent paramAnonymousDeviceEvent)
          {
            if (paramAnonymousDeviceEvent == null) {
              return;
            }
            if (serialNumber == null)
              serialNumber = lastSerial;
            Object localObject1;
            Object localObject2; Object localObject3; switch (nEvent)
            {
            case 0: 
              try {
                localObject1 = com.digitalpersona.onetouch.DPFPGlobal.getSampleFactory().createSample(data);
              } catch (IllegalArgumentException localIllegalArgumentException) {
                localIllegalArgumentException.printStackTrace();
                localObject1 = null;
              }
              localObject2 = new DPFPDataEvent(serialNumber, (com.digitalpersona.onetouch.DPFPSample)localObject1);
              for (DPFPDataListener localDPFPDataListener : (DPFPDataListener[])getListeners(DPFPDataListener.class))
                localDPFPDataListener.dataAcquired((DPFPDataEvent)localObject2);
              break;
            
            case 1: 
              localObject1 = new DPFPErrorEvent(serialNumber, new com.digitalpersona.onetouch.DPFPError(errorCode));
              for (localObject3 : (DPFPErrorListener[])getListeners(DPFPErrorListener.class))
                localObject3.errorOccured((DPFPErrorEvent)localObject1);
              break;
            
            case 2: 
              localObject1 = new DPFPReaderStatusEvent(serialNumber, 2);
              for (localObject3 : (DPFPReaderStatusListener[])getListeners(DPFPReaderStatusListener.class))
                localObject3.readerDisconnected((DPFPReaderStatusEvent)localObject1);
              break;
            
            case 3: 
              if ((lastSerial == null) && (serialNumber != null)) {
                lastSerial = serialNumber;
              }
              localObject1 = new DPFPReaderStatusEvent(serialNumber, 3);
              for (localObject3 : (DPFPReaderStatusListener[])getListeners(DPFPReaderStatusListener.class))
                localObject3.readerConnected((DPFPReaderStatusEvent)localObject1);
              break;
            
            case 4: 
              localObject1 = new DPFPImageQualityEvent(serialNumber, com.digitalpersona.onetouch.DPFPCaptureFeedback.values()[errorCode]);
              for (localObject3 : (DPFPImageQualityListener[])getListeners(DPFPImageQualityListener.class))
                localObject3.onImageQuality((DPFPImageQualityEvent)localObject1);
              break;
            
            case 5: 
              localObject1 = new DPFPSensorEvent(serialNumber, 5);
              for (localObject3 : (DPFPSensorListener[])getListeners(DPFPSensorListener.class))
                localObject3.fingerTouched((DPFPSensorEvent)localObject1);
              break;
            
            case 6: 
              localObject1 = new DPFPSensorEvent(serialNumber, 6);
              for (localObject3 : (DPFPSensorListener[])getListeners(DPFPSensorListener.class))
                localObject3.fingerGone((DPFPSensorEvent)localObject1);
              break;
            
            case 7: 
              if (serialNumber != null) {
                lastSerial = serialNumber;
              }
              localObject1 = new DPFPSensorEvent(serialNumber, 7);
              for (localObject3 : (DPFPSensorListener[])getListeners(DPFPSensorListener.class))
                localObject3.imageAcquired((DPFPSensorEvent)localObject1);
              break;
            

            }
            
          }
        });
        acquisition.startEvents();
        acquisition.subscribe();
      } catch (JniException localJniException) {
        throw new RuntimeException(localJniException);
      }
    }
    
    public synchronized void stopCapture() {
      if (!isStarted()) {
        return;
      }
      try {
        acquisition.unsubscribe();
      } catch (JniException localJniException) {
        localJniException.printStackTrace();
      }
      acquisition = null;
    }
    
    public synchronized boolean isStarted() {
      return acquisition != null;
    }
    
    public void addDataListener(DPFPDataListener paramDPFPDataListener) {
      listenerList.add(DPFPDataListener.class, paramDPFPDataListener);
    }
    
    public void removeDataListener(DPFPDataListener paramDPFPDataListener) {
      listenerList.remove(DPFPDataListener.class, paramDPFPDataListener);
    }
    
    public void addImageQualityListener(DPFPImageQualityListener paramDPFPImageQualityListener) {
      listenerList.add(DPFPImageQualityListener.class, paramDPFPImageQualityListener);
    }
    
    public void removeImageQualityListener(DPFPImageQualityListener paramDPFPImageQualityListener) {
      listenerList.remove(DPFPImageQualityListener.class, paramDPFPImageQualityListener);
    }
    
    public void addReaderStatusListener(DPFPReaderStatusListener paramDPFPReaderStatusListener) {
      listenerList.add(DPFPReaderStatusListener.class, paramDPFPReaderStatusListener);
    }
    
    public void removeReaderStatusListener(DPFPReaderStatusListener paramDPFPReaderStatusListener) {
      listenerList.remove(DPFPReaderStatusListener.class, paramDPFPReaderStatusListener);
    }
    
    public void addSensorListener(DPFPSensorListener paramDPFPSensorListener) {
      listenerList.add(DPFPSensorListener.class, paramDPFPSensorListener);
    }
    
    public void removeSensorListener(DPFPSensorListener paramDPFPSensorListener) {
      listenerList.remove(DPFPSensorListener.class, paramDPFPSensorListener);
    }
    
    public void addErrorListener(DPFPErrorListener paramDPFPErrorListener) {
      listenerList.add(DPFPErrorListener.class, paramDPFPErrorListener);
    }
    
    public void removeErrorListener(DPFPErrorListener paramDPFPErrorListener) {
      listenerList.remove(DPFPErrorListener.class, paramDPFPErrorListener);
    }
    
    public <T extends java.util.EventListener> T[] getListeners(Class<T> paramClass) {
      synchronized (listenerList) {
        return listenerList.getListeners(paramClass);
      }
    }
    





    private void assertStopped()
      throws IllegalStateException
    {
      if (isStarted()) {
        throw new IllegalStateException();
      }
    }
  }
}
