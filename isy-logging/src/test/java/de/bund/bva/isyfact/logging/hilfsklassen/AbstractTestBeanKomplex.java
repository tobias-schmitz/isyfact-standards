package de.bund.bva.isyfact.logging.hilfsklassen;

/*
 * #%L
 * isy-logging
 * %%
 * 
 * %%
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
 * #L%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abtrakteoberklasse des TestBeanKomplex, um auch Vererbung abzutesten.
 * @see TestBeanKomplex
 */
public abstract class AbstractTestBeanKomplex {
    

    /** Standard Stringwert. */
    private String einString;

    /** Stringwert für den kein Getter bereitgestellt wird. */
    @SuppressWarnings("unused")
    private String einStringOhneGetter;

    /** Stringwert für den kein Setter bereitgestellt wird. */
    private String einStringOhneSetter;

    /** Liste von Strings. */
    private List<String> eineStringListe;

    /** Liste von Objecten. */
    private List<Object> eineObjectListe;

    /**
     * Konstruktor der Klasse.
     * @param rekursiv
     *            gibt an, ob rekursive Objekte erzeugt werden sollen (also die Properties der Klasse, die
     *            selbst wieder vom Typ-Testbean sind mit 'this' gefüllt werden.
     */
    protected AbstractTestBeanKomplex(boolean rekursiv) {
        this.einString = "einString";
        this.einStringOhneGetter = "einStringOhneGetter";
        this.einStringOhneSetter = "einStringOhneSetter";
        this.eineStringListe = Arrays.asList("A", "B", null, "C");
        this.eineObjectListe = new ArrayList<Object>();
        eineObjectListe.add(new ThreadGroup("A"));
        eineObjectListe.add(null);
        eineObjectListe.add(getEinString());
        if (rekursiv) {
            eineObjectListe.add(new TestBeanKomplex(false));
        }
    }

    /**
     * Liefert den Wert des Attributs 'einString'.
     * 
     * @return Wert des Attributs.
     */
    public String getEinString() {
        return einString;
    }

    /**
     * Setzt den Wert des Attributs 'einString'.
     *
     * @param einString Neuer Wert des Attributs.
     */
    public void setEinString(String einString) {
        this.einString = einString;
    }

    /**
     * Setzt den Wert des Attributs 'einStringOhneGetter'.
     *
     * @param einStringOhneGetter Neuer Wert des Attributs.
     */
    public void setEinStringOhneGetter(String einStringOhneGetter) {
        this.einStringOhneGetter = einStringOhneGetter;
    }

    /**
     * Liefert den Wert des Attributs 'einStringOhneSetter'.
     * 
     * @return Wert des Attributs.
     */
    public String getEinStringOhneSetter() {
        return einStringOhneSetter;
    }

    /**
     * Liefert den Wert des Attributs 'eineStringListe'.
     * 
     * @return Wert des Attributs.
     */
    public List<String> getEineStringListe() {
        return eineStringListe;
    }

    /**
     * Setzt den Wert des Attributs 'eineStringListe'.
     *
     * @param eineStringListe Neuer Wert des Attributs.
     */
    public void setEineStringListe(List<String> eineStringListe) {
        this.eineStringListe = eineStringListe;
    }

    /**
     * Liefert den Wert des Attributs 'eineObjectListe'.
     * 
     * @return Wert des Attributs.
     */
    public List<Object> getEineObjectListe() {
        return eineObjectListe;
    }

    /**
     * Setzt den Wert des Attributs 'eineObjectListe'.
     *
     * @param eineObjectListe Neuer Wert des Attributs.
     */
    public void setEineObjectListe(List<Object> eineObjectListe) {
        this.eineObjectListe = eineObjectListe;
    }
    
}
