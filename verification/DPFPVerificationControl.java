package com.digitalpersona.onetouch.ui.swing;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCaptureFactory;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtractionFactory;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;



public class DPFPVerificationControl
  extends JPanel
{
  private static final long serialVersionUID = 642123616397849523L;
  private final DPFPSwingUtilities swingUtilities = new DPFPSwingUtilities();
  
  private transient DPFPCapture capture = null;
  private transient VerificationSensorControl sensor = new VerificationSensorControl();
  private transient boolean finishing = false;
  private String serialNumber = null;
  

  public static final String READER_SERIAL_NUMBER_PROPERTY = "readerSerialNumber";
  

  public DPFPVerificationControl()
  {
    add(sensor);
    

    addHierarchyListener(new HierarchyListener() {
      public void hierarchyChanged(HierarchyEvent paramAnonymousHierarchyEvent) {
        if ((paramAnonymousHierarchyEvent.getID() == 1400) && ((paramAnonymousHierarchyEvent.getChangeFlags() & 0x4) != 0L) && (!isShowing()))
        {


          stop();
        }
      }
    });
  }
  


  public String getReaderSerialNumber()
  {
    return serialNumber;
  }
  






  public void setReaderSerialNumber(String paramString)
  {
    String str = getReaderSerialNumber();
    stop();
    serialNumber = paramString;
    firePropertyChange("readerSerialNumber", str, getReaderSerialNumber());
  }
  






  public void addVerificationListener(DPFPVerificationListener paramDPFPVerificationListener)
  {
    listenerList.add(DPFPVerificationListener.class, paramDPFPVerificationListener);
  }
  






  public void removeVerificationListener(DPFPVerificationListener paramDPFPVerificationListener)
  {
    listenerList.remove(DPFPVerificationListener.class, paramDPFPVerificationListener);
  }
  






















  public void start()
  {
    sensor.showNormal();
    

    capture = DPFPGlobal.getCaptureFactory().createCapture(serialNumber);
    


    capture.addDataListener(new DPFPDataListener()
    {
      public void dataAcquired(final DPFPDataEvent paramAnonymousDPFPDataEvent)
      {
        if (finishing) {
          return;
        }
        swingUtilities.invokeLater(new Runnable() {
          public void run() { DPFPSample localDPFPSample = paramAnonymousDPFPDataEvent.getSample();
            if (localDPFPSample == null) {
              sensor.showFailed(true);
              return;
            }
            DPFPFeatureExtraction localDPFPFeatureExtraction = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
            try {
              DPFPFeatureSet localDPFPFeatureSet = localDPFPFeatureExtraction.createFeatureSet(localDPFPSample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
              DPFPVerificationEvent localDPFPVerificationEvent = new DPFPVerificationEvent(DPFPVerificationControl.this, localDPFPFeatureSet);
              try
              {
                for (DPFPVerificationListener localDPFPVerificationListener : (DPFPVerificationListener[])getListeners(DPFPVerificationListener.class)) {
                  localDPFPVerificationListener.captureCompleted(localDPFPVerificationEvent);
                }
              } catch (DPFPVerificationVetoException localDPFPVerificationVetoException) {
                localDPFPVerificationEvent.setMatched(false);
              } finally {
                if (localDPFPVerificationEvent.getStopCapture()) {
                  swingUtilities.invokeLater(new Runnable() {
                    public void run() { capture.stopCapture(); }
                  });
                }
                
                if (localDPFPVerificationEvent.getMatched()) {
                  sensor.showMatched(!localDPFPVerificationEvent.getStopCapture());
                } else {
                  sensor.showFailed(!localDPFPVerificationEvent.getStopCapture());
                }
              }
            } catch (DPFPImageQualityException localDPFPImageQualityException) {
              sensor.showFailed(true);
            } catch (Exception localException) {
              sensor.showFailed(true);
            }
          }
        });
      } });
    capture.addReaderStatusListener(new DPFPReaderStatusListener()
    {
      int lastStatus = 3;
      
      public void readerConnected(DPFPReaderStatusEvent paramAnonymousDPFPReaderStatusEvent) { if (lastStatus != paramAnonymousDPFPReaderStatusEvent.getReaderStatus())
          sensor.showNormal();
        lastStatus = paramAnonymousDPFPReaderStatusEvent.getReaderStatus();
      }
      
      public void readerDisconnected(DPFPReaderStatusEvent paramAnonymousDPFPReaderStatusEvent) { if (lastStatus != paramAnonymousDPFPReaderStatusEvent.getReaderStatus())
          sensor.showDisabled();
        lastStatus = paramAnonymousDPFPReaderStatusEvent.getReaderStatus();
      }
    });
    capture.startCapture();
  }
  





  public void stop()
  {
    if ((capture != null) && (!finishing))
    {
      finishing = true;
      swingUtilities.invokeLater(new Runnable() {
        public void run() {
          try {
            sensor.showFailed(false);
            capture.stopCapture();
            capture = null;
            finishing = false;
          }
          catch (Exception localException) {}
        }
      });
    }
  }
  


  public boolean isStopping()
  {
    return finishing;
  }
}
