# Kontakt App für die Präsentation über OOP

Diese Anwendung ist eine Java-Applikation, die eine grafische Benutzeroberfläche (GUI) verwendet, um auf eine lokale MySQL-Datenbank zuzugreifen und Kontakte anzuzeigen, zu erstellen, zu bearbeiten und zu entfernen.

## Erklärung des Codes

Die Anwendung besteht aus mehreren Klassen, die verschiedene Funktionen implementieren:

- `JFrameGui`: Diese Klasse stellt die Haupt-GUI der Anwendung dar und ermöglicht das Anzeigen, Hinzufügen, Bearbeiten und Entfernen von Kontakten.
- `NonEditableIdTableModel`: Eine benutzerdefinierte Tabellenmodellklasse, die sicherstellt, dass die ID-Spalte in der Tabelle nicht bearbeitet werden kann.
- `DbSetup`: Eine Hilfsklasse zum Einrichten der Verbindung zur MySQL-Datenbank.
- `AlterDbData`: Eine Hilfsklasse zum Ändern der Datenbank, einschließlich Hinzufügen, Aktualisieren und Entfernen von Kontakten.
- `Contacts`: Eine einfache Datenklasse zur Darstellung von Kontaktinformationen.

Die Anwendung verwendet JDBC für die Datenbankkommunikation und Swing für die GUI.

## Verwendete Bibliotheken

- `java.sql.*`: Für den Zugriff auf die MySQL-Datenbank.
- `javax.swing.*`: Zur Erstellung der grafischen Benutzeroberfläche.
- `java.util.*`: Für verschiedene Hilfsfunktionen und Datentypen.

## Voraussetzungen

Bevor Sie die Anwendung ausführen können, stellen Sie bitte sicher, dass Sie die folgenden Voraussetzungen erfüllen:

- Java Development Kit (JDK) installiert und konfiguriert
- Eine lokale MySQL-Datenbank mit einer Datenbank namens `ContactsDB`
- MySQL-Server läuft lokal auf Port 3306
- Benutzername und Passwort für den Zugriff auf die MySQL-Datenbank

## Installation und Ausführung

1. Klonen Sie dieses Repository auf Ihren lokalen Rechner.
2. Öffnen Sie das Projekt in Ihrer bevorzugten Java-Entwicklungsumgebung.
3. Stellen Sie sicher, dass die erforderlichen Bibliotheken und Abhängigkeiten in Ihrem Projekt eingebunden sind.
4. Passen Sie die Verbindungsdaten zur MySQL-Datenbank in der `DbSetup`-Klasse an.
5. Führen Sie das Projekt aus.

## Funktionalitäten

- Anzeigen von Kontakten aus der lokalen MySQL-Datenbank.
- Hinzufügen neuer Kontakte.
- Bearbeiten vorhandener Kontakte.
- Entfernen von Kontakten.

## Anleitung zur Verwendung

1. Beim Starten der Anwendung werden alle vorhandenen Kontakte aus der Datenbank geladen und in einer Tabelle angezeigt.
2. Verwenden Sie das Suchfeld oben, um nach Kontakten zu suchen. Die Tabelle wird automatisch aktualisiert, um nur passende Ergebnisse anzuzeigen.
3. Klicken Sie auf "Hinzufügen", um einen neuen Kontakt hinzuzufügen. Geben Sie die erforderlichen Informationen ein und klicken Sie auf "Speichern".
4. Um einen vorhandenen Kontakt zu bearbeiten, klicken Sie einfach auf die Zelle, die Sie bearbeiten möchten, und geben Sie die neuen Informationen ein. Klicken Sie dann auf "Speichern".
5. Um einen Kontakt zu entfernen, wählen Sie die Zeile des Kontakts aus, den Sie entfernen möchten, und klicken Sie auf "Entfernen".

## Entwickler

Diese Anwendung wurde entwickelt von Max Schwaderer & Michael Leichtl.

Für Fragen oder Anregungen können Sie mich unter mileic@icloud.com kontaktieren.

© 2024 Max Schwaderer & Michael Leichtl
