package com.digitalpersona.onetouch.capture;

import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPErrorListener;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorListener;
import java.util.EventListener;

public abstract interface DPFPCapture
{
  public abstract DPFPCapturePriority getPriority();
  
  public abstract void setPriority(DPFPCapturePriority paramDPFPCapturePriority);
  
  public abstract String getReaderSerialNumber();
  
  public abstract void setReaderSerialNumber(String paramString);
  
  public abstract void startCapture();
  
  public abstract void stopCapture();
  
  public abstract boolean isStarted();
  
  public abstract void addDataListener(DPFPDataListener paramDPFPDataListener);
  
  public abstract void removeDataListener(DPFPDataListener paramDPFPDataListener);
  
  public abstract void addImageQualityListener(DPFPImageQualityListener paramDPFPImageQualityListener);
  
  public abstract void removeImageQualityListener(DPFPImageQualityListener paramDPFPImageQualityListener);
  
  public abstract void addReaderStatusListener(DPFPReaderStatusListener paramDPFPReaderStatusListener);
  
  public abstract void removeReaderStatusListener(DPFPReaderStatusListener paramDPFPReaderStatusListener);
  
  public abstract void addSensorListener(DPFPSensorListener paramDPFPSensorListener);
  
  public abstract void removeSensorListener(DPFPSensorListener paramDPFPSensorListener);
  
  public abstract void addErrorListener(DPFPErrorListener paramDPFPErrorListener);
  
  public abstract void removeErrorListener(DPFPErrorListener paramDPFPErrorListener);
  
  public abstract <T extends EventListener> T[] getListeners(Class<T> paramClass);
}
