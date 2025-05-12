uniform sampler2D u_texture;  // 添加这行声明
uniform float u_time;

varying vec3 v_normal;
varying vec2 v_texCoord;
varying float v_depth;

void main() {
    // 从纹理采样
    vec4 texColor = texture2D(u_texture, v_texCoord);

    // 基础颜色 - 根据深度变化
    vec3 shallowColor = vec3(0.0, 0.3, 0.5);
    vec3 deepColor = vec3(0.0, 0.1, 0.3);

    // 根据Y坐标(深度)混合颜色
    float depthFactor = smoothstep(-0.5, 0.5, v_depth);
    vec3 waterColor = mix(deepColor, shallowColor, depthFactor);

    // 将纹理颜色与水深颜色混合
    waterColor = mix(waterColor, texColor.rgb, 0.3);

    // 添加一些波浪高光
    vec3 lightDir = normalize(vec3(1.0, 1.0, 1.0));
    float spec = pow(max(dot(v_normal, lightDir), 0.0), 32.0);

    // 最终颜色
    gl_FragColor = vec4(waterColor + spec * 0.5, 0.8);
}