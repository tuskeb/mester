package hu.csanyzeg.master.Demos.DemoOtherScr;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 10. 14..
 */
public class SinActor extends OneSpriteStaticActor {
    public SinActor(float freq, float modulation) {
        super(new Texture(generate(freq, modulation)));
    }

    private static Pixmap generate(float freq, float modulation)
    {
        Pixmap p = new Pixmap(360,200, Pixmap.Format.RGBA8888);
        p.setColor(1,1,1,1);
        for(int i = 1; i<p.getWidth(); i++)
        {
            p.drawLine(i,(int)((Math.sin(((double)i*3.6*freq) * Math.PI/180.0)*
                    (p.getHeight()/3.0)) * (double)((i/100)%2+1)*0.5) + p.getHeight()/2,
                    i-1,(int)((Math.sin(((double)(i-1.0)*3.6*freq) * Math.PI/180.0)*
                            (p.getHeight()/3.0) * (double)(((i-1)/100)%2+1)*0.5) + p.getHeight()/2));

        }
        return p;
    }
}
