package com.digitalpersona.onetouch.jni;

public class ReaderInfo {
  public String serialNumber;
  public int serialNumberType;
  
  public ReaderInfo(String paramString, int paramInt1, int paramInt2, int paramInt3, HardwareInfo paramHardwareInfo) { serialNumber = paramString;
    serialNumberType = paramInt1;
    modality = paramInt2;
    readerTechnology = paramInt3;
    hwInfo = paramHardwareInfo;
  }
  
  public int modality;
  public int readerTechnology;
  public HardwareInfo hwInfo;
}
