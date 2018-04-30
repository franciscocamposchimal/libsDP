package com.digitalpersona.onetouch.jni;

public class DeviceVersion
{
  public int major;
  public int minor;
  public int build;
  
  public DeviceVersion(int paramInt1, int paramInt2, int paramInt3)
  {
    major = paramInt1;
    minor = paramInt2;
    build = paramInt3;
  }
  
  public DeviceVersion() {
    this(1, 0, 0);
  }
  








  public boolean equals(Object paramObject)
  {
    return (super.equals(paramObject)) || (((paramObject instanceof ProductVersion)) && (major == major) && (minor == minor) && (build == build));
  }
}
