#include "Overlay.h"
#define FIELDSIZE 30

void Overlay::SetColor(Color _color)
{
	color = _color;
	(*this).Rectangle.setFillColor(_color);
}


Overlay::Overlay(Color _color, int _x, int _y)
{
	posit.x = _x;
	posit.y = _y;
	(*this).Rectangle = sf::RectangleShape(Vector2f(FIELDSIZE, FIELDSIZE));
	(*this).Rectangle.setPosition(Vector2f(_x, _y));
	(*this).Rectangle.setFillColor(_color);
	(*this).color = _color;
}

void Overlay::Open()
{
	this->Rectangle.setFillColor(Color::Transparent);
	color = Color::Transparent;
}

void Overlay::Display(RenderWindow & window)
{
	window.draw(this->Rectangle);
}

void Overlay::setSafeUnsafe()
{
	if (Rectangle.getFillColor() == Color::White || Rectangle.getFillColor() == Color::Black)
		Rectangle.setFillColor(Color::Green);
	else if (Rectangle.getFillColor() == Color::Green)
		Rectangle.setFillColor(color);
}

