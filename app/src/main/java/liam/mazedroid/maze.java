package liam.mazedroid;

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
    public void pint(int x, int y,int size, int linethicness)
    {
        if (playerison)
        {
            SDL_Rect p = {x + 0, y + 0, size, size};
            SDL_FillRect(screen, &p,SDL_MapRGB( screen->format, 0x00, 0xFF, 0x00 ));
            hasbeing = true;
        }
        else if (endpoint)
        {
            SDL_Rect p = {x + 0, y + 0, size, size};
            SDL_FillRect(screen, &p,SDL_MapRGB( screen->format, 0x00, 0x00, 0xFF ));
        }
        else if (bestpath)
        {
            SDL_Rect p = {x + 0, y + 0, size, size};
            SDL_FillRect(screen, &p,SDL_MapRGB( screen->format, 0xFF, 0xA5, 0x00 ));
        }
        else if (hasbeing)
        {
            SDL_Rect p = {x + 0, y + 0, size, size};
            SDL_FillRect(screen, &p,SDL_MapRGB( screen->format, 0xFF, 0x00, 0x00 ));
        }
        else if (nonreatchable)
        {
            SDL_Rect p = {x + 0, y + 0, size, size};
            SDL_FillRect(screen, &p,SDL_MapRGB( screen->format, 0x69, 0x69, 0x69 ));
        }
        else
        {
            SDL_Rect p = {x + 0, y + 0, size, size};
            SDL_FillRect(screen, &p,SDL_MapRGB( screen->format, 0xF2, 0xF2, 0xF0 ));
        }
        switch (picetype)
        {
            case east:{
                SDL_Rect q = {x + 0, y + 0, size, linethicness};
                SDL_Rect b = {x + 0, y + size - linethicness, size, linethicness};
                SDL_FillRect(screen, &q,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));
                SDL_FillRect(screen, &b,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case eastwastnorth:{
                SDL_Rect f = {x + 0, y + size - linethicness, size, linethicness};
                SDL_FillRect(screen, &f,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case eastwestsouth:{
                SDL_Rect g = {x + 0, y + 0, size, linethicness};
                SDL_FillRect(screen, &g,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case north:{
                SDL_Rect c = {x + 0, y + 0, linethicness, size};
                SDL_Rect d = {x + size - linethicness, y + 0, linethicness, size};
                SDL_FillRect(screen, &c,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));
                SDL_FillRect(screen, &d,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case northeast:{
                SDL_Rect h = {x + 0, y + 0, linethicness, size};
                SDL_Rect i = {x + 0, y + 0, size, linethicness};
                SDL_FillRect(screen, &h,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));
                SDL_FillRect(screen, &i,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case northwest:{
                SDL_Rect j = {x + size - linethicness, y + 0, linethicness, size};
                SDL_Rect k = {x + 0, y + 0, size, linethicness};
                SDL_FillRect(screen, &j,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));
                SDL_FillRect(screen, &k,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case southeast:{
                SDL_Rect l = {x + 0, y + size - linethicness, size, linethicness};
                SDL_Rect m = {x + 0, y + 0, linethicness, size};
                SDL_FillRect(screen, &l,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));
                SDL_FillRect(screen, &m,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case southwest:{
                SDL_Rect n = {x + 0, y + size - linethicness, size, linethicness};
                SDL_Rect o = {x + size - linethicness, y + 0, linethicness, size};
                SDL_FillRect(screen, &n,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));
                SDL_FillRect(screen, &o,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
            case noenterable:{
                SDL_Rect p = {x + 0, y + 0, size, size};
                SDL_FillRect(screen, &p,SDL_MapRGB( screen->format, 0x00, 0x00, 0x00 ));}
            break;
        }
    }
    }
}
