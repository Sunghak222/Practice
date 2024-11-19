package hk.edu.polyu.comp.comp2021.cvfs.model;

public enum Type {
    TXT("txt"),
    JAVA("java"),
    HTML("html"),
    CSS("css");

    private final String type;

    Type(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public static Type toType(String type) {
        for (Type t : Type.values()) {
            if (t.type.equalsIgnoreCase(type))
                return t;
        }
        throw new IllegalArgumentException("Invalid file type: " + type);
    }
    public static boolean isValidType(String type) {
        for (Type t : Type.values()) {
            if (t.type.equalsIgnoreCase(type)) return true;
        }
        return false;
    }
}
