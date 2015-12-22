package seed.seyfer.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import seed.seyfer.game.MarioBros;
import seed.seyfer.game.scenes.Hud;

/**
 * Created by seyfer on 12/22/15.
 */
public class PlayScreen implements Screen {

    private final MarioBros          game;
    private       OrthographicCamera gameCamera;
    private       Viewport           gamePort;
    private       Hud                hud;

    private TmxMapLoader               mapLoader;
    private TiledMap                   map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public PlayScreen(MarioBros game) {

        this.game = game;
        gameCamera = new OrthographicCamera();
//        gamePort = new StretchViewport(800, 480, gameCamera);
//        gamePort = new ScreenViewport(gameCamera);
        gamePort = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, gameCamera);

        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }

    private void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            //tmp to check
            gameCamera.position.x += 100 * dt;
        }
    }

    public void update(float dt) {
        handleInput(dt);
        gameCamera.update();

        mapRenderer.setView(gameCamera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        mapRenderer.render();

        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
