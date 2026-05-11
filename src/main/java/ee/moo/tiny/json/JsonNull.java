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

import java.util.Objects;

public final class JsonNull extends JsonValue {

    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {
    }

    @Override
    public JsonType getType() {
        return JsonType.JSON_NULL;
    }

    @Override
    public String toJson() {
        return "null";
    }

    @Override
    public String toJson(int indent) {
        return toJson();
    }

    @Override
    public Object asNull() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return obj instanceof JsonNull;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(null);
    }

    @Override
    public String toString() {
        return "null";
    }
}