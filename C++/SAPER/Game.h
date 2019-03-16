#pragma once
#include "Map.h"
#include <memory>

//Klasa modelujaca rozgrywke. Ma mape i do niej przekazuje tekstury.
class Game
{
	Map myMap;
	shared_ptr<Texture> MyTextures[8];
public:
	Game();
	void Initialize(RenderWindow & window);//Inicjalizacja gry, a w niej mapy.
	State ChooseField(const unsigned int index);//Wybranie pola o jakims indeksie - uzytkownik kliknal lewym przyciskiem myszki. 
											   //Pole trzeba odkryc i sprawdzic czy jest bomba.
	int GetMovesCount();//Liczenie ile ruchow zostalo do konca rozgrywki, jesli bedzie rowne zero - uzytkownik wygrywa.
	void Display(RenderWindow & window);//Wyswietlanie gry.
	bool IsFieldEmpty(int index);//Sprawdzenie czy pole o podanym indeksie jest puste. Uzywam do odkrywania pol pustych.
	bool IsFieldNumber(int index);//Czy pole to numerek. Jak powyzej. Jest to 'warunek stopu'.
	void setSafeUnsafe(unsigned int index);//Ustawianie zielonego kolorku - nie chcemy klikac, znacznik, ze myslimy, ze na polu jest bomba.
	void clearEmpty(unsigned int index);//Usuwanie sasiadujacych pol pustych wokol pustego pola.
	void SaveState();//Zapisanie stanu rozgrywki.
	~Game() = default;
	void OpenFromFile(unsigned int _size, RenderWindow & window);//Otwarcie stanu z pliku.
};









