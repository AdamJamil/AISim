package hja;

import java.util.ArrayList;

public class ProceduralAI implements Constants
{
    private Checker checker = new Checker();
    private int player = o;

    ProceduralAI(int player)
    {
        this.player = player;
    }

    Position getMove(Data data)
    {
        if (checker.getMoveToWin(player, data).isValid())
        {
            System.out.println("0");
            return checker.getMoveToWin(player, data);
        }
        else if (checker.getMoveToWin(player * enemy, data).isValid())
        {
            System.out.println("1");
            return checker.getMoveToWin(player * enemy, data);
        }
        //else if (checker.getForkingMove(player, data).isValid())
        //{
        //    System.out.println("2");
        //    return checker.getForkingMove(player, data);
        //}
        else
        {
            ArrayList<Position> remainingMoves = checker.getNonForkingMoves(player, data);
            System.out.println(remainingMoves);
            for (int i = 0; i < remainingMoves.size(); i++)
                if (remainingMoves.get(i).isCenter())
                    return remainingMoves.get(i);
            for(int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                    System.out.print(data.getPos()[i][j]);
                System.out.println();
            }

            if (data.getPos()[0][0] == player * enemy && data.getPos()[2][2] == empty)
                return new Position(2, 2);
            if (data.getPos()[0][2] == player * enemy && data.getPos()[2][0] == empty)
                return new Position(2, 0);
            if (data.getPos()[2][0] == player * enemy && data.getPos()[0][2] == empty)
                return new Position(0, 2);
            if (data.getPos()[2][2] == player * enemy && data.getPos()[0][0] == empty)
                return new Position(0, 0);
            for (int i = 0; i < remainingMoves.size(); i++)
                if (remainingMoves.get(i).isCorner())
                    return remainingMoves.get(i);
            for (int i = 0; i < remainingMoves.size(); i++)
                if (remainingMoves.get(i).isEdge())
                    return remainingMoves.get(i);
        }
        System.out.println("!!");
        return new Position();
    }

    int getPlayer()
    {
        return player;
    }
}
