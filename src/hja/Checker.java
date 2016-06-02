package hja;

import java.util.ArrayList;
import static hja.Constants.empty;

public class Checker
{
    boolean checkWin(int player, Data data)
    {
        int one = data.getPos()[0][0],
        two = data.getPos()[0][1],
        three = data.getPos()[0][2],
        four = data.getPos()[1][0],
        five = data.getPos()[1][1],
        six = data.getPos()[1][2],
        seven = data.getPos()[2][0],
        eight = data.getPos()[2][1],
        nine = data.getPos()[2][2];
        return (one == player && two == player && three == player) ||
                (four == player && five == player && six == player) ||
                (seven == player && eight == player && nine == player) ||
                (one == player && four == player && seven == player) ||
                (two == player && five  == player && eight == player) ||
                (three == player && six == player && nine == player) ||
                (one == player && five == player && nine == player) ||
                (three == player && five == player && seven == player);
    }

    public Position getMoveToWin(int player, Data data)
    {
        Data newData = data.clone();
        ArrayList<Position> movesAvailable = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (newData.getPos()[i][j] == empty)
                    movesAvailable.add(new Position(i ,j));
        for (int i = 0; i < movesAvailable.size(); i++)
            if (checkWin(player, (newData.clone()).move(player, movesAvailable.get(i))))
                return movesAvailable.get(i);
        return new Position();
    }
}
