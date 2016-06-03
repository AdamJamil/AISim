package hja;

import java.util.ArrayList;

public class Checker implements Constants
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
        ArrayList<Position> movesAvailable = data.getAvailableMoves();
        for (int i = 0; i < movesAvailable.size(); i++)
            if (checkWin(player, (newData.clone()).move(player, movesAvailable.get(i))))
                return movesAvailable.get(i);
        return new Position();
    }

    public ArrayList<Position> getNonForkingMoves(int player, Data data)
    {
        ArrayList<Position> movesAvailable = data.getAvailableMoves();
        for (int i = movesAvailable.size() - 1; i >= 0; i--)
        {
            Data newData = data.clone();
            newData.move(player, movesAvailable.get(i));
            ArrayList<Position> enemyMovesAvailable = data.getAvailableMoves();
            for (int j = 0; j < enemyMovesAvailable.size(); j++)
            {
                Data newNewData = newData.clone();
                newNewData.move(player * enemy, enemyMovesAvailable.get(j));
                if (this.getMoveToWin(player * enemy, newNewData).x != -1)
                {
                    newNewData.move(player, this.getMoveToWin(player * enemy, newNewData));
                    if (this.getMoveToWin(player * enemy, newNewData).x != -1)
                        movesAvailable.remove(i);
                }
            }
        }
        return movesAvailable;
    }
}
