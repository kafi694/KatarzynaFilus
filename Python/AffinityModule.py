import numpy as np


class AffineTransformation(object):
    """Affine transformations module. For 2D.
    Parameters
    ------------
    x : float
    x coordinate of point P(x, y) that is being transformed
    y : float
    y coordinate of point P(x, y) that is being transformed
    """
    def __init__(self, x: float, y: float):
        self.x = x
        self.y = y

    @staticmethod
    def translate(x: float, y: float, dx: float, dy: float):
        """Translate the point P(x, y). Translation vector: v = (dx, dy)
        Parameters
        ----------
        x: x coordinate of the point P
        y: y coordinate of the point P
        dx: translation of x
        dy: translation of y
        Returns
        -------
        translated_vector: vector representing the translated point
        1 additional parameter - z coordinate, it equals 1
        """
        translation_table = np.array([[1, 0, dx], [0, 1, dy], [0, 0, 1]])
        base_vector = np.array([x, y, 1])
        translated_vector = np.dot(translation_table, base_vector)
        return translated_vector

    def translate_self(self, dx: float, dy: float):
        """Translate the point P(self.x, self.y). Translation vector: v = (dx, dy)
        Parameters
        ----------
        dx: translation of x
        dy: translation of y
        Returns
        -------
        translated_vector: vector representing the translated point
        1 additional parameter - z coordinate, it equals 1
        """
        translation_table = np.array([[1, 0, dx], [0, 1, dy], [0, 0, 1]])
        base_vector = np.array([self.x, self.y, 1])
        translated_vector = np.dot(translation_table, base_vector)
        return translated_vector

    @staticmethod
    def scale(x: float, y: float, sx: float, sy: float):
        """Scale the point P(x, y). Scale: s = (sx, sy)
        Parameters
        ----------
        x: x coordinate of the point P
        y: y coordinate of the point P
        sx: scale of x
        sy: scale of y
        Returns
        -------
        scaled_vector: vector representing the scaled point
        1 additional parameter - z coordinate, it equals 1
        """
        scaling_table = np.array([[sx, 0, 0], [0, sy, 0], [0, 0, 1]])
        base_vector = np.array([x, y, 1])
        scaled_vector = np.dot(scaling_table, base_vector)
        return scaled_vector

    def scale_self(self, sx: float, sy: float):
        """Scale the point P(self.x, self.y). Scale: s = (sx, sy)
        Parameters
        ----------
        sx: scale of x
        sy: scale of y
        Returns
        -------
        scaled_vector: vector representing the scaled point
        1 additional parameter - z coordinate, it equals 1
        """
        scaling_table = np.array([[sx, 0, 0], [0, sy, 0], [0, 0, 1]])
        base_vector = np.array([self.x, self.y, 1])
        scaled_vector = np.dot(scaling_table, base_vector)
        return scaled_vector

    @staticmethod
    def rotate(x: float, y: float, a_rad: float, x0: float, y0: float):
        """Rotate the point P(x, y) around the P0(x0, y0). Scale: s = (sx, sy).
        Parameters
        ----------
        x: x coordinate of the point P
        y: y coordinate of the point P
        a_rad: angle of rotation in radians
        x0: x coordinate of P0
        y0: y coordinate of Po
        Returns
        -------
        rotated_vector: vector representing the rotated point
        1 additional parameter - z coordinate, it equals 1
        """
        base_vector = np.array([x, y, 1])
        rotation_table = np.array([[np.cos(a_rad), -np.sin(a_rad), 0],
                                   [np.sin(a_rad), np.cos(a_rad), 0],
                                   [0, 0, 1]])
        translation_forward_table = np.array([[1, 0, x0], [0, 1, y0], [0, 0, 1]])
        translation_back_table = np.array([[1, 0, -x0], [0, 1, -y0], [0, 0, 1]])
        rotated_vector = np.dot(translation_forward_table, np.dot(rotation_table,
                                                                  np.dot(translation_back_table, base_vector)))
        return rotated_vector

    def rotate_self(self, a_rad: float, x0: float, y0: float):
        """Rotate the point P(x, y) around the P0(x0, y0). Scale: s = (sx, sy).
        Parameters
        ----------
        a_rad: angle of rotation in radians
        x0: x coordinate of P0
        y0: y coordinate of Po
        Returns
        -------
        rotated_vector: vector representing the rotated point
        1 additional parameter - z coordinate, it equals 1
        """
        base_vector = np.array([self.x, self.y, 1])
        rotation_table = np.array([[np.cos(a_rad), -np.sin(a_rad), 0],
                                   [np.sin(a_rad), np.cos(a_rad), 0],
                                   [0, 0, 1]])
        translation_forward_table = np.array([[1, 0, x0], [0, 1, y0], [0, 0, 1]])
        translation_back_table = np.array([[1, 0, -x0], [0, 1, -y0], [0, 0, 1]])
        rotated_vector = np.dot(translation_forward_table, np.dot(rotation_table,
                                np.dot(translation_back_table, base_vector)))
        return rotated_vector

    @staticmethod
    def shear(x: float, y: float, s_xaxis: float, s_yaxis: float):
        """Shear the point P(x, y) along x axis by s_xaxis and y axis by s_yaxis.
        Parameters
        ----------
        x: x coordinate of the point P
        y: y coordinate of the point P
        s_xaxis: shear coefficient - x axis
        s_yaxis: shear coefficient - y axis
        Returns
        -------
        sheared_vector: vector representing the sheared point
        1 additional parameter - z coordinate, it equals 1
        """
        base_vector = np.array([x, y, 1])
        shear_matrix = np.array([[1, s_xaxis, 0],
                                [s_yaxis, 1, 0],
                                [0, 0, 1]])
        sheared_vector = np.dot(shear_matrix, base_vector)
        return sheared_vector

    def shear_self(self, s_xaxis: float, s_yaxis: float):
        """Shear the point P(x, y) along x axis by s_xaxis and y axis by s_yaxis.
        Parameters
        ----------
        s_xaxis: shear coefficient - x axis
        s_yaxis: shear coefficient - y axis
        Returns
        -------
        sheared_vector: vector representing the sheared point
        1 additional parameter - z coordinate, it equals 1
        """
        base_vector = np.array([self.x, self.y, 1])
        shear_matrix = np.array([[1, s_xaxis, 0],
                                [s_yaxis, 1, 0],
                                [0, 0, 1]])
        sheared_vector = np.dot(shear_matrix, base_vector)
        return sheared_vector

    @staticmethod
    def homothety(x: float, y: float, scale: float, x0: float, y0: float):
        """Count homothetic transformation of the point P(x, y) with given scale
        and point of homothety P0(x0, y0).
        Parameters
        ----------
        x: x coordinate of the point P
        y: y coordinate of the point P
        scale: scale value
        x0: point of reference, coordinate x
        y0: point of reference, coordinate y
        Returns
        -------
        homothety_result_vector: vector representing the point after homothetic
        transformation, 1 additional parameter - z coordinate, it equals 1
        """
        base_vector = np.array([x, y, 1])
        homothety_array = np.array([[scale, 0, (1 - scale) * x0],
                                    [0, scale, (1 - scale) * y0],
                                    [0, 0, 1]])
        homothety_result_vector = np.dot(homothety_array, base_vector)
        return homothety_result_vector

    def homothety_self(self, scale: float, x0: float, y0: float):
        """Count homothetic transformation of the point P(x, y) with given scale
        and point of homothety P0(x0, y0).
        Parameters
        ----------
        scale: scale value
        x0: point of reference, coordinate x
        y0: point of reference, coordinate y
        Returns
        -------
        homothety_result_vector: vector representing the point after homothetic
        transformation, 1 additional parameter - z coordinate, it equals 1
        """
        base_vector = np.array([self.x, self.y, 1])
        homothety_array = np.array([[scale, 0, (1 - scale) * x0],
                                    [0, scale, (1 - scale) * y0],
                                    [0, 0, 1]])
        homothety_result_vector = np.dot(homothety_array, base_vector)
        return homothety_result_vector

    @staticmethod
    def symmetry(x: float, y: float, x0: float, y0: float):
        """Count symmetrical transformation of the point P(x, y) with given scale
        and point of symmetry P0(x0, y0).
        Parameters
        ----------
        x: x coordinate of the point P
        y: y coordinate of the point P
        x0: point of reference, coordinate x
        y0: point of reference, coordinate y
        Returns
        -------
        symetry_vector: vector representing the point after symmetrical
        transformation, 1 additional parameter - z coordinate, it equals 1
        """
        symetry_vector = AffineTransformation.homothety(x, y, -1, x0, y0)
        return symetry_vector

    @staticmethod
    def reflection(x: float, y: float, a: float, b: float, c: float, k: float):
        """Count reflection of the point P(x, y) with given scale k
        and line: ax + by + c = 0.
        Parameters
        ----------
        x: x coordinate of the point P
        y: y coordinate of the point P
        a: x coefficient
        b: y coefficient
        c: constant term
        k: scale of reflection
        Returns
        -------
        reflected_vector: vector representing the reflected point,
        1 additional parameter - z coordinate, it equals 1
        """
        w = (k - 1)/(a ** 2 + b ** 2)
        reflection_matrix = np.array([[1 + w * a ** 2, w * a * b, w * a * c],
                                      [w * a * b, 1 + w * b ** 2, w * b * c],
                                      [0, 0, 1]])
        base_vector = np.array([x, y, 1])
        reflected_vector = np.dot(reflection_matrix, base_vector)
        return reflected_vector

    def reflection_self(self, a: float, b: float, c: float, k: float):
        """Count reflection of the point P(x, y) with given scale k
        and line: ax + by + c = 0.
        Parameters
        ----------
        a: x coefficient
        b: y coefficient
        c: constant term
        k: scale of reflection
        Returns
        -------
        reflected_vector: vector representing the reflected point,
        1 additional parameter - z coordinate, it equals 1
        """
        w = (k - 1)/(a ** 2 + b ** 2)
        reflection_matrix = np.array([[1 + w * a ** 2, w * a * b, w * a * c],
                                      [w * a * b, 1 + w * b ** 2, w * b * c],
                                      [0, 0, 1]])
        base_vector = np.array([self.x, self.y, 1])
        reflected_vector = np.dot(reflection_matrix, base_vector)
        return reflected_vector


z = AffineTransformation(1, 1)
print(z.translate_self(1, 1))
print(AffineTransformation.translate(1, 1, 1, 1))
print(AffineTransformation.scale(1, 1, 2, 3))
print(AffineTransformation.rotate(1, 4, 1.57, 1, 1))
print(AffineTransformation.shear(1, 2, 0.5, 0))
print(AffineTransformation.homothety(1, 2, 0.5, 0, 0))
print(AffineTransformation.symmetry(1, 2, 0, 0))
print(AffineTransformation.reflection(-4, 2, -3, -2, 5, -1))
