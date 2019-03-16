#pragma once
#include <SFML/Graphics.hpp>
#include <SFML/Graphics/RectangleShape.hpp>
#include <memory>

using namespace sf;
using namespace std;

//Pole graficzne, odzwierciedla logiczny stan.
class FieldGraphic
{
	Vector2f posit;
	RectangleShape sprite;
public:
	FieldGraphic() = default;
	FieldGraphic(shared_ptr<Texture> texture, int _x, int _y);//Musimy wczytac odpowiedni obrazek na podstawie pola logicznego - do tego uzyjemy funkcji.
	void Display(RenderWindow & window);//Wyswietlanie pola.
	~FieldGraphic() = default;
};



