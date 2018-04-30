package com.digitalpersona.onetouch.readers;

public abstract interface DPFPReaderDescription
{
  public abstract DPFPReaderVersion getFirmwareRevision();
  
  public abstract DPFPReaderVersion getHardwareRevision();
  
  public abstract DPFPReaderImpressionType getImpressionType();
  
  public abstract int getLanguage();
  
  public abstract String getProductName();
  
  public abstract String getSerialNumber();
  
  public abstract DPFPReaderSerialNumberType getSerialNumberType();
  
  public abstract DPFPReaderTechnology getTechnology();
  
  public abstract String getVendor();
}
