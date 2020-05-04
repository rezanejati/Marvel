package nejati.me.sample.utility;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 * Copyright © 2017
 */
public class MyLifecycleObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void ON_START(){
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void ON_CREATE(){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void ON_PAUSE(){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void ON_RESUME(){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ON_DESTROY(){

    }
}