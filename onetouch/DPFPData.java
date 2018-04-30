package com.digitalpersona.onetouch;

public abstract interface DPFPData
{
  public abstract void deserialize(byte[] paramArrayOfByte)
    throws IllegalArgumentException;
  
  public abstract byte[] serialize();
}
