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

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.stream.Stream;

import static ee.moo.tiny.json.TestHelper.resource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonOrgTest {

    public static Stream<TestPass> pass() {
        return Stream.of(
            new TestPass("/json.org/pass1.json"),
            new TestPass("/json.org/pass2.json"),
            new TestPass("/json.org/pass3.json")
        );
    }

    public static Stream<TestFail> fail() {
        return Stream.of(
            new TestFail("/json.org/fail1.json", "Expecting a JSON object {} or JSON array []"),
            new TestFail("/json.org/fail2.json", "Unexpected end of input"),
            new TestFail("/json.org/fail3.json", "Expected '\"', got 'u' at position 2"),
            new TestFail("/json.org/fail4.json", "Unexpected character at position 15: ']'"),
            new TestFail("/json.org/fail5.json", "Unexpected character at position 22: ','"),
            new TestFail("/json.org/fail6.json", "Unexpected character at position 4: ','"),
            new TestFail("/json.org/fail7.json", "Unexpected trailing data at position 25: ','"),
            new TestFail("/json.org/fail8.json", "Unexpected trailing data at position 15: ']'"),
            new TestFail("/json.org/fail9.json", "Expected '\"', got '}' at position 22"),
            new TestFail("/json.org/fail10.json", "Unexpected trailing data at position 34: '\"'"),
            new TestFail("/json.org/fail11.json", "Expected ',' or '}', got: + at position 25"),
            new TestFail("/json.org/fail12.json", "Unexpected character at position 23: 'a'"),
            new TestFail("/json.org/fail13.json", "Numbers can not have leading zeroes at position: 39"),
            new TestFail("/json.org/fail14.json", "Expected ',' or '}', got: x at position 27"),
            new TestFail("/json.org/fail15.json", "Invalid escape character at position 29: \\"),
            new TestFail("/json.org/fail16.json", "Unexpected character at position 1: '\\'"),
            new TestFail("/json.org/fail17.json", "Invalid escape character at position 29: \\"),
            new TestFail("/json.org/fail18.json", "Max depth reached: 20"),
            new TestFail("/json.org/fail19.json", "Expected ':', got 'n' at position 18"),
            new TestFail("/json.org/fail20.json", "Unexpected character at position 16: ':'"),
            new TestFail("/json.org/fail21.json", "Expected ':', got ',' at position 26"),
            new TestFail("/json.org/fail22.json", "Expected ',' or ']', got: : at position 25"),
            new TestFail("/json.org/fail23.json", "Expected 'e', got 't' at position 18"),
            new TestFail("/json.org/fail24.json", "Unexpected character at position 1: '''"),
            new TestFail("/json.org/fail25.json", "Unescaped control char (horizontal tab) not allowed in string at position: 3"),
            new TestFail("/json.org/fail26.json", "Invalid escape character at position 6: \\"),
            new TestFail("/json.org/fail27.json", "Unescaped control char (linefeed) not allowed in string at position: 7"),
            new TestFail("/json.org/fail28.json", "Invalid escape character at position 7: \\"),
            new TestFail("/json.org/fail29.json", "Number can not have leading zeroes at position: 1"),
            new TestFail("/json.org/fail30.json", "Number can not have leading zeroes at position: 1"),
            new TestFail("/json.org/fail31.json", "Number can not have leading zeroes at position: 1"),
            new TestFail("/json.org/fail32.json", "Unexpected end of input"),
            new TestFail("/json.org/fail33.json", "Expected ',' or ']', got: } at position 11")
        );
    }

    @ParameterizedTest
    @MethodSource("pass")
    public void testPass(TestPass pass) {
        assertNotNull(Json.read(resource(pass.file())));
    }

    @ParameterizedTest
    @MethodSource("fail")
    public void testFail(TestFail fail) {
        var e = Assertions.assertThrows(JsonException.class, () -> {
            Json.read(resource(fail.file()));
        });
        assertEquals(fail.message(), e.getMessage());
    }

    public record TestPass(String file) {
        @Override
        public @NonNull String toString() {
            return new File(file).getName();
        }
    }

    public record TestFail(String file, String message) {
        @Override
        public @NonNull String toString() {
            return new File(file).getName();
        }
    }
}
