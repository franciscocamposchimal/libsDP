package com.digitalpersona.onetouch._impl;

import com.digitalpersona.onetouch.DPFPData;

public abstract class DPFPDataImpl
  implements DPFPData
{
  private byte[] data;
  
  public DPFPDataImpl() {}
  
  public void deserialize(byte[] paramArrayOfByte) throws IllegalArgumentException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException();
    data = ((byte[])paramArrayOfByte.clone());
  }
  
  public byte[] serialize() {
    return data;
  }
}
