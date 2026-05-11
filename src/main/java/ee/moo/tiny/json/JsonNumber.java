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

public final class JsonNumber extends JsonValue {

    private final String value;

    public JsonNumber(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public JsonType getType() {
        return JsonType.JSON_NUMBER;
    }

    @Override
    public String toJson() {
        return value;
    }

    @Override
    public String toJson(int indent) {
        return toJson();
    }

    @Override
    public int asInt() {
        return Integer.parseInt(value);
    }

    @Override
    public long asLong() {
        return Long.parseLong(value);
    }

    @Override
    public float asFloat() {
        return Float.parseFloat(value);
    }

    @Override
    public double asDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof JsonNumber other)) {
            return false;
        }

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}