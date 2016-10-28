package de.bund.bva.isyfact.logging.impl;

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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Marker;

import de.bund.bva.isyfact.logging.IsyMarker;

/**
 * Standardimplementierung des IsyMarker-Interface.
 * 
 * @see de.bund.bva.isyfact.logging.IsyMarker
 * 
 */
public class IsyMarkerImpl implements IsyMarker {

    /** Eindeutige UID. */
    private static final long serialVersionUID = 1L;

    /** Name des Markers. */
    private final String name;

    /** Wert des Markers. */
    private final String value;

    /** Wert des Root-Markers. */
    private static final String ROOT_MARKER_VALUE = null;

    /** Gibt an, ob es sich um einen Root-Marker handelt. */
    private final boolean root;

    /** Referenzen auf enthaltene Marker. */
    private final List<Marker> references = new ArrayList<Marker>();

    /**
     * Konstruktor der Klasse. Er initialisiert die übergebenen Klassenattribute.
     * 
     * @param name
     *            Name des Markers.
     * @param value
     *            Wert des Markers.
     */
    public IsyMarkerImpl(MarkerSchluessel name, String value) {
        this(name, value, false);
    }

    /**
     * Erzeugt einen Root-Marker.
     * 
     * @return der erzeugte Root-Marker.
     */
    public static IsyMarker createRootMarker() {
        return new IsyMarkerImpl(MarkerSchluessel.ROOTMARKER, ROOT_MARKER_VALUE, true);
    }

    /**
     * Konstruktor der Klasse. Er initialisiert die übergebenen Klassenattribute.
     * 
     * @param name
     *            Name des Markers.
     * @param value
     *            Wert des Markers.
     * @param root
     *            Flag zum Kennzeichnen eines Root-Markers.
     */
    private IsyMarkerImpl(MarkerSchluessel name, String value, boolean root) {
        this.name = name.getWert();
        this.value = value;
        this.root = root;
    }

    /**
     * {@inheritDoc}
     * 
     * @see de.bund.bva.isyfact.logging.IsyMarker#getValue()
     */
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#remove(org.slf4j.Marker)
     */
    public boolean remove(Marker reference) {
        return references.remove(reference);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#hasChildren()
     */
    public boolean hasChildren() {
        return hasReferences();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#hasReferences()
     */
    public boolean hasReferences() {
        return !references.isEmpty();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#iterator()
     */
    public Iterator<Marker> iterator() {
        return references.iterator();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#contains(java.lang.String)
     */
    public boolean contains(String markerName) {
        if (this.name.equals(markerName)) {
            return true;
        } else {
            for (Marker reference : references) {
                if (reference.contains(markerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#add(org.slf4j.Marker)
     */
    public void add(Marker reference) {
        references.add(reference);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.slf4j.Marker#contains(org.slf4j.Marker)
     */
    public boolean contains(Marker other) {
        return references.contains(other);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        
        if (obj instanceof IsyMarker) {
            IsyMarker comp = (IsyMarker) obj;
            return compare(this.name, comp.getName()) && compare(this.value, comp.getValue());
        } else {
            return super.equals(obj);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (name + value).hashCode();
    }

    /**
     * Hilfsmethode zum Vergleichen zweier Strings.
     * 
     * @param str1
     *            String 1.
     * @param str2
     *            String 2.
     * @return <code>true</code> wenn die Strings gleich sind, <code>false</code> sonst.
     */
    private boolean compare(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equals(str2);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see de.bund.bva.isyfact.logging.IsyMarker#isRootMarker()
     */
    public boolean isRootMarker() {
        return root;
    }

    /**
     * {@inheritDoc}
     * 
     * @see de.bund.bva.isyfact.logging.IsyMarker#addAll(Collection)
     */
    public void addAll(Collection<Marker> markerReferences) {
        if (markerReferences != null) {
            for (Marker marker : markerReferences) {
                this.references.add(marker);
            }
        }
    }

}
