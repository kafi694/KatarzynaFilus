#pragma once
#include "Field.h"
#include "FieldGraphic.h"
#include "Overlay.h"
#include <vector>
#include <iostream>
#include <fstream>
using namespace std;

enum State { Safe, Bomb };

class Map
{
	vector<Field> FieldMap;
	vector<Overlay> Overlays;
	vector<FieldGraphic> FieldsG;
	unsigned int size;
	unsigned int leftCount = 100;
public:
	Map() = default;
	Map(unsigned int _size, RenderWindow & window, shared_ptr<Texture> * textures);//Konstrukcja mapy.
	State chooseField(int index);//Wybranie pola.
	int GetMovesCount();//Liczymy ile ruchow zostalo.
	void Display(RenderWindow & window);//Wyswietlamy mape.
	bool IsEmpty(int index);//Czy jest puste pole.
	bool IsNumber(int index);//Czy pole to numerek.
	void setSafeUnsafe(unsigned int index);//Ustawiamy safe/unsafe - w zaleznosci od tego czy zielone czy nie.
	~Map() = default;
	void clearEmpty(unsigned int index);//Czyszczenie pustych pol wokol pustego pola.
	void SaveState();//Zapis stanu mapy.
	void OpenFromFile(unsigned int _size, RenderWindow & window, shared_ptr<Texture> * textures);//Otwarcie mapy z pliku.
};
















