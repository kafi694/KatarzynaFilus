from sklearn import datasets
from ImageManagement import ImageManager
from ImageRecognizer import ImageRecognizer

digits = datasets.load_digits()

X = digits.data
y = digits.target

imageManager = ImageManager(r'C:\Users\filus\PycharmProjects\PicturesRecognition\file5.jpg')

image = imageManager.image2pixelarray()

imageManager.display_self()

imageRecognizer = ImageRecognizer(image, X, y)

print('Your match is: {}'.format(imageRecognizer.giveBestMatch()))

