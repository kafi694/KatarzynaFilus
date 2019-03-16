#include "StatisticsManager.h"
#include "Utils.h"
#include <Windows.h>



bool StatisticsManager::parseFile()
{
	try
	{
		file.open(filename.c_str(), std::ios_base::in);
		if (!file.good())
			return false;
		std::string line;
		std::getline( file, line);
		std::vector<std::string> splitted = Utils::split(line);
		if (splitted.size() != 2)
			return false;
		this->wins = std::stoi(splitted[0]);
		this->loses = std::stoi(splitted[1]);
		file.close();
		return ((wins>=0) && (loses>=0));
	}

	catch (std::invalid_argument)
	{
		MessageBox(NULL, "Invalid statistics file", "Invalid statistics", MB_OK | MB_ICONERROR);
		file.close();
		return false;
	}

	catch (...)
	{
		file.close();
		return false;
	}
	return false;
}

void StatisticsManager::outputFile()
{
	try
	{
		file.open(filename.c_str(), std::ios_base::out);
		if (!file.good())
		{
			file.close();
			return;
		}
		file << wins << " " << loses << std::endl;
		file.close();
	}
	catch (...)
	{
		file.close();
		return;
	}
}

StatisticsManager::StatisticsManager(std::string filename)
{
	this->filename = filename;
	this->isValid = this->parseFile();
	if (!this->isValid)
	{
		this->wins = 0;
		this->loses = 0;
	}
}

int StatisticsManager::GetLoses()
{
	return loses;
}

int StatisticsManager::GetWins()
{
	return wins;
}

void StatisticsManager::AddResult(StatisticsManager::GameResult result)
{
	if (result == StatisticsManager::GameResult::WINNER)
		wins++;
	else
		loses++;
	this->outputFile();
}

std::string StatisticsManager::StastisticsMessage()
{
	try 
	{
		std::string  message = "WINS: " + std::to_string(wins) + " LOSSES: " + std::to_string(loses);
		return message;
	}
	catch (...)
	{
		return std::string("");
	}
}


StatisticsManager::~StatisticsManager()
{
}
