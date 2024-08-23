/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.parser;


import io.nats.jparse.parser.indexoverlay.JsonFastParser;
import io.nats.jparse.parser.indexoverlay.JsonStrictParser;


/**
 * Builder class for creating instances of `JsonParser`.
 *
 * @see JsonParserBuilder#build() for details on how the build logic is formed.
 */
public class JsonParserBuilder {


    /**
     * Whether to use strict parsing when parsing JSON.
     */
    private boolean strict = false;

    /**
     * Whether object keys can be encoded when parsing JSON.
     */
    private boolean objectsKeysCanBeEncoded;



    /**
     * Builds a new instance of `JsonParser`.
     *
     */
    public JsonParserBuilder() {

    }

    /**
     * Returns a new instance of `JsonParserBuilder`.
     *
     * @return a new instance
     */
    public static JsonParserBuilder builder() {
        return new JsonParserBuilder();
    }



    /**
     * Gets whether to use strict parsing when parsing JSON.
     *
     * @return `true` if strict parsing is used, `false` otherwise
     */
    public boolean strict() {
        return strict;
    }

    /**
     * Sets whether to use strict parsing when parsing JSON.
     *
     * @param strict `true` to use strict parsing, `false` otherwise
     * @return the modified builder
     */
    public JsonParserBuilder setStrict(boolean strict) {
        this.strict = strict;
        return this;
    }

    /**
     * Gets whether object keys can be encoded when parsing JSON.
     *
     * @return `true` if object keys can be encoded, `false` otherwise
     */
    public boolean objectsKeysCanBeEncoded() {
        return objectsKeysCanBeEncoded;
    }

    /**
     * Sets whether object keys can be encoded when parsing JSON.
     *
     * @param objectsKeysCanBeEncoded `true` to allow encoded keys, `false` otherwise
     * @return the modified builder
     */
    public JsonParserBuilder setObjectsKeysCanBeEncoded(boolean objectsKeysCanBeEncoded) {
        this.objectsKeysCanBeEncoded = objectsKeysCanBeEncoded;
        return this;
    }


    /**
     * Returns a new instance of `JsonParser`.
     * <p>
     * The `JsonParserBuilder.build()` function returns an instance of `JsonParser`.
     * If any of the following conditions are met: `supportNoQuoteKeys` is `true`,
     * `allowHashComment` is `true`, `allowSlashSlashComment` is `true`,
     * `allowSlashStarComment` is `true`, or `parseKey` is not `null`,
     * the function sets up a parse function table for the JSON parser. It then
     * returns a new instance of `JsonParser`.
     * <p>
     * The function first checks if any of the conditions for setting up a parse
     * function table are true. If so, it sets up an array of `ParseFunction`
     * objects and populates it with parsing functions based on the type of JSON
     * element being parsed.
     * <p>
     * If `isAllowHashComment()` is true, the function sets the hash character `#`
     * to a function that skips the comment until the end of the line.
     * <p>
     * If `isSupportNoQuoteKeys()` is true and `getParseKey()` is null, the function
     * sets the parse function for keys to `JsonParserFunctions::parseKeyNoQuote`.
     * <p>
     * If `isAllowSlashStarComment()` or `isAllowSlashSlashComment()` is true, the
     * function sets the forward slash `/` character to a function that handles
     * comments. If the next character is another forward slash, the function skips
     * to the end of the line. If the next character is an asterisk, it skips
     * everything until it reaches a closing asterisk-slash sequence.
     * <p>
     * After setting up the parse function table, the function returns a new
     * instance of `JsonFuncParser` with the table, default parse function, and
     * parse function for keys.
     * <p>
     * If none of the above conditions are met but `strict()` is true, the function
     * returns a new instance of `JsonStrictParser`. If `strict()` is false, the
     * function returns a new instance of `JsonFastParser`.
     * <p>
     * The `JsonParserBuilder.build()` function checks if any of the conditions for
     * setting up a parse function table are true. If so, it sets up an array of
     * `ParseFunction` objects and populates it with parsing functions. If none of
     * those conditions are true but `strict()` is true, it returns a new instance
     * of `JsonStrictParser`. If `strict()` is false, it returns a new instance of
     * `JsonFastParser`.
     * <p>
     * The `ParseFunction` array is populated with parsing functions based on the
     * type of JSON element being parsed. For example, the string start token is
     * assigned the `JsonParserFunctions::parseString` function, while the null
     * start token is assigned the `JsonParserFunctions::parseNull` function.
     * The `JsonParserFunctions::parseNumber` function is assigned to all numbers,
     * and the minus and plus signs.
     * <p>
     * If `isAllowHashComment()` is true, the function sets the hash character `#`
     * to a function that skips the comment until the end of the line.
     * <p>
     * If `isSupportNoQuoteKeys()` is true and `getParseKey()` is null, the function
     * sets the parse function for keys to `JsonParserFunctions::parseKeyNoQuote`.
     * <p>
     * If `isAllowSlashStarComment()` or `isAllowSlashSlashComment()` is true, the
     * function sets the forward slash `/` character to a function that handles
     * comments. If the next character is another forward slash, the function skips
     * to the end of the line. If the next character is an asterisk, it skips
     * everything until it reaches a closing asterisk-slash  sequence.
     * <p>
     * After setting up the parse function table, the function returns a new
     * instance of `JsonFuncParser` with the parse function table, default parse function, and
     * parse function for keys.
     *
     * @return a new instance of `JsonParser`
     */
    public JsonParser build() {

        if (strict()) {
            return new JsonStrictParser(objectsKeysCanBeEncoded());
        } else {
            return new JsonFastParser(objectsKeysCanBeEncoded());
        }
    }

}
