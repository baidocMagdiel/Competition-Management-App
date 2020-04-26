import com.demo.entity.Club;
import com.demo.repository.ClubRepository;
import com.demo.service.ClubService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.demo.util.Constant.EMPTY_FIELD;
import static com.demo.util.Constant.SUCCES;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Clasa de test pentru Club
 */
public class ClubTest {

    @InjectMocks
    ClubService clubService;

    @Mock
    ClubRepository clubRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testez metoda de creare a unui club
     */
    @Test
    public void insertClubTest() {

        Club club = new Club();
        when(clubRepository.save(any(Club.class))).thenReturn(club);
        String status = clubService.create("A123", "ACS Tsubaki Sibiu", "Cornelui Coposu 23A Sibiu");
        assertEquals(SUCCES, status);
    }

    /**
     * Testez metoda de actualizare a unui club
     */
    @Test
    public void updateClubTest() {

        Club club = new Club(2, "A123", "ACS Tsubaki Sibiu", "Cornelui Coposu 23A Sibiu");
        when(clubRepository.save(any(Club.class))).thenReturn(club);
        when(clubRepository.findById(club.getId())).thenReturn(java.util.Optional.of(club));
        String status = clubService.update(2, "A123", "ACS Tsubaki Sibiu", "");
        assertEquals(EMPTY_FIELD, status);
    }
}
