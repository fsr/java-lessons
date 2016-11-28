---
title: Fahrzeuge
number: 7
lesson: 4
status: reviewed
authors: mangerlahn, LeonardFollner
---

### Beschreibung
Es gibt mehrere Arten von Fahrzeugen. Autos und Motorräder sind Beispiele. Elektroautos sind spezielle Autos.Jedes Fahrzeug besitzt eine bestimmte Anzahl von Rädern und wird von einer Marke hergestellt. Ob Steuern für ein Fahrzeug gezahlt werden müssen, wird mit einem boolean festgelegt, welcher standartmäßig auf true gesetzt ist. Autos können einen Autopiloten besitzen. Bei Elektroautos wird die Kapazität der Batterie angegeben außerdem müssen keine Steuern gezahlt werden.

### Aufgaben
1. Modelliere die Klassen `Vehicle`, `Car`, `ElectricCar` und `Motorcycle`.
2. Füge den Klassen die oben beschriebenen Variablen sowie deren Getter Methoden hinzu.
3. Konstruktoren jedes Fahrzeugs nehmen die Marke (String) und das Elektroauto zusätzlich die Kapazität entgegen.
4. Die `toString` Methode sollte den Markenname und bei dem Elektroauto zusätzlich die Kapazität ausgeben.
5. Erstelle in einer extra Klasse `Garage` mit einer main-Methode, die ein Auto, Elektroauto und ein Motorrad erstellt. Gib für jedes Fahrzeug den Namen, die Anzahl der Räder und die Steuerpflicht aus.

### Hinweise
- Die Anzahl der Räder sollte sich von außen nicht ändern lassen.
- Es sollte nicht möglich sein, Objekte der Klasse `Vehicle` zu erstellen.