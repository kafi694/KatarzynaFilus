from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd


class ImageRecognizer(object):
    """Affine transformations module. For 2D.
    Parameters
    ------------
    X: set of images (represented by arrays of numbers - pixels) as an array
    y: targets of X values (correct number that is represented on the image)
    image: image represented by array of numbers - pixels
    """
    def __init__(self, image, X, y):
        self.X = X
        self.image = image
        self.y = y

    def countCosineSimilarity(self):
        """Function responsible for counting cosine similarity
        Returns
        -------
        cosSimilarityResults: results of cosine similarity
        """
        try:
            cosineSimilarity = cosine_similarity(self.image.reshape(1, -1), self.X)
            cosSimilarityResults = pd.DataFrame(cosineSimilarity).T
            cosSimilarityResults.columns = ['similarity']
            yval = pd.DataFrame(self.y)
            cosSimilarityResults['yval'] = yval.values
            return cosSimilarityResults
        except Exception as excep:
            print('Exception: {}'.format(excep.args))

    def sortCosineSimilarity(self):
        """It sort the results of cosine similarity.
        Returns
        -------
        cosSimilarityResults.sort_values('similarity', ascending=False):
        sorted descending by similarity (probability) results of cosine similarity
        """
        try:
            cosSimilarityResults = self.countCosineSimilarity()
            return cosSimilarityResults.sort_values('similarity', ascending=False)
        except Exception as excep:
            print('Exception: {}'.format(excep.args))

    def giveBestMatch(self):
        """It counts the best match - most probable number.
        Returns
        -------
        bestMatch.values[0]: best match to given image, a number.
        """
        try:
            cosSimilarityResultsSorted = self.sortCosineSimilarity()
            labels = cosSimilarityResultsSorted['yval']
            labels = labels.T
            bestMatchLabels = labels[:5]
            bestMatch = bestMatchLabels.mode()
            return bestMatch.values[0]
        except Exception as excep:
            print('Exception: {}'.format(excep.args))
