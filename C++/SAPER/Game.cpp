#include "Game.h"
#include "loadFromFileFailed.h"
#define SIZE 100
string FIELD1_NAME = "1.png";
string FIELD2_NAME = "2.png";
string FIELD3_NAME = "3.png";
string FIELD4_NAME = "4.png";
string FIELD5_NAME = "5.png";
string FIELD6_NAME = "6.png";
string EMPTY_NAME = "empty.png";
string FIELDBOOM_NAME = "dead.jpg";



Game::Game()
{
	try
	{
		Texture text;
		if(text.loadFromFile(FIELDBOOM_NAME)==false)
			throw loadFromFileFailed("Nie da sie zaladowac tekstury bomby!");
		MyTextures[0].reset(new Texture(text));
		Texture text1;
		if(text1.loadFromFile(FIELD1_NAME)==false)
			throw loadFromFileFailed("Nie da sie zaladowac tekstury pola !");
		MyTextures[1].reset(new Texture(text1));
		Texture text2;
		if(text2.loadFromFile(FIELD2_NAME)==false)
			throw loadFromFileFailed("Nie da sie zaladowac tekstury pola 2!");
		MyTextures[2].reset(new Texture(text2));
		Texture text3;
		if(text3.loadFromFile(FIELD3_NAME)==false)
			throw loadFromFileFailed("Nie da sie zaladowac tekstury pola 3!");
		MyTextures[3].reset(new Texture(text3));
		Texture text4;
		if(text4.loadFromFile(FIELD4_NAME)==false)
			throw loadFromFileFailed("Nie da sie zaladowac tekstury pola 4!");
		MyTextures[4].reset(new Texture(text4));
		Texture text5;
		if (text5.loadFromFile(FIELD5_NAME) == false)
			throw loadFromFileFailed("Nie da sie zaladowac tekstury pola 5!");
		MyTextures[5].reset(new Texture(text5));
		Texture text6;
		if(text6.loadFromFile(FIELD6_NAME)==false)
			throw loadFromFileFailed("Nie da sie zaladowac tekstury pola 6!");
		MyTextures[6].reset(new Texture(text6));
		Texture text7;
		if(text7.loadFromFile(EMPTY_NAME)==false) 
			throw loadFromFileFailed("Nie da sie zaladowac tekstury pola!");
		MyTextures[7].reset(new Texture(text7));
	}
	catch (std::bad_alloc& ba)
	{
		std::cerr << "bad_alloc caught: " << ba.what() << '\n';
		return;
	}
	
}

void Game::Initialize(RenderWindow & window)
{
	myMap = Map(SIZE, window, MyTextures);
}

void Game::OpenFromFile(unsigned int _size, RenderWindow & window)
{
	myMap = Map();
	myMap.OpenFromFile(_size, window, MyTextures);
}


State Game::ChooseField(const unsigned int index)
{
	if (myMap.chooseField(index) == Safe)
		return Safe;
	else
		return Bomb;
}

int Game::GetMovesCount()
{
	return myMap.GetMovesCount();
}
void Game::Display(RenderWindow & window)
{
	myMap.Display(window);
}

bool Game::IsFieldEmpty(int index)
{
	if (myMap.IsEmpty(index) == true)
		return true;
	else
		return false;
}

bool Game::IsFieldNumber(int index)
{
	if (myMap.IsNumber(index) == true)
		return true;
	else
		return false;
}

void Game::setSafeUnsafe(unsigned int index)
{
	myMap.setSafeUnsafe(index);
}

void Game::clearEmpty(unsigned int index)
{
	myMap.clearEmpty(index);
}

void Game::SaveState()
{
	myMap.SaveState();
}