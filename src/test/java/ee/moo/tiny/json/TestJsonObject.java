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

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonObject {

    @Test
    public void testObject() {
        var o1 = new JsonObject();
        var o2 = new JsonObject();
        o2.put("message", "hello");
        o2.put("status", 42);
        o2.put("read", false);
        o2.put("states1", Set.of("foo"));
        o2.put("states2", List.of("foo", "bar", "foo"));

        var json = """
            {
              "message": "hello",
              "status": 42,
              "read": false,
              "states1": [
                "foo"
              ],
              "states2": [
                "foo",
                "bar",
                "foo"
              ]
            }
            """.trim();

        assertEquals("{}", o1.toJson(JsonWriteMode.PRETTY));
        assertEquals(json, o2.toJson(JsonWriteMode.PRETTY));

        assertTrue(o1.isEmpty());
        assertFalse(o2.isEmpty());

        assertEquals(0, o1.size());
        assertEquals(5, o2.size());

        assertEquals(Set.of(), o1.keySet());
        assertEquals(Set.of("message", "status", "read", "states1", "states2"), o2.keySet());

        assertEquals("hello", o2.getString("message"));
        assertEquals("hello", o2.getOptionalString("message"));
        assertNull(o2.getOptionalString("foo"));

        assertEquals(Set.of(new JsonString("foo")), o2.getSet("states1"));
        assertEquals(Set.of("foo"), o2.getSet("states1", String.class));

        assertEquals(
            List.of(
                new JsonString("foo"),
                new JsonString("bar"),
                new JsonString("foo")
            ),
            o2.getList("states2")
        );
        assertEquals(List.of("foo", "bar", "foo"), o2.getList("states2", String.class));

        assertEquals(42, o2.getInt("status"));
        assertEquals(42L, o2.getLong("status"));
        assertEquals(42, o2.getInt("status", 42));
        assertEquals(44, o2.getInt("status2", 44));

        assertEquals(42.0, o2.getDouble("status"));
        assertEquals(42.0F, o2.getFloat("status"));
        assertEquals(42.0, o2.getDouble("status"));

        assertFalse(o2.getBoolean("read"));

        var o3 = new JsonObject();
        var o4 = new JsonObject();
        o4.put("message", "tere");
        o3.put("object", o4);

        assertEquals("tere",
            o3.getObject("object").asObject().get("message").asString());


    }

    @Test
    public void testEnum() {
        var o = new JsonObject();
        o.put("type1", Type.TYPE_1.toString());
        o.put("type2", Type.TYPE_2.toString());
        o.put("type3", "type3");

        assertEquals(Type.TYPE_1, o.getEnum("type1", Type.class));
        assertEquals(Type.TYPE_2, o.getEnum("type2", Type.class));

        assertThrows(JsonException.class, () -> {
            o.getEnum("type3", Type.class);
        });
    }

    @Test
    public void testToString() {
        var o = new JsonObject();
        o.put("Hello", "World!");

        assertEquals("""
                {
                  "Hello": "World!"
                }
                """.trim(),
            o.toString()
        );
    }

    @Test
    public void testHashCode() {
        var o = new JsonObject();
        o.put("Hello", "World");

        Map<String, JsonValue> m = Map.of(
            "Hello", new JsonString("World")
        );

        assertEquals(o.hashCode(), m.hashCode());
    }

    @SuppressWarnings({"EqualsWithItself", "MisorderedAssertEqualsArguments", "AssertBetweenInconvertibleTypes"})
    @Test
    public void testEquals() {
        var o1 = new JsonObject();
        o1.put("hello", "World");
        o1.put("state", 42);

        var o2 = new JsonObject();
        o2.put("hello", "World");
        o2.put("state", 42);

        var o3 = new JsonObject();
        o2.put("hello", "World");

        assertEquals(o1, o1);
        assertEquals(o1, o2);
        assertNotEquals(o1, o3);
        assertNotEquals(o2, o3);

        assertNotEquals(o1, new JsonString("Tere"));
        assertNotEquals(o1, new Object());
        assertNotEquals(o1, null);
    }

    public enum Type {
        TYPE_1,
        TYPE_2
    }
}
