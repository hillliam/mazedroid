package liam.mazedroid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Color;
/**
 * Created by liam on 13/07/2015.
 */
enum maze_pices
{
    north,
    east,
    all,
    eastwastnorth,
    eastwestsouth,
    noenterable,
    southeast,
    southwest,
    northeast,
    northwest
}

public class maze
{
    public boolean playerison;
    public boolean endpoint;
    public int distance;
    public maze_pices picetype;
    public boolean hasbeing;
    public boolean bestpath;
    public boolean nonreatchable;
    public maze()
    {
        playerison = false;
        endpoint = false;
        hasbeing = false;
        bestpath = false;
        nonreatchable = false;
        picetype = maze_pices.all;
        distance = 10000;
    }
    public void print(int x, int y,int size, int linethicness, Canvas screen, Paint paint)
    {
        paint.setStrokeWidth(0);
        if (playerison)
        {
            paint.setColor(Color.rgb(0x00, 0xFF, 0x00));
            screen.drawRect(x, y, x+size, y+size,paint);
            //drawRect(left, top, right, bottom, paint)
            hasbeing = true;
        }
        else if (endpoint)
        {
            paint.setColor(Color.rgb(0x00, 0x00, 0xFF));
            screen.drawRect(x, y, x+size, y+size,paint);
        }
        else if (bestpath)
        {
            screen.drawRect(x, y, x+size, y+size,paint);
            paint.setColor(Color.rgb(0xFF, 0xA5, 0x00));
        }
        else if (hasbeing)
        {
            paint.setColor(Color.rgb(0xFF, 0x00, 0x00));
            screen.drawRect(x, y, x + size, y + size, paint);
        }
        else if (nonreatchable)
        {
            paint.setColor(Color.rgb(0x69, 0x69, 0x69));
            screen.drawRect(x, y, x + size, y + size, paint);
        }
        else
        {
            paint.setColor(Color.rgb(0xF2, 0xF2, 0xF0));
            screen.drawRect(x, y, x + size, y + size, paint);
        }
        paint.setStrokeWidth(linethicness);
        paint.setColor(Color.rgb(0x00, 0x00, 0x00));
        switch (picetype)
        {
            case east:
                screen.drawRect(x, y, x + size, y + linethicness, paint);
                screen.drawRect(x, y+size - linethicness, x + size, y + linethicness, paint);
            break;
            case eastwastnorth:
                screen.drawRect(x, y + size - linethicness, x + size, y + linethicness, paint);
            break;
            case eastwestsouth:
                screen.drawRect(x, y, x + size, y + linethicness, paint);
            break;
            case north:
                screen.drawRect(x, y, x + linethicness, y + size, paint);
                screen.drawRect(x + size - linethicness, y, x + linethicness, y + size, paint);
            break;
            case northeast:
                screen.drawRect(x, y, x + linethicness, y + size, paint);
                screen.drawRect(x, y, x + size, y + linethicness, paint);
            break;
            case northwest:
                screen.drawRect(x + size - linethicness, y, x + linethicness, y + size, paint);
                screen.drawRect(x, y, x + size, y + linethicness, paint);
            break;
            case southeast:
                screen.drawRect(x, y + size - linethicness, x + size, y + linethicness, paint);
                screen.drawRect(x, y, x + linethicness, y + size, paint);
            break;
            case southwest:
                screen.drawRect(x, y + size - linethicness, x + size, y + linethicness, paint);
                screen.drawRect(x + size - linethicness, y, x + linethicness + size, y + size, paint);
            break;
            case noenterable:
                //SDL_Rect p = {x + 0, y + 0, size, size};
                paint.setStrokeWidth(0);
                screen.drawRect(x, y, x + size, y + size, paint);
            break;
        }
    }
}
