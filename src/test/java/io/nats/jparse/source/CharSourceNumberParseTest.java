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
package io.nats.jparse.source;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharSourceNumberParseTest {


    @Test
    void longUpperRange() {
        String longMax = "" + Long.MAX_VALUE;
        final CharSource charSource = Sources.stringSource(longMax);
        assertEquals(Long.MAX_VALUE, charSource.getLong(0, longMax.length()));
    }

    @Test
    void longMinRange() {
        String str = "" + Long.MIN_VALUE;
        final CharSource charSource = Sources.stringSource(str);
        assertEquals(Long.MIN_VALUE, charSource.getLong(0, str.length()));
    }

    @Test
    void intMaxRange() {
        String str = "" + Integer.MAX_VALUE;
        final CharSource charSource = Sources.stringSource(str);
        assertEquals(Integer.MAX_VALUE, charSource.getLong(0, str.length()));
    }

    @Test
    void intMinRange() {
        String str = "" + Integer.MIN_VALUE;
        final CharSource charSource = Sources.stringSource(str);
        assertEquals(Integer.MIN_VALUE, charSource.getLong(0, str.length()));
    }

    @Test
    void parseMissingDecimalMantissa() {
        //.....................01234
        final String string = "9.e+ ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();

        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }


    @Test
    void parseMissingNumbersAfterExponent() {
        //.....................01234
        final String string = "1.0e+ ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }

    @Test
    void parseMissingNumbersAfterExponent4() {
        //.....................01234
        final String string = "0.3e+ ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }

    @Test
    void parseMissingDecimalMantissa2() {
        //.....................01234
        final String string = "2.e3";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }


    @Test
    void parseMissingNumbersAfterExponent2() {
        //.....................01234
        final String string = "1.0e- ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }

    @Test
    void parseMissingNumbersAfterExponent3() {
        //.....................01234
        final String string = "2.e-3 ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }


    @Test
    void startsWith0() {
        //.....................01234
        final String string = "012 ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }

    @Test
    void startsWithPlus() {
        //.....................01234
        final String string = "+1 ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }


    @Test
    void bigExponentMissingNumber() {
        //.....................01234
        final String string = "0E+ ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }

    /*
     * PASS! ./src/test/resources/validation/n_number_neg_int_starting_with_zero.json
     * [-012]
     *
     * PASS! ./src/test/resources/validation/n_number_minus_space_1.json
     * [- 1]
     * PASS! ./src/test/resources/validation/n_number_neg_real_without_int_part.json
     * [-.123]
     */

    @Test
    void negativeStartsWith0() {
        //.....................01234
        final String string = "-012 ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }

    @Test
    void cantBeJustAMinus() {
        //.....................01234
        final String string = "- 1 ";
        final CharSource charSource = Sources.stringSource(string);
        charSource.next();
        try {
            charSource.findEndOfNumber();
            assertTrue(false);
        } catch (Exception ex) {
        }
    }

}
