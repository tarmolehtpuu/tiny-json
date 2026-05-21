# tiny-json ![Static Badge](https://img.shields.io/badge/version-0.0.7-blue) ![Endpoint Badge](https://img.shields.io/endpoint?url=https%3A%2F%2Fgist.githubusercontent.com%2Ftarmolehtpuu%2F0c07bae091e8ac26aa21067c52892d66%2Fraw%2Fec90481c8c882fda64a36f968b825eaca1297cf8%2Ftiny-json-junit-tests.json) ![Endpoint Badge](https://img.shields.io/endpoint?url=https%3A%2F%2Fgist.githubusercontent.com%2Ftarmolehtpuu%2F0c07bae091e8ac26aa21067c52892d66%2Fraw%2Fec90481c8c882fda64a36f968b825eaca1297cf8%2Ftiny-json-jacoco-coverage.json)


Tiny JSON library for use in apps and games that are compiled with GraalVM Native Image. This library uses no reflection
and is very small (20 kilobytes JAR file).

Passes all tests from https://json.org/JSON_checker test suite.

## Import

### Maven

```xml
<repositories>
    <repository>
        <id>moo</id>
        <url>https://repo.repsy.io/moo/maven</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>ee.moo</groupId>
        <artifactId>tiny-json</artifactId>
        <version>0.0.7</version>
    </dependency>
</dependencies>
```

### Gradle

```kotlin
repositories {
    mavenCentral()
    maven {
        name = "moo"
        url = uri("https://repo.repsy.io/moo/maven")
    }
}

dependencies {
    implementation("ee.moo:tiny-json:0.0.7")
}
```

## Usage

### Read JSON

```java

var data = """
{
    "message": "Hello World!",
    "targets": ["WORLD", "UNIVERSE"],
    "size": 42,
    "seen": true
}
""";

var json = Json.read(data).asObject();

System.out.println(json.get("message").asString());

for (var t : json.get("targets").asList()) {
    System.out.println(t.asString());
}

System.out.println(json.get("size").asInt());
System.out.println(json.get("seen").asBoolean());

```

#### Output

```
Hello World!
WORLD
UNIVERSE
42
true
```

### Write JSON (Pretty)

```java
var object = new JsonObject();

object.put("message", "Hello World!");
object.put("targets", List.of("WORLD", "UNIVERSE"));
object.put("size", 42);
object.put("seen", true);

System.out.println(Json.write(object));
```

#### Output

```json
{
  "message": "Hello World!",
  "targets": [
    "WORLD",
    "UNIVERSE"
  ],
  "size": 42,
  "seen": true
}
```


### Write JSON (Normal)

```java
var object = new JsonObject();

object.put("message", "Hello World!");
object.put("targets", List.of("WORLD", "UNIVERSE"));
object.put("size", 42);
object.put("seen", true);

System.out.println(Json.write(object));
```

#### Output

```json
{"message": "Hello World","targets": ["WORLD","UNIVERSE"],"size": 42,"seen": true}
```