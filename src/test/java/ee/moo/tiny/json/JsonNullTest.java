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

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class JsonNullTest {

    @Test
    public void nullTest() {
        var n = JsonNull.INSTANCE;

        assertEquals(JsonType.JSON_NULL, n.getType());
        assertEquals("null", n.toJson());
        assertEquals("null", n.toJson(2));
        assertNull(n.asNull());
    }

    @Test
    public void testToString() {
        assertEquals("null", JsonNull.INSTANCE.toString());
    }

    @Test
    public void testHashCode() {
        assertEquals(Objects.hashCode(null), JsonNull.INSTANCE.hashCode());
    }

    @SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquas() {
        assertTrue(JsonNull.INSTANCE.equals(JsonNull.INSTANCE));
        assertFalse(JsonNull.INSTANCE.equals(new JsonString("hello")));
        assertFalse(JsonNull.INSTANCE.equals(new Object()));
    }
}
