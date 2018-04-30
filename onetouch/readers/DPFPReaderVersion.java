package com.digitalpersona.onetouch.readers;

public abstract interface DPFPReaderVersion
{
  public abstract int getMajor();
  
  public abstract int getMinor();
  
  public abstract int getBuild();
}
