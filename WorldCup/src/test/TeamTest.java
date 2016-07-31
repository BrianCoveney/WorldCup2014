import model.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by brian on 31/07/16.
 */
public class TeamTest {

    private Team team;
    private final String PLAYER_NAME = "John";
    private final String TEAM_NAME = "Ireland";
    private final int GAMES_WON = 4;


    @Before
    public void setUp() throws Exception {
        team = new Team("John", "Ireland", 4);

    }

    @Test
    public void getTeamName() throws Exception {
        assertEquals(TEAM_NAME, team.getTeamName());

    }

    @Test
    public void getPlayerName() throws Exception {
        assertEquals(PLAYER_NAME, team.getPlayerName());

    }

    @Test
    public void getGamesWon() throws Exception {
        assertEquals(GAMES_WON, team.getGamesWon());
    }

}