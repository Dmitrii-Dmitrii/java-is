import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.Onshin.Cats.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MethodTests {
    @Mock
    private CatDataAccessible catDataAccess;
    @InjectMocks
    private CatService catService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCatById() {
        var cat = new Cat();
        cat.setName("jsl");
        cat.setColor(Colors.BROWN);

        when(catDataAccess.findById(1)).thenReturn(Optional.of(cat));
        CatDto catDto = catService.findCatById(1);
        verify(catDataAccess).findById(1);
        assertEquals(catDto.getName(), "jsl");
        assertEquals(catDto.getColor(), Colors.BROWN);
    }

    @Test
    public void testDeleteCat() {
        var cat = new Cat();
        cat.setName("jsl");
        cat.setColor(Colors.BROWN);

        when(catDataAccess.findById(1)).thenReturn(Optional.of(cat));
        boolean deleteResult = catService.deleteCatById(1);
        verify(catDataAccess).delete(cat);
        assertTrue(deleteResult);
    }
}
