#include "Field.h"

int Field::getCounter()
{
	return bombCounter;
}
bool Field::getIsBomb()
{
	return isBomb;
}
Field & Field::operator++()
{
	if(isBomb==false)
		bombCounter++;
	return *this;
}
/*void Field::setCounter(unsigned int _bombCounter)
{
	if(isBomb==false)
		bombCounter = _bombCounter;
}*/
void Field::putBomb()
{
	isBomb = true;
	bombCounter = 0;
}
char Field::getSymbol()
{
	if (isBomb == true)
		return 'b';
	else if (bombCounter == 0)
		return '0';
	else if (bombCounter == 1)
		return '1';
	else if (bombCounter == 2)
		return '2';
	else if (bombCounter == 3)
		return '3';
	else if (bombCounter == 4)
		return '4';
	else if (bombCounter == 5)
		return '5';
	else if (bombCounter == 6)
		return '6';
}
