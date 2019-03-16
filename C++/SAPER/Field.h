#pragma once
#include <iostream>

//Klasa modeluje logiczne pole gry. Pole moze zawierac bombe, byc puste lub zawierac liczbe bomb w poach sasiadujacych.
class Field
{
	bool isBomb=false;
	int bombCounter=0;//Ile bomb jest wokol pola. Dla isBomb = true nie jest liczone, bo i tak nie bedzie wyswietlane.
public:
	int getCounter();//Pobranie ilosci bomb w polch sasiadujacych.
	bool getIsBomb();//Pobranie wartosci llogicznej, ktora mowi, czy na polu jest bomba.
	void putBomb();//Na polu ustawiamy bombe.
	Field & operator++();//Inkrementujemy pole, czyli liczbe bomb sasiadujacych. (bombCounter)
	char getSymbol();//Pobieramy symbol 'b' - dla bomby, '0' - dla pustego, odpowiednie numerki dl ainnych, do zapisu stanu gry.
	~Field() = default;
};



