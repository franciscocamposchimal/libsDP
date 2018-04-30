package com.digitalpersona.onetouch._impl;

import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPSampleFactory;

public class DPFPSampleFactoryImpl
  implements DPFPSampleFactory
{
  public DPFPSampleFactoryImpl() {}
  
  public DPFPSample createSample()
  {
    return new SampleImpl(null);
  }
  
  public DPFPSample createSample(byte[] paramArrayOfByte)
    throws IllegalArgumentException
  {
    SampleImpl localSampleImpl = new SampleImpl(null);
    localSampleImpl.deserialize(paramArrayOfByte);
    return localSampleImpl;
  }
  
  private static class SampleImpl
    extends DPFPDataImpl
    implements DPFPSample
  {
    private SampleImpl() {}
  }
}
