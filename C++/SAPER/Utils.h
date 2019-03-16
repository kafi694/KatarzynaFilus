#pragma once
#include <exception>
#include <vector>
#include <string>
#include <fstream>
typedef unsigned int uint;

//Do dzielenia stringa z pliku.
class Utils
{
	Utils();
public:
	static std::vector<std::string> split(std::string toSplit, std::string Separator= " ");//Dzielenie po znaku spacji.
};