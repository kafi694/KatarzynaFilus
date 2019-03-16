#pragma once
#include <SFML/Graphics.hpp>
#include <SFML/Graphics/RectangleShape.hpp>


using namespace sf;
using namespace std;

//Tutaj przechowujemy texture, ktora jest tlem applikacji w danym momencie.
//Klasa modeluje tlo aplikacji.
class Background
{
	Texture texture;
public:
	void SetTexture(string name);//Ustawienie  tekstury, wczytanie jej z pliku.
	void Display(RenderWindow & window);//Wyswietlenie tla.
	Background() = default;
	~Background() = default;
};
