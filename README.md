# Minesweeper Alpha Version


This Game is inspired by the original Windows Version.

### How to run:
There are two ways to run the Application: From IDE (e.g. IntelliJ) or to install an actual Java Runtime Environment.


#### Instruction for IDE (e.g. IntelliJ):
1. Clone the file to your directory.
2. Go to the build.gradle file in your IDE.
3. Try to build the project, if it fails follow the steps bellow.
4. Look at the top left corner and click on "Download coretto-17.0.2".
5. Wait until the JDK has been installed.
6. Run App.java to test the Application

#### Prerequisites for installing the release:
- Java Runtime Environment


### Das Spielfeld:
Sie können bei Minesweeper unter drei Standardspielfeldern mit unterschiedlicher Schwierigkeitsstufe wählen.

Easy - 15 x 10 Raster
Medium - 20 x 15 Raster
Hard - 25 x 20 Raster


### Bombenanzahl:
jedes Kästchen ist mit 15%iger Wahrscheinlichkeit eine Bombe.



### Spielweise:
Die Minesweeper-Regeln sind einfach:

Das Spiel ist normalerweise beendet, wenn eine Mine aufgedeckt wird. 

Das Spiel wird fortgesetzt, wenn Sie ein leeres Feld aufdecken.

Wird beim Aufdecken eines Feldes eine Zahl angezeigt, steht diese für die Anzahl der Minen, die in den benachbarten 8 Feldern verborgen sind. Anhand dieser Angabe kann abgeleitet werden, unter welchen der angrenzenden Feldern sich Minen befinden und auf welche Felder gefahrlos geklickt werden kann.



#### Highscore Room funktioniert noch nicht muss noch fertig implementiert werden!!


### Features that come in the next releases
- working Highscoreroom best highscores are saved there
- algorithm that checks if you have won
- winning screen
- code will be split up
- feature to mark a field

### Authors
- Tristan Westreicher Team-Lead
- Aaron-Kevin Santos
- Mihailo Vucinic
- Simon Weisser

Copyright (c) 2023 FH Campus Wien

This project, Minesweeper, is the property of FH Campus Wien and is protected by copyright laws. It is made available on GitHub for educational and reference purposes only. Any unauthorized use, reproduction, or distribution of this project is strictly prohibited.