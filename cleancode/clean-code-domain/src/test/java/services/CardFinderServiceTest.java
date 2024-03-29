package services;

import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.pojo.enums.cards.CardNameEnum;
import com.cleancode.domain.pojo.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.Card;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.services.card.CardFinderService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardFinderServiceTest {
    @Mock
    private CardPersistencePort cardPersistencePort;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private CardFinderService cardCreator;

    @Test
    public void shouldFindAllCards() {
        List<Card> toReturn = new ArrayList<>();
        for(long i = 0L; i < 8; i++){
            Card card = new Card(
                    i,
                    "12345",
                    CardRarityEnum.COMMON,
                    CardSpecialtyEnum.MAGE,
                    CardNameEnum.JONATHAN,
                    100,
                    10
            );
            toReturn.add(card);
        }


        when(cardPersistencePort.findAllCards()).thenReturn(toReturn);

        final var savedCard = cardCreator.findAllCards();

        assertEquals(savedCard.size(), toReturn.size());
        verify(cardPersistencePort, times(1)).findAllCards();
    }
}
