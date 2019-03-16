#pragma once
#include <SFML/Graphics.hpp>
#include <SFML/Graphics/RectangleShape.hpp>


using namespace sf;

//Powloka planszy - biale i czrne pola zakrywajace pole minowe.
class Overlay
{
	Vector2f posit;
	RectangleShape Rectangle;
	Color color;
public:
	void Open();
	Overlay() = default;
	Overlay(Color _color, int _x, int _y);
	void SetColor(Color _color);
	void Display(RenderWindow & window);
	void setSafeUnsafe();//Ustawienie safe/unsafe w zaleznosci od koloru pola - jesli zielone to safe.
	Color GetColor() { return Rectangle.getFillColor(); }
	~Overlay() = default;
};


