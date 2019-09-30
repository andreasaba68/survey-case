package com.softcase.surc.bo.commands;

import java.util.Properties;

/**
 * Handier extension of Properties to support easier building.
 * 
 * @author andrea.saba@soft-case.com
 */
public class PropsBuilder extends Properties {

  private static final long serialVersionUID = 7942579598678506689L;

  public PropsBuilder() {
    super();
  }

  public PropsBuilder(int initialCapacity) {
    super(initialCapacity);
  }

  public PropsBuilder(Properties defaults) {
    super(defaults);
  }

  public PropsBuilder(String k, String v) {
    super();
    setProperty(k, v);
  }

}
