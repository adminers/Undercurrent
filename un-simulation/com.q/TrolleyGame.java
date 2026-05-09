public class TrolleyGame extends ApplicationAdapter {
    private World world;
    private GameRenderer renderer;
    private Hud hud;
    private Skin skin;
    private SpriteBatch uiBatch;  // 专门用于 UI 的 batch

    @Override
    public void create() {
        float cx = Gdx.graphics.getWidth() / 2f;
        float cy = Gdx.graphics.getHeight() / 2f;
        Arena arena = new Arena(cx, cy, 200f, 120f);
        world = new World(arena);
        renderer = new GameRenderer(world);
        uiBatch = new SpriteBatch();
        hud = new Hud(world, getSkin(), uiBatch);   // 传递 uiBatch

        Gdx.input.setInputProcessor(new GameInputProcessor(world));
    }

    private Skin getSkin() {
        if (skin == null) {
            skin = new Skin(Gdx.files.classpath("com/badlogic/gdx/scenes/scene2d/ui/uiskin.json"));
        }
        return skin;
    }

    @Override
    public void render() {
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 0.033f);
        world.update(delta);
        renderer.render();
        hud.render();  // Hud 使用自己的 uiBatch 渲染
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
        hud.resize(width, height);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        hud.dispose();
        uiBatch.dispose();
        if (skin != null) skin.dispose();
    }
}