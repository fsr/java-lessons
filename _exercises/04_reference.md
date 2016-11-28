---
title: Referenzen
number: 4
lesson: 2
status: reviewed
authors: mangerlahn, LeonardFollner
---

### Beschreibung
```java
public class Reference {	public static void main(String[] args) {		int var1 = 4;		int var2 = var1;
		var1 = 8;		System.out.println(var2);		Number ref1 = new Number(4);		Number ref2 = ref1;		ref1.number = 8;		System.out.println(ref2.number);}}
```

### Aufgaben
1. Schreibe die Klassen `Number` die zu dem Quelltext oben passt.
2. Führe das Programm aus. Was beobachtest Du? Was kannst Du daraus folgern?