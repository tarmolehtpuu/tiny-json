# tiny-json

Tiny JSON library for use in apps and games that are compiled with GraalVM Native Image. This library uses no reflection
and is very small (20 kilobytes JAR file).

Passes all tests from https://json.org/JSON_checker test suite.

## Import

### Maven

#### ~/.m2/settings.xml
```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>GITHUB_USERNAME</username>
            <password>GITHUB_TOKEN</password>
        </server>
    </servers>
</settings>
```

#### pom.xml

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/tarmolehtpuu/tiny-json</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>ee.moo</groupId>
        <artifactId>tiny-json</artifactId>
        <version>0.0.3</version>
    </dependency>
</dependencies>
```

### Gradle

```kotlin
repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/tarmolehtpuu/tiny-json")
        credentials {
            username = project.findProperty("github.user") as String? ?: System.getenv("GITHUB_USER")
            password = project.findProperty("github.token") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("ee.moo:tiny-json:0.0.3")
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

### Write JSON

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
