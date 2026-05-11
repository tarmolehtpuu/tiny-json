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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonParserTest {

    @Test
    public void testParser() {
        var json = """
            {
              "message": "Hello World!"
            }
            """;

        assertEquals("Hello World!", new JsonParser(json).parse().asObject().get("message").asString());
    }

    @Test
    public void testNull() {
        assertThrows(JsonException.class, () -> {
            new JsonParser(null);
        });

        assertThrows(JsonException.class, () -> {
            new JsonParser(null, 2);
        });
    }

    @Test
    public void testDepth() {
        var json1 = "[[[1]]]";
        var json2 = """
            {
              "object": {
                "object": {
                }
              }
            }
            """;

        new JsonParser(json1, 4).parse();
        new JsonParser(json2, 4).parse();

        assertThrows(JsonException.class, () -> {
            new JsonParser(json1, 3).parse();
        });
        assertThrows(JsonException.class, () -> {
            new JsonParser(json2, 3).parse();
        });
    }
}
