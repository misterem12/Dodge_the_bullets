package graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class IRenderableHolder {

	private static final IRenderableHolder instance = new IRenderableHolder();

	public static Image grassField;
	public static Image brickFloor;
	public static Image desert;
	public static Image heart;
	public static Image playerModel;
	public static Image witchModel;
	public static Image trollModel;
	public static Image wispModel;
	public static Image wispModelLeft;
	public static Image banditModel;
	public static Image playerAura;
	public static Image skill1_icon;
	public static Image skill2_icon;
	public static Image skill3_icon;
	public static Image skill4_icon;
	public static Media animeBGM;
	public static Media desertBGM;
	public static Media forestBGM;
	public static Media skill4_sound;
	public static Media skill1_sound;
	public static Media skill2_sound;
	public static Media skill3_sound;
	public static Media ouch;
	public static Image menuBG;

	private List<IRenderableObject> entities;
	private Comparator<IRenderableObject> comparator;

	static {
		loadResource();
	}

	public IRenderableHolder() {
		entities = new ArrayList<IRenderableObject>();
		comparator = (IRenderableObject o1, IRenderableObject o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static void loadResource() {

		ClassLoader loader = ClassLoader.getSystemClassLoader();

		skill4_sound = new Media(loader.getResource("media/skill4.mp3").toString());
		skill3_sound = new Media(loader.getResource("media/skill3.mp3").toString());
		skill2_sound = new Media(loader.getResource("media/skill2.mp3").toString());
		skill1_sound = new Media(loader.getResource("media/skill1.mp3").toString());
		ouch = new Media(loader.getResource("media/ouch1.mp3").toString());

		forestBGM = new Media(loader.getResource("media/magical_theme.mp3").toString());
		desertBGM = new Media(loader.getResource("media/Arabesque.mp3").toString());
		animeBGM = new Media(loader.getResource("media/secretbase.mp3").toString());

		menuBG = new Image(loader.getResourceAsStream("bg/magicka_2.jpg"));
		grassField = new Image(loader.getResourceAsStream("bg/grass1.png"));
		brickFloor = new Image(loader.getResourceAsStream("bg/city.png"));
		desert = new Image(loader.getResourceAsStream("bg/desert1.png"));
		heart = new Image(loader.getResourceAsStream("model/heart.png"));
		playerModel = new Image(loader.getResourceAsStream("model/playerModel.png"));
		witchModel = new Image(loader.getResourceAsStream("model/WitchModel.png"));
		trollModel = new Image(loader.getResourceAsStream("model/trollModel.png"));
		wispModel = new Image(loader.getResourceAsStream("model/wispModel.png"));
		wispModelLeft = new Image(loader.getResourceAsStream("model/wispModelLeft.png"));
		banditModel = new Image(loader.getResourceAsStream("model/BanditModel.png"));
		skill1_icon = new Image(loader.getResourceAsStream("model/skill1_icon.png"));
		skill2_icon = new Image(loader.getResourceAsStream("model/skill2_icon.png"));
		skill3_icon = new Image(loader.getResourceAsStream("model/skill3_icon.png"));
		skill4_icon = new Image(loader.getResourceAsStream("model/skill4_icon.png"));
		playerAura = new Image(loader.getResourceAsStream("model/playerAura.png"));

	}

	public static IRenderableHolder getInstance() {
		return instance;
	}

	public void addAndSort(IRenderableObject entity) {
		add(entity);
		sort();
	}

	public synchronized void add(IRenderableObject entity) {
		entities.add(entity);
		sort();
	}

	public synchronized void sort() {
		Collections.sort(entities, comparator);
	}

	public List<IRenderableObject> getEntities() {
		return entities;
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}
}
