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

import static org.junit.jupiter.api.Assertions.*;

public class JsonBooleanTest {

    @Test
    public void testBoolean() {
        var t = JsonBoolean.of(true);
        var f = JsonBoolean.of(false);

        assertTrue(t.value());
        assertFalse(f.value());
        assertTrue(t.asBoolean());
        assertFalse(f.asBoolean());

        assertEquals(JsonType.JSON_BOOLEAN, t.getType());
        assertEquals(JsonType.JSON_BOOLEAN, f.getType());

        assertEquals("true", t.toJson(JsonWriteMode.PRETTY));
        assertEquals("true", t.toJson(JsonWriteMode.PRETTY, 2));

        assertEquals("false", f.toJson(JsonWriteMode.PRETTY));
        assertEquals("false", f.toJson(JsonWriteMode.PRETTY, 2));
    }

    @Test
    public void testToString() {
        assertEquals("true", JsonBoolean.of(true).toString());
        assertEquals("false", JsonBoolean.of(false).toString());
    }

    @Test
    public void testHashCode() {
        assertEquals(Boolean.hashCode(true), JsonBoolean.of(true).hashCode());
        assertEquals(Boolean.hashCode(false), JsonBoolean.of(false).hashCode());
    }

    @SuppressWarnings({"EqualsWithItself", "SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() {
        var b1 = JsonBoolean.of(true);
        var b2 = JsonBoolean.of(true);

        assertTrue(b1.equals(b1));
        assertTrue(b1.equals(b2));
        assertFalse(b1.equals(JsonBoolean.of(false)));
        assertFalse(b1.equals(new JsonString("tere")));
        assertFalse(b1.equals(null));
        assertFalse(b1.equals(new Object()));
    }
}
