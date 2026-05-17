package ee.moo.tiny.json;

public enum JsonWriteMode {
    NORMAL,
    PRETTY;

    public boolean isPretty() {
        return this == PRETTY;
    }
}
