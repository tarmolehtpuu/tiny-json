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


public enum JsonType {
    JSON_ARRAY,
    JSON_BOOLEAN,
    JSON_NULL,
    JSON_NUMBER,
    JSON_OBJECT,
    JSON_STRING;

    public boolean isArray() {
        return this == JSON_ARRAY;
    }

    public boolean isBoolean() {
        return this == JSON_BOOLEAN;
    }

    public boolean isNull() {
        return this == JSON_NULL;
    }

    public boolean isNumber() {
        return this == JSON_NUMBER;
    }

    public boolean isObject() {
        return this == JSON_OBJECT;
    }

    public boolean isString() {
        return this == JSON_STRING;
    }
}