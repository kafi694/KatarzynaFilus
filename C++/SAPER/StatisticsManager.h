#pragma once
#include <iostream>
#include <fstream>
#include <string>

//Klasa odpowiedzialna za zarzadzanie statystykami. 
//Statystyki w formie: ile wygranych i przegranych rozgrywek.
class StatisticsManager
{
	std::string filename;
	std::fstream file;
	int loses;
	int wins;
	bool isValid;
	bool parseFile();//Wczytanie statystyk.
	void outputFile();//Zapis statystyk.
public:
	StatisticsManager(std::string filename);
	int GetLoses();
	int GetWins();
	static enum GameResult{ WINNER, LOSER};
	void AddResult(StatisticsManager::GameResult result);//Po grze trzeba dodac albo do wygranych albo przegranych.
	std::string StastisticsMessage();
	~StatisticsManager();
};

