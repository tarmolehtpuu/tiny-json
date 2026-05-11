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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonExceptionTest {

    @Test
    public void testException() {
        var cause = new RuntimeException("The Cause");
        var e1 = new JsonException("Test");
        var e2 = new JsonException("Test with: %s", "args");
        var e3 = new JsonException("Test", cause);
        var e4 = new JsonException("Test with: %s", cause, "args");

        assertEquals("Test", e1.getMessage());
        assertEquals("Test with: args", e2.getMessage());
        assertEquals("Test", e3.getMessage());
        assertEquals(cause, e3.getCause());
        assertEquals("Test with: args", e4.getMessage());
        assertEquals(cause, e4.getCause());
    }
}
