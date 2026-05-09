public class GameInputProcessor implements InputProcessor {
    private World world;
    private OrthographicCamera camera;
    private Vector3 touchPos = new Vector3();
    // ... 其他成员

    public GameInputProcessor(World world, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX, screenY, 0);
        camera.unproject(touchPos);
        float worldX = touchPos.x;
        float worldY = touchPos.y;
        if (world.tryGrabNPC(worldX, worldY)) {
            // ...
        }
        return true;
    }

    // touchDragged 和 touchUp 同样需要转换坐标
}