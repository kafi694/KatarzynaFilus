#include "Map.h"
#include <ctime>
#include<memory>
unsigned int ROWS = 10, COLUMNS = 10;

Map::Map(unsigned int _size, RenderWindow & window, shared_ptr<Texture> * textures)
{
	FieldMap.resize(_size);
	FieldsG.resize(_size);
	srand(time(NULL));
	int random;
	int BombCount = 0;
	int x = 0;
	int y = 0;
	for (unsigned int i = 0; i < _size; i++)
	{
		random = (rand() % 20);
		if (random % 7 == 0)
		{
			(*this).FieldMap[i].putBomb();
			BombCount++;
			if (int(i - ROWS) > 0 && int(i - ROWS) < 99)
				++((*this).FieldMap[i - ROWS]);
			if (int(i + ROWS) > 0 && int(i + ROWS) < 99)
				++((*this).FieldMap[i + ROWS]);
			if (int(i - ROWS + 1) > 0 && int(i - ROWS + 1) < 99 && int(i - ROWS + 1)%10!=0)
				++((*this).FieldMap[i - ROWS + 1]);
			if (int(i - ROWS - 1) > 0 && int(i - ROWS - 1) < 99&& int(i - ROWS - 1)%10!=9)
				++((*this).FieldMap[i - ROWS - 1]);
			if (int(i + ROWS - 1) > 0 && int(i + ROWS - 1) < 99&& int(i + ROWS - 1)%10!=9)
				++((*this).FieldMap[i + ROWS - 1]);
			if (int(i + ROWS + 1) > 0 && int(i + ROWS + 1) < 99&& int(i + ROWS + 1)%10!=0)
				++((*this).FieldMap[i + ROWS + 1]);
			if (int(i - 1) > 0 && int(i - 1) < 99&& int(i - 1)%10!=9)
				++((*this).FieldMap[i - 1]);
			if (int(i + 1) > 0 && int(i + 1) < 99&& int(i + 1)%10!=0)
				++((*this).FieldMap[i + 1]);
		}
	}

	this->leftCount = ROWS*COLUMNS - BombCount;
	int counter = 0;
	int ind = 0;
	int i = 100;
	int j = 0;
	Overlays.resize(_size);
	for (auto it = FieldMap.begin(); it != FieldMap.end(); it++)
	{
		Overlay overlay = Overlay(Color::White, j, i);
		if (counter % 2 == 0)
			overlay.SetColor(Color::Black);

		(*this).Overlays[ind] = overlay;

		Texture  texture;

		if (it->getIsBomb() == false)
		{
			switch (it->getCounter())
			{
			case 0:
				(*this).FieldsG[ind] = FieldGraphic(textures[7], j, i);
				break;
			case 1:
				(*this).FieldsG[ind] = FieldGraphic(textures[1], j, i);
				break;
			case 2:
				(*this).FieldsG[ind] = FieldGraphic(textures[2], j, i);
				break;
			case 3:
				(*this).FieldsG[ind] = FieldGraphic(textures[3], j, i);
				break;
			case 4:
				(*this).FieldsG[ind] = FieldGraphic(textures[4], j, i);
				break;
			case 5:
				(*this).FieldsG[ind] = FieldGraphic(textures[5], j, i);
				break;
			case 6:
				(*this).FieldsG[ind] = FieldGraphic(textures[6], j, i);
				break;
			default:
				break;
			}
		}
		else
		{
			(*this).FieldsG[ind] = FieldGraphic(textures[0], j, i);
		}
		j += 30;
		if (j >= 300)
		{
			j = 0;
			i += 30;
			counter++;
		}
		counter++;
		ind++;
	}
}


State Map::chooseField(int index)
{
	if (index < 100 && index >= 0)
	{
		if (FieldMap[index].getIsBomb() == false)
		{
			if (Overlays[index].GetColor() != Color::Transparent)
			{
				(*this).Overlays[index].Open();
				this->leftCount--;
			}
			return Safe;
		}
		else
			return Bomb;
	}
}

void Map::setSafeUnsafe(unsigned int index)
{
	if (index < 100 && index >= 0)
	{
		Overlays[index].setSafeUnsafe();
	}
}



int Map::GetMovesCount()
{
	return leftCount;
}

void Map::Display(RenderWindow & window)
{
	for (auto it1 = this->FieldsG.begin(); it1 != FieldsG.end(); it1++)
		(*it1).Display(window);
	for (auto it2 = this->Overlays.begin(); it2 != Overlays.end(); it2++)
		(*it2).Display(window);

}

bool Map::IsEmpty(int index)
{
	if (index < 100 && index >= 0)
	{
		if (FieldMap[index].getIsBomb() == false && FieldMap[index].getCounter() == 0)
			return true;
		else
			return false;
	}
}

bool Map::IsNumber(int index)
{
	if (index < 100 && index >= 0)
	{
		if (FieldMap[index].getIsBomb() == false && FieldMap[index].getCounter() != 0)
			return true;
		else
			return false;
	}
}

void Map::clearEmpty(unsigned int index)
{
	if (index < 100 && index >= 0)
	{
		chooseField(index);
		if ((index / 10) != 0)
		{
			if (IsEmpty(index - 10) && Overlays[index - 10].GetColor() != Color::Transparent)
			{
				clearEmpty(index - 10);

			}

			else if (IsNumber(index - 10))
			{
				chooseField(index - 10);
			}
		}
		if ((index / 10) != 9)
		{
			if (IsEmpty(index + 10) && Overlays[index + 10].GetColor() != Color::Transparent)
			{
				clearEmpty(index + 10);
			}
			else if (IsNumber(index + 10))
			{
				chooseField(index + 10);
			}
		}
		if ((index - index / 10 * 10) != 0)
		{
			if (IsEmpty(index - 1) && Overlays[index - 1].GetColor() != Color::Transparent)
			{
				clearEmpty(index - 1);
			}
			else if (IsNumber(index - 1))
			{
				chooseField(index - 1);
			}
		}
		if ((index - index / 10 * 10) != 9)
		{
			if (IsEmpty(index + 1) && Overlays[index + 1].GetColor() != Color::Transparent)
			{
				clearEmpty(index + 1);
			}
			else if (IsNumber(index + 1))
			{
				chooseField(index + 1);
			}
		}
		if ((index / 10) != 0 && (index - index / 10 * 10) != 0)
		{
			if (IsEmpty(index - 10 - 1) && Overlays[index - 10 - 1].GetColor() != Color::Transparent)
			{
				clearEmpty(index - 10 - 1);
			}

			else if (IsNumber(index - 10 - 1))
			{
				chooseField(index - 10 - 1);
			}
		}
		if ((index / 10) != 0 && (index - index / 10 * 10) != 9)
		{
			if (IsEmpty(index - 10 + 1) && Overlays[index - 10 + 1].GetColor() != Color::Transparent)
			{
				clearEmpty(index - 10 + 1);
			}
			else if (IsNumber(index - 10 + 1))
			{
				chooseField(index - 10 + 1);
			}
		}
		if ((index / 10) != 9 && (index - index / 10 * 10) != 0)
		{
			if (IsEmpty(index + 10 - 1) && Overlays[index + 10 - 1].GetColor() != Color::Transparent)
			{
				clearEmpty(index + 10 - 1);
			}
			else if (IsNumber(index + 10 - 1))
			{
				chooseField(index + 10 - 1);
			}
		}
		if ((index / 10) != 9 && (index - index / 10 * 10) != 9)
		{
			if (IsEmpty(index + 10 + 1) && Overlays[index + 10 + 1].GetColor() != Color::Transparent)
			{
				clearEmpty(index + 10 + 1);
			}
			else if (IsNumber(index + 10 + 1))
			{
				chooseField(index + 10 + 1);
			}
		}
	}
	return;
}

void Map::SaveState()
{
	fstream output;
	output.open("stangry.txt", ios::out);
	for (auto it1 = FieldMap.begin(); it1 != FieldMap.end(); it1++)
	{
		output << it1->getSymbol();
	}
	for (auto it2 = Overlays.begin(); it2 != Overlays.end(); it2++)
	{
		if (it2->GetColor() == Color::White)
			output << 'w';
		else if (it2->GetColor() == Color::Black)
			output << 'b';
		else if (it2->GetColor() == Color::Green)
			output << 'g';
		else
			output << 't';
	}
	output.close();
}

void Map::OpenFromFile(unsigned int _size, RenderWindow & window, shared_ptr<Texture> * textures)
{
	fstream input;
	input.open("stangry.txt", std::ios::in | std::ios::out);
	if (input.good() == true)
	{
		char SymbolFromFile;
		FieldMap.resize(_size);
		FieldsG.resize(_size);
		int BombCount = 0;
		for (unsigned int i = 0; i < _size; i++)
		{
			input >> SymbolFromFile;
			if (SymbolFromFile == 'b')
			{
				(*this).FieldMap[i].putBomb();
				BombCount++;
				if (int(i - ROWS) > 0 && int(i - ROWS) < 99)
					++((*this).FieldMap[i - ROWS]);
				if (int(i + ROWS) > 0 && int(i + ROWS) < 99)
					++((*this).FieldMap[i + ROWS]);
				if (int(i - ROWS + 1) > 0 && int(i - ROWS + 1) < 99 && int(i - ROWS + 1) % 10 != 0)
					++((*this).FieldMap[i - ROWS + 1]);
				if (int(i - ROWS - 1) > 0 && int(i - ROWS - 1) < 99 && int(i - ROWS - 1) % 10 != 9)
					++((*this).FieldMap[i - ROWS - 1]);
				if (int(i + ROWS - 1) > 0 && int(i + ROWS - 1) < 99 && int(i + ROWS - 1) % 10 != 9)
					++((*this).FieldMap[i + ROWS - 1]);
				if (int(i + ROWS + 1) > 0 && int(i + ROWS + 1) < 99 && int(i + ROWS + 1) % 10 != 0)
					++((*this).FieldMap[i + ROWS + 1]);
				if (int(i - 1) > 0 && int(i - 1) < 99 && int(i - 1) % 10 != 9)
					++((*this).FieldMap[i - 1]);
				if (int(i + 1) > 0 && int(i + 1) < 99 && int(i + 1) % 10 != 0)
					++((*this).FieldMap[i + 1]);
			}
		}

		this->leftCount = ROWS*COLUMNS - BombCount;
		int counter = 0;
		int ind = 0;
		int i = 100;
		int j = 0;
		Overlays.resize(_size);
		for (auto it = FieldMap.begin(); it != FieldMap.end(); it++)
		{
			input >> SymbolFromFile;
			Overlay overlay = Overlay(Color::White, j, i);

			if (SymbolFromFile == 'b')
				overlay.SetColor(Color::Black);
			else if (SymbolFromFile == 'w')
				overlay.SetColor(Color::White);
			else if (SymbolFromFile == 'g')
				overlay.SetColor(Color::Green);
			else
			{
				overlay.SetColor(Color::Transparent);
				this->leftCount--;
			}


			(*this).Overlays[ind] = overlay;

			Texture  texture;

			if (it->getIsBomb() == false)
			{
				switch (it->getCounter())
				{
				case 0:
					(*this).FieldsG[ind] = FieldGraphic(textures[7], j, i);
					break;
				case 1:
					(*this).FieldsG[ind] = FieldGraphic(textures[1], j, i);
					break;
				case 2:
					(*this).FieldsG[ind] = FieldGraphic(textures[2], j, i);
					break;
				case 3:
					(*this).FieldsG[ind] = FieldGraphic(textures[3], j, i);
					break;
				case 4:
					(*this).FieldsG[ind] = FieldGraphic(textures[4], j, i);
					break;
				case 5:
					(*this).FieldsG[ind] = FieldGraphic(textures[5], j, i);
					break;
				case 6:
					(*this).FieldsG[ind] = FieldGraphic(textures[6], j, i);
					break;
				default:
					break;
				}
			}
			else
			{
				(*this).FieldsG[ind] = FieldGraphic(textures[0], j, i);
			}
			j += 30;
			if (j >= 300)
			{
				j = 0;
				i += 30;
				counter++;
			}
			counter++;
			ind++;
		}
		input.close();
	}
	else
	{
		//co robimy
	}

}