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
package de.bund.bva.pliscommon.exception;

import java.util.UUID;

import de.bund.bva.pliscommon.exception.common.FehlertextUtil;

/**
 * Abstrakte technische <i>unchecked</i> Hauptexception, welche als Basis für die abstrakte technische
 * <i>unchecked</i> Exception einer Anwendung verwendet werden muss. Diese Exception und davon abgeleitete
 * Klassen d�rfen nicht als Schnittstellen-Exceptions sind.
 * <p>
 * <b>Anmerkung:</b> Alle technischen <i>unchecked</i> Exceptions, die als Kindklassen zu dieser Klasse
 * implementiert werden, müssen die hier angegebenen Konstruktoren implementieren. Dies ist notwendig, um
 * Fehler-IDs und die zugeordneten Texte in der Exception zu haben. Dies ist wichtig, da diese technischen
 * Exceptions in Transport-Exceptions umgewandelt werden, die zum Aufrufer durchgeroutet werden. Diese
 * wiederum müssen immer eine Ausnahme-ID besitzen.
 * <p>
 * Für die Konstruktoren wurde die Java 1.5er Funktionalität von variablen Parametern verwendet.
 * Details zu Vargs sind unter <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/language/varargs.html">http:/
 * /java.sun.com/j2se/1.5.0/docs/guide/language/varargs.html</a> beschrieben.
 * 
 */
public abstract class PlisTechnicalRuntimeException extends RuntimeException {
    /**
     * Ausnahme-ID.
     */
    private String ausnahmeId;

    /**
     * Die eineindeutige ID, die den aufgetretenen Fehler referenziert (nicht die Fehlerart).
     */
    private String uniqueId;

    /**
     * Erstellt eine neue technische <i>unchecked</i> Exception mit einer Ausnahme-ID für den Fehlertext
     * und einem Array mit Werten für die Variablenersetzung im Fehlertext.
     * <p>
     * Der Grund wird nicht initialisiert und kann später durch den Aufruf der Methode
     * {@link #initCause(Throwable)} initialisiert werden.
     * 
     * @param ausnahmeId
     *            Die Ausnahme-ID. Der Schlüssel, welcher verwendet wird, um einen Nachrichtentext,
     *            welcher als Fehler-Nachricht für die Exception verwendet wird aus einem ResourceBundle
     *            zu laden.
     * @param fehlertextProvider
     *            Die FehlertextProvider-Implementierung, welche verwendet wird, um eine Fehlertext zu laden.
     * @param parameter
     *            Variable Anzahl an Parameterwerten. Parameter f&uml;r die möglichen Variablen in einer
     *            Fehler-Nachricht.
     */
    protected PlisTechnicalRuntimeException(String ausnahmeId, FehlertextProvider fehlertextProvider,
        String... parameter) {
        super(fehlertextProvider.getMessage(ausnahmeId, parameter));
        this.ausnahmeId = ausnahmeId;
        this.uniqueId = UUID.randomUUID().toString();
    }

    /**
     * Erstellt eine neue technische <i>unchecked</i> Exception mit einer Ausnahme-ID für den Fehlertext,
     * einem Werten für die Variablenersetzung im Fehlertext und mit dem übergebenen Grund.
     * <p>
     * Anmerkung: Der Fehlertext von <code>cause</code> (dem Grund) ist <i>nicht</i> automatisch mit dem
     * übergebenen Fehlertext verbunden.
     * 
     * @param ausnahmeId
     *            Die Ausnahme-ID. Der Schlüssel, welcher verwendet wird, um einen Nachrichtentext,
     *            welcher als Fehler-Nachricht für die Exception verwendet wird aus einem ResourceBundle
     *            zu laden.
     * @param throwable
     *            Der Grund. Throwable wird gespeichert hfür die spätere Nutzung durch die Methode
     *            {@link #getCause()}. <tt>null</tt> als Wert ist erlaubt und bedeutet, dass kein Grund
     *            existiert oder der Grund nicht bekannt ist.
     * @param fehlertextProvider
     *            Die FehlertextProvider-Implementierung, welche verwendet wird, um eine Fehlertext zu laden.
     * @param parameter
     *            Variable Anzahl an Parameterwerten. Parameterwert f&uml;r die mögliche Variable in
     *            einer Fehler-Nachricht.
     */
    protected PlisTechnicalRuntimeException(String ausnahmeId, Throwable throwable,
        FehlertextProvider fehlertextProvider, String... parameter) {
        super(fehlertextProvider.getMessage(ausnahmeId, parameter), throwable);
        this.ausnahmeId = ausnahmeId;
        setUniqueId(throwable);
    }

    /**
     * Setzt die eineindeutige ID (UUID) aus der übergebenen Exception.
     * 
     * @param throwable
     *            Die übergebene Exception.
     */
    private void setUniqueId(Throwable throwable) {
        if (throwable instanceof PlisException) {
            this.uniqueId = ((PlisException) throwable).getUniqueId();
        } else if (throwable instanceof PlisTechnicalRuntimeException) {
            this.uniqueId = ((PlisTechnicalRuntimeException) throwable).getUniqueId();
        } else {
            this.uniqueId = UUID.randomUUID().toString();
        }
    }

    /**
     * Liefert die Ausnahme-ID zurück, welche gleichzeitig der Schlüssel für den Fehlertext
     * ist.
     * 
     * @return Ausnahme-ID String sofern gesetzt, ansonsten <code>null</code>.
     */
    public String getAusnahmeId() {
        return ausnahmeId;
    }

    /**
     * Liefert die eineindeutige ID (UUID) zurück, welche beim Auftreten der Exception erzeugt wurde.
     * 
     * @return the ausnahmeId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @return Den unformattierten Fehlertext
     */
    public String getFehlertext() {
        return super.getMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return FehlertextUtil.erweitereFehlertext(ausnahmeId, super.getMessage(), uniqueId);
    }
}
