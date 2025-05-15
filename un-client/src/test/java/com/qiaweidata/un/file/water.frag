#ifdef GL_ES
precision mediump float;
#endif

uniform float u_time;
uniform sampler2D u_texture; // 输入纹理（如果需要）
varying vec2 v_texCoord;

void main() {
    // 动态波浪偏移
    float waveX = sin(v_texCoord.y * 20.0 + u_time * 3.0) * 0.01;
    float waveY = sin(v_texCoord.x * 15.0 + u_time * 2.0) * 0.01;

    vec2 distortedUV = v_texCoord + vec2(waveX, waveY);

    // 基础颜色（可替换为纹理采样）
    vec4 deepColor = vec4(0.0, 0.3, 0.6, 0.8);
    vec4 shallowColor = vec4(0.2, 0.5, 1.0, 0.9);

    // 水位渐变 + 泡沫
    float waterLine = smoothstep(0.3, 0.8, distortedUV.y);
    float foam = sin(distortedUV.x * 50.0 + u_time * 5.0) * 0.5 + 0.5;
    foam *= (1.0 - distortedUV.y) * 0.3;

    gl_FragColor = mix(deepColor, shallowColor, waterLine) + foam;
}