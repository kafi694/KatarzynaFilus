from PIL import Image
import numpy
import matplotlib.pyplot as plt
import re


class ImageManager(object):
    """Image management module.
    Parameters
    ------------
    filepath: file path of the image
    """
    def __init__(self, filepath):
        try:
            if re.search(".*.png$", filepath) or re.search(".*jpg$", filepath):
                self.filepath = filepath
            else:
                raise Exception('File path in a wrong format')
        except Exception as excep:
            print('Exception: {}'.format(excep.args))

    def image2pixelarray(self):
        """
        It converts image to array of numbers representing pixels. It is grey-scaled.
        Returns
        -------
        greyscale_map: 2d numpy array
        A list of lists which make it simple to access the greyscale value by
        im[y][x]
        """
        try:
            im = Image.open(self.filepath).convert('L')
            (width, height) = im.size
            greyscale_map = list(im.getdata())
            greyscale_map = numpy.array(greyscale_map)
            greyscale_map = greyscale_map.reshape((height, width))
            greyscale_map = greyscale_map / 25.5
            greyscale_map = greyscale_map.astype(int)
            return greyscale_map
        except Exception as excep:
            print('Exception: {}'.format(excep.args))

    @staticmethod
    def display_img(img):
        """Display image.
        Parameters
        ----------
        img: array representing image
        """
        try:
            fig, ax = plt.subplots()
            ax.set_xticklabels([])
            ax.set_yticklabels([])
            ax.matshow(img, cmap=plt.cm.binary)
            plt.show()
        except Exception as excep:
            print('Exception: {}'.format(excep.args))

    def display_self(self):
        """Display image."""
        self.display_img(self.image2pixelarray())
