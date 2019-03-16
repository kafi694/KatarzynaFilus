#include "User.h"
#include <Windows.h>

#define WIDTH 300
#define HEIGHT 400

GameState User::Play(RenderWindow & window)
{

	myGame.Display(window);
	State state, checkstate;
	static Vector2i posit;
	posit = Mouse::getPosition(window);
	if (Mouse::isButtonPressed(Mouse::Button::Right) == true)
		if (posit.x > 0 && posit.y > 0 && posit.y < HEIGHT && posit.x < WIDTH)
			if (posit.y >= 100)
			{
				Sleep(10);
				int ind = (floor(posit.x / 3 / 10)) + (floor((posit.y - 100) / 3 / 10) * 10);
				myGame.setSafeUnsafe(ind);
				Sleep(30);
			}
	if (Mouse::isButtonPressed(Mouse::Button::Left) == true)
		if (posit.x > 0 && posit.y > 0 && posit.y < HEIGHT && posit.x < WIDTH)
			if (posit.y >= 100)
			{
				int ind = (floor(posit.x / 3 / 10)) + (floor((posit.y - 100) / 3 / 10) * 10);
				if (myGame.IsFieldNumber(ind) == true)
					state = myGame.ChooseField(ind);			
				else if (myGame.IsFieldEmpty(ind) == true)
				{
					myGame.clearEmpty(ind);
					state = Safe;
				}
				else
					state = Bomb;
				if (state == Bomb)
				{
					return Lost;
				}
				

			}
	if (myGame.GetMovesCount() == 0)
		return Won;
	else
		return Playing;
}



User::User(RenderWindow & window)
{
}

void User::NewGame(RenderWindow & window)
{
	myGame.Initialize(window);
}

void User::SaveState()
{
	myGame.SaveState();
}

void User::OpenFromFile(unsigned int _size, RenderWindow & window)
{
	myGame.OpenFromFile(_size, window);
}