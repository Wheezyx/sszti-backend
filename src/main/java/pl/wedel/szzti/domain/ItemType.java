package pl.wedel.szzti.domain;

public enum ItemType {
  EQUIPMENT, FURNITURE, TOOLS, SOFTWARE, OFFICE;


  public static ItemType fromString(String arg) {
    for (ItemType itemType : values()) {
      if (itemType.name().equalsIgnoreCase(arg)) {
        return itemType;
      }
    }

    return null;
  }
}
