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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonArrayTest {

    @Test
    public void testArray() {
        var a1 = new JsonArray();
        var a2 = new JsonArray(List.of(
            new JsonString("foo"),
            new JsonString("bar")
        ));
        a2.add(new JsonString("baz"));

        assertEquals(JsonType.JSON_ARRAY, a1.getType());
        assertEquals(JsonType.JSON_ARRAY, a2.getType());

        assertEquals("[]", a1.toJson());
        assertEquals("""
            [
              "foo",
              "bar",
              "baz"
            ]
            """.trim(), a2.toJson());
    }

    @Test
    public void testNull() {
        var a = new JsonArray(null);
        assertEquals("[]", a.toJson());
    }

    @Test
    public void testAsList() {
        var a1 = new JsonArray(List.of(new JsonString("foo"), new JsonString("bar")));
        var a2 = new JsonArray(List.of(new JsonNumber("123"), new JsonNumber("456")));
        var a3 = new JsonArray(List.of(JsonBoolean.of(true), JsonBoolean.of(true), JsonBoolean.of(false)));

        assertEquals(List.of("foo", "bar"), a1.asList(String.class));
        assertEquals(List.of(123, 456), a2.asList(Integer.class));
        assertEquals(List.of(123L, 456L), a2.asList(Long.class));
        assertEquals(List.of(123.0F, 456.0F), a2.asList(Float.class));
        assertEquals(List.of(123.0, 456.0), a2.asList(Double.class));
        assertEquals(List.of(true, true, false), a3.asList(Boolean.class));
    }

    @Test
    public void testAsSet() {
        var a = new JsonArray(List.of(new JsonString("foo"), new JsonString("foo")));

        Set<JsonValue> s1 = a.asSet();
        Set<JsonValue> s2 = Set.of(new JsonString("foo"));

        assertEquals(s1, s2);
        assertEquals(Set.of("foo"), a.asSet(String.class));
    }
}
