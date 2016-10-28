/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * The Federal Office of Administration (Bundesverwaltungsamt, BVA)
 * licenses this file to you under the Apache License, Version 2.0 (the
 * License). You may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package de.bund.bva.pliscommon.plissonderzeichen.core.transformation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.bund.bva.isyfact.logging.IsyLogger;
import de.bund.bva.isyfact.logging.IsyLoggerFactory;
import de.bund.bva.pliscommon.plissonderzeichen.core.transformation.ZeichenKategorie;

import junit.framework.TestCase;

/**
 * Testet den IdentischerTransformator.
 *
 */
public class IdentischerTransformatorTest extends TestCase {

    /** Transformator. */
    public AbstractTransformator transformator;

    /** Logger. */
    private static IsyLogger LOG = IsyLoggerFactory.getLogger(IdentischerTransformatorTest.class);

    @Override
    public void setUp() {
        this.transformator = new IdentischerTransformator();
    }

    public void testInitialisierung() {
        this.transformator.initialisiere(null);
    }

    public void testTransformiere() {

        this.transformator.initialisiere(null);

        String toTransform = "AaBZzöTä?/!$Ô+*#-";
        String expected = "AaBZzöTä?/!$Ô+*#-";

        LOG.debug("Eingabe: " + toTransform);
        LOG.debug("Erwartete Ausgabe: " + expected);

        String transformed = this.transformator.transformiere(toTransform);
        LOG.debug("Ausgabe: " + transformed);

        assertEquals(expected, transformed);
    }

    public void testGueltigeZeichen() {

        this.transformator.initialisiere(null);

    }

    public void testRegulaererAusdruckSpeziell() {

        this.transformator.initialisiere(null);

        String regex = this.transformator.getRegulaererAusdruck(new String[] { ZeichenKategorie.ALLE });

        Pattern pattern = Pattern.compile(regex);

        String testpatternGueltig_1 = "Î";
        String testpatternGueltig_2 = "\u004A\u030C";
        String testpatternGueltig_3 = "\u006C\u0302";
        String testpatternUngueltig_1 = "B\u0302uivir%$";
        String testpatternUngueltig_2 = "\u0302";
        Matcher matcherGueltig_1 = pattern.matcher(testpatternGueltig_1);
        Matcher matcherGueltig_2 = pattern.matcher(testpatternGueltig_2);
        Matcher matcherGueltig_3 = pattern.matcher(testpatternGueltig_3);
        Matcher matcherUngueltig_1 = pattern.matcher(testpatternUngueltig_1);
        Matcher matcherUngueltig_2 = pattern.matcher(testpatternUngueltig_2);

        assertTrue(matcherGueltig_1.matches());
        assertTrue(matcherGueltig_2.matches());
        assertTrue(matcherGueltig_3.matches());
        assertFalse(matcherUngueltig_1.matches());
        assertFalse(matcherUngueltig_2.matches());
    }

    public void testIsGueltigesString() {

        this.transformator.initialisiere(null);

        String testpatternGueltig_1 = "Î";
        String testpatternGueltig_2 = "\u004A\u030C";
        String testpatternGueltig_3 = "\u006C\u0302";
        String testpatternUngueltig_1 = "B\u0302uivir%$";
        String testpatternUngueltig_2 = "\u0302";
        String testpatternUngueltig_3 = "\u006C\u0302uivir%$";

        assertTrue(this.transformator.isGueltigerString(testpatternGueltig_1,
            new String[] { ZeichenKategorie.ALLE }));
        assertTrue(this.transformator.isGueltigerString(testpatternGueltig_2,
            new String[] { ZeichenKategorie.ALLE }));
        assertTrue(this.transformator.isGueltigerString(testpatternGueltig_3,
            new String[] { ZeichenKategorie.ALLE }));
        assertFalse(this.transformator.isGueltigerString(testpatternUngueltig_1,
            new String[] { ZeichenKategorie.ALLE }));
        assertFalse(this.transformator.isGueltigerString(testpatternUngueltig_2,
            new String[] { ZeichenKategorie.ALLE }));
        assertFalse(this.transformator.isGueltigerString(testpatternUngueltig_3,
            new String[] { ZeichenKategorie.LETTER }));
    }

}
