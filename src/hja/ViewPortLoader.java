package hja;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.HashMap;

class ViewPortLoader implements Constants
{
    private GraphicsContext graphicsContext;
    private Data data;
    private HashMap<Short, Image> textures;

    ViewPortLoader(GraphicsContext aGraphicsContext, Data someData)
    {
        graphicsContext = aGraphicsContext;                                                  //gets the gc that was loaded in Main, so that it can draw to it
        data = someData;                                                                     //gets more relevant information for drawing
    }

    void loadViewPort()
    {
        graphicsContext.setLineWidth(lineWidth);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeLine(50, 0, 50, 152);
        graphicsContext.strokeLine(101, 0, 101, 152);
        graphicsContext.strokeLine(0, 50, 152, 50);
        graphicsContext.strokeLine(0, 101, 152, 101);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (data.getPos()[i][j] == x)
                {
                    graphicsContext.strokeLine(i * 51, j * 51, 50 + (i * 51), 50 + (j * 51));
                    graphicsContext.strokeLine(50 + (i * 51), j * 51, i * 51, 50 + (j * 51));
                }
                else if (data.getPos()[i][j] == o)
                {
                    graphicsContext.strokeOval(i * 51, j * 51, 50, 50);
                }
    }
}