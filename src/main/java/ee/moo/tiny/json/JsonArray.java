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

public final class JsonArray extends JsonValue {

    private final List<JsonValue> values = new ArrayList<>();

    public JsonArray() {
    }

    public JsonArray(List<JsonValue> values) {
        if (values != null) {
            this.values.addAll(values);
        }
    }

    @Override
    public JsonType getType() {
        return JsonType.JSON_ARRAY;
    }

    @Override
    public String toJson(JsonWriteMode mode) {
        return toJson(mode, 0);
    }

    @Override
    public String toJson(JsonWriteMode mode, int indent) {
        var sb = new StringBuilder();

        var pad1 = "";
        var pad2 = "";
        if (mode.isPretty()) {
            pad1 = "  ".repeat(indent);
            pad2 = "  ".repeat(indent + 1);
        }

        sb.append('[');

        if (!values.isEmpty()) {
            if (mode.isPretty()) {
                sb.append('\n');
            }

            for (int i = 0; i < values.size(); i++) {
                sb.append(pad2);
                sb.append(values.get(i).toJson(mode, indent + 1));

                if (i < values.size() - 1) {
                    sb.append(',');
                }

                if (mode.isPretty()) {
                    sb.append('\n');
                }

            }

            sb.append(pad1);
        }

        sb.append(']');

        return sb.toString();
    }

    public void add(JsonValue v) {
        values.add(v);
    }

    @Override
    public List<JsonValue> asList() {
        return Collections.unmodifiableList(values);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> asList(Class<T> cls) {
        var result = new ArrayList<T>(values.size());

        for (JsonValue value : values) {
            if (cls == String.class) {
                result.add((T) value.asString());

            } else if (cls == Integer.class) {
                result.add((T) Integer.valueOf(value.asInt()));

            } else if (cls == Long.class) {
                result.add((T) Long.valueOf(value.asLong()));

            } else if (cls == Float.class) {
                result.add((T) Float.valueOf(value.asFloat()));

            } else if (cls == Double.class) {
                result.add((T) Double.valueOf(value.asDouble()));

            } else if (cls == Boolean.class) {
                result.add((T) Boolean.valueOf(value.asBoolean()));

            } else if (JsonValue.class.isAssignableFrom(cls)) {
                if (!cls.isInstance(value)) {
                    throw new JsonException("Expected %s but got %s", cls.getName(), value.getClass().getName());
                }
                result.add((T) value);

            } else {
                throw new JsonException("Unsupported list element type: %s", cls.getName());

            }
        }

        return result;
    }

    @Override
    public Set<JsonValue> asSet() {
        return new HashSet<>(asList());
    }

    @Override
    public <T> Set<T> asSet(Class<T> cls) {
        return new HashSet<>(asList(cls));
    }
}
