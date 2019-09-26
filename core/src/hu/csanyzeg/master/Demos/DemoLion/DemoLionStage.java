package hu.csanyzeg.master.Demos.DemoLion;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;

import java.util.Random;

/**
 * Created by tanulo on 2017. 10. 09..
 */

public class DemoLionStage extends MyStage
{
    static Random random = new Random();
    DemoLionActor demoLionActor;
    private float prevElapsedTime = 0;


    public DemoLionStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        addActor( demoLionActor = new DemoLionActor());
        getLastAdded().setY(512);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if ((int)getElapsedTime() != (int)prevElapsedTime){
            addActor(new DemoTree());
            getLastAdded().setPosition(demoLionActor.getX()+1024 + random.nextInt(1024), demoLionActor.getY());
            setCameraMoveToXY(getLastAdded().getX(), getLastAdded().getY(), 4, 2, 1500);
        }


        prevElapsedTime = getElapsedTime();


        for (Actor a: getActors()) {
            if (a instanceof DemoTree){
                DemoTree demoTree = ((DemoTree)a);
                if (demoTree.overlaps(ShapeType.Rectangle, demoLionActor)){
                    demoTree.explode();
                }
            }
        }
    }

    @Override
    public void init() {

    }
}
