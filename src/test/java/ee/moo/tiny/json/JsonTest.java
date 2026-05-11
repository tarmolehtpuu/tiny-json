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

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonTest {

    @Test
    public void testWriteObject() {
        var object = new JsonObject();
        object.put("foo", "foo");
        object.put("bar", 42);
        object.put("baz", Boolean.TRUE);

        var json = """
            {
              "foo": "foo",
              "bar": 42,
              "baz": true
            }
            """;

        assertEquals(json, Json.write(object));
    }

    @Test
    public void testWriteArray() {
        var array = new JsonArray();
        array.add(new JsonString("foo"));
        array.add(new JsonString("bar"));
        array.add(new JsonString("baz"));

        var json = """
            [
              "foo",
              "bar",
              "baz"
            ]
            """;

        assertEquals(json, Json.write(array));
    }

    @Test
    public void testWriteNested() {
        var array1 = new JsonArray();
        array1.add(new JsonString("foo"));
        array1.add(new JsonString("bar"));
        array1.add(new JsonString("baz"));

        var array2 = new JsonArray();
        array2.add(new JsonNumber("4"));
        array2.add(new JsonNumber("17"));
        array2.add(new JsonNumber("44"));

        var object1 = new JsonObject();
        object1.put("id", new JsonNumber("76"));
        object1.put("labels", array1);

        var object2 = new JsonObject();
        object2.put("celsisus", new JsonNumber("38.0"));
        object2.put("fahrenheit", new JsonNumber("100.4"));

        var object3 = new JsonObject();
        object3.put("temperature", object2);

        var array3 = new JsonArray();
        array3.add(object3);
        array3.add(object3);

        var object4 = new JsonObject();
        object4.put("id", new JsonNumber("77"));
        object4.put("item", object1);
        object4.put("active", JsonBoolean.of(false));
        object4.put("status", JsonNull.INSTANCE);
        object4.put("temperatures", array3);

        var json = """
            {
              "id": 77,
              "item": {
                "id": 76,
                "labels": [
                  "foo",
                  "bar",
                  "baz"
                ]
              },
              "active": false,
              "status": null,
              "temperatures": [
                {
                  "temperature": {
                    "celsisus": 38.0,
                    "fahrenheit": 100.4
                  }
                },
                {
                  "temperature": {
                    "celsisus": 38.0,
                    "fahrenheit": 100.4
                  }
                }
              ]
            }
            """;

        assertEquals(json, Json.write(object4));
    }

    @Test
    public void testRead() {
        var json = """
            {"message": "tere"}
            """;

        assertEquals("tere",
            Json.read(json).asObject().get("message").asString());

        assertThrows(JsonException.class, () -> {
            String s = null;
            Json.read(s);
        });
    }

    @Test
    public void testReadBytes() {
        var json = """
            {
                "message": "Hi"
            }
            """.getBytes();

        assertEquals("Hi",
            Json.read(json).asObject().get("message").asString());

        assertThrows(JsonException.class, () -> {
            byte[] bytes = null;
            Json.read(bytes);
        });
    }

    @Test
    public void testReadFile() throws IOException {
        var path = Files.createTempFile("tiny-json", ".json");
        Files.writeString(path, "[1, 2, 3]");

        assertEquals(List.of(1, 2, 3), Json.read(path.toFile()).asList(Integer.class));

        assertThrows(JsonException.class, () -> {
            File file = null;
            Json.read(file);
        });
    }

    @Test
    public void testReadStream() {
        var reader = new StringReader("""
            {"message": "HI!"}
            """);

        assertEquals("HI!",
            Json.read(reader).asObject().get("message").asString());

        assertThrows(JsonException.class, () -> {
            StringReader r = null;
            Json.read(r);
        });
    }

    @Test
    public void testReadObject() {
        var json1 = """
            {
                "message": "Hello!"
            }
            """;
        var json2 = """
            {
                "message": "World!"
            }
            """.getBytes();

        assertEquals("Hello!", Json.readObject(json1)
            .get("message").asString());
        assertEquals("World!", Json.readObject(json2)
            .get("message").asString());

    }
}
