package com.javadocmd.interstellar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Art {

	public static Color allyBlue = new Color(51f/255f, 135f/255f, 204f/255f, 1f);
	public static Color enemyRed = new Color(229f/255f, 63f/255f, 57f/255f, 1f);
	public static Color yellow = new Color(199f/255f, 204f/255f, 51f/255f, 1f);
	
	public static Texture pixture;
	public static Texture planetCity;
	public static Texture planetMetal;
	public static Texture planetGas;
	public static Texture path;
	public static Texture aura;
	public static Texture resources;
	public static Texture buttonBuildConn;
	public static Texture buttonHireAdmin;
	public static Texture buttonClaim;
	public static Texture buttonMineCredits;
	public static Texture buttonMineMetals;
	public static Texture buttonMineGas;

	public static BitmapFont font;
	public static LabelStyle labelStyle;

	public static void init() {
		font = new BitmapFont(Gdx.files.internal("slkscr.fnt"));
		labelStyle = new LabelStyle(font, Color.WHITE);

		pixture = new Texture("pixel.png");
		planetCity = new Texture("planet-city.png");
		planetMetal = new Texture("planet-metal.png");
		planetGas = new Texture("planet-gas.png");
		path = new Texture("path.png");
		aura = new Texture("aura.png");
		resources = new Texture("resources.png");

		buttonBuildConn = new Texture("button-buildconn.png");
		buttonHireAdmin = new Texture("button-hireAdmin.png");
		buttonClaim = new Texture("button-claim.png");
		buttonMineCredits = new Texture("button-mineCredits.png");
		buttonMineMetals = new Texture("button-mineMetals.png");
		buttonMineGas = new Texture("button-mineGas.png");
	}

	public static void dispose() {
		font.dispose();
		pixture.dispose();
		planetCity.dispose();
		planetMetal.dispose();
		planetGas.dispose();
		path.dispose();
		aura.dispose();
		resources.dispose();
		buttonBuildConn.dispose();
		buttonHireAdmin.dispose();
		buttonClaim.dispose();
		buttonMineCredits.dispose();
		buttonMineMetals.dispose();
		buttonMineGas.dispose();
	}
}
