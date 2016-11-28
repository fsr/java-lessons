---
title: Fibonacci Menge
number: 10
lesson: 6
status: reviewed
authors: mangerlahn, LeonardFollner
---

### Beschreibung

Die Menge `FibonacciArray` kann genau solche Zahlen enthalten die auch in der *Fibonacci-Folge* vorkommen. Wenn andere Zahlen hinzugefügt werden soll eine unchecked `NoFibonacciException` geworfen werden.

### Aufgaben

1. Erstelle eine Klasse `FibonacciArray ` die das Interface `Set` implementiert.2. Erstelle eine neue Klasse `NoFibonacciException`.3. Implementiere `void add(Integer i)` so, dass nur Zahlen der Fibonacci-Folge hinzugefügt werden können und bei Bedarf eine `NoFibonacciException` geworfen wird.4. Teste deine Implemtierung mit der unten gegebenen Testklasse.

### Hinweise

 - [Fibonacci-Folge](https://de.wikipedia.org/wiki/Fibonacci-Folge) - [Array kopieren](http://stackoverflow.com/a/5067621/4113940)

 
```java
import java.util.Set; 

public class Test {	public static void main(String[] args) {
		
		Set<Integer> fiboMenge = new FibonacciSet();		for (int i = 0; i < 100; i++) { 
			try {				fiboMenge.add(i);				System.out.println(i);			} catch (NoFibonacciException e) {			} 
		}	} 
}
```

<!-----
title: Fibonacci Menge
number: 10
lesson: 6
status: reviewed
authors: mangerlahn, LeonardFollner
---

### Beschreibung

Die Menge `FibonacciSet` kann genau solche Zahlen enthalten die auch in der *Fibonacci-Folge* vorkommen. Wenn andere Zahlen hinzugefügt werden soll eine unchecked `NoFibonacciException` geworfen werden.

### Aufgaben

1. Erstelle eine Klasse `FibonacciSet` die das Interface `Set` implementiert.2. Erstelle eine neue Klasse `NoFibonacciException`.3. Implementiere `boolean add(Integer i)` so, dass nur Zahlen der Fibonacci-Folge hinzugefügt werden können und bei Bedarf eine `NoFibonacciException` geworfen wird.4. Teste deine Implemtierung mit der unten gegebenen Testklasse.

### Hinweise

 - [Fibonacci-Folge](https://de.wikipedia.org/wiki/Fibonacci-Folge) - [Dokumentation](https://docs.oracle.com/javase/7/docs/api/index.html?java/util/Set.html) für `Set`

```java
import java.util.Set; 

public class Test {	public static void main(String[] args) {
		
		Set<Integer> fiboMenge = new FibonacciSet();		for (int i = 0; i < 100; i++) { 
			try {				fiboMenge.add(i);				System.out.println(i);			} catch (NoFibonacciException e) {			} 
		}	} 
}
```-->