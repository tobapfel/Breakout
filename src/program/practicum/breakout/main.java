package program.practicum.breakout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


/*
 * Aufbau der Classen und Mehtoden

Es gibt 4 wichtige Klassen, die das Grundger�st bilden:
    - main
    - BreakoutModel
    - View
    - BreakoutController
    
Das Programm wird in der Klasse main gestartet.
Hier wird zuerst eine neue View erstellt und dann eine Schleife
gestartet, die in jedem Durchlauf erst den BreakoutController und dann die View aktuallisiert.  

Hinzu kommen noch einige Klassen, die Funktionalit�ten f�r das Programm bereitstellen:
    - Paddle
    - Ball
    - Bricks
    -Settings
Die Klassen Paddle, Ball und Bricks sind Subklassen von GObjekt und werden vom View genutzt die jeweiligen SpielKOMPONENTEN darzustellen.
Die vierte Klasse Settings liest aus einer Datei Key-Value-Paare aus und schreibt gibt diese als HashMap an Model, View und Controller weiter.
Das Model nutzt z.B. die H�he und Breite des Paddles. Die View liest die Anzahl der Reihen und Spalten als Bricks aus und dem Controller wird
 die Anzahl an Leben, sowie Parameter f�r Ballbewegungen �bergeben.

Sowohl der BreakoutController als auch die View greifen auf dieselbe Instanz von BreakoutModel zu,
in dem der aktuelle Status des Programms gespeichert ist.
Der BreakoutController reagiert auf die Eingaben des Benutzers und f�llt die dadurch entstehenden Attribute des BreakoutModels, 
wie z.B. die Position und die Geschwindigkeit des Balls oder die Position des Paddles.
Die View greift daraufhin auf diese Attribute zu, und zeigt die Elemente auf der Zeichenfl�che (GCanvas...) den Werten entsprechend an.

Der Controller ist dadurch nicht davon abh�ngig,  wie die Anzeige des Programms aussieht
und die View nicht davon, wie die Werte im Model zustande kommen.

Techniken der Objekt-Orientieren-Programmierung:
    
Vererbung:
Vererbung wird von den Klassen View, Ball, Paddle und Bricks genutzt.
Die View ist eine Subklasse von GraphicsProgram, erbt dessen Methoden und Attribute.
Es wurden auch noch jene Methoden hinzugef�gt bzw. �berschrieben, die f�r das Breakout-Programm ben�tigt werden.
Die anderen drei Klassen sind alle von einer Unterklasse von GObject abgeleitet ( z.B. GRect).
Zus�tzlich wurden bei diesen Klassen die Konstruktoren so angepasst, sodass beim Instanzieren das Object automatisch die entsprechenden Eigenschaften hat.

Kapselung und Sichtbarkeit:
Kapselung ist besonders in der Klasse BreakoutModel zu sehen.
Hier sind die meisten Attribute nicht direkt zug�nglich und nur durch eine Get-Methode abrufbar bzw. �ber einen Set-Methode ver�nderbar.
In unseren Klassen sind sind die Attrubute und Methoden grunds�tzlich auf private gesetzt. Nur wenn eine andere Klasse darauf zugreifen muss und wir uns entschieden haben,
keine Get- und Setmethoden zu schreiben, haben diese die Sichtbarkeit public.

Statische Methoden:
Da von jeder Klasse Objekte erstellt werden, deren Zustand jeweils durch dessen Eigenschaften abgebildet ist, w�re es nicht sinnvoll gewesen statische Methoden anzulegen.

Instanz-  und Klassenvariablen und lokale Variablen:
Einige Werte m�ssen auch von au�erhalb der Klasse erreichbar f�r andere Objekte sein (z.B. die Werte in den Klassen BreakoutModel oder Settings)
Diese Attribute sind deshalb als Instanzvariablen angelegt.

 */


public class main {
	public static void main(String[] args) {
		
		// A map to store the settings
		Settings settings = new Settings();
		
		View view = new View(settings);
		//start the window
		view.start();
		
		//update loop
		while (true) {
			view.controller.updateController();
			view.updateView();

			try {
				Thread.sleep(settings.get("FRAME_PAUSE"));
			} catch (InterruptedException e) {

			}
		}
	}
}
