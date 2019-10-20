package pl.wedel.szzti.domain;

public enum MeasureUnit {
  SZT, KPL, OP;

  public static MeasureUnit fromString(String arg) {
    for (MeasureUnit measureUnit : values()) {
      if (measureUnit.name().equalsIgnoreCase(arg)) {
        return measureUnit;
      }
    }

    return null;
  }

}
