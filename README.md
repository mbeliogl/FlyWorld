#### Fly World

------

A fly's life is not easy. Predators are always on the hunt. Can you help _Mosca_ the fly get home safely?

> In the **worlds** directory, there are two _.txt_ files provided.
> Each file represents a different world. 
> You can create your own world of any size and with any number of predators. 
>
> The world layout is as follows: 
>
> 1. First two numbers represent the size of the grid (rows must equal columns)
> 2. '.' -> empty space 
> 3. 'h' -> home (final destination)
> 4. 's' -> start location 
> 5. 'b' -> bird 
> 6. 'a' -> spider 
> 7. 'f' -> frog 
>
> Once the world is ready, point to the correct world file in _FlyWorldGUI.main()_ and run. 
> Note: you also have to provide an absolute path for the image files in _Fly.java_, _Frog.java_, _Spider.java_, _Bird.java_ 

> How to play: 
>
> 1. Use the arrow keys to move the fly in one of the four directions (UP, DOWN, LEFT, RIGHT)
>
> 2. Frogs move randomly to one of the squares (4 directions) surrounding them
>
>    - They eat Mosca when they are next to him
>
> 3. Birds can move randomly to any square on the map 
>
>    - They eat Mosca when they land on his location
>
> 4. Spiders always move towards Mosca (one square at a time)
>
>    - They eat Mosca when they land on his location 
>
> 5. Every time the Mosca moves, each predator on the map also moves 
>
>    
