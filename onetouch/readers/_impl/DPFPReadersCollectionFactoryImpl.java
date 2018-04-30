package com.digitalpersona.onetouch.readers._impl;

import com.digitalpersona.onetouch.jni.AcquisitionLibrary;
import com.digitalpersona.onetouch.jni.DeviceVersion;
import com.digitalpersona.onetouch.jni.HardwareInfo;
import com.digitalpersona.onetouch.jni.JniException;
import com.digitalpersona.onetouch.jni.ReaderInfo;
import com.digitalpersona.onetouch.readers.DPFPReaderDescription;
import com.digitalpersona.onetouch.readers.DPFPReaderImpressionType;
import com.digitalpersona.onetouch.readers.DPFPReaderSerialNumberType;
import com.digitalpersona.onetouch.readers.DPFPReaderTechnology;
import com.digitalpersona.onetouch.readers.DPFPReaderVersion;
import com.digitalpersona.onetouch.readers.DPFPReadersCollection;
import java.util.ArrayList;

public class DPFPReadersCollectionFactoryImpl implements com.digitalpersona.onetouch.readers.DPFPReadersCollectionFactory
{
  public DPFPReadersCollectionFactoryImpl() {}
  
  public DPFPReadersCollection getReaders()
  {
    try
    {
      AcquisitionLibrary localAcquisitionLibrary = AcquisitionLibrary.getInstance();
      String[] arrayOfString1 = localAcquisitionLibrary.enumerateDevices();
      ReadersCollectionImpl localReadersCollectionImpl = new ReadersCollectionImpl(null);
      for (String str : arrayOfString1)
        localReadersCollectionImpl.add(new ReaderDescriptionImpl(localAcquisitionLibrary.getDeviceInfo(str)));
      return localReadersCollectionImpl;
    } catch (JniException localJniException) {
      throw new RuntimeException(localJniException);
    }
  }
  
  private static class ReadersCollectionImpl
    extends ArrayList<DPFPReaderDescription>
    implements DPFPReadersCollection
  {
    private static final long serialVersionUID = 4154813561319903426L;
    
    private ReadersCollectionImpl() {}
    
    public DPFPReaderDescription get(String paramString)
    {
      for (int i = 0; i < size(); i++) {
        DPFPReaderDescription localDPFPReaderDescription = (DPFPReaderDescription)get(i);
        String str = localDPFPReaderDescription.getSerialNumber();
        if (((paramString == null) && (str == null)) || ((paramString != null) && (paramString.equals(str))))
          return localDPFPReaderDescription;
      }
      return null;
    }
  }
  

  private static class ReaderDescriptionImpl
    implements DPFPReaderDescription
  {
    private final DPFPReaderVersion firmwareRevision;
    
    private final DPFPReaderVersion hardwareRevision;
    
    private final DPFPReaderImpressionType impressionType;
    private final int language;
    private final String productName;
    private final String serialNumber;
    private final DPFPReaderSerialNumberType serialNumberType;
    private final DPFPReaderTechnology technology;
    private final String vendor;
    
    public ReaderDescriptionImpl(ReaderInfo paramReaderInfo)
    {
      this(serialNumber, DPFPReaderSerialNumberType.values()[serialNumberType], new ReaderVersionImpl(hwInfo.firmwareRevision.major, hwInfo.firmwareRevision.minor, hwInfo.firmwareRevision.build), new ReaderVersionImpl(hwInfo.hardwareRevision.major, hwInfo.hardwareRevision.minor, hwInfo.hardwareRevision.build), DPFPReaderImpressionType.values()[modality], hwInfo.languageId, hwInfo.product, DPFPReaderTechnology.values()[readerTechnology], hwInfo.vendor);
    }
    












    public ReaderDescriptionImpl(String paramString1, DPFPReaderSerialNumberType paramDPFPReaderSerialNumberType, DPFPReaderVersion paramDPFPReaderVersion1, DPFPReaderVersion paramDPFPReaderVersion2, DPFPReaderImpressionType paramDPFPReaderImpressionType, int paramInt, String paramString2, DPFPReaderTechnology paramDPFPReaderTechnology, String paramString3)
    {
      serialNumber = paramString1;
      serialNumberType = paramDPFPReaderSerialNumberType;
      firmwareRevision = paramDPFPReaderVersion1;
      hardwareRevision = paramDPFPReaderVersion2;
      impressionType = paramDPFPReaderImpressionType;
      language = paramInt;
      productName = paramString2;
      technology = paramDPFPReaderTechnology;
      vendor = paramString3;
    }
    
    public DPFPReaderVersion getFirmwareRevision() {
      return firmwareRevision;
    }
    
    public DPFPReaderVersion getHardwareRevision() {
      return hardwareRevision;
    }
    
    public DPFPReaderImpressionType getImpressionType() {
      return impressionType;
    }
    
    public int getLanguage() {
      return language;
    }
    
    public String getProductName() {
      return productName;
    }
    
    public String getSerialNumber() {
      return serialNumber;
    }
    
    public DPFPReaderSerialNumberType getSerialNumberType() {
      return serialNumberType;
    }
    
    public DPFPReaderTechnology getTechnology() {
      return technology;
    }
    
    public String getVendor() {
      return vendor;
    }
    

    private static class ReaderVersionImpl
      implements DPFPReaderVersion
    {
      private final int major;
      
      private final int minor;
      private final int build;
      
      public ReaderVersionImpl(int paramInt1, int paramInt2, int paramInt3)
      {
        major = paramInt1;
        minor = paramInt2;
        build = paramInt3;
      }
      
      public int getMajor() {
        return major;
      }
      
      public int getMinor() {
        return minor;
      }
      
      public int getBuild() {
        return build;
      }
    }
  }
}
