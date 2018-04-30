package com.digitalpersona.onetouch;

public abstract interface DPFPSampleFactory
{
  public abstract DPFPSample createSample();
  
  public abstract DPFPSample createSample(byte[] paramArrayOfByte)
    throws IllegalArgumentException;
}
