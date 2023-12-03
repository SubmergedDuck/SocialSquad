package app;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MainFileTest {

    @Test
    public void testMain() throws IOException {
        MainFile.main(new String[]{});
    }
}