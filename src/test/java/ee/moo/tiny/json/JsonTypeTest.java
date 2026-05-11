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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTypeTest {

    @Test
    public void testIsArray() {
        for (var type : JsonType.values()) {
            if (type == JsonType.JSON_ARRAY) {
                assertTrue(type.isArray());
            } else {
                assertFalse(type.isArray());
            }
        }
    }

    @Test
    public void testIsObject() {
        for (var type : JsonType.values()) {
            if (type == JsonType.JSON_OBJECT) {
                assertTrue(type.isObject());
            } else {
                assertFalse(type.isObject());
            }
        }
    }

    @Test
    public void testIsNumber() {
        for (var type : JsonType.values()) {
            if (type == JsonType.JSON_NUMBER) {
                assertTrue(type.isNumber());
            } else {
                assertFalse(type.isNumber());
            }
        }
    }

    @Test
    public void testIsBoolean() {
        for (var type : JsonType.values()) {
            if (type == JsonType.JSON_BOOLEAN) {
                assertTrue(type.isBoolean());
            } else {
                assertFalse(type.isBoolean());
            }
        }
    }

    @Test
    public void testIsNull() {
        for (var type : JsonType.values()) {
            if (type == JsonType.JSON_NULL) {
                assertTrue(type.isNull());
            } else {
                assertFalse(type.isNull());
            }
        }
    }

    @Test
    public void testIsString() {
        for (var type : JsonType.values()) {
            if (type == JsonType.JSON_STRING) {
                assertTrue(type.isString());
            } else {
                assertFalse(type.isString());
            }
        }
    }
}
