package com.digitalpersona.onetouch;

public abstract interface DPFPFeatureSetFactory
{
  public abstract DPFPFeatureSet createFeatureSet();
  
  public abstract DPFPFeatureSet createFeatureSet(byte[] paramArrayOfByte)
    throws IllegalArgumentException;
}
