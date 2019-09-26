package hu.csanyzeg.master.Demos.DemoMenu;

import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class CrossActor extends OneSpriteStaticActor {
    public CrossActor() {
        //super("badlogic.jpg");
        super(Assets.manager.get(Assets.BADLOGIC_TEXTURE));

        //addBaseCollisionRectangleShape();
        //addBaseCollisionCircleShape();
        //System.out.println(getCollisionShape("BaseCircle"));
        //System.out.println(getCollisionShape("BaseRectangle"));
        /*addCollisionShape("LeftBottom1",new MyRectangle(20,20,40,40,getRotation(),40,getOriginX(), getOriginY()));
        addCollisionShape("LeftBottom2",new MyRectangle(40,40,40,40,getRotation(),0,getOriginX(), getOriginY()));
        addCollisionShape("Circle1", new MyCircle(90,180,30,getOriginX(), getOriginY()));
        addCollisionShape("Circle2", new MyCircle(0,0,60,getOriginX(), getOriginY()));*/

        setPosition(300,100);
        //setOrigintoCenter();
        setOrigin(64,64);
        //System.out.println(getCollisionShape("BaseCircle"));
        //System.out.println(getCollisionShape("BaseRectangle"));
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        //setPosition(getX()+1f, getY()+1f);
        //getCollisionShape("LeftBottom1").offsetRotateBy(2);
        //getCollisionShape("LeftBottom2").offsetRotateBy(-1);
        //rotateBy(1);
        //setWidthWhithAspectRatio(getWidth()+0.5f);
    }
}
