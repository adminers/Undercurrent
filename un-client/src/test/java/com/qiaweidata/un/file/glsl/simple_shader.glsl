#ifdef GL_ES
precision mediump float;
#endif
uniform sampler2D u_texture; // 纹理 uniform
uniform float u_time; // 时间
uniform vec2 u_resolution; // 画布分辨率
varying vec2 v_texCoord; // 从顶点着色器传递的纹理坐标
// 生成噪声的函数
float noise(vec2 p) {
    return fract(sin(dot(p, vec2(12.9898, 78.233))) * 43758.5453);
}
// 生成分形布朗运动的函数
float fbm(vec2 p) {
    float f = 0.0;
    float a = 0.5;
    for (int i = 0; i < 5; i++) {
        f += a * noise(p);
        p *= 2.0; // 放大
        a *= 0.5; // 减小振幅
    }
    return f;
}
void main() {
    vec2 uv = v_texCoord; // 使用传递过来的纹理坐标
    float t = u_time * 0.1; // 时间缩放
    float n = fbm(uv + t); // 生成分形布朗运动
    // 获取纹理颜色
    vec4 textureColor = texture2D(u_texture, uv); // 使用纹理
    vec3 color = vec3(0.0, 0.5 + n * 0.5, 1.0); // 水面颜色
    gl_FragColor = vec4(textureColor.rgb * color, 1.0); // 输出颜色
}