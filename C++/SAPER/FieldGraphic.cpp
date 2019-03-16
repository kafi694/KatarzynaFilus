#include "FieldGraphic.h"
#include <memory>


FieldGraphic::FieldGraphic(shared_ptr<Texture> texture, int _x, int _y)
{
	posit.x = _x;
	posit.y = _y;
	(*this).sprite = sf::RectangleShape(Vector2f(30, 30));
	(*this).sprite.setPosition(Vector2f(_x, _y));
	(*this).sprite.setTexture(texture.get(), false);
}


void FieldGraphic::Display(RenderWindow & window)
{
	window.draw(sprite);
}
