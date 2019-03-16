#include "Background.h"
#include "loadFromFileFailed.h"

#define STARTBCKG_NAME "start.png"
#define GAMEBCKG_NAME "game.png"
#define WINBCKG_NAME "winner.png"
#define LOSEBCKG_NAME "loser.png"

void Background::SetTexture(string name)
{
	if(texture.loadFromFile(name)==false)
		throw loadFromFileFailed("Nie da sie zaladowac tla!");
}

void Background::Display(RenderWindow & window)
{
	Sprite sprite(texture);
	window.draw(sprite);
}
