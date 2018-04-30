package com.digitalpersona.onetouch.jni;

public class HardwareInfo { public int languageId;
  public String vendor;
  public String product;
  
  public HardwareInfo(int paramInt, String paramString1, String paramString2, String paramString3, DeviceVersion paramDeviceVersion1, DeviceVersion paramDeviceVersion2) { languageId = paramInt;
    vendor = paramString1;
    product = paramString2;
    serialNumber = paramString3;
    hardwareRevision = paramDeviceVersion1;
    firmwareRevision = paramDeviceVersion2;
  }
  
  public String serialNumber;
  public DeviceVersion hardwareRevision;
  public DeviceVersion firmwareRevision;
}
