package com.digitalpersona.onetouch.readers;

import java.util.List;

public abstract interface DPFPReadersCollection
  extends List<DPFPReaderDescription>
{
  public abstract DPFPReaderDescription get(String paramString);
}
