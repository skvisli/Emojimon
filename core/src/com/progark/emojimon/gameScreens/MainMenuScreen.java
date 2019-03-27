package com.progark.emojimon.gameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.progark.emojimon.Emojimon;

public class MainMenuScreen implements Screen {

    private Stage stage;
    final Emojimon game;
    private OrthographicCamera camera;

    public MainMenuScreen(final Emojimon game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
    }


    @Override
    public void show() {
        /**/
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final TextButton txtbtn = new TextButton("Create game",mySkin);
        //button2.setSize(col_width*4,row_height);
        txtbtn.setPosition(Gdx.graphics.getWidth()/2 - txtbtn.getWidth()/2,
        Gdx.graphics.getHeight()/2 - txtbtn.getHeight()/2);
        txtbtn.addListener(new InputListener(){
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//                System.out.println("Touch up");
//            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Create game");
                game.setScreen(new CreateRulesetScreen(game));
                return true;
            }
        });
        stage.addActor(txtbtn);
        //stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

   @Override
   public void hide() {
        /**/
   }

    @Override
    public void dispose() {
    }
}
