package hja;

class Position
{
    int x = -1, y = -1;

    Position()
    {

    }

    Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    boolean isValid()
    {
        return (x != -1 && y != -1);
    }

    boolean isCorner()
    {
        return (x == 0 || x == 2) && (y == 0 || y == 2);
    }

    boolean isEdge()
    {
        return (x == 1 && y != 1) || (y == 1 && x != 1);
    }

    boolean isCenter()
    {
        return (x == 1 && y == 1);
    }
}
