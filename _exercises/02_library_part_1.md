---
title: Bibliothek - Teil 1
number: 2
lesson: 2
status: reviewed
authors: mangerlahn, LeonardFollner
---

### Beschreibung
In einer Dorfbibliothek können Bücher ausgeliehen werden. Jedes Buch hat einen Titel und eine ISBN. Die Bibliothek kann mehrere Exemplare von einem Buch vorhalten. Die kleine Dorfbibliothek hat nur Platz für 10 Bücher.

### Aufgabe
1. Modelliere die Klassen `Library` und `Book`.
2. Baue einen Konstruktor für die Klasse `Book`, welcher Titel und ISBN entgegen nimmt.
3. Implementiere eine `main` Methode in der Klasse `Library`, welche die Methoden der Klasse `Book` testet.

### Hinweise
- Eine ISBN hat 13 Stellen. Nutze `long` oder `String` anstatt int.
- Bei der Zuweisung (assignment) von `long` wird ein `L` angehangen.`long var = 1234501234567890L`.