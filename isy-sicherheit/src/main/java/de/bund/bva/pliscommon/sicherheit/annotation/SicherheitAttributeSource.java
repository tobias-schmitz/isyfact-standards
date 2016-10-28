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
package de.bund.bva.pliscommon.sicherheit.annotation;

import java.lang.reflect.Method;

/**
 * Diese Schnittstelle bietet Operationen zum Ermitteln der benötigten Rechte zu einer Service-Methode.
 * 
 */
public interface SicherheitAttributeSource {

    /**
     * Ermittelt die benötigten Rechte zu einer Service-Methode.
     * 
     * @param method
     *            die gerufene Service-Methode
     * @param targetClass
     *            die aufgerufene Zielklasse
     * @return die benötigten Rechte
     */
    public String[] getBenoetigeRechte(Method method, Class<?> targetClass);

}
