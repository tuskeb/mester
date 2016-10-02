package com.mygdx.game;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class ExplosionActor extends OneSpriteAnimatedActor {
    public ExplosionActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.EXPLOSION_TEXTUREATLAS));
        setFps(2);
    }
}
