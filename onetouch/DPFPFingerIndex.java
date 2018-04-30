package com.digitalpersona.onetouch;

import java.util.EnumSet;





public enum DPFPFingerIndex
{
  LEFT_PINKY, 
  LEFT_RING, 
  LEFT_MIDDLE, 
  LEFT_INDEX, 
  LEFT_THUMB, 
  RIGHT_THUMB, 
  RIGHT_INDEX, 
  RIGHT_MIDDLE, 
  RIGHT_RING, 
  RIGHT_PINKY;
  


  private DPFPFingerIndex() {}
  


  public long toBit()
  {
    return 1 << ordinal();
  }
  
  public static EnumSet<DPFPFingerIndex> fromMask(long paramLong) {
    EnumSet localEnumSet = EnumSet.noneOf(DPFPFingerIndex.class);
    for (DPFPFingerIndex localDPFPFingerIndex : values()) {
      if ((localDPFPFingerIndex.toBit() & paramLong) != 0L)
        localEnumSet.add(localDPFPFingerIndex);
    }
    return localEnumSet;
  }
}
