#pragma once
#include "Game.h"
#include <SFML/Window/Event.hpp>
#include <SFML/Window/Mouse.hpp>

enum GameState{Lost, Won, Playing, NotStarted};

//Model uzytkownika.
class User
{
	Game myGame;
public:
	User() = default;
	void NewGame(RenderWindow & window);//Uzytkownik moze zaczac nowa gre.
	User(RenderWindow & window);
	GameState Play(RenderWindow & window);//Moze grac.
	void SaveState();//Zapisujemy stan gry (gdy zamkniemy przed koncem).
	void OpenFromFile(unsigned int _size, RenderWindow & window);//Otwieramy gre z pliku.
};


