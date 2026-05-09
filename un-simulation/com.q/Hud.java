public class Hud {
    private Stage stage;
    private World world;
    private BitmapFont font;
    private TextButton btnUpgradeRadius, btnUpgradeCapacity;
    private SpriteBatch batch;   // 保存传入的 batch

    public Hud(World world, Skin skin, SpriteBatch batch) {
        this.world = world;
        this.batch = batch;
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        btnUpgradeRadius = new TextButton("+Radius(" + Constants.UPGRADE_RADIUS_COST + ")", skin);
        btnUpgradeCapacity = new TextButton("+Capacity(" + Constants.UPGRADE_CAPACITY_COST + ")", skin);
        btnUpgradeRadius.setPosition(20, 50);
        btnUpgradeCapacity.setPosition(20, 100);
        btnUpgradeRadius.addListener(event -> { world.upgradeRadius(); return true; });
        btnUpgradeCapacity.addListener(event -> { world.upgradeCapacity(); return true; });
        stage.addActor(btnUpgradeRadius);
        stage.addActor(btnUpgradeCapacity);
    }

    public void render() {
        stage.act();
        stage.draw();   // Stage 内部会调用自己的 batch，无需外部 batch
        // 但为了绘制文字（不使用 Stage 的话），我们单独用 batch：
        batch.begin();
        font.draw(batch, "Money: " + world.getMoney(), 20, 150);
        font.draw(batch, "Radius: " + world.getGrabRadius(), 20, 180);
        font.draw(batch, "Capacity: " + world.getGrabCapacity(), 20, 210);
        batch.end();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}