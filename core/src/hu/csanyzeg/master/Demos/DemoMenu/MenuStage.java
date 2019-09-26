package hu.csanyzeg.master.Demos.DemoMenu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.csanyzeg.master.Demos.DemoBluetooth.BluetoothScreen;
import hu.csanyzeg.master.Demos.DemoGame.GameScreen;
import hu.csanyzeg.master.Demos.DemoInput.InputScreen;
import hu.csanyzeg.master.Demos.DemoLion.DemoLionScreen;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.Demos.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyCircle;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OffsetSprite;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.Demos.DemoOtherScr.OtherScreen;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;
import hu.csanyzeg.master.Demos.DemoWorld.WorldDemoScreen;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {
    private BadlActor badlActor;
    private CrossActor crossActor;
    private TextButton textButton, textButton2, textButton3, textButton4, textButton5, textButton6, textButton7;
    private ExplosionActor explosionActor;
    private Label utkozesMyLabel;
    private OneSpriteStaticActor circle;
    private OneSpriteStaticActor rectangle;
    private MultiSpriteActor asd;
    private OneSpriteStaticActor asd0;

    private boolean clck = false;

    public MenuStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {
        addBackEventStackListener();
        badlActor = new BadlActor();
        crossActor = new CrossActor();

        badlActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //badlActor.setZIndex(Integer.MAX_VALUE);

            }
        });
        textButton = new MyButton("Előre", Styles.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new OtherScreen(game));
            }
        });

        textButton2 = new MyButton("Űrlap", Styles.getTextButtonStyle());
        textButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new InputScreen(game));
            }
        });

        textButton3 = new MyButton("Box2D teszt", Styles.getTextButtonStyle());
        textButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new WorldDemoScreen(game));
            }
        });

        textButton4 = new MyButton("Box2d Demo", Styles.getTextButtonStyle());
        textButton4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        });


        textButton5 = new MyButton("Bluetooth demo", Styles.getTextButtonStyle());
        textButton5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new BluetoothScreen(game));
            }
        });
/*
        textButton6 = new MyButton("Http demo", game.getTextButtonStyle());
        textButton6.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HttpDemoScreen(game));
            }
        });
*/
        textButton7 = new MyButton("Mountain Lion", Styles.getTextButtonStyle());
        textButton7.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new DemoLionScreen(game));
            }
        });


        addActor(badlActor, Integer.MAX_VALUE);
        addActor(crossActor);
        textButton.setPosition(200,100);
        textButton.debug();
        addActor(textButton , 1);
        textButton2.setPosition(200,200);
        textButton2.debug();
        addActor(textButton2 , 1);
        textButton3.setPosition(200,300);
        textButton3.debug();
        addActor(textButton3 , 1);
        textButton4.setPosition(200,400);
        textButton4.debug();
        addActor(textButton4 , 1);
        textButton5.setPosition(200,500);
        textButton5.debug();
/*        textButton6.setPosition(200,600);
        textButton6.debug();
        addActor(textButton6);*/
        addActor(textButton5);
        textButton7.setPosition(200,700);
        textButton7.debug();
        addActor(textButton7);

        addActor(new StarActor());
        explosionActor = new ExplosionActor();
        explosionActor.setPosition(0, getHeight() - explosionActor.getHeight());
        addActor(explosionActor);
        utkozesMyLabel = new Label("", Styles.getLabelStyle());
        utkozesMyLabel.setPosition(getViewport().getWorldWidth()/2, getViewport().getWorldHeight()-25);
        addActor(utkozesMyLabel);


        addActor(asd = new MultiSpriteActor(50,100, ShapeType.Rectangle, new OffsetSprite(Assets.manager.get(Assets.BLUE_TEXTURE),0,0,50,50), new OffsetSprite(Assets.manager.get(Assets.GREEN_TEXTURE),0,50,50,50), new OffsetSprite(Assets.manager.get(Assets.BADLOGIC_TEXTURE),30,20,30,40),new AnimatedOffsetSprite(Assets.manager.get(Assets.EXPLOSION_TEXTUREATLAS),60,60,30,40)) {
            @Override
            public void init() {
                this.setPosition(700,300);
                addCollisionShape("rrr", new MyRectangle(30,70,40,20,getOriginX(), getOriginY(),0,33));
                addCollisionShape("ccc", new MyCircle(10,40,20,getOriginX(), getOriginY(),0,33));
                //this.setSize(50,100);
                //this.setSize(getWidth(), getHeight() + 50);
                //OffsetSprite sprite = getSprite("Sprite1");
                //sprite.setSize(50,sprite.getHeight() * 2);
                setOrigin(20,20);

                setWidthWhithAspectRatio(100);
                //setOrigin((float)Math.random()*getWidth(),(float)Math.random()*getHeight());
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        if(clck){
                            OffsetSprite sprite = new OffsetSprite(Assets.manager.get(Assets.BADLOGIC_TEXTURE),0,50);
                            sprite.setSize(50, 100);
                            addSprite(sprite);
                            clck = false;
                        }else{
                            clck = true;
                            removeSprite("Sprite1");
                        }
                    }
                });
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                rotateBy(0.5f);
                magnifyByOrigin((float)(Math.sin(elapsedTime)/100)+1);
            }
        });

        //getActors().get(getActors().size-1).setPosition(-300, 100);

        OneSpriteAnimatedActor a = new OneSpriteAnimatedActor("walk.atlas"){
            @Override
            public void act(float delta) {
                super.act(delta);
                setX(getX()+delta*70);
            }
        };
        //a.addCollisionShape("Fej",new MyCircle(20,80,60));

        //crossActor.seHeightWhithAspectRatio(200);
        a.setFps(5);
       // addActor(a);
       // addActor(new OneSpriteAnimatedActor("walk.atlas"));
/*
        addActor(rectangle = new OneSpriteStaticActor(Assets.manager.get(Assets.YELLOW_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(100,50);
                addBaseCollisionRectangleShape();
                addCollisionShape("rect", new MyRectangle(150,25,0,0,getOriginX(),getOriginY(),0,20));
                addCollisionShape("circle1", new MyCircle(10,-30,-30,getOriginX(), getOriginY()));
                addCollisionShape("rect2", new MyRectangle(10, 10,-30,-30,getOriginX(), getOriginY()));
                setPosition(600,400);
                setRotation(0);
                setOrigin(50,50);
                //setSize(200,100);

                //setWidthWhithAspectRatio(300);
                //setWidthWhithAspectRatio(500);
                setWidthWhithAspectRatio(150);

            }

            @Override
            public void act(float delta) {
                super.act(delta);
                //setOrigin(100,100);

                //getCollisionShape("rect").offsetRotateBy(delta*10);
                rotateBy(delta*10);
                //setWidthWhithAspectRatio(256 + (float)Math.sin(elapsedTime)*128);
            }
        });

*/

        addActor(circle = new OneSpriteStaticActor(Assets.manager.get(Assets.BADLOGIC_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(50,50);
                addBaseCollisionRectangleShape();
                addCollisionShape("circle1", new MyCircle(10,-30,-30,getOriginX(), getOriginY()));
                addCollisionShape("rect1", new MyRectangle(10, 20,80,80,getOriginX(), getOriginY()));
                setPosition(800,400);
                setWidthWhithAspectRatio(200);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                setOrigin(getOriginX(),getOriginY());
                getCollisionShape("rect1").offsetRotateBy(delta*10);
                rotateBy(delta*2);
            }
        }, 0);



        asd0 = new OneSpriteStaticActor(Assets.manager.get(Assets.BADLOGIC_TEXTURE)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setRotation(getRotation() + delta * 5);
            }
        };
        asd0.addCollisionShape("balalul", new MyRectangle(30,30, 0,0,asd0.getOriginX(),asd0.getOriginY(), 0, 45));
        asd0.addCollisionShape("feljebb", new MyRectangle(30,30, 0,30,asd0.getOriginX(),asd0.getOriginY(), 0, 10));
        asd0.setPosition(300,300);

        addActor(asd0);

        addListener(new ClickListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                circle.setPosition(x,y);
                return super.mouseMoved(event, x, y);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                circle.setPosition(x,y);
            }
        });


        //badlActor.addBaseCollisionRectangleShape();
        //crossActor.addBaseCollisionCircleShape();

        //addActor(car = new CarActor());
    }


    private MultiSpriteActor car;

    @Override
    public void act(float delta) {
        super.act(delta);
       // System.out.println();
        //if (badlActor.overlaps(ShapeType.Rectangle, crossActor))
        if (circle.overlaps(asd))
        {
            utkozesMyLabel.setText("Ütközés!");
            System.out.println(asd.getMyOverlappedShapeKeys(circle));
        }
        else
        {
            utkozesMyLabel.setText("Nincs ütközés.");
        }
        if (asd0.overlaps(circle)){
            utkozesMyLabel.setText(asd0.getMyOverlappedShapeKeys(circle).toString());
        }

    }

    @Override
    protected void resized() {

    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
