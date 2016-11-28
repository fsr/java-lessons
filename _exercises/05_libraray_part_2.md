---
title: Bibliothek - Teil 2
number: 5
lesson: 3
status: reviewed
authors: mangerlahn, LeonardFollner
---

### Beschreibung
In einer Bibliothek können Bücher ausgeliehen werden. Jedes Buch hat einen Titel, einen Autor und eine ISBN. Die Bibliothek kann mehrere Exemplare von einem Buch vorhalten. Die Bibliothek hat 10 Regal mit maximal je 100 Bücher.

### Aufgaben
1. Erweitere die Klasse `Book` um ein Attribut author.
2. Schreibe einen neuen Konstruktor, welcher title, ISBN und author entgegen nimmt.
3. Instanziiere eine Bibliothek. Nutze `Arrays` für die Bücherregale.
4. Implementiere die Methode `listBooks()`, welche alle Bücher ausgibt, jedoch keine leeren Regalplätze.

### Hinweise
- Ein `Array` aus Objekten hat an den Positionen, an denen noch kein Objekt gespeichert ist, eine Referenz auf `null` statt auf ein Objekt.
- Mit `if (sampleReference == null)` kannst du überprüfen ob die sampleReference eine null-Referenz ist.