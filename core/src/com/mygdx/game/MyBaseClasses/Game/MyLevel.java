package com.mygdx.game.MyBaseClasses.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyBaseClasses.Scene2D.MyActor;
import com.mygdx.game.MyBaseClasses.Scene2D.MyScreen;
import com.mygdx.game.MyBaseClasses.Scene2D.MyStage;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.sun.istack.internal.*;

import java.util.ArrayList;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

public class MyLevel {

    public final OneSpriteStaticActor backgroundActor;
    private ArrayList<MyActor> actors;
    public static int ALL = -1;
    private MyStage stage;
    private int ID;

    public MyLevel(final Texture background, MyStage stage, MyScreen screen) {
        actors = new ArrayList<MyActor>();
        this.stage = stage;
        backgroundActor = new OneSpriteStaticActor(background) {
            @Override
            public void act(float delta) {
                super.act(delta);
            }

            @Override
            public void init() {
                super.init();
            }
        };
        actors.add(backgroundActor);
        stage.addActor(backgroundActor);
    }

    @Getter
    public int getID() {
        return ID;
    }

    @Setter
    public void setID(int ID) {
        this.ID = ID;
    }

    @Getter
    public ArrayList<MyActor> getActors() {
        return actors;
    }

    public void removeActorFromLevel(MyActor actor, @Nullable boolean all) {
        try {
            if (!all){
                if(!isActorOnLevel(actor)) throw new ActorIsNotOnLevelException("Ilyen actor nincs a pályán!");
                actors.remove(actor);
                actor.remove();
            }
            else for (MyActor a : actors) if(a.getClass() == actor.getClass()) {
                if(!isActorOnLevel(actor)) throw new ActorIsNotOnLevelException("Ilyen actor nincs a pályán!");
                actors.remove(a);
                actor.remove();
            }
        } catch (ActorIsNotOnLevelException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Nincs ilyen actor a pályán! NullPointer" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeActorFromLevel(MyActor actor) {
        try {
            if(!isActorOnLevel(actor)) throw new ActorIsNotOnLevelException("Ilyen actor nincs a pályán!");
            actors.remove(actor);
            actor.remove();
        } catch (ActorIsNotOnLevelException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Nincs ilyen actor a pályán! NullPointer" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeActorFromLevel(MyActor... actorGroup) {
        try{
            for (MyActor actor : actorGroup) {
                if(!isActorOnLevel(actor)) throw new ActorIsNotOnLevelException("Ilyen actor nincs a pályán!");
                actor.remove();
            }
        } catch (ActorIsNotOnLevelException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Nincs ilyen actor a pályán! NullPointer" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeActorFromLevel(int i) {
        try {
            if(i > -1 && i < actors.size()) {
                if(!isActorOnLevel(actors.get(i))) throw new ActorIsNotOnLevelException("Ilyen ID-vel nincs actor a pályán!");
                actors.get(i).remove();
                actors.remove(i);
            }
            else if (i == -1) {
                for (MyActor actor : actors) {
                    actor.remove();
                }
                actors.clear();
            }
        } catch (ActorIsNotOnLevelException ex) {
            ex.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Nincs ilyen ID! NullPointer" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addActorToLevel(MyActor... actorGroup) {
        for (MyActor actor : actorGroup) {
            stage.addActor(actor);
            actors.add(actor);
        }
    }

    public boolean isActorOnLevel(MyActor actor) {
        for (MyActor a : actors) if(a.equals(actor)) return true;
        return false;
    }

    public class ActorIsNotOnLevelException extends Exception {
        public ActorIsNotOnLevelException() {
            super();
        }

        public ActorIsNotOnLevelException(String message) {
            super(message);
        }

        public ActorIsNotOnLevelException(String message, Throwable cause) {
            super(message, cause);
        }

        public ActorIsNotOnLevelException(Throwable cause) {
            super(cause);
        }
    }
}
