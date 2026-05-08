public class GameRenderer {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private World world;
    private Trolley trolley;   // 添加成员变量

    public GameRenderer(World world) {
        this.world = world;
        this.trolley = world.getTrolley();  // 通过 world 获取
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    // 提供 getBatch 方法
    public SpriteBatch getBatch() {
        return batch;
    }

    public void render() {
        // 更新火车位置（每帧重新获取最新位置，因为火车在移动）
        trolley = world.getTrolley();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 绘制轨道
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
        Arena arena = world.getArena();
        shapeRenderer.ellipse(arena.cx - arena.rx, arena.cy - arena.ry,
                              arena.rx*2, arena.ry*2, 50);
        shapeRenderer.end();

        // 绘制 NPC（使用 ShapeRenderer 绘制圆点，避免 Batch 冲突）
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (NPC npc : world.getNpcs()) {
            if (npc.state == NPC.State.TIED)
                shapeRenderer.setColor(Color.RED);
            else if (npc.state == NPC.State.GRABBED)
                shapeRenderer.setColor(Color.BLUE);
            else
                shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(npc.position.x, npc.position.y, 12);
        }
        shapeRenderer.end();

        // 绘制火车（矩形 + 圆形）
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(trolley.position.x - 20, trolley.position.y - 10, 40, 20);
        shapeRenderer.circle(trolley.position.x, trolley.position.y, 15);
        shapeRenderer.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width/2f, height/2f, 0);
        camera.update();
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}