import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import com.alllioooooo.repository.CatRepository;
import com.alllioooooo.services.CatService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CatServiceTests {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private CatRepository catRepository;

    private CatService catService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(null);
        catService = new CatService(sessionFactory);
        catService.catRepository = catRepository;
    }

    @Test
    void findCatByName_shouldReturnCat_whenCatExists() {
        String catName = "Какун";
        Cat cat = new Cat();
        cat.setName(catName);
        when(catRepository.findByName(catName)).thenReturn(cat);

        Cat foundCat = catService.findCatByName(catName);

        assertEquals(catName, foundCat.getName());
        verify(catRepository).findByName(catName);
    }

    @Test
    void findCatsByOwner_shouldReturnCatsList_whenOwnerHasCats() {
        Owner owner = new Owner();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        List<Cat> cats = Arrays.asList(cat1, cat2);
        when(catRepository.findByOwner(owner)).thenReturn(cats);

        List<Cat> result = catService.findCatsByOwner(owner);

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(catRepository).findByOwner(owner);
    }

    @Test
    void deleteCat_shouldCallDeleteCatMethodOfRepository() {
        Cat cat = new Cat();

        catService.deleteCat(cat);

        verify(catRepository).deleteCat(cat);
    }

    @Test
    void addFriendship_shouldEstablishFriendshipBetweenTwoCats() {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();

        catService.addFriendship(cat1, cat2);

        verify(catRepository).addFriendship(cat1, cat2);
    }

    @Test
    void findAllCats_shouldReturnAllCats() {
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        when(catRepository.findAll()).thenReturn(cats);

        List<Cat> result = catService.findAllCats();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(catRepository).findAll();
    }
}