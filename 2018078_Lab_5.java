// Lab - 5
// Rajat Prakash
// Roll no. - 2018078 
// 08-09-2019
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.lang.Exception;


//Custom exceptions : - 

	// SnakeBiteException,
	// CricketBiteException
	// VultureBiteException
	// TrampolineBiteException

class SnakeBiteException extends Exception
{
	public SnakeBiteException(String message)
	{
		super(message);
	}
}
class CricketBiteException extends Exception
{
	public CricketBiteException(String message)
	{
		super(message);
	}
}
class VultureBiteException extends Exception
{
	public VultureBiteException(String message)
	{
		super(message);
	}
}
class TrampolineException extends Exception
{
	public TrampolineException(String message)
	{
		super(message);
	}
}
class GameWinnerException extends Exception
{
	public GameWinnerException(String message)
	{
		super(message);
	}
}

//Parent Tiles class 

abstract class Tiles
{
	protected final String tile_type;
	protected final int bite_effect;
	public Tiles(String tile_type, int bite_effect)
	{
		this.tile_type = tile_type;
		this.bite_effect = bite_effect;
	}
	abstract public void bite(Player p) throws Exception;
}

// Snake, Vulture, Cricket, White, Trampoline
//All the Hurdles are taken as subclasses


class Snake extends Tiles
{
	public Snake(int bite_effect) {
		super("Snake", bite_effect );
	}
	@Override
	public void bite(Player p) throws SnakeBiteException
	{
		p.setSnake_bites(p.getSnake_bites() + 1);
		if ( p.getCurrent_tile() - this.bite_effect >= 1 )
		{
			System.out.println("Hiss...! I am a Snake, you go back " + this.bite_effect + " tiles!");
			
			p.setCurrent_tile(p.getCurrent_tile() - this.bite_effect);
			
			throw new  SnakeBiteException("SnakeBiteException");
			
		}else
		{
			System.out.println("Hiss...! I am a Snake, you go back " + this.bite_effect + " tiles!");
			
			p.setCurrent_tile(1);
			System.out.println("Josh moved to Tile 1 as it can't go back further");
			throw new  SnakeBiteException("SnakeBiteException");
			
		}
	}
}
class Cricket extends Tiles
{
	public Cricket(int bite_effect) {
		super("Cricket", bite_effect);
	}
	@Override
	public void bite(Player p) throws CricketBiteException
	{
		p.setCricket_bites(p.getCricket_bites() + 1);
		if ( p.getCurrent_tile() - this.bite_effect >= 1 )
		{
			System.out.println("Chirp...! I am a Cricket, you go back " + this.bite_effect + " tiles!");
			p.setCurrent_tile(p.getCurrent_tile() - this.bite_effect);
			throw new CricketBiteException("CricketBiteException");
		}else
		{
			System.out.println("Chirp...! I am a Cricket, you go back " + this.bite_effect + " tiles!");
			p.setCurrent_tile(1);
			System.out.println("Josh moved to Tile 1 as it can't go back further");
			throw new CricketBiteException("CricketBiteException");
		}
		
	}
}
class Vulture extends Tiles
{
	public Vulture(int bite_effect) {
		super("Vulture", bite_effect);
	}
	@Override
	public void bite(Player p) throws VultureBiteException
	{
		p.setVulture_bites(p.getCricket_bites() + 1);
		if ( p.getCurrent_tile() - this.bite_effect > 1 )
		{
			System.out.println("Yapping...! I am a Vulture, you go back " + this.bite_effect + " tiles!");
			p.setCurrent_tile(p.getCurrent_tile() - this.bite_effect);
			throw new VultureBiteException("VultureBiteException");
		}else
		{
			System.out.println("Yapping...! I am a Vulture, you go back " + this.bite_effect + " tiles!");
			p.setCurrent_tile(1);
			System.out.println("Josh moved to Tile 1 as it can't go back further");
			throw new VultureBiteException("VultureBiteException");
		}
	}
}

class Trampoline extends Tiles
{
	public Trampoline( int bite_effect) {
		super("Trampoline", bite_effect);
	}
	@Override
	public void bite(Player p) throws TrampolineException
	{
		
		if ( p.getCurrent_tile() + this.bite_effect <= p.getDestination() )
		{
			p.setTampoline_hits(p.getTampoline_hits() + 1);
			System.out.println("PingPong! I am a Trampoline, you advance " + this.bite_effect + " tiles.");
			p.setCurrent_tile(p.getCurrent_tile() + this.bite_effect);
			throw new TrampolineException("TrampolineException");
		}
	}
}
class White extends Tiles
{
	public White() {
		super("White", 0);
	}
	@Override
	public void bite(Player p)
	{
		
	}
}

// Player class to store all Player related data
class Player
{
	private final String name;
	private int current_tile;
	private boolean caged = true;
	private int snake_bites = 0;
	private int vulture_bites = 0; 
	private int cricket_bites = 0;
	private int tampoline_hits = 0;
	private int destination;
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public int getSnake_bites() {
		return snake_bites;
	}
	public void setSnake_bites(int snake_bites) {
		this.snake_bites = snake_bites;
	}
	public int getVulture_bites() {
		return vulture_bites;
	}
	public void setVulture_bites(int vulture_bites) {
		this.vulture_bites = vulture_bites;
	}
	public int getCricket_bites() {
		return cricket_bites;
	}
	public void setCricket_bites(int cricket_bites) {
		this.cricket_bites = cricket_bites;
	}
	public int getTampoline_hits() {
		return tampoline_hits;
	}
	public void setTampoline_hits(int tampoline_hits) {
		this.tampoline_hits = tampoline_hits;
	}
	public boolean isCaged() {
		return caged;
	}
	public void setCaged(boolean caged) {
		this.caged = caged;
	}
	public int getCurrent_tile() {
		return current_tile;
	}
	public void setCurrent_tile(int current_tile) {
		this.current_tile = current_tile;
	}
	public Player (String name)
	{
		this.name = name;
		this.current_tile = 1;
	}
	public String getName()
	{
		return name;
	}
}

class Game
{
	ArrayList<Tiles> game_track;
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	int roll_count = 1;
	public void set_the_game()
	{
		
		System.out.println("Enter total number of tiles on the race track(length)");	
		int total_tiles = sc.nextInt();
		System.out.println("Setting up the race track...");
		//////////////////
		int rand_int_snakes = rand.nextInt(total_tiles/5) + 1;
		int rand_int_cricket = rand.nextInt(total_tiles/5) + 1;
		int rand_int_vultures = rand.nextInt(total_tiles/5) + 1;
		System.out.println("Danger: There are " + rand_int_snakes + " , " + rand_int_cricket + " , " + rand_int_vultures + " numbers of Snakes, Cricket, and Vultures respectively on your track!");
		
		//////////////////////////////////
		int rand_effect_snakes = rand.nextInt(10) + 1;
		int rand_effect_cricket = rand.nextInt(10) + 1;
		int rand_effect_vultures = rand.nextInt(10) + 1;
		System.out.println("Danger: Each Snake, Cricket, and Vultures can throw you back by " + rand_effect_snakes + " , " + rand_effect_cricket + " , " + rand_effect_vultures + "  number of Tiles respectively!");
		//////////////////////////////////////////
		
		int rand_int_trampoline = rand.nextInt(total_tiles/5) + 1;
		int rand_effect_trampoline = rand.nextInt(10) + 1;
		System.out.println("Good News: There are " + rand_int_trampoline + " number of Trampolines on your track!");
		System.out.println("Good News: Each Trampoline can help you advance by " + rand_effect_trampoline + " number of Tiles");
		/////////////////////////////////////////////
		
		game_track = new ArrayList<Tiles>();
		int count_snakes = 0;int count_crickets = 0;int count_vultures = 0;int count_trampoline = 0;
		for ( int i = 0 ; i < total_tiles; i++)
		{
			int random_allocation = rand.nextInt(5);
			if (random_allocation == 0 && count_snakes < rand_int_snakes)
			{
				Tiles snake = new Snake(rand_effect_snakes);
				game_track.add(snake);
				count_snakes++;
			}
			else if (random_allocation == 1 && count_crickets < rand_int_cricket)
			{
				Tiles cricket = new Cricket(rand_effect_cricket);
				game_track.add(cricket);
				count_crickets++;
			}
			else if (random_allocation == 2 && count_vultures < rand_int_vultures)
			{
				Tiles vulture = new Vulture(rand_effect_vultures);
				game_track.add(vulture);
				count_vultures++;
			}
			else if (random_allocation == 3 && count_trampoline < rand_int_trampoline)
			{
				 Tiles trampoline = new Trampoline(rand_effect_trampoline);
				game_track.add(trampoline);
				count_trampoline++;
			}
			else if (random_allocation == 4 )
			{
				Tiles white = new White();
				game_track.add(white);
			}else{
				Tiles white = new White();
				game_track.add(white);
			}
			
		}
	}
	public void game_is_on(Player player) throws GameWinnerException
	{
		player.setDestination(game_track.size());
		while ( player.getCurrent_tile() != game_track.size() )
		{
			int random_move = rand.nextInt(6) + 1;
			if ( player.isCaged() == true )
			{
				
				System.out.print("[Roll-" + roll_count++ + "]: " + player.getName() + " rolled " + random_move + " at Tile - " + player.getCurrent_tile());
				if ( random_move != 6  )
				{
					System.out.println(" ,OOPs you need 6 to start");
				}
				if (random_move == 6 ){
					player.setCaged(false);
					System.out.println(" ,You are out of the cage! You get a free roll");
				}
			}
			if (player.isCaged() == false){
				
				System.out.print("[Roll-" + roll_count++ + "]: " + player.getName() + " rolled " + random_move + " at Tile - " + player.getCurrent_tile());
				int temp = player.getCurrent_tile();
				player.setCurrent_tile(player.getCurrent_tile() + random_move);
				if ( player.getCurrent_tile() > game_track.size())
				{
					player.setCurrent_tile(temp);
				}	
				System.out.println(" , landed on Tile " + player.getCurrent_tile());
				if ( player.getCurrent_tile() == game_track.size())
				{
					throw new  GameWinnerException("GameWinnerException");
				}
				System.out.println("Trying to shake the Tile - " + player.getCurrent_tile());
				try {
					game_track.get(player.getCurrent_tile() - 1).bite(player);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
			else if ( player.getCurrent_tile() == 1)
			{
				player.setCaged(true);
			}
		}
	}
}

public class Main
{
	static Player player;
	public static void main(String[] args)
	{
		Scanner scm = new Scanner(System.in);
		Game new_game = new Game();
		new_game.set_the_game();
		//////////////////////////////////////////////
		System.out.println("Enter the Player Name");
		player = new Player(scm.next());
		System.out.println("Control transferred to Computer for rolling the Dice for " + player.getName());
		System.out.println("Hit enter to start the game");
		scm.nextLine();
		scm.nextLine();
		System.out.println();
		System.out.println("Game Started ============================>");
		try {                                    //GameWinnerException
			new_game.game_is_on(player);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Final count for all hurdles
		System.out.println(player.getName() + " wins the race in " + new_game.roll_count + " rolls.");
		System.out.println("Total Snake Bites = " + player.getSnake_bites());
		System.out.println("Total Vulture Bites = " + player.getVulture_bites());
		System.out.println("Total Cricket Bites = " + player.getCricket_bites());
		System.out.println("Total Trampolines = " + player.getTampoline_hits());
		
	}
}


