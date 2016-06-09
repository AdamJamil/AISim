package hja;

import java.util.ArrayList;

class Data implements Constants
{
    private int[][] pos = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

    int[][] getPos()
    {
        return pos;
    }

    boolean move(int player, int x, int y)
    {
        if (pos[x][y] == empty)
        {
            pos[x][y] = player;
            return true;
        }
        System.out.println("EEEEEEEEEEEEEE");
        return false;
    }

    Data move(int player, Position move)
    {
        int x = move.x;
        int y = move.y;
        if (pos[x][y] == empty)
            pos[x][y] = player;
        else
            System.out.println("EEEEEEEEEEEEEE");
        return this;
    }

    ArrayList<Position> getAvailableMoves()
    {
        ArrayList<Position> movesAvailable = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (this.getPos()[i][j] == empty)
                    movesAvailable.add(new Position(i ,j));
        return movesAvailable;
    }

    @Override
    protected Data clone()
    {
        Data data = new Data();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                data.move(this.getPos()[i][j], i, j);
        return data;
    }
}
