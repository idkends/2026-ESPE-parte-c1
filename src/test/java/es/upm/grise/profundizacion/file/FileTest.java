package es.upm.grise.profundizacion.file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    public void smokeTest() {

    }

    @Test
    public void addProperty_guardaContenidoYMarcaTipoProperty() {
        File file = new File();

        file.addProperty(new char[] {'a', 'b', 'c'});

        assertEquals(FileType.PROPERTY, file.getType());
        assertEquals(3, file.getContent().size());
        assertEquals(Character.valueOf('a'), file.getContent().get(0));
        assertEquals(Character.valueOf('b'), file.getContent().get(1));
        assertEquals(Character.valueOf('c'), file.getContent().get(2));
    }

    @Test
    public void getCRC32_devuelveValorConfiguradoEnFileUtils() {
        FileUtils utils = new FileUtils();
        utils.setCRC(12345L);

        File file = new File(utils);
        file.addProperty(new char[] {'h', 'i'});

        assertEquals(12345L, file.getCRC32());
    }

    @Test
    public void getCRC32_conContenidoVacio_devuelveCRCDeFileUtils() {
        FileUtils utils = new FileUtils();
        utils.setCRC(999L);

        File file = new File(utils);

        assertEquals(999L, file.getCRC32());
    }
}
