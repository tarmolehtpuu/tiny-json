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

public class JsonStringTest {

    public enum Type {
        TYPE_1,
        TYPE_2
    }

    @Test
    public void testString() {
        var s = new JsonString("hello");

        assertEquals("hello", s.value());
        assertEquals(JsonType.JSON_STRING, s.getType());
        assertEquals("\"hello\"", s.toJson(JsonWriteMode.PRETTY));
        assertEquals("\"hello\"", s.toJson(JsonWriteMode.PRETTY, 2));
        assertEquals("hello", s.asString());
    }

    @Test
    public void testAsFloat() {
        assertEquals(0.5F, new JsonString("0.5").asFloat());
    }

    @Test
    public void testAsDouble() {
        assertEquals(0.4, new JsonString("0.4").asDouble());
    }

    @Test
    public void testAsInt() {
        assertEquals(42, new JsonString("42").asInt());
    }

    @Test
    public void testAsLong() {
        assertEquals(44L, new JsonString("44").asLong());
    }

    @Test
    public void testAsType() {
        var s1 = new JsonString("TYPE_1");
        var s2 = new JsonString("TYPE_2");
        var s3 = new JsonString("TYPE_3");

        assertEquals(Type.TYPE_1, s1.asEnum(Type.class));
        assertEquals(Type.TYPE_2, s2.asEnum(Type.class));

        assertThrows(JsonException.class, () -> {
            s3.asEnum(Type.class);
        });
    }

    @Test
    public void testToString() {
        assertEquals("foo", new JsonString("foo").toString());
    }

    @Test
    public void testHashCode() {
        assertEquals("foo".hashCode(), new JsonString("foo").hashCode());
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() {
        var s = new JsonString("tere");

        assertTrue(s.equals(s));
        assertTrue(s.equals(new JsonString("tere")));
        assertFalse(s.equals(new JsonString("hello")));
        assertFalse(new JsonString(null).equals(s));
        assertTrue(new JsonString(null).equals(new JsonString(null)));
        assertFalse(s.equals(new JsonString(null)));
        assertFalse(s.equals(new JsonNumber("tere")));
        assertFalse(s.equals(new Object()));
    }
}
