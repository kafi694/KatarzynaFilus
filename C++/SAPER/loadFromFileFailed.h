#pragma once


#include <exception>
#include <string>

//Tworze klase wyjatku - korzystam z niej w razie zlego wczytania pliku z obrazkami z pliku.
class loadFromFileFailed :public std::exception
{
	std::string _message;
public:
	loadFromFileFailed(const std::string& message) : _message(message) {};
	const char* what() const noexcept
	{
		return _message.c_str();
	}
	~loadFromFileFailed() = default;
};
