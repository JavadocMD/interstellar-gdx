package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class ToolTip extends Label {

	public ToolTip(float centerX, float y) {
		super("", Art.labelStyle);
		setSize(300, 30);
		setPosition(centerX - getWidth() / 2, y);
		setAlignment(Align.center);
	}
}
