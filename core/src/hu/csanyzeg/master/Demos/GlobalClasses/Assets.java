//https://github.com/tuskeb/mester
package hu.csanyzeg.master.Demos.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import hu.csanyzeg.master.Demos.DemoGame.AssetsGroupDemoGame;


public class Assets {
	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;
	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";


	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "alegreyaregular.otf";
		fontParameter.fontParameters.size = 50;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> ALEGREYAREGULAR_FONT
			= new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);


	public static final AssetDescriptor<Texture> BADLOGIC_TEXTURE
			= new AssetDescriptor<Texture>("badlogic.jpg", Texture.class);
	public static final AssetDescriptor<Texture> CURSOR_TEXTURE
			= new AssetDescriptor<Texture>("cursor.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXTBOX_TEXTURE
			= new AssetDescriptor<Texture>("textbox.png", Texture.class);


	public static final AssetDescriptor<TextureAtlas> EXPLOSION_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("explosion.atlas", TextureAtlas.class);
	public static final AssetDescriptor<TextureAtlas> STAR_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("star.atlas", TextureAtlas.class);

	/*
    public static final AssetDescriptor<Music> MUSIC
            = new AssetDescriptor<Music>("***.mp3", Music.class);
*/
    public static final AssetDescriptor<Sound> STAR_SOUND
            = new AssetDescriptor<Sound>("star.wav", Sound.class);



    public static final AssetDescriptor<Texture> BLUE_TEXTURE
            = new AssetDescriptor<Texture>("blue.png", Texture.class);


    public static final AssetDescriptor<Texture> GREEN_TEXTURE
            = new AssetDescriptor<Texture>("green.png", Texture.class);

    public static final AssetDescriptor<Texture> YELLOW_TEXTURE
            = new AssetDescriptor<Texture>("yellow.png", Texture.class);



	public static final AssetDescriptor<TextureAtlas> LION_TEXTURE = new AssetDescriptor<TextureAtlas>("mountainlion.atlas", TextureAtlas.class);
	public static final AssetDescriptor<TextureAtlas> TREE_TEXTURE = new AssetDescriptor<TextureAtlas>("tree.atlas", TextureAtlas.class);


    public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		manager.load(BADLOGIC_TEXTURE);
		manager.load(TEXTBOX_TEXTURE);
		manager.load(CURSOR_TEXTURE);

		manager.load(EXPLOSION_TEXTUREATLAS);
		manager.load(STAR_TEXTUREATLAS);
		manager.load(LION_TEXTURE);
		manager.load(TREE_TEXTURE);

		manager.load(STAR_SOUND);

		manager.load(AssetsGroupDemoGame.HILLS_TEXTURE);
		manager.load(AssetsGroupDemoGame.HOUSE_TEXTURE);
		manager.load(AssetsGroupDemoGame.UFO2_TEXTURE);
		manager.load(AssetsGroupDemoGame.UFO_TEXTURE);
		manager.load(AssetsGroupDemoGame.WHEEL_TEXTURE);
		/*
        manager.load(MUSIC);
        */
		manager.load(ALEGREYAREGULAR_FONT);


        manager.load(BLUE_TEXTURE);
        manager.load(GREEN_TEXTURE);
        manager.load(YELLOW_TEXTURE);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
