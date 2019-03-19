import math


def WyznaczWartoscFunkcji(x):
    """Wyznacza wartość funkcji w punkcie x.
    Parameters
    ----------
    liczba1: pierwsze liczba do sprawdzenia
    liczba2: druga liczba do sprawdzenia
    Returns
    -------
    True: gdy sa zaprzyjaznione
    False: nei sa zaprzyjaznione
    """
    return abs(x)/(1+x*x)


def TablicowanieRownoodlegle():
    """Funkcja tablicuje argumenty oraz wartosci w wezlach
    dla rozlozenia punktow rownoodleglego.
    """
    for i in range(n+1):
        x.append(a + i*(b-a)/n)
        y.append(WyznaczWartoscFunkcji(x[i]))


def Lagrange(X):
    """Wylicza wartosc wielomianu lagrange'a w punkcie x.
    Parameters
    ----------
    X: wektor z wartosciami x
    Returns
    -------
    Y: wektor wartosci wielomianu lagrange'a dla odpowiednich wartosci x
    """
    Y = 0.0
    for i in range(n+1):
        temp = 1.0
        for j in range(n+1):
            if(i!=j):
                temp *= ((X-x[j])/(x[i]-x[j]))
        Y = Y+(temp*y[i])
    return Y


def GenerujzLagrangea():
    """Tworzenie tablic opisujacych wielomian lagrangea
    """
    for j in range(np+1):
        xlagrange.append(a+j*(b-a)/np)
        ylagrange.append(Lagrange(xlagrange[j]))
    outputx = open("outx.txt", "w")
    outputy = open("outy.txt", "w")
    outputx.write(''.join(str(xlagrange)))
    outputy.write(''.join(str(ylagrange)))
    outputx.close()
    outputy.close()


x = []
y = []
xlagrange = []
ylagrange = []

# WprowadzanieDanych
a = int(input("Poczatek przedzialu: "))
b = int(input("Koniec przedzialu: "))
n = int(input("Podaj n. n = "))
np = int(input("Podaj np. np = "))

TablicowanieRownoodlegle()
GenerujzLagrangea()
