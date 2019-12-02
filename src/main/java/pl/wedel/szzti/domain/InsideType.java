package pl.wedel.szzti.domain;

public enum InsideType {
  NN, N, T, M;
  
  public static InsideType fromString(String arg) {
    for (InsideType insideType : values()) {
      if (insideType.name().equalsIgnoreCase(arg)) {
        return insideType;
      }
    }

    return null;
  }

}
