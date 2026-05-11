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

public class JsonValueTest {

    @Test
    public void testIsObject() {
        assertTrue(new JsonObject().isObject());
        assertFalse(new JsonArray().isObject());
        assertFalse(new JsonString("foo").isObject());
        assertFalse(new JsonNumber("123").isObject());
        assertFalse(JsonBoolean.of(false).isObject());
        assertFalse(JsonNull.INSTANCE.isObject());
    }

    @Test
    public void testIsArray() {
        assertFalse(new JsonObject().isArray());
        assertTrue(new JsonArray().isArray());
        assertFalse(new JsonString("foo").isArray());
        assertFalse(new JsonNumber("123").isArray());
        assertFalse(JsonBoolean.of(false).isArray());
        assertFalse(JsonNull.INSTANCE.isArray());
    }

    @Test
    public void testIsString() {
        assertFalse(new JsonObject().isString());
        assertFalse(new JsonArray().isString());
        assertTrue(new JsonString("foo").isString());
        assertFalse(new JsonNumber("123").isString());
        assertFalse(JsonBoolean.of(false).isString());
        assertFalse(JsonNull.INSTANCE.isString());
    }

    @Test
    public void testIsNumber() {
        assertFalse(new JsonObject().isNumber());
        assertFalse(new JsonArray().isNumber());
        assertFalse(new JsonString("foo").isNumber());
        assertTrue(new JsonNumber("123").isNumber());
        assertFalse(JsonBoolean.of(false).isNumber());
        assertFalse(JsonNull.INSTANCE.isNumber());
    }


    @Test
    public void testIsBoolean() {
        assertFalse(new JsonObject().isBoolean());
        assertFalse(new JsonArray().isBoolean());
        assertFalse(new JsonString("foo").isBoolean());
        assertFalse(new JsonNumber("123").isBoolean());
        assertTrue(JsonBoolean.of(false).isBoolean());
        assertFalse(JsonNull.INSTANCE.isBoolean());
    }

    @Test
    public void testIsNull() {
        assertFalse(new JsonObject().isNull());
        assertFalse(new JsonArray().isNull());
        assertFalse(new JsonString("foo").isNull());
        assertFalse(new JsonNumber("123").isNull());
        assertFalse(JsonBoolean.of(false).isNull());
        assertTrue(JsonNull.INSTANCE.isNull());
    }
}
