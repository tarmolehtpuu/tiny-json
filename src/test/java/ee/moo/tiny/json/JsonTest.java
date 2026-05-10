package ee.moo.tiny.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
