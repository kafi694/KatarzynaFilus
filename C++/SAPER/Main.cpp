#include <SFML/Graphics.hpp>
#include "Map.h"
#include "User.h"
#include "Background.h"
#include "loadFromFileFailed.h"
#include <fstream>
#include "StatisticsManager.h"
#define _CRTDBG_MAP_ALLOC  
#include <stdlib.h>
#include <crtdbg.h>
#include <Windows.h>

//Czy uzytkownik chce zagrac? Jezeli wcisnie "lets play" to zagra.
bool WantToPlay(RenderWindow & window, Background & bground)
{
	static Vector2i posit;
	posit = Mouse::getPosition(window);
	if (Mouse::isButtonPressed(Mouse::Button::Left) == true)
		if (posit.x>20 && posit.y>320 && posit.y<380 && posit.x<220)
		{
				bground.SetTexture("game.png");
				Sleep(300);
				return true;
		}
	return false;
}

//Inicjalizacja okna, wyswietlanie go
//lapanie bledow
int main()
{
	FreeConsole();
	bool displayed = false;
	sf::RenderWindow window(sf::VideoMode(300, 400), "SFML window", sf::Style::Titlebar | sf::Style::Close);
	StatisticsManager statsManager("statistics.txt");
	try
	{	

		Background bground;
		bground.SetTexture("start.png");
		GameState state = NotStarted;
		User u(window);
		bool StartNewGame = false;

		fstream input;
		input.open("stangry.txt", ios::in);
		char check;
		if (input.good() == true)
		{
			int msgBox = MessageBox(window.getSystemHandle(), LPCTSTR("Dokoncz stara gre"), LPCTSTR("Stara rozgrywka"), MB_OKCANCEL);
			switch (msgBox)
			{
			case IDCANCEL:
				while (window.isOpen() && state == NotStarted)
				{
					window.clear();
					sf::Event event;
					bground.Display(window);
					StartNewGame = WantToPlay(window, bground);
					if (StartNewGame == true)
					{
						state = Playing;
						u.NewGame(window);
					}
					while (window.pollEvent(event))
					{
						if (event.type == sf::Event::Closed)
							window.close();
					}
					window.display();
				}
				break;
			case IDOK:
				bground.SetTexture("game.png");
				Sleep(300);
				state = Playing;
				input.close();
				u.OpenFromFile(100, window);
				remove("stangry.txt");
				break;
			}		
		}
		else
		{
			while (window.isOpen() && state == NotStarted)
			{
				window.clear();
				sf::Event event;
				bground.Display(window);
				StartNewGame = WantToPlay(window, bground);
				if (StartNewGame == true)
				{
					state = Playing;
					u.NewGame(window);
				}
				while (window.pollEvent(event))
				{
					if (event.type == sf::Event::Closed)
						window.close();
				}
				window.display();
			}
		}
		/*input >> check;
		if (check == 'b' || (check >= 48 && check <= 54))
		{

		}*/
			
		
		
		while (window.isOpen())
		{
			window.clear();
			sf::Event event;
			bground.Display(window);
			if (state == Playing)
				state = u.Play(window);
			else if (state == Lost)
			{
				bground.SetTexture("loser.png");
				if (!displayed)
				{
					statsManager.AddResult(StatisticsManager::GameResult::LOSER);
					MessageBox(window.getSystemHandle(), statsManager.StastisticsMessage().c_str(), "LOSER", MB_OK | MB_ICONINFORMATION);
					displayed = true;
				}
				StartNewGame = WantToPlay(window, bground);
				if (StartNewGame == true)
				{
					displayed = false;
					state = Playing;
					u.NewGame(window);
				}

			}
			else if (state == Won)
			{
				bground.SetTexture("winner.png");
				if (!displayed)
				{
					statsManager.AddResult(StatisticsManager::GameResult::WINNER);
					MessageBox(window.getSystemHandle(), statsManager.StastisticsMessage().c_str(), "WINNER", MB_OK | MB_ICONINFORMATION);
					displayed = true;
				}
				StartNewGame = WantToPlay(window, bground);
				if (StartNewGame == true)
				{
					displayed = false;
					state = Playing;
					u.NewGame(window);
				}
			}
			
			while (window.pollEvent(event))
			{
				if (event.type == sf::Event::Closed)
				{
					if (state == Playing)
					{
						//fstream output;
						//output.open("stangry.txt", ios::out);
						u.SaveState();

					}
					window.close();
				}
					
			}
			window.display();


		}		
	}
	catch (const loadFromFileFailed& e)
	{
		MessageBox(window.getSystemHandle(), LPCTSTR(e.what()), LPCTSTR("BLAD GRAFIKI"), MB_OK | MB_ICONERROR);
		window.close();
		return 1;
	}
	catch (...)
	{
		MessageBox(window.getSystemHandle(), LPCSTR(L"Wyst¹pil nieoczekiwany problem :( Uruchom ponownie."), LPCSTR(L"BLAD"), MB_OK | MB_ICONERROR);
		window.close();
		return 1;
	}
		
	return 0;
}