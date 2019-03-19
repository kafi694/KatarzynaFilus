def czyZaprzyjaznione(liczba1: int, liczba2: int):
    """Wyznacza czy dwie liczby podane jako argumenty sa zaprzyjaznione (suma dzielnikow jednej jest rowna drugiej,
    a suma dzielnikow drugiej jest rowna pierwszej).
    Parameters
    ----------
    liczba1: pierwsze liczba do sprawdzenia
    liczba2: druga liczba do sprawdzenia
    Returns
    -------
    True: gdy sa zaprzyjaznione
    False: nei sa zaprzyjaznione
    """
    liczby1 = list(range(1, liczba1))
    liczby1 = list(filter(lambda x: liczba1 % x == 0, liczby1))
    liczby2 = list(range(1, liczba2))
    liczby2 = list(filter(lambda x: liczba2 % x == 0, liczby2))

    suma1 = sum(liczby1)
    suma2 = sum(liczby2)

    return suma1 == liczba2 and suma2 == liczba1


kontynuacja = 't'
while kontynuacja == 't':
    print("Podaj 1 liczbe: ")
    liczba1 = int(input())
    print("Podaj 2 liczbe: ")
    liczba2 = int(input())

    print("Liczba 1: {}".format(liczba1))
    print("Liczba 2: {}".format(liczba2))

    if czyZaprzyjaznione(liczba1, liczba2):
        print("Sa zaprzyjaznione")
    else:
        print("Nie sa zaprzyjaznione")

    print("Aby kontynuowac wpisz t: ")
    kontynuacja = input()
