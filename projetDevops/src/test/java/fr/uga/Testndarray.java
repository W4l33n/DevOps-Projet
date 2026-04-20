package fr.uga;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class Testndarray {

    @Test
    public void TestConstructeur1D() {
        Ndarray a = new Ndarray(new float[] { 1f, 2f, 3f });
        assertEquals(1, a.getNdim());// verifier dimension
        assertArrayEquals(new int[] { 3 }, a.getShape());// verifier forme
        assertEquals(3, a.getSize());// verifier taille
    }

    @Test
    public void TestConstructeur2D() {
        Ndarray a = new Ndarray(new float[][] { { 1f, 2f, 3f }, { 4f, 5f, 6f } });
        assertEquals(2, a.getNdim());// verifier dimension
        assertArrayEquals(new int[] { 2, 3 }, a.getShape());// verifier forme
        assertEquals(6, a.getSize());// verifier taille
    }

    @Test
    public void TestCopieConstructeur1D() {
        float[] tab = { 1f, 2f, 3f };
        Ndarray a = new Ndarray(tab);
        tab[0] = 17f;
        assertEquals(1f, a.get(0));
    }

    @Test
    public void TestCopieConstructeur2D() {
        float[][] tab = { { 1f, 2f, 3f }, { 1f, 2f, 3f } };
        Ndarray a = new Ndarray(tab);
        tab[0][0] = 17f;
        assertEquals(1f, a.get(0, 0));
    }

    @Test
    public void TestZeros1D() {
        Ndarray a = Ndarray.zeros(4);
        assertEquals(1, a.getNdim());// verifier dimension
        assertEquals(4, a.getSize());// verifier taille
        for (int i = 0; i < 4; i++) {
            assertEquals(0f, a.get(i));
        }
    }

    @Test
    public void TestZeros2D() {
        Ndarray a = Ndarray.zeros(2, 3);
        assertEquals(2, a.getNdim());// verifier dimension
        assertArrayEquals(new int[] { 2, 3 }, a.getShape());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0f, a.get(i, j));
            }
        }
    }

    @Test
    public void testZerosDimInvalide() {
        assertThrows(IllegalArgumentException.class, () -> Ndarray.zeros(2, 3, 4));
    }

    @Test
    public void TestOnes1D() {
        Ndarray a = Ndarray.ones(3);
        for (int i = 0; i < 3; i++) {
            assertEquals(1f, a.get(i));
        }
    }

    @Test
    public void TestOnes2D() {
        Ndarray a = Ndarray.ones(2, 2);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(1f, a.get(i, j));
            }
        }
    }

    @Test
    public void testArrangeInvalide() {
        assertThrows(IllegalArgumentException.class, () -> Ndarray.arange(1, 5, 0));
    }

    @Test
    // arange de 0 à 5 avec un pas de 1
    public void testArrangeEntier() {
        Ndarray a = Ndarray.arange(0f, 5f, 1f);
        assertEquals(6, a.getSize());
        for (int i = 0; i <= 5; i++) {
            assertEquals((float) i, a.get(i));
        }
    }

    @Test
    // arange de 1 à 10 avec un pas de 3
    public void testArrangeEntier3() {
        Ndarray a = Ndarray.arange(1f, 10f, 3f);
        assertEquals(4, a.getSize());
        assertEquals(1, a.get(0));
        assertEquals(4, a.get(1));
        assertEquals(7, a.get(2));
        assertEquals(10, a.get(3));

    }

    @Test
    // arange de 0 à 1 avec un pas de 0.5
    public void testArrangeFloat() {
        Ndarray a = Ndarray.arange(0f, 1f, 0.5f);
        assertEquals(3, a.getSize());
        assertEquals(0f, a.get(0));
        assertEquals(0.5f, a.get(1));
        assertEquals(1f, a.get(2));
    }

    @Test
    public void Get1Don2D() {
        Ndarray a = new Ndarray(new float[][] { { 1f, 2f }, { 3f, 4f } });
        assertThrows(IllegalStateException.class, () -> a.get(0));
    }

    @Test
    public void Get2Don1D() {
        Ndarray a = new Ndarray(new float[] { 3f, 4f });
        assertThrows(IllegalStateException.class, () -> a.get(0, 0));
    }

    @Test
    // faire des modificatione ne doit pas affecter la shape
    void Getshapecopy() {
        Ndarray a = new Ndarray(new float[] { 1f, 2f, 3f });
        int[] shape = a.getShape();
        shape[0] = 8;
        assertArrayEquals(new int[] { 3 }, a.getShape());
    }

    @Test
    void TestToString2D() {
        Ndarray a = new Ndarray(new float[][] { { 1f, 2f }, { 3f, 4f } });
        String s = a.toString();

        assertTrue(s.startsWith("array("));
        assertTrue(s.contains("1."));
        assertTrue(s.contains("2."));
        assertTrue(s.contains("3."));
        assertTrue(s.contains("4."));
    }

    @Test
    void TestToString1D() {
        Ndarray a = new Ndarray(new float[] { 3f, 4f });
        String s = a.toString();

        assertTrue(s.startsWith("array("));
        assertTrue(s.contains("3."));
        assertTrue(s.contains("4."));
    }

    // tests pour l'addition
    @Test
    public void TestAdd1D() {
        Ndarray a = new Ndarray(new float[] { 1f, 2f, 3f });
        Ndarray b = new Ndarray(new float[] { 4f, 5f, 6f });
        Ndarray c = a.add(b);
        assertEquals(5f, c.get(0));
        assertEquals(7f, c.get(1));
        assertEquals(9f, c.get(2));
    }

    @Test
    public void TestAdd2D() {
        Ndarray a = new Ndarray(new float[][] { { 1f, 2f }, { 3f, 4f } });
        Ndarray b = new Ndarray(new float[][] { { 5f, 6f }, { 7f, 8f } });
        Ndarray c = a.add(b);
        assertEquals(6f, c.get(0, 0));
        assertEquals(12f, c.get(1, 1));
    }

    @Test
    public void TestAddInvalidShape() {
        Ndarray a = new Ndarray(new float[] { 1f, 2f, 3f });
        Ndarray b = Ndarray.zeros(2, 2);
        assertThrows(IllegalArgumentException.class, () -> a.add(b));
    }

    // tests pour le reshape
    @Test
    public void TestReshape1DTo2D() {
        Ndarray a = new Ndarray(new float[] { 1f, 2f, 3f, 4f, 5f, 6f });
        Ndarray b = a.reshape(2, 3);
        assertEquals(2, b.getNdim());
        assertEquals(1f, b.get(0, 0));
        assertEquals(4f, b.get(1, 0));
        assertEquals(6f, b.get(1, 2));
    }

    @Test
    public void TestReshape2DTo1D() {
        Ndarray a = new Ndarray(new float[][] { { 1f, 2f, 3f }, { 4f, 5f, 6f } });
        Ndarray b = a.reshape(6);
        assertEquals(1, b.getNdim());
        assertEquals(4f, b.get(3));
        assertEquals(6f, b.get(5));
    }

    @Test
    public void TestReshapeInvalidSize() {
        Ndarray a = new Ndarray(new float[] { 1f, 2f, 3f, 4f });
        assertThrows(IllegalArgumentException.class, () -> a.reshape(3, 3));
    }
}
