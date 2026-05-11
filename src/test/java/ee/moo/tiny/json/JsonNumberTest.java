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

public class JsonNumberTest {

    @Test
    public void testNumber() {
        var n = new JsonNumber("123");

        assertEquals("123", n.value());
        assertEquals(JsonType.JSON_NUMBER, n.getType());
        assertEquals("123", n.toJson());
        assertEquals("123", n.toJson(2));
        assertEquals(123, n.asInt());
        assertEquals(123L, n.asLong());
        assertEquals(123.0F, n.asFloat());
        assertEquals(123.0, n.asDouble());
    }

    @Test
    public void testToString() {
        assertEquals("123", new JsonNumber("123").toString());
    }

    @Test
    public void testHashCode() {
        assertEquals("123".hashCode(), new JsonNumber("123").hashCode());
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself"})
    @Test
    public void testEquals() {
        var n = new JsonNumber("456");

        assertTrue(n.equals(n));
        assertTrue(n.equals(new JsonNumber("456")));
        assertFalse(n.equals(new JsonNumber("-1")));
        assertFalse(n.equals(new Object()));
    }
}
