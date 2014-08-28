package com.javadocmd.interstellar.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.javadocmd.interstellar.InterstellarGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                GwtApplicationConfiguration conf = new GwtApplicationConfiguration(480, 320);
                conf.width = 1024;
                conf.height = 768;
				return conf;
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new InterstellarGame();
        }
}