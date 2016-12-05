title: Fibonacci Menge (Set)
number: 12
lesson: 7
status: reviewed
authors: mangerlahn, LeonardFollner
---

### Beschreibung

Die Menge `FibonacciSet` kann genau solche Zahlen enthalten die auch in der *Fibonacci-Folge* vorkommen. Wenn andere Zahlen hinzugefügt werden soll eine unchecked `NoFibonacciException` geworfen werden.

### Aufgaben

1. Erstelle eine Klasse `FibonacciSet` die das Interface `Set` implementiert.2. Erstelle eine neue Klasse `NoFibonacciException`.3. Implementiere `boolean add(Integer i)` so, dass nur Zahlen der Fibonacci-Folge hinzugefügt werden können und bei Bedarf eine `NoFibonacciException` geworfen wird.4. Teste deine Implemtierung mit der unten gegebenen Testklasse.

### Hinweise

 - [Fibonacci-Folge](https://de.wikipedia.org/wiki/Fibonacci-Folge)

```java
import java.util.Set; 

public class Test {	public static void main(String[] args) {
		
		Set<Integer> fiboMenge = new FibonacciSet();		for (int i = 0; i < 100; i++) { 
			try {				fiboMenge.add(i);				System.out.println(i);			} catch (NoFibonacciException e) {			} 
		}	} 
}
```