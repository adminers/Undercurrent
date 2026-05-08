private Skin createDefaultSkin() {
    Skin skin = new Skin();

    // 1. 生成白色1x1纹理，用于所有简单按钮背景
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.WHITE);
    pixmap.fill();
    Texture whiteTex = new Texture(pixmap);
    pixmap.dispose();
    TextureRegionDrawable whiteDrawable = new TextureRegionDrawable(new TextureRegion(whiteTex, 0, 0, 1, 1));

    // 2. 创建字体（如果 default.fnt 存在就加载，否则用 FreeType 生成）
    BitmapFont font = null;
    if (Gdx.files.internal("default.fnt").exists()) {
        font = new BitmapFont(Gdx.files.internal("default.fnt"));
    } else {
        // 如果没有 default.fnt，使用 FreeTypeFontGenerator 从系统字体生成（需要依赖 extension）
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf")); // 确保有该文件
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 16;
        font = generator.generateFont(param);
        generator.dispose();
    }

    // 3. 添加基础资源
    skin.add("default-font", font);
    skin.add("white", Color.WHITE);
    skin.add("gray", Color.GRAY);
    skin.add("black", Color.BLACK);

    // 4. 注册 Drawable（不同状态可以使用相同纹理，但 down 可以换颜色）
    skin.add("default-round", whiteDrawable);
    skin.add("default-round-down", whiteDrawable);
    skin.add("default-rect", whiteDrawable);
    skin.add("default-scroll", whiteDrawable);
    skin.add("default-select", whiteDrawable);
    skin.add("default-select-selection", whiteDrawable);
    skin.add("default-splitpane", whiteDrawable);
    skin.add("default-splitpane-vertical", whiteDrawable);
    skin.add("default-window", whiteDrawable);
    skin.add("default-slider", whiteDrawable);
    skin.add("default-slider-knob", whiteDrawable);
    skin.add("default-pane", whiteDrawable);
    skin.add("textfield", whiteDrawable);
    skin.add("cursor", whiteDrawable);
    skin.add("selection", whiteDrawable);
    skin.add("check-on", whiteDrawable);
    skin.add("check-off", whiteDrawable);
    skin.add("tree-minus", whiteDrawable);
    skin.add("tree-plus", whiteDrawable);

    // 5. 定义 ButtonStyle（普通按钮）
    Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
    buttonStyle.up = skin.getDrawable("default-round");
    buttonStyle.down = skin.getDrawable("default-round-down");
    buttonStyle.disabled = skin.getDrawable("default-round");
    skin.add("default", buttonStyle);

    // 6. 定义 TextButtonStyle（文本按钮）—— 这是您报错的地方，现在被正确添加
    TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    textButtonStyle.up = skin.getDrawable("default-round");
    textButtonStyle.down = skin.getDrawable("default-round-down");
    textButtonStyle.font = font;
    textButtonStyle.fontColor = Color.WHITE;
    textButtonStyle.disabledFontColor = Color.GRAY;
    skin.add("default", textButtonStyle);

    // 7. 定义 LabelStyle（如果需要）
    Label.LabelStyle labelStyle = new Label.LabelStyle();
    labelStyle.font = font;
    labelStyle.fontColor = Color.WHITE;
    skin.add("default", labelStyle);

    // 8. 其他风格按需添加（可选）
    // ScrollPaneStyle, SelectBoxStyle 等，如果不使用可以暂时不添加

    return skin;
}