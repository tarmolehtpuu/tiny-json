/*
   tiny-json - Tiny JSON Library
   Copyright 2026 Tarmo Lehtpuu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package ee.moo.tiny.json;

import java.util.*;

public final class JsonObject extends JsonValue {

    private final Map<String, JsonValue> values = new LinkedHashMap<>();

    public JsonObject() {
    }

    @Override
    public JsonType getType() {
        return JsonType.JSON_OBJECT;
    }

    @Override
    public String toJson(JsonWriteMode mode) {
        return toJson(mode, 0);
    }

    @Override
    public String toJson(JsonWriteMode mode, int indent) {
        var sb = new StringBuilder();
        var keys = new ArrayList<>(values.keySet());

        var pad1 = "";
        var pad2 = "";

        if (mode.isPretty()) {
            pad1 = "  ".repeat(indent);
            pad2 = "  ".repeat(indent + 1);
        }

        sb.append('{');

        if (!keys.isEmpty()) {
            if (mode.isPretty()) {
                sb.append('\n');
            }

            for (int i = 0; i < keys.size(); i++) {
                var key = keys.get(i);
                var val = values.get(key);

                sb.append(pad2);
                sb.append('"');
                sb.append(key);
                sb.append('"');
                sb.append(':');
                sb.append(' ');
                sb.append(val.toJson(mode, indent + 1));

                if (i < keys.size() - 1) {
                    sb.append(',');
                }

                if (mode.isPretty()) {
                    sb.append('\n');
                }

            }

            sb.append(pad1);
        }

        sb.append('}');

        return sb.toString();
    }

    @Override
    public JsonObject asObject() {
        return this;
    }

    public void put(String key, JsonValue value) {
        values.put(key, value);
    }

    public void put(String key, String value) {
        put(key, new JsonString(value));
    }

    public void put(String key, int value) {
        put(key, new JsonNumber(String.valueOf(value)));
    }

    public void put(String key, boolean value) {
        put(key, JsonBoolean.of(value));
    }

    public void put(String key, Set<?> value) {
        put(key, new ArrayList<Object>(value));
    }

    public void put(String key, List<?> value) {
        var data = new JsonArray();
        for (var item : value) {
            if (item == null) {
                data.add(JsonNull.INSTANCE);
            }
            if (item instanceof Number) {
                data.add(new JsonNumber(String.valueOf(item)));
            } else if (item instanceof Boolean) {
                if (String.valueOf(item).equals("true")) {
                    data.add(JsonBoolean.TRUE);
                } else {
                    data.add(JsonBoolean.FALSE);
                }
            } else {
                data.add(new JsonString(String.valueOf(item)));
            }
        }
        put(key, data);
    }

    public JsonValue get(String key) {
        return values.get(key);
    }

    public List<JsonValue> getList(String key) {
        return Collections.unmodifiableList(values.get(key).asList());
    }

    public <T> List<T> getList(String key, Class<T> cls) {
        return values.get(key).asList(cls);
    }

    public Set<JsonValue> getSet(String key) {
        return Collections.unmodifiableSet(values.get(key).asSet());
    }

    public <T> Set<T> getSet(String key, Class<T> cls) {
        return values.get(key).asSet(cls);
    }

    public String getString(String key) {
        return values.get(key).asString();
    }

    public String getOptionalString(String key) {
        return values.containsKey(key)
            ? values.get(key).asString()
            : null;
    }

    public <E extends Enum<E>> E getEnum(String key, Class<E> cls) {
        return values.get(key).asEnum(cls);
    }

    public JsonObject getObject(String name) {
        return values.get(name).asObject();
    }

    public boolean getBoolean(String key) {
        return values.get(key).asBoolean();
    }

    public int getInt(String key) {
        return values.get(key).asInt();
    }

    public int getInt(String key, int defaultValue) {
        return values.containsKey(key)
            ? values.get(key).asInt()
            : defaultValue;
    }

    public long getLong(String key) {
        return values.get(key).asLong();
    }

    public float getFloat(String key) {
        return values.get(key).asFloat();
    }

    public double getDouble(String key) {
        return values.get(key).asDouble();
    }

    public Set<String> keySet() {
        return values.keySet();
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof JsonObject other)) {
            return false;
        }

        return values.equals(other.values);
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public String toString() {
        return toJson(JsonWriteMode.PRETTY);
    }
}