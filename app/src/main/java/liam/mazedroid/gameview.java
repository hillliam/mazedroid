// Copyright © Liam Hill 2015
package liam.mazedroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * TODO: game display class
 */
public class gameview extends View {
    int mazesize = 30;
    int moves = 0;
    int bestmoves = 0;
    maze [][] map = new maze[mazesize][mazesize];
    int with = 820;
    int hight = 820;
    int bpp = 32;
    boolean win = false;
    int beginx = 0;
    int beginy = 0;
    int numFrames = 0;
    int mousex = 0;
    int mousey = 0;
    Paint paint;

    public gameview(Context context, int mode) {
        super(context);
        init(mode);
    }

    public gameview(Context context, AttributeSet attrs, int mode) {
        super(context, attrs);
        init(mode);
    }

    public gameview(Context context, AttributeSet attrs, int defStyle, int mode) {
        super(context, attrs, defStyle);
        init(mode);
    }

    private void init(int mode) {
        for (int i = 0; i < mazesize; i++)
            for (int j = 0; j < mazesize; j++)
                map[i][j] = new maze();
        switch (mode) {
            case 1: // completable random
                startcompleatablerandom();
                break;
            case 2: // random
                startrandom();
                break;
            case 3: // load maze
                startload();
                break;
            case 4: // preset
                startpreset();
            //case 5: // edit mode moveing to new view for save and exit button
             //   startedit();
        }
    }
    public void startcompleatablerandom()
    {
        clearmap();
        randommaze();
        while (!compleatiblemaze())
        {
            rerandommaze();
        }
        int a = getbestmoves();
        //cout<<"this maze can be compleated in "<<a<<"moves"<<endl;
        blankstart();
    }
    public void blankstart()
    {
        bestmoves = getbestmoves();
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                if (map[i][j].playerison)
                {
                    beginx = i;
                    beginy = j;
                }
            }
        }
    }
    public void startrandom()
    {
        randommaze();
        blankstart();
    }
    public void startload()
    {

        blankstart();
    }
    public void startpreset()
    {
        blankstart();
    }
    public void clearmap()
    {
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                map[i][j].playerison = false;
                map[i][j].endpoint = false;
                map[i][j].bestpath = false;
                map[i][j].hasbeing = false;
                map[i][j].nonreatchable = false;
                map[i][j].distance = 10000;
                map[i][j].picetype = maze_pices.all;
            }
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawmaze(canvas);
    }
    boolean ValidMoves(int startx, int starty, int tox, int toy)
    {
        if (tox < 0 || tox > mazesize - 1 || toy < 0 || toy > mazesize - 1)
        {
            return false;
        }
        switch (map[startx][starty].picetype)
        {
            case all:
            case north:
            case eastwastnorth:
            case southeast:
            case southwest:
                if (tox == startx && toy == starty - 1)
                {
                    switch (map[tox][toy].picetype)
                    {
                        case all:
                        case north:
                        case eastwestsouth:
                        case northeast:
                        case northwest:
                            return true;
                    }
                }
                break;
        }
        switch (map[startx][starty].picetype)
        {
            case all:
            case east:
            case eastwestsouth:
            case eastwastnorth:
            case northwest:
            case southwest:
                if (tox == startx - 1 && toy == starty)
                {
                    switch (map[tox][toy].picetype)
                    {
                        case all:
                        case east:
                        case eastwastnorth:
                        case eastwestsouth:
                        case southeast:
                        case northeast:
                            return true;
                    }
                }
                break;
        }
        switch (map[startx][starty].picetype)
        {
            case all:
            case north:
            case eastwestsouth:
            case northeast:
            case northwest:
                if (tox == startx && toy == starty + 1)
                {
                    switch (map[tox][toy].picetype)
                    {
                        case all:
                        case north:
                        case eastwastnorth:
                        case southeast:
                        case southwest:
                            return true;
                    }
                }
                break;
        }
        switch (map[startx][starty].picetype)
        {
            case all:
            case east:
            case eastwestsouth:
            case eastwastnorth:
            case northeast:
            case southeast:
                if (tox == startx + 1 && toy == starty)
                {
                    switch (map[tox][toy].picetype)
                    {
                        case all:
                        case east:
                        case eastwastnorth:
                        case eastwestsouth:
                        case northwest:
                        case southwest:
                            return true;
                    }
                }
                break;
        }
        return false;
    }
    int getmovesx(int startx, int direction)
    {
        switch (direction)
        {
            case 1:
                return startx;
            case 2:
                return startx - 1;
            case 3:
                return startx;
            case 4:
                return startx + 1;
        }
        return 0;
    }
    int getmovesy(int starty, int direction)
    {
        switch (direction)
        {
            case 1:
                return starty - 1;
            case 2:
                return starty;
            case 3:
                return starty + 1;
            case 4:
                return starty;
        }
        return 0;
    }
    void Pathfind()
    {
        int startx = 0;
        int starty = 0;
        boolean found = false;
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                if (map[i][j].playerison)
                {
                    startx = i;
                    starty = j;
                    found = true;
                    break;
                }
            }
        }
        if (found)
        {
            map[startx][starty].distance = 0;
            while (true)
            {
                boolean madeProgress = false;
                for (int i = 0; i < mazesize; i++)
                {
                    for (int j = 0; j < mazesize; j++)
                    {
                        int x = i;
                        int y = j;
                        int passHere = map[x][y].distance;
                        for (int a = 1; a < 5; a++)
                        {
                            int newx = getmovesx(x,a);
                            int newy = getmovesy(y,a);
                            if (ValidMoves(x, y, newx, newy))
                            {
                                int newPass = passHere + 1;
                                if (map[newx][newy].distance > newPass)
                                {
                                    map[newx][newy].distance = newPass;
                                    madeProgress = true;
                                }
                            }
                        }
                    }
                }
                if (!madeProgress)
                {
                    break;
                }
            }
        }
    }
    int getbestmoves()
    {
        Pathfind();
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                if (map[i][j].endpoint)
                {
                    return map[i][j].distance;
                }
            }
        }
        return 0;
    }
    boolean compleatiblemaze()
    {
        Pathfind();
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                if (map[i][j].endpoint && map[i][j].distance != 10000)
                {
                    return true;
                }
            }
        }
        return false;
    }
    void rerandommaze()
    {
        int lowerBound = 0;
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                if (map[i][j].distance == 10000)
                {
                    Random random = new Random();
                    int randnum = random.nextInt(10 - lowerBound + 1) + lowerBound; // (lowerBound + (int)(1 * 100) % (10 - lowerBound));
                    map[i][j].picetype = maze_pices.values()[randnum];
                    if (map[i][j].picetype == maze_pices.noenterable && map[i][j].playerison)
                    {
                        map[i][j].picetype = maze_pices.all;
                    }
                    if (map[i][j].picetype == maze_pices.noenterable && map[i][j].endpoint)
                    {
                        map[i][j].picetype = maze_pices.all;
                    }
                }
            }
        }
    }
    void showbestpath()
    {
        boolean found = false;
        int exitx = 0;
        int exity = 0;
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                if (map[i][j].endpoint)
                {
                    exitx = i;
                    exity = j;
                    found = true;
                }
            }
        }
        while (found)
        {
            int lowestx = 0;
            int lowesty = 0;
            int lowest = 10000;
            for (int i = 1; i < 5; i++)
            {
                int newx = getmovesx(exitx,i);
                int newy = getmovesy(exity,i);
                if (ValidMoves(exitx, exity, newx, newy))
                {
                    int count = map[newx][newy].distance;
                    if (count < lowest)
                    {
                        lowest = count;
                        lowestx = newx;
                        lowesty = newy;
                    }
                }
            }
            if (lowest != 10000)
            {
                map[lowestx][lowesty].bestpath = true;
                exitx = lowestx;
                exity = lowesty;
            }
            else
            {
                break;
            }
            if (exitx == beginx & exity == beginy)
            {
                break;
            }
        }
    }
    void shownonreatchable()
    {
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                if (map[i][j].distance == 10000)
                {
                    map[i][j].nonreatchable = true;
                }
            }
        }
    }
    void movemaze(int direction)
    {
        int x = 0;
        int y = 0;
        boolean canmove = false;
        for (int i = 0; i < 30; i++)
        {
            for (int j = 0; j < 30; j++)
            {
                if (map[i][j].playerison)
                {
                    x = i;
                    y = j;
                    canmove = true;
                }
                if (map[i][j].playerison & map[i][j].endpoint)
                {
                    canmove = false;
                    if (!win)
                    {
                        shownonreatchable();
                        win = true;
                        if (moves > bestmoves)
                        {
                            //EM_ASM_(window.alert('you have compleated the maze in ' + $0 + ' try again and acheave ' + $1),moves, bestmoves);
                            //cout<<"you have compleated the maze in "<<moves<<" try again and acheave "<<bestmoves<<endl;
                            showbestpath();
                        }
                        else
                        {
                            //EM_ASM_(window.alert('you have compleated the maze in ' + $0),moves);
                            //cout<<"you have compleated the maze in "<<moves<<endl;
                        }
                    }
                    else
                    {
                        //EM_ASM_(window.alert('you have compleated the maze in ' + $0 + ' try a new maze'),moves);
                        //cout<<"you have compleated the maze in "<<moves<<" try a new maze"<<endl;
                    }
                }
            }
        }
        if (canmove)
        {
            if (direction == 1)// up
            {
                if (y != 0)
                {
                    switch (map[x][y].picetype)
                    {
                        case all:
                        case north:
                        case eastwastnorth:
                        case southeast:
                        case southwest:
                            switch(map[x][y - 1].picetype)
                            {
                                case all:
                                case north:
                                case eastwestsouth:
                                case northeast:
                                case northwest:
                                    map[x][y].playerison = false;
                                    map[x][y - 1].playerison = true;
                                    moves++;
                                    break;
                            }
                            break;
                    }

                }
            }
            if (direction == 2)//left
            {
                if (x != 0)
                {
                    switch (map[x][y].picetype)
                    {
                        case all:
                        case east:
                        case eastwestsouth:
                        case eastwastnorth:
                        case northwest:
                        case southwest:
                            switch (map[x - 1][y].picetype)
                            {
                                case all:
                                case east:
                                case eastwastnorth:
                                case eastwestsouth:
                                case southeast:
                                case northeast:
                                    map[x][y].playerison = false;
                                    map[x - 1][y].playerison = true;
                                    moves++;
                                    break;
                            }
                            break;
                    }
                }
            }
            if (direction == 3)//down
            {
                if (y != 30 - 1)
                {
                    switch (map[x][y].picetype)
                    {
                        case all:
                        case north:
                        case eastwestsouth:
                        case northeast:
                        case northwest:
                            switch (map[x][y + 1].picetype)
                            {
                                case all:
                                case north:
                                case eastwastnorth:
                                case southeast:
                                case southwest:
                                    map[x][y].playerison = false;
                                    map[x][y + 1].playerison = true;
                                    moves++;
                                    break;
                            }
                            break;
                    }
                }
            }
            if (direction == 4)//right
            {
                if (x != 30 - 1)
                {
                    switch (map[x][y].picetype)
                    {
                        case all:
                        case east:
                        case eastwestsouth:
                        case eastwastnorth:
                        case northeast:
                        case southeast:
                            switch (map[x + 1][y].picetype)
                            {
                                case all:
                                case east:
                                case eastwastnorth:
                                case eastwestsouth:
                                case northwest:
                                case southwest:
                                    map[x][y].playerison = false;
                                    map[x + 1][y].playerison = true;
                                    moves++;
                                    break;
                            }
                            break;
                    }
                }
            }
        }
    }
    void drawmaze(Canvas screen)
    {
        //SDL_LockSurface(screen);
        int space = 25;
        int linethicness = 2;
        int locationx = 20;
        int locationy = 30;
        for (int i = 0; i < mazesize; i++)
        {
            locationy = 30;
            for (int j = 0; j < mazesize; j++)
            {
                locationy += space;
                map[i][j].print(locationx ,locationy,space,linethicness,screen,paint);
                if (map[i][j].endpoint && mousex >= locationx && mousex <= locationx + space && mousey >= locationy && mousey <= locationy + space)
                {
                    //cout<<"this is the location you have to reach"<<endl;
                }
                else if (map[i][j].playerison && mousex >= locationx && mousex <= locationx + space && mousey >= locationy && mousey <= locationy + space)
                {
                    //cout<<"you are here"<<endl;
                }
                else if (map[i][j].bestpath && mousex >= locationx && mousex <= locationx + space && mousey >= locationy && mousey <= locationy + space)
                {
                    //cout<<"the way you should have gone"<<endl;
                }
                else if (map[i][j].hasbeing && mousex >= locationx && mousex <= locationx + space && mousey >= locationy && mousey <= locationy + space)
                {
                    //cout<<"you where here"<<endl;
                }
                else if (map[i][j].nonreatchable && mousex >= locationx && mousex <= locationx + space && mousey >= locationy && mousey <= locationy + space)
                {
                    //cout<<"not reatchable"<<endl;
                }

            }
            locationx += space;
        }
    }
    void randommaze()
    {
        Random random = new Random();
        int lowerBound = 0;
        int upperBound = 30;
        int rndValue1 = random.nextInt(upperBound - lowerBound +1)+lowerBound; //(lowerBound + (int)(emscripten_random()* 100) % (upperBound - lowerBound));
        int rndValue2 = random.nextInt(upperBound - lowerBound +1)+lowerBound; //(lowerBound + (int)(emscripten_random()* 100) % (upperBound - lowerBound));
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                map[i][j].playerison = false;
                map[i][j].endpoint = false;
            }
        }
        map[rndValue1][rndValue2].playerison = true;
        rndValue1 = random.nextInt(upperBound - lowerBound +1)+lowerBound;
        rndValue2 = random.nextInt(upperBound - lowerBound +1)+lowerBound;
        map[rndValue1][rndValue2].endpoint = true;
        for (int i = 0; i < mazesize; i++)
        {
            for (int j = 0; j < mazesize; j++)
            {
                int randnum = random.nextInt(10 - lowerBound + 1) + lowerBound; // (lowerBound + (int)(1 * 100) % (10 - lowerBound));
                map[i][j].picetype = maze_pices.values()[randnum];
                if (map[i][j].picetype == maze_pices.noenterable && map[i][j].playerison)
                {
                    map[i][j].picetype = maze_pices.all;
                }
                if (map[i][j].picetype == maze_pices.noenterable && map[i][j].endpoint)
                {
                    map[i][j].picetype = maze_pices.all;
                }
            }
        }
    }
}
