#ifdef GL_ES
precision mediump float;
#endif

uniform float u_time;
uniform vec2 u_resolution; // 屏幕分辨率（用于适配不同设备）
varying vec2 v_texCoord;

void main() {
    // 计算归一化坐标（基于屏幕下方区域）
    vec2 uv = vec2(v_texCoord.x, v_texCoord.y * 0.5); // 仅使用下半部分

    // 波浪效果（Y轴偏移）
    float wave = sin(uv.x * 15.0 + u_time * 2.0) * 0.01;
    uv.y += wave;

    // 颜色渐变（深蓝到浅蓝）
    vec3 deepBlue = vec3(0.0, 0.2, 0.4);
    vec3 shallowBlue = vec3(0.2, 0.5, 0.8);
    float gradient = smoothstep(0.1, 0.6, uv.y); // 控制渐变范围
    vec3 color = mix(deepBlue, shallowBlue, gradient);

    // 泡沫效果（波浪顶端）
    float foam = sin(uv.x * 30.0 + u_time * 4.0) * 0.5 + 0.5;
    foam *= smoothstep(0.4, 0.5, uv.y); // 限制泡沫在波浪顶部
    color += foam * 0.3;

    gl_FragColor = vec4(color, 1.0);
}