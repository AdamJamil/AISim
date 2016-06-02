package hja;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

/*
    Written by Kenny
    Uses a stack to place priorities on keys pressed by order
 */

class InputHandler
{
    private ArrayList<KeyCode> stack = new ArrayList<>();
    private Position lastClicked = new Position();
    //private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    void mousePressed(int x, int y)
    {
        lastClicked = new Position(x / 51, y / 51);
    }

    void keyPressed(KeyEvent e)                         //Sets the index of the key pressed's keyCode to true
    {                                                   //adds the current key pressed's keyCode to the stack
        //keys.put(e.getCode(), true);
        addToStack(e.getCode());
    }

    void keyReleased(KeyEvent e)                        //Sets the index of the key released's keyCode to false
    {                                                   //removes any key released from the stack
        //keys.put(e.getCode(), false);
        removeFromStack(e.getCode());
    }

    private void addToStack(KeyCode x)                  //If the stack size is 0 it just adds the integer to the arraylist
    {                                                   //Otherwise it scans through the list to see if it already exists
        if (stack.size() == 0)                          //If it does exist the element is not added
            stack.add(x);
        else
        {
            boolean inStack = false;
            for(int i = stack.size() - 1; i >= 0; i--)
            {
                if(stack.get(i) == x)
                    inStack = true;
            }
            if (!inStack)
                stack.add(x);
        }
    }

    private void removeFromStack(KeyCode x)             //Searches through the stack and removes the element that matches the parameter value
    {
        for(int i = stack.size() - 1; i >= 0; i--)
        {
            if(stack.get(i)== x)
                stack.remove(i);
        }
    }

    KeyCode getAction()
    {
        if (stack.size() == 0)
            return KeyCode.DEAD_ABOVEDOT;
        return stack.get(0);
    }

    Position getLastClicked()
    {
        Position temp = new Position(lastClicked.x, lastClicked.y);
        lastClicked = new Position();
        return temp;
    }

    //returns if a key is down and returns the keyCode if it is
    /*
    boolean isKeyDown(KeyCode keyCode)
    {
        return keys.get(keyCode);
    }

    public ArrayList<KeyCode> getStack()
    {
        return stack;
    }

    //returns an element at a specific index of stack
    public KeyCode getIndex(int a)
    {
        return stack.get(a);
    }

    //returns the size of the stack
    public int getSize()
    {
        return stack.size();
    }
    */

}
