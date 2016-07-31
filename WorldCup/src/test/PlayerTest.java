import model.Player;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.portable.Streamable;

import static org.junit.Assert.*;

/**
 * Created by brian on 31/07/16.
 */
public class PlayerTest {


    private Player player;
    private final String PLAYER_NAME = "Brian";
    private final String PLAYER_POSITION = "Striker";
    private final int GOALS_SCORED = 5;
    private final int GOALS_SAVED = 0;


    @Before
    public void setUp() throws Exception {

        player = new Player("Brian", "Striker", 5, 0);

    }

    @Test
    public void getName() throws Exception {
        assertEquals(PLAYER_NAME, player.getName());
    }

    @Test
    public void getPlayerPosition() throws Exception {
        assertEquals(PLAYER_POSITION, player.getPlayerPosition());

    }

    @Test
    public void getGoalsScored() throws Exception {
        assertEquals(GOALS_SCORED, player.getGoalsScored());
    }


    @Test
    public void getGoalsSaved() throws Exception {
        assertEquals(GOALS_SAVED, player.getGoalsSaved());
    }


}