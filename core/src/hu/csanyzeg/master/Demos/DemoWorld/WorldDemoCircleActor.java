package hu.csanyzeg.master.Demos.DemoWorld;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoCircleActor extends WorldActorGroup {
    public WorldDemoCircleActor(World world) {
        super(world, ShapeType.Circle, BodyDef.BodyType.DynamicBody, 0.2f, 0.2f, 0.2f, false);
    }

    @Override
    public void init() {
        super.init();
        int x;
        setSize(x = MathUtils.random(0,5)+ 5, x);
        addToWorld();
        setPosition(MathUtils.random(0,64),MathUtils.random(0,48));
        //setPosition(0,0);
        //setPositionByLeftBottomCorner(0,0);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getStage().addActor(new WorldDemoCircleActor(world));
            }
        });

    }


}
