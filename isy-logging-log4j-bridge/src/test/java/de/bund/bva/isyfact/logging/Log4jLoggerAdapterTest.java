package de.bund.bva.isyfact.logging;

/*
 * #%L
 * isy-logging-log4j-bridge
 * %%
 * 
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import de.bund.bva.isyfact.log4jbridge.Log4jLoggerAdapter;

/**
 * Testfälle für den Log4jLoggerAdapter.
 */
public class Log4jLoggerAdapterTest {

    /**
     * Testet das erstellen eines Logeintrags in einem ungültigen Loglevel.
     */
    @Test
    public void testUngueltigesLogLevel() {
        Log4jLoggerAdapter adapter = new Log4jLoggerAdapter(Logger.getLogger(Log4jLoggerAdapterTest.class));
        int ungueltigerLoglevel = -5;
        try {
            adapter.log(null, "fqcn", ungueltigerLoglevel, "message", new Object[0], null);
            Assert.fail("Keine Exception obwohl ein ungültiger Loglevel übergeben wurde.");
        } catch (IllegalStateException ise) {
            Assert.assertEquals("Unbekannter Level-Integer: "
                    + ungueltigerLoglevel, ise.getMessage());
        }
    }

}
