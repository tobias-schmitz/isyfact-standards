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
package de.bund.bva.pliscommon.sicherheit;

/**
 * Diese Interface definiert ein Recht das vorhanden sein kann. Die meisten Rechte hängen an dem vorhandsein
 * einer oder mehrere Rollen.
 * 
 */
public interface Recht extends Berechtigungsdaten {

    /**
     * Gibt das Property eines Rechtes zurück wenn das Recht ein Property mit diesem Namen hat.
     * 
     * @param propertyName
     *            der Name des Properties
     * @return Das Property oder null
     */
    public Object getProperty(String propertyName);
}
