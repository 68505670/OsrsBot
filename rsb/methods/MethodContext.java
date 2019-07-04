package net.runelite.client.rsb.methods;

import net.runelite.client.rsb.botLauncher.RuneLite;
import net.runelite.api.Client;
import net.runelite.client.rsb.internal.input.VirtualKeyboard;
import net.runelite.client.rsb.internal.input.VirtualMouse;
import net.runelite.client.rsb.internal.InputManager;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * For internal use to link MethodProviders.
 *
 * @author Jacmob
 */
@Slf4j
public class MethodContext {

	/**
	 * The singleton of GameScreen
	 */
	public final Game game = new Game(this);

	/**
	 * The instance of {@link java.util.Random} for random number generation.
	 */
	public final java.util.Random random = new java.util.Random();

	/**
	 * The singleton of Calculations
	 */
	public final Calculations calc = new Calculations(this);

	/**
	 * The singleton of Interfaces
	 */
	public final Interfaces interfaces = new Interfaces(this);

	/**
	 * The singleton of DynamicConstants
	 */
	public final GameGUI gui = new GameGUI(this);

	/**
	 * The singleton of Mouse
	 */
	public final Mouse mouse = new Mouse(this);

	/**
	 * The singleton of Keyboard
	 */
	public final Keyboard keyboard = new Keyboard(this);

	/**
	 * The singleton of Menu
	 */
	public final Menu menu = new Menu(this);


	/**
	 * The Bot's input manager
	 */
	public final InputManager inputManager;

	/**
	 * The singleton of NPCs
	 */
	public final NPCs npcs = new NPCs(this);

	/**
	 * The singleton of players
	 */
	public final Players players = new Players(this);

	/**
	 * The singleton of Tile
	 */
	public final Tiles tiles = new Tiles(this);

	/**
	 * The singleton of Camera
	 */
	public final Camera camera = new Camera(this);

	/**
	 * The singleton of Objects
	 */
	public final Objects objects = new Objects(this);

	/**
	 * The singleton of store
	 */
	public final Store store = new Store(this);

	/**
	 * The singleton of Inventory
	 */
	public final Inventory inventory = new Inventory(this);

	/**
	 * The singleton of bank
	 */
	public final Bank bank = new Bank(this);

	/**
	 * The singleton of Environment
	 */
	public final Environment env = new Environment(this);

	/**
	 * The singleton of Walking
	 */
	public final Walking walking = new Walking(this);

	/**
	 * The singleton of Settings
	 */
	public final Settings settings = new Settings(this);

	/**
	 * the singleton of Account
	 */
	public final Account account = new Account(this);

	/**
	 * The singleton of Combat
	 */
	public final Combat combat = new Combat(this);

	/**
	 * The singleton of skills
	 */
	public final Skills skills = new Skills(this);

	/**
	 * The singleton of Prayer
	 */
	public final Prayer prayer = new Prayer(this);

	/**
	 * The singleton of Magic
	 */

	public final Magic magic = new Magic(this);

	/**
	 * The singleton of GroundItems
	 */
	public final GroundItems groundItems = new GroundItems(this);

	/**
	 * The singleton of Web
	 */
	public final Web web = new Web(this);

	/**
	 * The singleton of ClanChat
	 */
	public final ClanChat clanChat = new ClanChat(this);

	/**
	 * The singleton of Trade
	 */
	public final Trade trade = new Trade(this);

	/**
	 * The singleton of Equipment
	 */
	public final Equipment equipment = new Equipment(this);

	/**
	 * The singleton of virtual mouse
	 */
	public final VirtualMouse virtualMouse = new VirtualMouse(this);

	/**
	 * The singleton of virtual keyboard
	 */
	public final VirtualKeyboard virtualKeyboard = new VirtualKeyboard(this);

	/**
	 * The client
	 */
	public final Client client;




	public final RuneLite runeLite;

	//public final ClientUI clientUI;

	//public final JPanel clientPanel;

	public MethodContext(RuneLite runeLite) {
		this.runeLite = runeLite;
		this.client = runeLite.getInjector().getInstance(Client.class);
		//this.clientUI = runeLite.getClientUI();
		//this.client = injector.getInstance(Client.class);
		//this.clientUI = injector.getInstance(ClientUI.class);
		//this.clientPanel = injector.getInstance(ClientUI.class).container;
		this.inputManager = runeLite.getInputManager();

	}

}
